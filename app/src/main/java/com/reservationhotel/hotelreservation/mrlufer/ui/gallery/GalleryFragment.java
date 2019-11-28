package com.reservationhotel.hotelreservation.mrlufer.ui.gallery;

import android.database.Observable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.github.davidmoten.rtree2.Entry;
import com.github.davidmoten.rtree2.RTree;
import com.github.davidmoten.rtree2.geometry.Geometries;
import com.github.davidmoten.rtree2.geometry.Geometry;
import com.github.davidmoten.rtree2.geometry.Point;

import com.github.davidmoten.rtree2.geometry.Rectangle;
import com.reservationhotel.hotelreservation.mrlufer.AdapterHotel;
import com.reservationhotel.hotelreservation.mrlufer.Hotel;
import com.reservationhotel.hotelreservation.mrlufer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static com.github.davidmoten.rtree2.geometry.Geometries.point;

public class GalleryFragment extends Fragment {
    ListView listView;
    AdapterHotel adapter;
    ArrayList<Hotel> listdata = new ArrayList<>();
    RTree<Hotel, Rectangle> tree = RTree.maxChildren(5).create();
    EditText et_x, et_y, et_radius;
    String[] elementos = {"Belmond Miraflores Park", "JW Marriot Hotel Lima", "Courtyard Miraflores", "The Westin Lima hotel", "Hotel Estelar", "Swissôtel Lima", "Hilton Lima Miraflores", "Country Club Lima", "Casa Andina Private"};


    private GalleryViewModel galleryViewModel;

    @RequiresApi(api = Build.VERSION_CODES.N)
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        et_x = root.findViewById(R.id.et_x);
        et_y = root.findViewById(R.id.et_y);
        et_radius = root.findViewById(R.id.et_radius);
        final Button btn_search = root.findViewById(R.id.btn_search);
        btn_search.setOnClickListener(v -> filterHotels());
        return root;
    }
    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);

        getRequest("https://hotel-reservation-lufer.herokuapp.com/api/hotel",  "");

    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    private void filterHotels() {
        listView = getView().findViewById(R.id.listView);
        listdata.clear();
        Iterable<Entry<Hotel, Rectangle>> it = tree.search(Geometries.circle(Float.parseFloat(et_x.getText().toString()),
                Float.parseFloat(et_y.getText().toString()), Float.parseFloat(et_radius.getText().toString())));
        it.forEach(entry -> listdata.add(entry.value()));
        adapter = new AdapterHotel(getActivity(), listdata);
        listView.setAdapter(adapter);
    }

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String result;
    int code;

    private void getRequest(String postUrl,  String postBody) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        final RequestBody body = RequestBody.create(JSON, postBody);

        Request request = new Request.Builder()
                .url(postUrl)
                .get()
                .build();

        Log.e("Http Request", "Url: " + postUrl + "/Data: " + postBody);
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                code = response.code();
                result = response.body().string();

                Log.e("Server Response", "Code: " + code + "/Data: " + result);
                //JSONArray hoteles = new JSONArray();


                if (code == 200) {

                    try {
                        final JSONArray hoteles = new JSONArray(result);

                        for (int i=0;i<hoteles.length();i++){
                            String title = hoteles.getJSONObject(i).getString("title");
                            String category = hoteles.getJSONObject(i).getString("category");
                            String description = hoteles.getJSONObject(i).getString("description");
                            String imagen = hoteles.getJSONObject(i).getString("imageUrl");
                            String id = hoteles.getJSONObject(i).getString("_id");
                            float lat = Float.parseFloat(hoteles.getJSONObject(i).getString("lat"));
                            float lng = Float.parseFloat(hoteles.getJSONObject(i).getString("lng"));

                            Hotel hotel = new Hotel(id,imagen,category,title,description ,lat,lng);

                            tree = tree.add(hotel, point(lat,lng));
                        }
                        //Iterable<Entry<Hotel, Rectangle>> it = tree.search(Geometries.point(0,500));

                        if (getActivity() != null) {
                            getActivity().runOnUiThread(() -> {
                                filterHotels();
                            });
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
}
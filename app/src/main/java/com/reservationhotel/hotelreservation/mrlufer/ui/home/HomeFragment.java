package com.reservationhotel.hotelreservation.mrlufer.ui.home;

import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import com.github.davidmoten.rtree2.RTree;
import com.github.davidmoten.rtree2.geometry.Geometries;
import com.github.davidmoten.rtree2.geometry.Rectangle;
import com.reservationhotel.hotelreservation.mrlufer.Hotel;
import com.reservationhotel.hotelreservation.mrlufer.LoginActivity;
import com.reservationhotel.hotelreservation.mrlufer.R;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeFragment extends Fragment {




    ListView listView;
    String[] elementos = {"Belmond Miraflores Park", "JW Marriot Hotel Lima", "Courtyard Miraflores", "The Westin Lima hotel", "Hotel Estelar", "Swissôtel Lima", "Hilton Lima Miraflores", "Country Club Lima", "Casa Andina Private"};

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);



        return root;
    }


    @Override
    public void onActivityCreated(Bundle state) {
        super.onActivityCreated(state);
        getRequest("https://hotel-reservation-lufer.herokuapp.com/api/hotel",  "");
        RTree<Hotel, Rectangle> tree = RTree.maxChildren(4).create();

        //tree = tree.add(item, Geometries.point(10,20));
         listView = (ListView) getView().findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_expandable_list_item_1,elementos);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity().getApplicationContext(),String.valueOf(position),Toast.LENGTH_SHORT).show();

            }
        });
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

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                code = response.code();
                result = response.body().string();

                Log.e("Server Response", "Code: " + code + "/Data: " + result);
                JSONArray hoteles = new JSONArray();


                    if (code == 200) {

                        try {
                            final JSONObject jsonObject = new JSONObject(result);
                             hoteles.put(jsonObject);



                          //  if(jsonObject.has("hoteles")) hoteles  = jsonObject.getJSONArray("hoteles");

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }



                    }else if ( code == 400 ){



                    }else if ( code == 500 ){



                }
            }
        });
    }
}
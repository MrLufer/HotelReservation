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
import com.reservationhotel.hotelreservation.mrlufer.AdapterReservation;
import com.reservationhotel.hotelreservation.mrlufer.Hotel;
import com.reservationhotel.hotelreservation.mrlufer.LoginActivity;
import com.reservationhotel.hotelreservation.mrlufer.R;
import com.reservationhotel.hotelreservation.mrlufer.Reservation;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeFragment extends Fragment {

    AdapterReservation adapter;
    ListView listView;
    ArrayList<Reservation> listdata = new ArrayList<>();

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
        getRequest("https://hotel-reservation-lufer.herokuapp.com/api/reservation",  "");
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

                if (code == 200) {

                    try {
                        final JSONArray hoteles = new JSONArray(result);
                        listdata.clear();

                        for (int i=0;i<hoteles.length();i++){
                            String hotelId = hoteles.getJSONObject(i).getJSONObject("hotel").getString("_id");
                            String hotelTitle = hoteles.getJSONObject(i).getJSONObject("hotel").getString("title");
                            String date = hoteles.getJSONObject(i).getString("date");
                            String price = hoteles.getJSONObject(i).getString("price");
                            String id = hoteles.getJSONObject(i).getString("_id");

                            Reservation reservation = new Reservation(id, hotelTitle, date, hotelId, price);
                            listdata.add(reservation);
                        }
                        if (getActivity() != null) {
                            getActivity().runOnUiThread(() -> {
                                listView = getView().findViewById(R.id.listView);
                                adapter = new AdapterReservation(getActivity(), listdata);
                                listView.setAdapter(adapter);
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
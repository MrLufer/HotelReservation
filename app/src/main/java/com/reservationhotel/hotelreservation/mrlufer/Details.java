package com.reservationhotel.hotelreservation.mrlufer;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class Details extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        final Button btn_reserva = findViewById(R.id.btn_reserva);
        final TextView text_title = findViewById(R.id.text_hotel_details_title);
        final TextView text_description = findViewById(R.id.text_hotel_details_description);
        final EditText et_date = findViewById(R.id.text_date);
        text_title.setText(getIntent().getStringExtra("title"));
        text_description.setText(getIntent().getStringExtra("description"));

        et_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(
                        Details.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                                month = month + 1;
                                String day,mon ;
                                if(dayOfMonth==1) day = "01";
                                else if(dayOfMonth==2) day = "02";
                                else if(dayOfMonth==3) day = "03";
                                else if(dayOfMonth==4) day = "04";
                                else if(dayOfMonth==5) day = "05";
                                else if(dayOfMonth==6) day = "06";
                                else if(dayOfMonth==7) day = "07";
                                else if(dayOfMonth==8) day = "08";
                                else if(dayOfMonth==9) day = "09";
                                else day = String.valueOf(dayOfMonth);
                                if(month==1) mon = "01";
                                else if(month==2) mon = "02";
                                else if(month==3) mon = "03";
                                else if(month==4) mon = "04";
                                else if(month==5) mon = "05";
                                else if(month==6) mon = "06";
                                else if(month==7) mon = "07";
                                else if(month==8) mon = "08";
                                else if(month==9) mon = "09";
                                else mon = String.valueOf(month);
                                String date = day + "/" + mon + "/" + year;
                                et_date.setText(date);
                            }
                        },
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        btn_reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitReservation(et_date.getText().toString());
            }
        });
    }

    private void submitReservation(String bir) {

        String[] date = bir.split("/");
        String newBir = date[0]+"-"+date[1]+"-"+date[2];


        String postUrl = "https://hotel-reservation-lufer.herokuapp.com/api/reservation";
        String postBody = "{\n" +
                "    \"hotel\": \""+ getIntent().getStringExtra("id")+"\",\n" +
                "    \"date\": \""+ newBir+"\",\n" +
                "    \"price\": \"150\"\n" +
                "}";

        postRequest(postUrl, postBody);
    }


    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    String result;
    int code;

    private void postRequest(String postUrl,  String postBody) {

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(15, TimeUnit.SECONDS)
                .writeTimeout(15, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        final RequestBody body = RequestBody.create(JSON, postBody);

        Request request = new Request.Builder()
                .url(postUrl)
                .post(body)
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
                //JSONArray hoteles = new JSONArray();


                if (code == 200) {



                }else if ( code == 400 ){



                }else if ( code == 500 ){



                }
            }
        });
    }
}

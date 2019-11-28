package com.reservationhotel.hotelreservation.mrlufer;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

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
        ImageView imagen = findViewById(R.id.imageView2);
        text_title.setText(getIntent().getStringExtra("title"));
        text_description.setText(getIntent().getStringExtra("description"));
        Glide
                .with(imagen.getContext())
                .load(getIntent().getStringExtra("image"))
                .centerCrop()
                .into(imagen);

        et_date.setOnClickListener(v -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(
                    Details.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    (view, year1, month1, dayOfMonth) -> {

                        month1 = month1 + 1;
                        String day1,mon ;
                        if(dayOfMonth==1) day1 = "01";
                        else if(dayOfMonth==2) day1 = "02";
                        else if(dayOfMonth==3) day1 = "03";
                        else if(dayOfMonth==4) day1 = "04";
                        else if(dayOfMonth==5) day1 = "05";
                        else if(dayOfMonth==6) day1 = "06";
                        else if(dayOfMonth==7) day1 = "07";
                        else if(dayOfMonth==8) day1 = "08";
                        else if(dayOfMonth==9) day1 = "09";
                        else day1 = String.valueOf(dayOfMonth);
                        if(month1 ==1) mon = "01";
                        else if(month1 ==2) mon = "02";
                        else if(month1 ==3) mon = "03";
                        else if(month1 ==4) mon = "04";
                        else if(month1 ==5) mon = "05";
                        else if(month1 ==6) mon = "06";
                        else if(month1 ==7) mon = "07";
                        else if(month1 ==8) mon = "08";
                        else if(month1 ==9) mon = "09";
                        else mon = String.valueOf(month1);
                        String date = day1 + "/" + mon + "/" + year1;
                        et_date.setText(date);
                    },
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        btn_reserva.setOnClickListener(v -> submitReservation(et_date.getText().toString()));
    }

    private void submitReservation(String date) {

        if(date.equals("") || date.length()<10) {
            Toast.makeText(Details.this, "Ingrese la fecha de reserva", Toast.LENGTH_LONG).show();
            return;
        }
        String postUrl = "https://hotel-reservation-lufer.herokuapp.com/api/reservation";
        String postBody = "{\n" +
                "    \"hotel\": \""+ getIntent().getStringExtra("id")+"\",\n" +
                "    \"date\": \""+ date+"\"\n" +
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
        final Button btn_reserva = findViewById(R.id.btn_reserva);
        btn_reserva.setEnabled(false);
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
                    Details.this.runOnUiThread(() -> {
                        Toast.makeText(Details.this, "Reserva creada", Toast.LENGTH_LONG).show();
                        finish();
                    });
                } else {
                    Details.this.runOnUiThread(() -> {
                        Toast.makeText(Details.this, "Ocurrió un error", Toast.LENGTH_LONG).show();
                        btn_reserva.setEnabled(true);
                    });
                }
            }
        });
    }
}

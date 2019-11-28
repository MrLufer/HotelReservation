package com.reservationhotel.hotelreservation.mrlufer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final Button btn_login = findViewById(R.id.btn_login);
        btn_login.setOnClickListener(v -> startActivity(new Intent(LoginActivity.this, MainActivity.class)));
    }


}

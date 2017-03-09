package com.jake.chyna.icare;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by chyna on 3/9/17.
 */

public class PatientApplicationsRespond extends AppCompatActivity {
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_application_respond);
        Intent intent = getIntent();

        ((ImageView)findViewById(R.id.r_typeIcon)).setImageResource(Integer.parseInt(intent.getStringExtra("icon")));
        ((TextView)findViewById(R.id.r_title)).setText(
                intent.getStringExtra("title"));
        ((TextView)findViewById(R.id.r_area)).setText(
                intent.getStringExtra("area"));
        ((TextView)findViewById(R.id.r_price)).setText(
                intent.getStringExtra("price"));
        ((TextView)findViewById(R.id.r_response)).setText(
                intent.getStringExtra("response"));
        ((TextView)findViewById(R.id.r_app_id)).setText(
                intent.getStringExtra("id"));
        ((TextView)findViewById(R.id.r_date)).setText(
                intent.getStringExtra("date"));
        ((TextView)findViewById(R.id.r_type)).setText(
                intent.getStringExtra("type"));



    }
}

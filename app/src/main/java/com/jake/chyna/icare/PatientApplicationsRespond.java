package com.jake.chyna.icare;

import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chyna on 3/9/17.
 */

public class PatientApplicationsRespond extends AppCompatActivity {

    TextView btn_all;
    TextView btn_resp;

    FragmentDoctors frag1;
    FragmentRespondDoctors frag2;
    FragmentTransaction fTrans;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_application_respond);

        frag1 = new FragmentDoctors();
        frag2 = new FragmentRespondDoctors();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(R.id.frgmContainer, frag2);
        fTrans.commit();

        btn_all = (TextView) findViewById(R.id.all);
        btn_resp = (TextView) findViewById(R.id.responded);
        btn_all.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.frgmContainer, frag1);
                btn_all.setTextColor(Color.parseColor("#4E1668")); // #868686
                btn_resp.setTextColor(Color.parseColor("#868686")); // #4E1668
                fTrans.commit();
            }
        });
        btn_resp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fTrans = getFragmentManager().beginTransaction();
                fTrans.replace(R.id.frgmContainer, frag2);
                btn_all.setTextColor(Color.parseColor("#868686")); // #868686
                btn_resp.setTextColor(Color.parseColor("#4E1668")); // #4E1668
                fTrans.commit();
            }
        });


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

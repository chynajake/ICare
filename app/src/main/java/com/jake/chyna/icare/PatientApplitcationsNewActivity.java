package com.jake.chyna.icare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ExpandableListView;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by chyna on 3/8/17.
 */

public class PatientApplitcationsNewActivity extends AppCompatActivity {
    JSONObject jObject = new JSONObject();
    ListView apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_applications_new);
        fillJson();
        getActionBar().setTitle("Мои Заявки");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //// Pretend json were recieved here
    public void fillJson(){
        JSONObject application1 = new JSONObject();
        try {
            application1.put("id", "0");
            application1.put("type", "Вызов врача на дом");
            application1.put("title", "Гематома в мезинце");
            application1.put("tags", "Терапевт Хирург Костоправ");
            application1.put("date", "16 декабря 09:30 - 10:30");
            application1.put("area", "Наурызбайский, Алматинский");
            application1.put("price", "2500");
            application1.put("response", "25");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject application2 = new JSONObject();
        try {
            application2.put("id", "1");
            application2.put("type", "Запись на прием к врачу");
            application2.put("title", "Восполение хитрости");
            application2.put("tags", "Терапевт Терапевт-Невролог");
            application2.put("date", "16 декабря 09:30 - 10:30");
            application2.put("area", "");
            application2.put("price", "2500");
            application2.put("response", "18");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject application3 = new JSONObject();
        try {
            application2.put("id", "2");
            application2.put("type", "Обследование и анализы");
            application2.put("title", "Укол в голову на проверку мозга");
            application2.put("tags", "Обследование");
            application2.put("date", "16 декабря 11:30 - 12:30");
            application2.put("area", "Наурызбайский, Алматинский");
            application2.put("price", "8000");
            application2.put("response", "6");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject application4 = new JSONObject();
        try {
            application2.put("id", "3");
            application2.put("type", "Вызов Врача на дом");
            application2.put("title", "Измерить давление");
            application2.put("tags", "Терапевт Медсестра ");
            application2.put("date", "20 декабря 12:30 - 13:30");
            application2.put("area", "Fire Nation");
            application2.put("price", "9000");
            application2.put("response", "9");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();

        jsonArray.put(application1);
        jsonArray.put(application2);
        jsonArray.put(application3);
        jsonArray.put(application4);


        try {
            jObject.put("Applications", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

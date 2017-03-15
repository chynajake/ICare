package com.jake.chyna.icare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.*;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chyna on 3/10/17.
 */

public class PatientApplicationsApproved extends Activity {
    JSONObject jApps = new JSONObject();
    JSONObject jDoctors = new JSONObject();

    // коллекция для пациентов
    ArrayList<ArrayList<String>> appsParent;
    //Apps Attribs
    ArrayList<String> appsAttr;

    //doctor atttributes
    ArrayList<String> docAttr;
    // коллекция для доктора пациентов
    ArrayList<ArrayList<String>> doctorsChildDataItem;
    // List of children
    ArrayList<ArrayList<ArrayList<String>>> doctorsChildData;
    // в итоге получится childData = ArrayList<childDataItem>

    // список атрибутов группы или элемента
    ExpandableListView elvMain;

    TextView btn_new;
    TextView btn_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_application_approved);
        fillJsonApplications();
        fillJsonDoctors();

        // INITIALIZING BUTTONS
        btn_new = (TextView) findViewById(R.id.appr_newApplications);
        btn_app = (TextView) findViewById(R.id.appr_approvedApplications);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getApplicationContext(), PatientApplitcationsNewActivity.class);
                startActivity(in);
            }
        });
        ////


        // stuff for myELV
        appsParent = new ArrayList<ArrayList<String>>();
        doctorsChildData = new ArrayList<ArrayList<ArrayList<String>>>();

        try {
            // Fill parent
            JSONArray traverse = jApps.getJSONArray("Applications");
            for(int i = 0; i < traverse.length(); i++) {
                appsAttr = new ArrayList<>();
                appsAttr.add(traverse.getJSONObject(i).get("icon").toString());
                appsAttr.add(traverse.getJSONObject(i).get("type").toString());
                appsAttr.add(traverse.getJSONObject(i).get("date").toString());
                appsAttr.add(traverse.getJSONObject(i).get("title").toString());
                appsAttr.add(traverse.getJSONObject(i).get("tags").toString());
                appsAttr.add("Район(ы): "+traverse.getJSONObject(i).get("area").toString());
                appsAttr.add(traverse.getJSONObject(i).get("price").toString()+ " тг");
                appsAttr.add(traverse.getJSONObject(i).get("id").toString());
                Log.d("MYLOGS_APS", appsParent.toString());
                appsParent.add(appsAttr);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
            ////
        try{
            // fill child doctor
            JSONArray traverse2 = jDoctors.getJSONArray("Doctors");
            Log.d("MYLOGS_TRAVERSE2", traverse2.toString());
            Log.d("MYLOGS_LENGTH", traverse2.length()+"");
            for(int j = 0; j < traverse2.length(); j++) {
                docAttr = new ArrayList<>();
                doctorsChildDataItem = new ArrayList<ArrayList<String>>();
                docAttr.add(traverse2.getJSONObject(j).get("doctor_photo").toString());
                docAttr.add(traverse2.getJSONObject(j).get("doctor_name").toString());
                docAttr.add(traverse2.getJSONObject(j).get("address_one").toString());
                docAttr.add(traverse2.getJSONObject(j).get("address_two").toString());
                docAttr.add(traverse2.getJSONObject(j).get("rating").toString());
                docAttr.add(traverse2.getJSONObject(j).get("tags").toString());
                docAttr.add(traverse2.getJSONObject(j).get("id").toString());

                doctorsChildDataItem.add(docAttr);
                doctorsChildData.add(doctorsChildDataItem);
                Log.d("MYLOGS_DCD", doctorsChildDataItem.toString());
            }
            for(Object a : doctorsChildData.toArray()) {
                Log.d("MYLOGS_DCD", a.toString());
            }
            ////
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ////

        MyExpandableListAdapter adapt = new MyExpandableListAdapter(this,
                appsParent,
                doctorsChildData);
        elvMain = (ExpandableListView) findViewById(R.id.appr_applications);
        elvMain.setAdapter(adapt);
        elvMain.expandGroup(0, true);
        elvMain.expandGroup(1, true);
        //elvMain.expandGroup(2, true);
        //elvMain.expandGroup(3, true);
        elvMain.setOnGroupClickListener(new OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                if(parent.isGroupExpanded(groupPosition)) {
                    Log.d("MYLOGS", groupPosition+"");

                }
                Log.d("MYLOGS", groupPosition+"");
                return false;
            }
        });
    }

    public void fillJsonApplications() {
        JSONObject a = new JSONObject();
        try {
            a.put("id", "0");
            a.put("icon", R.drawable.doctorhour);
            a.put("type", "Вызов врача на дом");
            a.put("date", "16 декабря 09:30 - 10:30");
            a.put("title", "Гематома в мезинце");
            a.put("tags", "Терапевт Лор Хирург");
            a.put("area", "Наурызбайский, \nАлматинский");
            a.put("price", "2500");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject b = new JSONObject();
        try {
            b.put("id", "1");
            b.put("icon", R.drawable.doctoroncall);
            b.put("type", "Запись на прием к врачу");
            b.put("title", "Восполение хитрости");
            b.put("tags", "Терапевт Невропатолог");
            b.put("date", "16 декабря 09:30 - 10:30");
            b.put("area", "Наурызбайский, \nАлматинский");
            b.put("price", "2500");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject c = new JSONObject();

        try {
            c.put("id", "2");
            c.put("icon", R.drawable.medtests);
            c.put("type", "Обследование и анализы");
            c.put("title", "Укол в голову на проверку мозга");
            c.put("tags", "Терапевт Процедуры");
            c.put("date", "16 декабря 11:30 - 12:30");
            c.put("area", "Наурызбайский, \n Алматинский");
            c.put("price", "8000");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject d = new JSONObject();

        try {
            d.put("id", "3");
            d.put("icon", R.drawable.doctorhour);
            d.put("type", "Вызов Врача на дом");
            d.put("title", "Измерить давление");
            d.put("tags", "Терапевт Бензопила-Скальпель");
            d.put("date", "20 декабря 12:30 - 13:30");
            d.put("area", "Fire Nation");
            d.put("price", "9000");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONArray abcd = new JSONArray();

        abcd.put(a); abcd.put(b); abcd.put(c); abcd.put(d);

        try {
            jApps.put("Applications", abcd);
            Log.d("MYLOGS_APPLICATIONS", jApps.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void fillJsonDoctors() {
        JSONObject doc1 = new JSONObject();
        try {
            doc1.put("id", "0");
            doc1.put("doctor_photo", R.drawable.house);
            doc1.put("doctor_name", "Грегорий Хаус");
            doc1.put("rating", "10");
            doc1.put("address_one", "Науаи 68");
            doc1.put("address_two", "Науай 68");
            doc1.put("tags", "Хирург Гастронтролог");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject doc2 = new JSONObject();
        try {
            doc2.put("id", "1");
            doc2.put("doctor_photo", R.drawable.house);
            doc2.put("doctor_name", "Грегорий Хаус");
            doc2.put("rating", "9");
            doc2.put("address_one", "Науаи 68");
            doc2.put("address_two", "Науай 68");
            doc2.put("tags", "Терапевт Псих Инвалид");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        JSONObject doc3 = new JSONObject();
        try {
            doc3.put("id", "2");
            doc3.put("photo", R.drawable.bane);
            doc3.put("name", "Дорранс Бейн");
            doc3.put("rating", "9");
            doc3.put("address_one", "Готем 68");
            doc3.put("address_two", "Науай 68");
            doc3.put("tags", "Костоправ ЛечитСпину");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject doc4 = new JSONObject();
        try {
            doc4.put("id", "3");
            doc4.put("photo", R.drawable.house);
            doc4.put("name", "Грегорий Хаус coolOne");
            doc4.put("rating", "9");
            doc4.put("address_one", "Науаи 68");
            doc4.put("address_two", "Науай 68");
            doc4.put("tags", "Терапевт");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray docs = new JSONArray();
        docs.put(doc1); docs.put(doc2); docs.put(doc3); docs.put(doc4);

        try {
            jDoctors.put("Doctors", docs);
            Log.d("MYLOGS_DOCTORS", jDoctors.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}

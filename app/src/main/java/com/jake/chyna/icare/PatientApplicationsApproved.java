package com.jake.chyna.icare;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
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

public class PatientApplicationsApproved extends AppCompatActivity {
    JSONObject jObject = new JSONObject();
    JSONObject jDoctors = new JSONObject();

    // коллекция для пациентов
    ArrayList<Map<String, String>> applicationData;
    String [] app_from = {"app_id", "type", "title",
            "date", "area", "price", "response", "icon"};
    int app_to[] = {R.id.app_id, R.id.type, R.id.title, R.id.date, R.id.area, R.id.price, R.id.response, R.id.typeIcon};

    // коллекция для докторов пациентов
    ArrayList<Map<String, String>> doctorDataItem;
    String [] doc_from = {"doc_id", "photo", "name", "rating", "address1", "address2"};
    int [] doc_to = {R.id.doc_id, R.id.doctor_photo, R.id.doctor_name, R.id.rating, R.id.pin_text1, R.id.pin_text2};

    // общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> patientData;
    // в итоге получится childData = ArrayList<childDataItem>

    // список атрибутов группы или элемента
    Map<String, String> m;

    ExpandableListView elvMain;

    TextView btn_new;
    TextView btn_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_application_approved);
        initButtons();
        fillJson();
        fillJDoctor();
        fillApplicationData();
        fillDoctorDataItem();
        SimpleExpandableListAdapter adapt = new SimpleExpandableListAdapter(this,
                applicationData,
                R.layout.item_application_new,
                app_from, app_to,
                patientData,
                R.layout.item_doctor_approved,
                doc_from, doc_to);
        elvMain = (ExpandableListView) findViewById(R.id.appr_applications);
        elvMain.setAdapter(adapt);

    }
    public void initButtons() {
        btn_new = (TextView) findViewById(R.id.appr_newApplications);
        btn_app = (TextView) findViewById(R.id.appr_approvedApplications);
        btn_new.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), PatientApplitcationsNewActivity.class);
                startActivity(intent);
            }
        });
        btn_app.setTextColor(Color.parseColor("#868686"));
        btn_new.setTextColor(Color.parseColor("#4E1668"));
    }
    public void fillJDoctor() {
        JSONObject d1 = new JSONObject();
        try {
            d1.put("id", "0");
            d1.put("photo", R.drawable.house+"");
            d1.put("name", "Грегорий Хаус");
            d1.put("rating", "10");
            d1.put("tags", "Терапевт Хирург Псих");
            d1.put("address_one", "Науаи 68");
            d1.put("address_two", "Науай 68");
            d1.put("type", true); // if true doctor accepted you

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject d2 = new JSONObject();
        try {
            d2.put("id", "1");
            d2.put("photo", R.drawable.house+"");
            d2.put("name", "Грегорий Хаус");
            d2.put("rating", "9");
            d2.put("tags", "Терапевт");
            d2.put("address_one", "Науаи 68");
            d2.put("address_two", "Науай 68");
            d2.put("type", true); // if true doctor accepted you

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject d3 = new JSONObject();
        try {
            d3.put("id", "2");
            d3.put("photo", R.drawable.bane+"");
            d3.put("name", "Дорранс Бейн");
            d3.put("rating", "9");
            d3.put("tags", "Костоправ");
            d3.put("address_one", "Готем 68");
            d3.put("address_two", "Науай 68");
            d3.put("type", true);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject d4 = new JSONObject();
        try {
            d4.put("id", "3");
            d4.put("photo", R.drawable.house+"");
            d4.put("name", "Грегорий Хаус coolOne");
            d4.put("rating", "9");
            d4.put("tags", "Терапевт");
            d4.put("address_one", "Науаи 68");
            d4.put("address_two", "Науай 68");
            d4.put("type", false);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONArray jsonArray = new JSONArray();

        jsonArray.put(d1);
        jsonArray.put(d2);
        jsonArray.put(d3);
        jsonArray.put(d4);


        try {
            jDoctors.put("Doctors", jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void fillJson() {
        JSONObject application1 = new JSONObject();
        try {
            application1.put("id", "0");
            application1.put("pic_id", "0");
            application1.put("type", "Вызов врача на дом");
            application1.put("title", "Гематома в мезинце");
            application1.put("tags", "Терапевт Хирург Костоправ");
            application1.put("date", "16 декабря 09:30 - 10:30");
            application1.put("area", "Наурызбайский, \n Алматинский");
            application1.put("price", "2500");
            application1.put("response", "25");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject application2 = new JSONObject();
        try {
            application2.put("id", "1");
            application2.put("pic_id", "1");
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
            application3.put("id", "2");
            application3.put("pic_id", "2");
            application3.put("type", "Обследование и анализы");
            application3.put("title", "Укол в голову на проверку мозга");
            application3.put("tags", "Обследование");
            application3.put("date", "16 декабря 11:30 - 12:30");
            application3.put("area", "Наурызбайский, \n Алматинский");
            application3.put("price", "8000");
            application3.put("response", "6");

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        JSONObject application4 = new JSONObject();
        try {
            application4.put("id", "3");
            application4.put("pic_id", "0");
            application4.put("type", "Вызов Врача на дом");
            application4.put("title", "Измерить давление");
            application4.put("tags", "Терапевт Медсестра ");
            application4.put("date", "20 декабря 12:30 - 13:30");
            application4.put("area", "Fire Nation");
            application4.put("price", "9000");
            application4.put("response", "9");

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
    public void fillApplicationData() {
        applicationData = new ArrayList<Map<String, String>>();
        try {
            JSONArray arr = jObject.getJSONArray("Applications");
            for(int i = 0; i < arr.length(); i++) {
                m = new HashMap<String, String>();
                m.put("app_id", arr.getJSONObject(i).get("id").toString());
                m.put("type", arr.getJSONObject(i).get("type").toString());
                m.put("title", arr.getJSONObject(i).get("title").toString());
                m.put("date", arr.getJSONObject(i).get("date").toString());
                if(!arr.getJSONObject(i).get("area").toString().equals("")) {
                    m.put("area", "Районы: "+ arr.getJSONObject(i).get("area").toString());
                }else {
                    m.put("area", "");
                }
                m.put("price", arr.getJSONObject(i).get("price").toString() + " тг");
                m.put("response", arr.getJSONObject(i).get("response").toString() + " Отзывов");

                String pi = arr.getJSONObject(i).get("pic_id").toString();
                if(pi.equals("0")){
                    m.put("icon", R.drawable.doctoroncall+"");
                }else if(pi.equals("1")){
                    m.put("icon", R.drawable.doctorhour+"");
                }else if(pi.equals("2")){
                    m.put("icon", R.drawable.medtests +"");
                }

                applicationData.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void fillDoctorDataItem() {
        patientData = new ArrayList<ArrayList<Map<String, String>>>();
        doctorDataItem = new ArrayList<Map<String, String>>();
        try {
            JSONArray jar = jDoctors.getJSONArray("Doctors");
            for(int i = 0; i < jar.length(); i++){
                m = new HashMap<>();
                m.put("doc_id", jar.getJSONObject(i).get("id").toString());
                m.put("photo", jar.getJSONObject(i).get("photo").toString());
                m.put("name", jar.getJSONObject(i).get("name").toString());
                m.put("rating", jar.getJSONObject(i).get("rating").toString());
                m.put("address1", jar.getJSONObject(i).get("address_one").toString());
                m.put("address2", jar.getJSONObject(i).get("address_two").toString());
                doctorDataItem.add(m);
                patientData.add(doctorDataItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}

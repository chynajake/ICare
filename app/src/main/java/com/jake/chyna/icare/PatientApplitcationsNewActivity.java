package com.jake.chyna.icare;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.tag;

/**
 * Created by chyna on 3/8/17.
 */

public class PatientApplitcationsNewActivity extends AppCompatActivity {
    JSONObject jObject = new JSONObject();



    // коллекция для групп
    ArrayList<Map<String, String>> appData;

    // список атрибутов группы или элемента
    Map<String, String> m;


    ListView lv;

    String [] apps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_applications_new);
        fillJson();
        //getActionBar().setTitle("Мои Заявки");

        Log.d("MyLOGS", jObject.toString());
        appData = new ArrayList<Map<String, String>>();

        String [] from = {"type", "title",
                "date", "area", "price", "response", "icon"};
        int to[] = {R.id.type, R.id.title,
                R.id.date, R.id.area, R.id.price, R.id.response, R.id.typeIcon};


        try {
            Log.d("MyLOGS", "We are inside try");
            JSONArray arr = jObject.getJSONArray("Applications");
            Log.d("MyLogs", arr.toString());
            for(int i = 0; i < arr.length(); i++) {

                m = new HashMap<String, String>();
                m.put("type", arr.getJSONObject(i).get("type").toString());
                m.put("title", arr.getJSONObject(i).get("title").toString());
                m.put("date", arr.getJSONObject(i).get("date").toString());
                if(!arr.getJSONObject(i).get("area").toString().equals("")) {
                    m.put("area", Html.fromHtml("<p><font color=&quot;#4E1686&quot;>Район(ы): </font></p>" + arr.getJSONObject(i).get("area").toString()).toString());
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
                    m.put("icon", R.drawable.procedures +"");
                }
                Log.d("MyLogs", m.toString());

                appData.add(m);
                Log.d("MyLogs", "Our map: " + m.toString());



            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SimpleAdapter adapter = new SimpleAdapter(
                this,
                appData,
                R.layout.item_application_new, from, to);
        lv = (ListView) findViewById(R.id.applications);
        lv.setAdapter(adapter);



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
}

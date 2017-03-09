package com.jake.chyna.icare;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by chyna on 3/10/17.
 */

public class FragmentRespondDoctors extends Fragment {

    JSONObject jDoctors = new JSONObject();


    // коллекция для групп
    ArrayList<Map<String, String>> docData;

    // список атрибутов группы или элемента
    Map<String, String> m;


    ListView r_lv;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_res_doctors, null);
        fillJDoctor();
        r_lv = (ListView) v.findViewById(R.id.r_lv);
        docData = new ArrayList<Map<String,String>>();
        String [] from = {"doc_id", "photo", "name", "rating", "address1", "address2"};
        int [] to = {R.id.doc_id, R.id.doctor_photo, R.id.doctor_name, R.id.rating, R.id.pin_text1, R.id.pin_text2};


        try {
            JSONArray jar = jDoctors.getJSONArray("Doctors");
            for(int i = 0; i < jar.length(); i++){
                if(jar.getJSONObject(i).getBoolean("type")) {
                    m = new HashMap<>();
                    m.put("doc_id", jar.getJSONObject(i).get("id").toString());
                    m.put("photo", jar.getJSONObject(i).get("photo").toString());
                    m.put("name", jar.getJSONObject(i).get("name").toString());
                    m.put("rating", jar.getJSONObject(i).get("rating").toString());
                    m.put("address1", jar.getJSONObject(i).get("address_one").toString());
                    m.put("address2", jar.getJSONObject(i).get("address_two").toString());
                    docData.add(m);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        SimpleAdapter docAdapter = new SimpleAdapter(getActivity(), docData, R.layout.item_doctor, from, to);
        docAdapter.setViewBinder(new ButtonBinder());
        r_lv.setAdapter(docAdapter);
        r_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            }
        });
        return v;
    }
    class ButtonBinder implements SimpleAdapter.ViewBinder {

        @Override
        public boolean setViewValue(View view, Object data,
                                    String textRepresentation) {
            switch (view.getId()) {
                case R.id.btn_accept:
                    ((Button) view).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    });

            }

            return false;
        }
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
}

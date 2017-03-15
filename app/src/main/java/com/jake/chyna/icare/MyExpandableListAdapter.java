package com.jake.chyna.icare;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

/**
 * Created by chyna on 3/15/17.
 */

public class MyExpandableListAdapter extends BaseExpandableListAdapter {

    private ArrayList<ArrayList<String>> mGroups;
    private ArrayList<ArrayList<ArrayList<String>>> mChild;
    private Context mContext;

    public MyExpandableListAdapter (Context context,ArrayList<ArrayList<String>> groups, ArrayList<ArrayList<ArrayList<String>>> childs){
        mContext = context;
        mGroups = groups;
        mChild = childs;
    }

    @Override
    public int getGroupCount() {
        return mGroups.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return mChild.get(groupPosition).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return mGroups.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return mChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView,
                             ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_application_new, null);
        }

        if (isExpanded){
            //Изменяем что-нибудь, если текущая Group раскрыта
        }
        else{
            //Изменяем что-нибудь, если текущая Group скрыта
        }

        ImageView img = (ImageView) convertView.findViewById(R.id.typeIcon);
        TextView type = (TextView) convertView.findViewById(R.id.type);
        TextView date = (TextView) convertView.findViewById(R.id.date);
        TextView title = (TextView) convertView.findViewById(R.id.title);
        GridView cont = (GridView) convertView.findViewById(R.id.tag_container);
        TextView area = (TextView) convertView.findViewById(R.id.area);
        TextView price = (TextView) convertView.findViewById(R.id.price);
        TextView resp = (TextView) convertView.findViewById(R.id.response);
        TextView app_id = (TextView) convertView.findViewById(R.id.app_id);

        img.setImageResource(Integer.parseInt(mGroups.get(groupPosition).get(0)));
        type.setText(mGroups.get(groupPosition).get(1));
        date.setText(mGroups.get(groupPosition).get(2));
        title.setText(mGroups.get(groupPosition).get(3));
        cont.setAdapter(new ArrayAdapter(mContext, R.layout.item_application_new_tag, mGroups.get(groupPosition).get(4).split(" ")));
        area.setText(mGroups.get(groupPosition).get(5));
        price.setText(mGroups.get(groupPosition).get(6));
        app_id.setText(mGroups.get(groupPosition).get(7));
        resp.setText("");

        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild,
                             View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_doctor_approved, null);
        }


        ImageView doc_ph = (ImageView) convertView.findViewById(R.id.doctor_photo_app);
        TextView doc_name = (TextView) convertView.findViewById(R.id.doctor_name_app);
        TextView pin_text1 = (TextView) convertView.findViewById(R.id.pin_text1_app);
        TextView pin_text2 = (TextView) convertView.findViewById(R.id.pin_text2_app);
        Button btn = (Button) convertView.findViewById(R.id.btn_write);
        TextView rating = (TextView) convertView.findViewById(R.id.rating_app);
        GridView gv = (GridView) convertView.findViewById(R.id.doc_tag_container_app);
        TextView id = (TextView) convertView.findViewById(R.id.doc_id_app);


        doc_ph.setImageResource(Integer.parseInt(mChild.get(groupPosition).get(childPosition).get(0)));
        doc_name.setText(mChild.get(groupPosition).get(childPosition).get(1));
        pin_text1.setText(mChild.get(groupPosition).get(childPosition).get(2));
        pin_text2.setText(mChild.get(groupPosition).get(childPosition).get(3));
        rating.setText(mChild.get(groupPosition).get(childPosition).get(4) + " / 10");
        ArrayAdapter aa = new ArrayAdapter(mContext, R.layout.item_application_new_tag, mChild.get(groupPosition).get(childPosition).get(5).split(" "));
        gv.setAdapter(aa);
        id.setText(mChild.get(groupPosition).get(childPosition).get(6));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Write to doctor and praise the sun", Toast.LENGTH_LONG).show();
            }
        });



        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
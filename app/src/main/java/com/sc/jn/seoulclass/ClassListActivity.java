package com.sc.jn.seoulclass;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.jn.seoulclass.Model.ClassListItem;
import com.sc.jn.seoulclass.Util.ClassListAdapter;
import com.sc.jn.seoulclass.Util.ClassListTask;
import com.sc.jn.seoulclass.Util.ManagePublicData;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ClassListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        int nm = getIntent().getIntExtra("nm",0);
        ArrayList<ClassListItem> temp;
        temp = selectCategory(nm);

        ListView listView;
        ClassListAdapter adapter;



        adapter = new ClassListAdapter(temp);
        listView = (ListView)findViewById(R.id.listview_classList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                
            }
        });


    }

    private ArrayList<ClassListItem> selectCategory(int nm){
        ArrayList<ClassListItem> temp=null;
        switch(nm){
            case 1 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory1ArrayList();
                break;
            }
            case 2 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory2ArrayList();
                break;
            }
            case 3 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory3ArrayList();
                break;
            }
            case 4 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory4ArrayList();
                break;
            }
            case 5 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory5ArrayList();
                break;
            }
            case 6 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory6ArrayList();
                break;
            }
            case 7 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory7ArrayList();
                break;
            }
            case 8 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory8ArrayList();
                break;
            }
            case 9 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory9ArrayList();
                break;
            }
            case 10 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory10ArrayList();
                break;
            }
            case 11 :{
                temp = ManagePublicData.getInstance(getApplicationContext()).getCategory11ArrayList();
                break;
            }
        }

        return temp;

    }
}

package com.sc.jn.seoulclass;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.sc.jn.seoulclass.Model.ClassListItem;
import com.sc.jn.seoulclass.Util.ClassListAdapter;
import com.sc.jn.seoulclass.Util.ManagePublicData;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ClassListActivity extends AppCompatActivity {

    private ClassListAdapter adapter;
    private Toolbar toolbar ;
    private ArrayList<ClassListItem> temp;
    private ListView listView;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        int nm = getIntent().getIntExtra("nm",0);
        String title = getIntent().getStringExtra("title");




//        Start : to ListView
        temp = new ArrayList<ClassListItem>(selectCategory(nm));
        listView = (ListView)findViewById(R.id.listview_classList);
        adapter = new ClassListAdapter(temp);
        listView.setEmptyView(findViewById(R.id.listview_empty));
        listView.setAdapter(adapter);
//        End


//        Start : for Search View
        searchView = (SearchView)findViewById(R.id.cl_searchView);
        searchView.setIconifiedByDefault(false);
        searchView.setQueryHint("Search Here");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(TextUtils.isEmpty(newText)){
                    listView.clearTextFilter();
                }else{
                    Log.d("jinho",newText);
                    listView.setFilterText(newText);
                }
                return true;
            }
        });
//        End


//        For Toolbar=========================================

        toolbar = (Toolbar) findViewById(R.id.dt_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ((TextView)findViewById(R.id.dt_txt_toolbar)).setText(title);

//====================================================================


    }

    @Override
    protected void onResume() {
        super.onResume();



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getApplicationContext(),DetailActivity.class);
                ClassListItem item =(ClassListItem) adapter.getItem(position);
                if(item.getUrl().contains("yeyak")){
                    intent.putExtra("title", item.getTitle() );
                    intent.putExtra("id",item.getId());
                    adapter.getItem(position);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"서비스 준비중입니다.", Toast.LENGTH_SHORT).show();

                    //다른사이트
                }

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.activity_classlist, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case  R.id.cl_sub1 : {
                Collections.sort(temp,new CompareBase());
                adapter.notifyDataSetChanged();
            }
        }
        return true;
    }


    class CompareBase implements Comparator<ClassListItem> {

        @Override
        public int compare(ClassListItem o1, ClassListItem o2) {
            if(o1.getTitle().compareTo(o2.getTitle())>0){
                return 1;
            }else if(o1.getTitle().compareTo(o2.getTitle())<0){
                return -1;
            }else{
                return 0;
            }
        }
    }
    private ArrayList<ClassListItem> selectCategory(int nm){
        ArrayList<ClassListItem> temp=null;
        switch(nm){
            case 1 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory1ArrayList();
                break;
            }
            case 2 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory2ArrayList();
                break;
            }
            case 3 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory3ArrayList();
                break;
            }
            case 4 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory4ArrayList();
                break;
            }
            case 5 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory5ArrayList();
                break;
            }
            case 6 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory6ArrayList();
                break;
            }
            case 7 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory7ArrayList();
                break;
            }
            case 8 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory8ArrayList();
                break;
            }
            case 9 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory9ArrayList();
                break;
            }
            case 10 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory10ArrayList();
                break;
            }
            case 11 :{
                temp = ManagePublicData.getInstance(ClassListActivity.this).getCategory11ArrayList();
                break;
            }
        }

        return temp;

    }
}
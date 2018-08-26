package com.sc.jn.seoulclass;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sc.jn.seoulclass.Util.ManageSharedPreference;
import com.sc.jn.seoulclass.Util.PermissionUtil;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private String[] permissions = {Manifest.permission.INTERNET};
    private WebView webView;
    private TextView txtToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        checkAddress();//주소 설정되어있는지 확인


        txtToolbar = (TextView)findViewById(R.id.txt_toolbar);
        txtToolbar.setText(ManageSharedPreference.getPreference("address",getApplicationContext()));

        webView = (WebView) findViewById(R.id.webView_main);
        WebSettings webSettings = webView.getSettings();
        webSettings.setAppCacheEnabled(false);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                webView.loadUrl(url);
                return true;
            }
        });



        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

//        HeaderView Click Event
        View headerView = navigationView.getHeaderView(0);
        LinearLayout nav_dibs = (LinearLayout)headerView.findViewById(R.id.nav_dibs);//찜
        LinearLayout nav_review = (LinearLayout)headerView.findViewById(R.id.nav_review);//리뷰관리
        nav_dibs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DibsActivity.class);
                startActivity(intent);

            }
        });
        nav_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ReviewActivity.class);
                startActivity(intent);

            }
        });



        //    Click Event
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intent = new Intent(getApplicationContext(),AddressActivity.class);
               startActivity(intent);



            }
        });




    }

    @Override
    protected void onResume() {
        super.onResume();
        txtToolbar.setText(ManageSharedPreference.getPreference("address",getApplicationContext()));
    }

    @Override
    public void onStart() {
        super.onStart();

        if (!PermissionUtil.checkPermissions(this, Manifest.permission.INTERNET)) {
            PermissionUtil.requestPermissions(this,permissions,1);
        } else {
            webView.loadUrl("http://www.seoul.go.kr/main/index.html");
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    public void CategoryClickHandler(View view){
        Intent intent = new Intent(getApplicationContext(), ClassListActivity.class);
        switch(view.getId()){
            case  R.id.main_1 : {
                intent.putExtra("nm", 1);
                startActivity(intent);
                break;
            }
            case  R.id.main_2 : {
                intent.putExtra("nm", 2);
                startActivity(intent);
                break;
            }
            case  R.id.main_3 : {
                intent.putExtra("nm", 3);
                startActivity(intent);
                break;
            }
            case  R.id.main_4 : {
                intent.putExtra("nm", 4);
                startActivity(intent);
                break;
            }
            case  R.id.main_5 : {
                intent.putExtra("nm", 5);
                startActivity(intent);
                break;
            }
            case  R.id.main_6 : {
                intent.putExtra("nm", 6);
                startActivity(intent);
                break;
            }
            case  R.id.main_7 : {
                intent.putExtra("nm", 7);
                startActivity(intent);
                break;
            }
            case  R.id.main_8 : {
                intent.putExtra("nm", 8);
                startActivity(intent);
                break;
            }

            case R.id.main_9 : {
                intent.putExtra("nm",9);
                startActivity(intent);
                break;
            }
            case R.id.main_10 : {
                intent.putExtra("nm",10);
                startActivity(intent);
                break;
            }
            case R.id.main_11 : {
                intent.putExtra("nm",11);
                startActivity(intent);
                break;
            }
        }

    }
    private void checkAddress(){
        if(ManageSharedPreference.getPreference("address",getApplicationContext())==""){
            Intent intent = new Intent(getApplicationContext(), AddressActivity.class);
            startActivity(intent);
        }
    }

    public Point getScreenSize(Activity activity){
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    public int getToolBarHeight() {
        int[] attrs = new int[] {R.attr.actionBarSize};
        TypedArray ta = getApplicationContext().obtainStyledAttributes(attrs);
        int toolBarHeight = ta.getDimensionPixelSize(0, -1);
        ta.recycle();
        return toolBarHeight;
    }



}

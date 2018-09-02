package com.sc.jn.seoulclass.Fragments;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.sc.jn.seoulclass.AddressActivity;
import com.sc.jn.seoulclass.DetailActivity;
import com.sc.jn.seoulclass.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    private View v;



    public MapFragment() {
        // Required empty public constructor

    }

    public static MapFragment newInstance() {

        MapFragment fragment = new MapFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_map, container, false);

        ContentParser contentParser = new ContentParser();
        contentParser.execute(DetailActivity.id);
        return v;
    }


    public class ContentParser extends AsyncTask<String,Void,Void>{
        Element how;
        Element phone;
        Element addr;
        @Override
        protected Void doInBackground(String... str) {

            String url = "https://yeyak.seoul.go.kr/reservation/view.web?rsvsvcid="+str[0];
            try {
                Document doc = Jsoup.connect(url).get();
                how = doc.selectFirst("#objGIS dl dd:nth-last-child(1)");
                phone = doc.selectFirst("#objGIS dl dd:nth-last-child(3)");
                addr = doc.selectFirst("#objGIS dl dd:nth-last-child(5)");

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            TextView addr = (TextView)v.findViewById(R.id.fmap_addr);
            TextView phone = (TextView)v.findViewById(R.id.fmap_phone);
            TextView how = (TextView)v.findViewById(R.id.fmap_how);




            addr.setText(this.addr.text());
            phone.setText(this.phone.text());
            how.setText(this.how.text());

        }

    }

}
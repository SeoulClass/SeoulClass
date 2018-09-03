package com.sc.jn.seoulclass.Fragments;

import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
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

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

public class MapFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match


    private View v;
    MapView mapView;



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
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        DetailActivity parent = (DetailActivity)getActivity();
        if(isVisibleToUser){
            parent.findViewById(R.id.dt_webView).setVisibility(View.GONE);
            parent.findViewById(R.id.dt_menu).setVisibility(View.GONE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_map, container, false);

        mapView = v.findViewById(R.id.fmap_mapView);

        ContentParser contentParser = new ContentParser();
        contentParser.execute(DetailActivity.id);
        return v;
    }

    public static Location findGeoPoint(Context mcontext, String address) {
        Location loc = new Location("");
        Geocoder coder = new Geocoder(mcontext);
        List<Address> addr = null;// 한좌표에 대해 두개이상의 이름이 존재할수있기에 주소배열을 리턴받기 위해 설정

        try {
            addr = coder.getFromLocationName(address, 1);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }// 몇개 까지의 주소를 원하는지 지정 1~5개 정도가 적당
        if (addr != null) {
            for (int i = 0; i < addr.size(); i++) {
                Address lating = addr.get(i);
                double lat = lating.getLatitude(); // 위도가져오기
                double lon = lating.getLongitude(); // 경도가져오기
                loc.setLatitude(lat);
                loc.setLongitude(lon);
            }
        }
        return loc;
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

            Location loc = findGeoPoint(getActivity(), this.addr.text());
            MapPoint mapPoint = MapPoint.mapPointWithGeoCoord(loc.getLatitude(),loc.getLongitude());
            mapView.setMapCenterPoint(mapPoint, true);

            MapPOIItem marker = new MapPOIItem();
            marker.setItemName(this.addr.text());
            marker.setMapPoint(mapPoint);
            marker.setMarkerType(MapPOIItem.MarkerType.BluePin); // 기본으로 제공하는 BluePin 마커 모양.

            mapView.addPOIItem(marker);

            addr.setText(this.addr.text());
            phone.setText(this.phone.text());
            how.setText(this.how.text());

        }

    }

}
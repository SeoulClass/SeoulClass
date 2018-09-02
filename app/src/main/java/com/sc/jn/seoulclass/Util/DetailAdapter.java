package com.sc.jn.seoulclass.Util;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;
import android.view.View;

import com.sc.jn.seoulclass.Fragments.InfoFragment;
import com.sc.jn.seoulclass.Fragments.MapFragment;
import com.sc.jn.seoulclass.Fragments.ReviewFragment;
import com.sc.jn.seoulclass.R;

public class DetailAdapter extends FragmentPagerAdapter {

    private static int PAGE_NUMBER = 3;
    public DetailAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){

            case 0 :

                return InfoFragment.newInstance();
            case 1 :

                return MapFragment.newInstance();
            case 2 :


                return ReviewFragment.newInstance();
            default :
                return null;
        }
    }

    @Override
    public int getCount() {
        return PAGE_NUMBER;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "정보";
            case 1:
                return "찾아오시는 길";
            case 2:
                return "리뷰";
            default :
                return null;

        }

    }
}

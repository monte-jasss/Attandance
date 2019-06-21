package com.ashwani.example.attendance.Admin;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class ViewPageAdapter  extends FragmentPagerAdapter {


    ArrayList<Fragment> fragments=new ArrayList<>();
    ArrayList<String> tabTitles = new ArrayList<>();


    public void addFragments (Fragment fragments,String titles)
    {

        this.fragments.add(fragments);
        this.tabTitles.add(titles);

    }


    public ViewPageAdapter(FragmentManager fm) {



        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        //DemoFragment demoFragment=new DemoFragment();
        //position=position+1;
        //Bundle bundle=new Bundle();
        //bundle.putString("message","fragment" +position);
        //demoFragment.setArguments(bundle);
        return fragments.get(position);
    }

    @Override
    public int getCount() {

        return fragments.size();
    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        //position=position+1;
        return tabTitles.get(position);
    }
}

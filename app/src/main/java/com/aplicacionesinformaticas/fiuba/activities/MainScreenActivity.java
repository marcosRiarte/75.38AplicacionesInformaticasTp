
package com.aplicacionesinformaticas.fiuba.activities;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.aplicacionesinformaticas.fiuba.R;
import com.aplicacionesinformaticas.fiuba.fragments.ScreenSlidePageFragment;

import java.util.ArrayList;

import static android.R.attr.fragment;

public class MainScreenActivity extends AppCompatActivity {

    private ViewPager pager;
    private MyFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        ActionBar actionBar = getActionBar();


        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        pager = (ViewPager)findViewById(R.id.pager);

        adapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager());
        adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.blue), 0));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.green), 1));
        adapter.addFragment(ScreenSlidePageFragment.newInstance(getResources()
                .getColor(R.color.red), 2));
        this.pager.setAdapter(adapter);
    }
}

class MyFragmentPagerAdapter extends FragmentPagerAdapter {

    // List of fragments which are going to set in the view pager widget
    ArrayList<Fragment> fragments;

    /**
     * Constructor
     *
     * @param fm
     *            interface for interacting with Fragment objects inside of an
     *            Activity
     */
    public MyFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<Fragment>();
    }

    public void addFragment(Fragment fragment) {
        this.fragments.add(fragment);
    }

    @Override
    public Fragment getItem(int arg0) {
        return this.fragments.get(arg0);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

}
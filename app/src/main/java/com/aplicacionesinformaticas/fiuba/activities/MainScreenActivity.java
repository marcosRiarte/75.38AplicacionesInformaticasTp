
package com.aplicacionesinformaticas.fiuba.activities;

import android.app.ActionBar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;

import com.aplicacionesinformaticas.fiuba.R;
import com.aplicacionesinformaticas.fiuba.fragments.ContactFragment;
import com.aplicacionesinformaticas.fiuba.fragments.PedidosFragment;
import com.aplicacionesinformaticas.fiuba.fragments.PerfilFragment;
import com.aplicacionesinformaticas.fiuba.fragments.ScreenSlidePageFragment;

import java.util.ArrayList;

import static android.R.attr.fragment;

public class MainScreenActivity extends AppCompatActivity {

    private String TAG = "MainScreenActivity";
    private ViewPager pager;
    private MyFragmentPagerAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main_screen);

        //actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        pager = (ViewPager)findViewById(R.id.pager);

        adapter = new MyFragmentPagerAdapter(
                getSupportFragmentManager());

        adapter.addFragment(new PedidosFragment());
        adapter.addFragment(new PerfilFragment());
        adapter.addFragment(new ContactFragment());
        this.pager.setAdapter(adapter);

        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Pedidos"));
        tabLayout.addTab(tabLayout.newTab().setText("Perfil"));
        tabLayout.addTab(tabLayout.newTab().setText("Contacto"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
                if (adapter.getItem(tab.getPosition()) instanceof PedidosFragment){
                    PedidosFragment pedidosFragment = (PedidosFragment)adapter.getItem(tab.getPosition());
                    pedidosFragment.inicializarVista();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
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
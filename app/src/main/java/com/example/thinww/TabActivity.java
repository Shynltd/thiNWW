package com.example.thinww;

import android.os.Bundle;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.thinww.ui.main.SectionsPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class TabActivity extends AppCompatActivity {
    private List<Photo> photoList;
    SectionsPagerAdapter sectionsPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        photoList = new ArrayList<>();
        getA(2);
        sectionsPagerAdapter = new SectionsPagerAdapter(photoList,TabActivity.this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);


    }
    private void getA(int page) {
        AndroidNetworking.get("https://picsum.photos/v2/list?limit=100")
                .addQueryParameter("page", String.valueOf(page))
                .build().getAsJSONArray(new JSONArrayRequestListener() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {

                        String url = response.getJSONObject(i).getString("url");
                        String author = response.getJSONObject(i).getString("author");
                        photoList.add(new Photo( author,url));


                    }
                    sectionsPagerAdapter.notifyDataSetChanged();
                    Log.e("list", String.valueOf(photoList.size()));
                } catch (JSONException e) {
                    e.printStackTrace();
                    Log.e("catch", e.getMessage());
                }
            }

            @Override
            public void onError(ANError anError) {

                Log.e("anError", anError.getMessage());
            }
        });
    }
}
package com.example.thinww;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.jacksonandroidnetworking.JacksonParserFactory;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Photo> photoList;
    private ViewPager vqg;
    ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        AndroidNetworking.initialize(MainActivity.this);
        AndroidNetworking.setParserFactory(new JacksonParserFactory());
        photoList = new ArrayList<>();

        getA(2);
        viewPagerAdapter = new ViewPagerAdapter(photoList, MainActivity.this);
        vqg.setAdapter(viewPagerAdapter);

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


                    }
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

    private void initView() {
        vqg = (ViewPager) findViewById(R.id.vqg);
    }
}
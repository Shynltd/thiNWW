package com.example.thinww.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.thinww.Photo;
import com.example.thinww.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PlaceHolderFragment extends Fragment {

    List<Photo> photoList;

    public PlaceHolderFragment() {

    }

    // Method static dạng singleton, cho phép tạo fragment mới, lấy tham số đầu vào để cài đặt màu sắc.
    public static PlaceHolderFragment newInstance(List<Photo> photoList, int i) {
        PlaceHolderFragment fragment = new PlaceHolderFragment();
        Bundle args = new Bundle();
        args.putString("list", photoList.get(i).getUrl());
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);
        ImageView img = rootView.findViewById(R.id.img);

        String url= getArguments().getString("list");
//        for (int i = 0; i < photoList.size(); i++) {

                    Picasso.get().load(url).into(img);


        return rootView;
    }
}

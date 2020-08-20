package com.example.thinww.ui.main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.thinww.Photo;
import com.example.thinww.R;

import java.util.List;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private List<Photo> photoList;
    private final Context context;

    public SectionsPagerAdapter(List<Photo> photoList, Context context, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.photoList = photoList;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceHolderFragment.newInstance(photoList,position+1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return photoList.get(position).getAuthor();
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return photoList.size();
    }
}
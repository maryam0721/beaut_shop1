package com.project.beautyshop.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.project.beautyshop.R;
import com.project.beautyshop.adapter.TabLayoutAdapter;

public class ProfileFragment extends Fragment {
    TabLayout tabLayout;
    ViewPager viewPagerProfile;
    TabLayoutAdapter tabLayoutAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupProfile();
        viewPagerProfile.setAdapter(tabLayoutAdapter);
        tabLayout.setupWithViewPager(viewPagerProfile);
        TabLayout.Tab tab = tabLayout.getTabAt(1);
        tab.select();
    }

    public void setupProfile() {
        //FindViewById--------
        tabLayout = getView().findViewById(R.id.tabLayout);
        viewPagerProfile = getView().findViewById(R.id.viewPagerProfile);
        //Lists---------------
        //initialize----------
        try {
            tabLayoutAdapter = new TabLayoutAdapter(getChildFragmentManager());
        } catch (Exception exception) {
            Log.d("3434", exception.getMessage());
        }
    }
}

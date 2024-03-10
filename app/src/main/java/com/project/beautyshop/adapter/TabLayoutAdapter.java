package com.project.beautyshop.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.project.beautyshop.view.OrderFragment;
import com.project.beautyshop.view.AddressFragment;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutAdapter extends FragmentPagerAdapter {
    List<String> tabsTitle = new ArrayList<>();
    public TabLayoutAdapter(@NonNull FragmentManager fm) {
        super(fm);
        tabsTitle.add("آدرس");
        tabsTitle.add("سفارش");
//        tabsTitle.setSele
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1 : return new OrderFragment();
        }
        return new AddressFragment();
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabsTitle.get(position);
    }
}

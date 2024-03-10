package com.project.beautyshop.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.project.beautyshop.view.BasketFragment;
import com.project.beautyshop.view.CategoryFragment;
import com.project.beautyshop.view.HomeFragment;
import com.project.beautyshop.view.ProfileFragment;
import com.project.beautyshop.view.SearchFragment;

public class ViewPagerMainAdapter extends FragmentStateAdapter {


    public ViewPagerMainAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0 : return new ProfileFragment();
            case 1 : return new SearchFragment();
            case 2 : return new BasketFragment();
            case 3 : return new CategoryFragment();
        }
        return new HomeFragment();
    }

    @Override
    public int getItemCount() {
        return 5;
    }
}

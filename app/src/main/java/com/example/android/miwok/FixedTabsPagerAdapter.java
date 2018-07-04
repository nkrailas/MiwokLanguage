package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class FixedTabsPagerAdapter extends FragmentPagerAdapter {

    private Context context;

    FixedTabsPagerAdapter(FragmentManager fragmentManager, Context context) {
        super(fragmentManager);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new NumbersFragment();
        } else if (position == 1) {
            return new FamilyFragment();
        } else if (position == 2) {
            return new ColorsFragment();
        } else {
            return new PhrasesFragment();
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.numbers_title);
        } else if (position == 1) {
            return context.getString(R.string.family_title);
        } else if (position == 2) {
            return context.getString(R.string.colors_title);
        } else if (position == 3) {
            return context.getString(R.string.phrases_title);
        }

        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }
}
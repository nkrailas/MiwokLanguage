package com.example.android.miwok;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class FixedTabsPagerAdapter extends FragmentPagerAdapter {

    // Context of the app
    private Context context;

    /**
     * Create a new Adapter object
     *
     * @param context         Context of the app
     * @param fragmentManager Keep each fragment's state in the adapter across swipes
     */

    public FixedTabsPagerAdapter(Context context, FragmentManager fragmentManager) {
        super(fragmentManager);
        this.context = context;
    }

    // Return the fragment that should be displayed for the given page number
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
            return context.getString(R.string.category_numbers);
        } else if (position == 1) {
            return context.getString(R.string.category_family);
        } else if (position == 2) {
            return context.getString(R.string.category_colors);
        } else if (position == 3) {
            return context.getString(R.string.category_phrases);
        }

        return null;
    }

    // Return the total number of pages
    @Override
    public int getCount() {
        return 4;
    }
}
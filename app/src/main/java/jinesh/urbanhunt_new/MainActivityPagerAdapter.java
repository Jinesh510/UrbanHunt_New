package jinesh.urbanhunt_new;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Jinesh on 26/03/16.
 */
public class MainActivityPagerAdapter extends FragmentStatePagerAdapter {

    final int PAGE_COUNT = 3;
    private String tabTitles[] = new String[] { "DEALS", "MISSIONS", "REWARDS" };
    private Context context;

    public MainActivityPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }


    @Override
    public Fragment getItem(int position) {

        Fragment fragment = null;

        Class fragmentClass;
        switch(position) {
            case 0:
//                fragment = ProductListFragment.newInstance();
                return fragment;
            case 1:
//                fragment = SelectCategoryFragment.newInstance();
                return fragment;
            case 2:
//                fragment = BrandFragment.newInstance();
                return fragment;

//                fragment = BrandFragment.newInstance();
//                return fragment;
        }

        return null;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return tabTitles[position];
    }

}

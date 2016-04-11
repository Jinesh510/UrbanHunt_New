package jinesh.urbanhunt_new.Activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;

import jinesh.urbanhunt_new.Fragments.UserPointsFragment;
import jinesh.urbanhunt_new.Fragments.UserPromoCodeFragment;
import jinesh.urbanhunt_new.Fragments.UserReferEarnFragment;
import jinesh.urbanhunt_new.Fragments.UserTransactionsListFragment;
import jinesh.urbanhunt_new.R;

/**
 * Created by Jinesh on 10/04/16.
 */
public class NavigationDrawerActivity extends AppCompatActivity {

    Toolbar mToolbar;
    FrameLayout mFrameLayout;
    int id;
    Fragment flFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_drawer_activity);

        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        id = getIntent().getIntExtra("id",1);

        mFrameLayout = (FrameLayout)findViewById(R.id.flContent);


        flFragment = getFragment(id);

        FragmentManager fragmentManager = this.getSupportFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.flContent, flFragment).commit();

    }

    public Fragment getFragment(int id){

        Fragment fragment = null;

        switch(id) {
            case 1:
                fragment = UserPointsFragment.newInstance();
                return fragment;
            case 2:
                fragment = UserTransactionsListFragment.newInstance();
                return fragment;
            case 3:
                fragment = UserPromoCodeFragment.newInstance();
                return fragment;
            case 4:
                fragment = UserReferEarnFragment.newInstance();
                return fragment;

        }

        return null;
    }
}

package com.adamgrieger.pythoneasydocs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;

import io.karim.MaterialTabs;


/**
 * Activity for downloading and deleting offline versions of Python documentation.
 */
public class ManageDocsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_docs);

        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);

        ViewPager mPager = (ViewPager) findViewById(R.id.view_pager);
        mPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), 3));

        MaterialTabs mMaterialTabs = (MaterialTabs) findViewById(R.id.material_tabs);
        mMaterialTabs.setViewPager(mPager);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }

        return true;
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {
        private final String[] TITLES = {"HTML", "PDF", "TXT"};
        private final ArrayList<String> mTitles;

        public MyPagerAdapter(FragmentManager fm, int numberOfTabs) {
            super(fm);
            mTitles = new ArrayList<>();
            for (int i = 0; i < numberOfTabs; i++) {
                mTitles.add(TITLES[i]);
            }
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles.get(position);
        }

        @Override
        public int getCount() {
            return mTitles.size();
        }

        @Override
        public Fragment getItem(int position) {
            return ManageDocsTabFragment.newInstance(position);
        }
    }
}

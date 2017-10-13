package tech.hackdtu.developers2.theftsecurity.Activities;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import tech.hackdtu.developers2.theftsecurity.Fragments.LockFragment;
import tech.hackdtu.developers2.theftsecurity.Fragments.StationAlarmFragment;
import tech.hackdtu.developers2.theftsecurity.R;
import tech.hackdtu.developers2.theftsecurity.Utils.ViewPagerAdapter;

public class HomeScreenActivity extends AppCompatActivity {

    ViewPager viewPager;
    TabLayout tabLayout;
    ViewPagerAdapter adapter;
    LockFragment lockFragment;
    StationAlarmFragment stationAlarmFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition(),false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.getTabAt(position).select();

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        lockFragment = new LockFragment();
        stationAlarmFragment = new StationAlarmFragment();
        adapter.addFragment(lockFragment, "Lock");
        adapter.addFragment(stationAlarmFragment,"Station Alarm");
        viewPager.setAdapter(adapter);
    }
}
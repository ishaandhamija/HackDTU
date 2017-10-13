package tech.hackdtu.developers2.theftsecurity.Utils;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import tech.hackdtu.developers2.theftsecurity.Fragments.LockFragment;
import tech.hackdtu.developers2.theftsecurity.Fragments.StationAlarmFragment;

/**
 * Created by ishaandhamija on 14/10/17.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
        // TODO Auto-generated constructor stub
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new LockFragment();
            case 1:
                return new StationAlarmFragment();
        }
        return null;

    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return 2; //No of Tabs you can give your number of tabs
    }


}
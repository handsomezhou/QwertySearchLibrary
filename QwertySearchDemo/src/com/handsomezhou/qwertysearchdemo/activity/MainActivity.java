
package com.handsomezhou.qwertysearchdemo.activity;

import android.support.v4.app.Fragment;

import com.handsomezhou.qwertysearchdemo.fragment.MainFragment;

public class MainActivity extends BaseSingleFragmentActivity {

    @Override
    protected Fragment createFragment() {

        return new MainFragment();
    }

    @Override
    protected boolean isRealTimeLoadFragment() {
        // TODO Auto-generated method stub
        return false;
    }

}

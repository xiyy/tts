package com.xi.liuliu.tts.view;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.widget.RadioGroup;

import com.xi.liuliu.tts.R;

public class MainActivity extends Activity {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RadioGroup mNavigatorRg;
    private MakeFragment mMakeFragment;
    private WorkFragment mWorkFragment;
    private MineFragment mMineFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

    }

    private void initView() {
        mNavigatorRg = findViewById(R.id.ma_rg_navigator);
        mNavigatorRg.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.ma_rbt_navigator_make:
                    showFragment(Navigator.Navigator_Make);
                    break;
                case R.id.ma_rbt_navigator_work:
                    showFragment(Navigator.Navigator_Work);
                    break;
                case R.id.ma_rbt_navigator_mine:
                    showFragment(Navigator.Navigator_Mine);
                    break;
            }
        });
    }

    private void showFragment(Navigator navigator) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        hideFragment(transaction);
        switch (navigator) {
            case Navigator_Make:
                if (mMakeFragment == null) {
                    mMakeFragment = new MakeFragment();
                    transaction.add(R.id.ma_flt_fragment, mMakeFragment);
                } else {
                    transaction.show(mMakeFragment);
                }
                break;
            case Navigator_Work:
                if (mWorkFragment == null) {
                    mWorkFragment = new WorkFragment();
                    transaction.add(R.id.ma_flt_fragment, mWorkFragment);
                } else {
                    transaction.show(mWorkFragment);
                }
                break;
            case Navigator_Mine:
                if (mMineFragment == null) {
                    mMineFragment = new MineFragment();
                    transaction.add(R.id.ma_flt_fragment, mMineFragment);
                } else {
                    transaction.show(mMineFragment);
                }
                break;
        }
        transaction.commit();
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (fragmentTransaction != null) {
            if (mMakeFragment != null) {
                fragmentTransaction.hide(mMakeFragment);
            }
            if (mWorkFragment != null) {
                fragmentTransaction.hide(mWorkFragment);
            }
            if (mMineFragment != null) {
                fragmentTransaction.hide(mMineFragment);
            }
        }
    }

    enum Navigator {
        Navigator_Make,
        Navigator_Work,
        Navigator_Mine
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
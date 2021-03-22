package com.xi.liuliu.tts.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.xi.liuliu.tts.R;

public class MineFragment extends Fragment implements View.OnClickListener {
    private LinearLayout mLoginLl;
    private LinearLayout mBillLl;
    private LinearLayout mHelpLl;
    private LinearLayout mSettingLl;
    private LinearLayout mAboutLl;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigator_mine, container, false);
        mLoginLl = view.findViewById(R.id.mf_ll_login);
        mLoginLl.setOnClickListener(this);
        mBillLl = view.findViewById(R.id.mf_spend_bill_ll);
        mBillLl.setOnClickListener(this);
        mHelpLl = view.findViewById(R.id.mf_help_ll);
        mHelpLl.setOnClickListener(this);
        mSettingLl = view.findViewById(R.id.mf_settings_ll);
        mSettingLl.setOnClickListener(this);
        mAboutLl = view.findViewById(R.id.mf_about_ll);
        mAboutLl.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mf_ll_login:

                break;
            case R.id.mf_spend_bill_ll:

                break;
            case R.id.mf_help_ll:

                break;
            case R.id.mf_settings_ll:

                break;
            case R.id.mf_about_ll:

                break;
        }
    }
}

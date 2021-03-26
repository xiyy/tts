package com.xi.liuliu.tts.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.tencent.mmkv.MMKV;
import com.xi.liuliu.tts.R;
import com.xi.liuliu.tts.global.Constant;

import java.lang.ref.WeakReference;


public class UserProtocolDialog implements View.OnClickListener{

    private DialogView mDialogView;
    private WeakReference<Activity> mActivityWeakReference;

    private TextView mUserProtocolAgreeTv;
    private TextView mUserProtocolExitTv;
    public UserProtocolDialog(WeakReference<Activity>  activityWeakReference) {
        mActivityWeakReference = activityWeakReference;
        init();
    }

    private void init() {
        Activity activity = mActivityWeakReference.get();
        if (activity!=null) {
            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_user_protocol, null);
            mUserProtocolAgreeTv = view.findViewById(R.id.dup_tv_agree);
            mUserProtocolExitTv = view.findViewById(R.id.dup_tv_exit);
            mUserProtocolAgreeTv.setOnClickListener(this);
            mUserProtocolExitTv.setOnClickListener(this);
            mDialogView = new DialogView(activity,view);
            mDialogView.setCancelable(false);
            mDialogView.setDimBehind(true);
            mDialogView.setCanceledOnTouchOutside(false);
        }

    }


    public void show() {
        mDialogView.showDialog();
    }

    public void dismiss() {
        mDialogView.dismissDialog();

    }

    public boolean isShowing() {
        return mDialogView.isShowing();
    }

    @Override
    public void onClick(View v) {
        Activity activity = mActivityWeakReference.get();
        dismiss();
        if (activity!=null) {
            activity.finish();
        }
        switch (v.getId()) {
            case R.id.dup_tv_agree:
                Intent intent = new Intent(activity, MainActivity.class);
                activity.startActivity(intent);
                MMKV.defaultMMKV().putBoolean(Constant.KEY_USER_PROTOCOL,true);
                break;

        }
    }
}

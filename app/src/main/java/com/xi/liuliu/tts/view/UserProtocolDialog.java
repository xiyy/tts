package com.xi.liuliu.tts.view;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
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
    private TextView mProtocolContentTv;
    public UserProtocolDialog(WeakReference<Activity>  activityWeakReference) {
        mActivityWeakReference = activityWeakReference;
        init();
    }

    private void init() {
        Activity activity = mActivityWeakReference.get();
        if (activity!=null) {
            View view = LayoutInflater.from(activity).inflate(R.layout.dialog_user_protocol, null);
            mProtocolContentTv = view.findViewById(R.id.dup_tv_protocol_content);
            mProtocolContentTv.setText(getClickableSpan());
            mProtocolContentTv.setMovementMethod(LinkMovementMethod.getInstance());
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


    private SpannableString getClickableSpan() {
        Activity activity = mActivityWeakReference.get();
        SpannableString spanStr = new SpannableString("感谢您使用文字转语音软件，在使用我们的软件前请您仔细阅读《隐私政策》及《用户协议》、为保证您可以正常使用本软件所有功能，请同意我们的协议。");
        spanStr.setSpan(new UnderlineSpan(), 28, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new ClickableSpan() {

            @Override
            public void onClick(View widget) {
                if (activity!=null) {
                    Intent intent = new Intent(activity, UserProtocolActivity.class);
                    intent.putExtra(Constant.KEY_PROTOCOL_TYPE,Constant.PROTOCOL_PRIVACY);
                    activity.startActivity(intent);
                }

            }
        }, 28, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new ForegroundColorSpan(Color.RED), 28, 34, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new UnderlineSpan(), 35, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new ClickableSpan() {
            @Override
            public void onClick(View widget) {

                if (activity!=null) {
                    Intent intent = new Intent(activity, UserProtocolActivity.class);
                    intent.putExtra(Constant.KEY_PROTOCOL_TYPE,Constant.PROTOCOL_USER);
                    activity.startActivity(intent);
                }
            }
        }, 35, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spanStr.setSpan(new ForegroundColorSpan(Color.BLUE), 35, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spanStr;
    }
}

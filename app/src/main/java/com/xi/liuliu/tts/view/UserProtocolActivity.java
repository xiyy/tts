package com.xi.liuliu.tts.view;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import com.xi.liuliu.tts.R;
import com.xi.liuliu.tts.global.Constant;

public class UserProtocolActivity extends Activity {
     private Button mBackBtn;
    private WebView mContactWv;
    private TextView mTitleTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_protocol);
        init();
    }

    private void init() {
        mBackBtn = findViewById(R.id.upa_btn_go_back);
        mBackBtn.setOnClickListener(v -> finish());
        mTitleTv = findViewById(R.id.upa_tv_title);
        mContactWv = findViewById(R.id.upa_wv);
        mContactWv.getSettings().setJavaScriptEnabled(true);
        int which = getIntent().getIntExtra(Constant.KEY_PROTOCOL_TYPE,0);
        if (which==Constant.PROTOCOL_USER) {
            mTitleTv.setText(R.string.protocol_user);
            mContactWv.loadUrl(Constant.urlUserProtocol);
        }else if (which==Constant.PROTOCOL_PRIVACY) {
            mTitleTv.setText(R.string.protocol_privacy);
            mContactWv.loadUrl(Constant.urlPrivacy);
        }





    }
}
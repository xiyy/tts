package com.xi.liuliu.tts.view;


import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.Button;

import com.xi.liuliu.tts.R;
import com.xi.liuliu.tts.global.Constant;

public class ContactUsActivity extends Activity {
    private Button mBackBtn;
    private WebView mContactWv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        init();
    }

    private void init() {
        mBackBtn = findViewById(R.id.cua_btn_go_back);
        mBackBtn.setOnClickListener(v -> finish());
        mContactWv = findViewById(R.id.cua_contact_wv);
        mContactWv.loadUrl(Constant.urlContactUs);
        mContactWv.getSettings().setJavaScriptEnabled(true);
    }
}
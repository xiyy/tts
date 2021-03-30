package com.xi.liuliu.tts.view;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xi.liuliu.tts.R;
public class TtsActivity extends Activity implements View.OnClickListener {
    private Button mBackBtn;
    private EditText mTtsContentEt;
    private ImageView mModelPortraitIv;
    private TextView mModelNameTv;
    private ImageView mBgmIv;
    private TextView mBgmTv;
    private ImageView mPlaySpeedIv;
    private TextView mTtsStartTv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tts);
        init();
    }

    private void init() {
        mBackBtn = findViewById(R.id.tts_activity_btn_go_back);
        mBackBtn.setOnClickListener(this);
        mTtsContentEt = findViewById(R.id.tts_activity_text_content);
        mModelPortraitIv = findViewById(R.id.tts_activity_model_portrait_iv);
        mModelPortraitIv.setOnClickListener(this);
        mBgmIv = findViewById(R.id.tts_activity_bgm_iv);
        mBgmIv.setOnClickListener(this);
        mBgmTv = findViewById(R.id.tts_activity_bgm_tv);
        mPlaySpeedIv = findViewById(R.id.tts_activity_play_speed_iv);
        mPlaySpeedIv.setOnClickListener(this);
        mTtsStartTv = findViewById(R.id.tts_activity_tts_start);
        mTtsStartTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tts_activity_btn_go_back:
                finish();
                break;
            case R.id.tts_activity_model_portrait_iv:

                break;
            case R.id.tts_activity_bgm_iv:

                break;
            case R.id.tts_activity_play_speed_iv:

                break;
            case R.id.tts_activity_tts_start:

                break;
        }
    }
}
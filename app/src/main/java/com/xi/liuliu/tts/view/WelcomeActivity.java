package com.xi.liuliu.tts.view;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.xi.liuliu.tts.R;

import java.util.Random;

public class WelcomeActivity extends Activity {
    private ImageView mIvWelcome;
    static final long mDelayTime = 1500;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        initView();

    }

    private void initView() {
        mIvWelcome = findViewById(R.id.aw_iv_welcome);
        int randomIndex = new Random().nextInt(3);
        switch (randomIndex) {
            case 0:
                mIvWelcome.setImageResource(R.drawable.welcome_girl);
                break;
            case 1:
                mIvWelcome.setImageResource(R.drawable.welcome_lighthouse);
                break;
            default:
                mIvWelcome.setImageResource(R.drawable.welcome_road);

        }
        mIvWelcome.postDelayed(() -> {
            Intent intent = new Intent(WelcomeActivity.this,MainActivity.class);
            startActivity(intent);
            finish();
        },mDelayTime);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
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
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    //禁止用返回键
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}

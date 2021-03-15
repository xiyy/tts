package com.xi.liuliu.tts.global;

import android.app.Application;

import com.xi.liuliu.tts.util.LogUtil;

public class TtsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.setDebug(true);
    }
}

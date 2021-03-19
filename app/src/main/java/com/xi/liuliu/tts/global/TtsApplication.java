package com.xi.liuliu.tts.global;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.tencent.mmkv.MMKV;
import com.xi.liuliu.tts.util.LogUtil;

public class TtsApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            System.loadLibrary("ttsoff");
        }catch (Throwable t) {
            t.printStackTrace();
        }
        LogUtil.setDebug(true);
        //application监听activity的生命周期
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(@NonNull Activity activity, @Nullable Bundle savedInstanceState) {
                LogUtil.log(activity.getClass().getSimpleName(),"onActivityCreated");
            }

            @Override
            public void onActivityStarted(@NonNull Activity activity) {
                LogUtil.log(activity.getClass().getSimpleName(),"onActivityStarted");
            }

            @Override
            public void onActivityResumed(@NonNull Activity activity) {
                LogUtil.log(activity.getClass().getSimpleName(),"onActivityResumed");
            }

            @Override
            public void onActivityPaused(@NonNull Activity activity) {
                LogUtil.log(activity.getClass().getSimpleName(),"onActivityPaused");
            }

            @Override
            public void onActivityStopped(@NonNull Activity activity) {
                LogUtil.log(activity.getClass().getSimpleName(),"onActivityStopped");
            }

            @Override
            public void onActivitySaveInstanceState(@NonNull Activity activity, @NonNull Bundle outState) {
                LogUtil.log(activity.getClass().getSimpleName(),"onActivitySaveInstanceState");
            }

            @Override
            public void onActivityDestroyed(@NonNull Activity activity) {
                LogUtil.log(activity.getClass().getSimpleName(),"onActivityDestroyed");
            }
        });
        String rootDir = MMKV.initialize(this);
        LogUtil.log("mmkv rootDir:"+rootDir);
    }
}

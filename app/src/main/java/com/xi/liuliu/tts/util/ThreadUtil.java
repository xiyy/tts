package com.xi.liuliu.tts.util;

import android.os.Looper;

public class ThreadUtil {
    public static boolean isMainThread() {
        return Looper.getMainLooper().getThread().getId() == Thread.currentThread().getId();
    }
}

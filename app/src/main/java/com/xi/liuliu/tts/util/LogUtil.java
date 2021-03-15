package com.xi.liuliu.tts.util;

import android.text.TextUtils;
import android.util.Log;

/**
 * Date:2019/8/29
 * Author:zhangxiaobei
 * Describe:
 */
public class LogUtil {
    private static final String DEFAULT_TAG = "tts";
    private static boolean isDebug = false;

    public static void log(String content) {
        log(DEFAULT_TAG,content);
    }


    public static void log(String tag, String content) {
        if (TextUtils.isEmpty(tag)||TextUtils.isEmpty(content)) return;
        if (isDebug) {
            Log.d(tag, content);
        }

    }


    public static void logWarn(String content) {
       logWarn(DEFAULT_TAG,content);

    }


    public static void logWarn(String tag, String content) {
        if (TextUtils.isEmpty(tag)||TextUtils.isEmpty(content)) return;
        if (isDebug) {
            Log.w(tag, content);

        }

    }

    public static void logError(String content) {
        logError(DEFAULT_TAG,content);

    }



    public static void logError(String tag, String content) {
        if (TextUtils.isEmpty(tag)||TextUtils.isEmpty(content)) return;
        if (isDebug) {
            Log.e(tag, content);
        }
    }

    public static void setDebug(boolean isDebug) {
        LogUtil.isDebug = isDebug;
    }

    public static boolean isDebug() {
        return isDebug;
    }
}

package com.xi.liuliu.tts.util

import android.app.Activity
import android.content.Context
import android.os.Build
import android.telephony.TelephonyManager

object DeviceUtil {
    fun getArmType(): String {

        return if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            Build.CPU_ABI;
        } else {
            Build.SUPPORTED_ABIS[0];
        }
    }

    fun getOsVersion(): String {
        return Build.VERSION.RELEASE
    }

    fun getModel(): String {
        return Build.MODEL
    }

    fun getBrand(): String {
        return Build.BRAND
    }

    fun getUUid(context: Context): String {
        var service: Any = context.getSystemService(Activity.TELEPHONY_SERVICE)
        var imei: String = (service as TelephonyManager)?.getDeviceId()
        imei ?: getBrand() + "_" + getModel() + "_" + getImsi(context)
        return imei
    }

    fun getImsi(context: Context): String {
        var service: Any = context.getSystemService(Activity.TELEPHONY_SERVICE)
        var imsi: String = (service as TelephonyManager)?.getSubscriberId()
        return imsi
    }
}
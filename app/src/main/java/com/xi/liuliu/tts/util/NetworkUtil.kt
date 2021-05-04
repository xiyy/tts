package com.xi.liuliu.tts.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetworkUtil {
    companion object {
        fun isNetworkAvailable(context: Context): Boolean? {
            var service: Any = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            if (service is ConnectivityManager) {
                var networkInfo: NetworkInfo? = service.activeNetworkInfo
                return networkInfo?.isAvailable
            }
            return false
        }

        fun isWifiConnected(context: Context): Boolean? {
            var service: Any = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            if (service is ConnectivityManager) {
                var wifiNetworkInfo: NetworkInfo? = service.getNetworkInfo(ConnectivityManager.TYPE_WIFI)
                return wifiNetworkInfo?.isAvailable
            }
            return false
        }

        fun isMobileConnected(context: Context): Boolean? {
            var service: Any = context.getSystemService(Context.CONNECTIVITY_SERVICE)
            if (service is ConnectivityManager) {
                var mobileNetworkInfo: NetworkInfo? = service.getNetworkInfo(ConnectivityManager.TYPE_MOBILE)
                return mobileNetworkInfo?.isAvailable
            }
            return false
        }
    }
}
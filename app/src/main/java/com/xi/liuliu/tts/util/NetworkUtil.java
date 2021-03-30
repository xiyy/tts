package com.xi.liuliu.tts.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.lang.reflect.Method;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * Created by Administrator on 2020/4/15.
 */

public class NetworkUtil {
    private static final String TAG = NetworkUtil.class.getSimpleName();
    public static final int NETWORK_NONE = 0;
    public static final int NETWORK_WIFI = 1;
    public static final int NETWORK_2G = 2;
    public static final int NETWORK_3G = 3;
    public static final int NETWORK_4G = 4;
    public static final int NETWORK_MOBILE = 5;

    /**
     * @param context
     * @return 网络是否已经连接（连接了也不能保证网络能访问通）
     */
    public static boolean isNetworkConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo.isConnected();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @param context
     * @return Wi-Fi是否已经连接（连接了也不能保证网络能访问通）
     */
    public static boolean isWifiConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo wiFiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
                return wiFiInfo.isConnected();
            } else {
                return false;
            }
        }
        return false;
    }


    public static boolean isMobileConnected(Context context) {
        if (context != null) {
            ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (connectivityManager != null) {
                NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
                return mobileInfo.isConnected();
            } else {
                return false;
            }
        }
        return false;
    }

    /**
     * @param context
     * @return 运营商名字
     */
    public static String getOperatorName(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return telephonyManager.getSimOperatorName();
    }

    /**
     * @param context
     * @return 网络类型
     */
    public static int getNetworkState(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null == connManager) {
            return NETWORK_NONE;
        }
        NetworkInfo activeNetInfo = connManager.getActiveNetworkInfo();
        if (activeNetInfo == null || !activeNetInfo.isAvailable()) {
            return NETWORK_NONE;
        }
        NetworkInfo wifiInfo = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (null != wifiInfo) {
            NetworkInfo.State state = wifiInfo.getState();
            if (null != state) {
                if (state == NetworkInfo.State.CONNECTED || state == NetworkInfo.State.CONNECTING) {
                    return NETWORK_WIFI;
                }
            }
        }
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = telephonyManager.getNetworkType();
        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return NETWORK_2G;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return NETWORK_3G;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return NETWORK_4G;
            default:
                return NETWORK_MOBILE;
        }
    }

    /**
     * 耗时操作，在子线程中调用
     *
     * @param ipOrDomain
     * @return 网络是否畅通
     */
    public static boolean isNetworkAvailableByPing(String ipOrDomain) {
        if (TextUtils.isEmpty(ipOrDomain)) {
            return false;
        }
        ShellUtil.CommandResult result = ShellUtil.execCmd(String.format("ping -c 1 %s", ipOrDomain), false);
        boolean ret = result.result == 0;
        if (result.successMsg != null) {
            LogUtil.log(TAG, "isNetworkAvailableByPing," + result.successMsg);
        }
        if (result.errorMsg != null) {
            LogUtil.log(TAG, "isNetworkAvailableByPing," + result.successMsg);
        }
        return ret;

    }

    /**
     * 判断wifi开关是否打开
     *
     * @return
     */
    public static boolean isWifiEnabled(Context context) {
        if (context != null) {
            @SuppressLint("WifiManagerLeak")
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            return wifiManager.isWifiEnabled();
        }
        return false;

    }

    /**
     * 打开或者关闭Wi-Fi
     *
     * @param context
     * @param enabled
     */
    public static void setWifiEnabled(Context context, boolean enabled) {
        if (context != null) {
            @SuppressLint("WifiManagerLeak")
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (enabled) {
                if (!wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(true);
                }
            } else {
                if (wifiManager.isWifiEnabled()) {
                    wifiManager.setWifiEnabled(false);
                }
            }
        }


    }

    /**
     * @param context
     * @return 移动数据开关是否打开
     */
    public static boolean isMobileDataEnabled(Context context) {
        if (context != null) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                Method getMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("getDataEnabled");
                if (getMobileDataEnabledMethod != null) {
                    return (boolean) getMobileDataEnabledMethod.invoke(tm);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }
        return false;

    }

    /**
     * 打开或关闭移动数据,需要MODIFY_PHONE_STATE系统权限，普通app做不到
     */
    public void setMobileDataEnabled(Context context, boolean enabled) {
        if (context != null) {
            try {
                TelephonyManager tm = (TelephonyManager) context.getApplicationContext().getSystemService(Context.TELEPHONY_SERVICE);
                Method setMobileDataEnabledMethod = tm.getClass().getDeclaredMethod("setDataEnabled", boolean.class);
                if (null != setMobileDataEnabledMethod) {
                    setMobileDataEnabledMethod.invoke(tm, enabled);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 根据域名获取IP，主线程调用会崩溃
     *
     * @param domain 域名
     * @return IP 地址
     */
    public static String domain2Ip(@Nullable String domain) {
        if (!ThreadUtil.isMainThread()) {
            InetAddress inetAddress;
            try {
                inetAddress = InetAddress.getByName(domain);
                return inetAddress.getHostAddress();
            } catch (UnknownHostException e) {
                e.printStackTrace();
                return null;
            }
        }
        return null;

    }

    /**
     * 通过Wi-Fi或者移动网络获取局域网IP
     *
     * @return
     */
    public static String getLocalIp(Context context) {
        if (context != null) {
            NetworkInfo info = ((ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
            if (info != null && info.isConnected()) {
                if (info.getType() == ConnectivityManager.TYPE_MOBILE) {
                    try {
                        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                            NetworkInterface intf = en.nextElement();
                            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                                InetAddress inetAddress = enumIpAddr.nextElement();
                                if (!inetAddress.isLoopbackAddress() && inetAddress instanceof Inet4Address) {
                                    return inetAddress.getHostAddress();
                                }
                            }
                        }
                    } catch (SocketException e) {
                        e.printStackTrace();
                    }

                } else if (info.getType() == ConnectivityManager.TYPE_WIFI) {
                    WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
                    WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                    String ipAddress = intIP2StringIP(wifiInfo.getIpAddress());
                    return ipAddress;
                }
            } else {
                return null;
            }
        }

        return null;
    }

    /**
     * 将得到的int类型的IP转换为String类型
     *
     * @param ip
     * @return
     */
    private static String intIP2StringIP(int ip) {
        return (ip & 0xFF) + "." + ((ip >> 8) & 0xFF) + "." + ((ip >> 16) & 0xFF) + "." + (ip >> 24 & 0xFF);
    }

    /**
     * 发送网络请求获取公网IP
     *
     * @return
     */
    public static String getPublicIp() {
        return null;
    }


}

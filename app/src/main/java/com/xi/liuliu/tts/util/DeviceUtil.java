package com.xi.liuliu.tts.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Display;
import android.view.WindowManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DeviceUtil {
    private static final String TAG = DeviceUtil.class.getSimpleName();
    private final static String CPU_FILE_PATH = "/sys/devices/system/cpu/";
    private final static String CPU_CUR_FREQ_FILE_PATH = "/cpufreq/scaling_cur_freq";
    private final static String CPU_MAX_FREQ_FILE_PATH = "/cpufreq/cpuinfo_max_freq";
    private final static String CPU_MIN_FREQ_FILE_PATH = "/cpufreq/cpuinfo_min_freq";
    private final static String CPU_AVAILABLE_FREQS_FILE_PATH = "/cpufreq/scaling_available_frequencies";
    private final static String CPU_GOVERNOR_FILE_PATH = "/cpufreq/scaling_governor";
    private final static String CPU_AVAILABLE_GOVERNORS_FILE_PATH = "/cpufreq/scaling_available_governors";
    private static final String CPU_STATE_FILE_PATH = "/proc/stat";
    private static final String PROCESS_FILE_PATH = "/proc";
    public static String getProduct() {
        return Build.PRODUCT;
    }

    public static String getBrand() {
        return Build.BRAND;
    }

    public static String getManufacturer() {
        return Build.MANUFACTURER;
    }

    public static String getHardWare() {
        return Build.HARDWARE;
    }

    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }


    public static String getModel() {
        return Build.MODEL;
    }


    public static String getAndroidID(Context context) {
        try {
            return Settings.System.getString(context.getContentResolver(), Settings.System.ANDROID_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    @SuppressLint("MissingPermission")
    private static String getIMEI(Context context) {
        try {
            TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            if (telephonyManager != null) {
                return telephonyManager.getDeviceId();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";

    }

    /**
     * @return cpu架构
     */
    public static String getCpuAbi() {
        return Build.CPU_ABI;
    }

    /**
     * @return 该设备支持哪些平台的so, String[]中的第一个是该设备最先选择的so, arm64-v8a设备依次从arm64-v8a，armeabi-v7a，armeabi寻找s；armeabi-v7a依次从armeabi-v7a，armeabi中寻找so
     */
    public static String[] getSupportedAbis() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            return Build.SUPPORTED_ABIS;
        }
        return null;
    }


    /**
     * @return cpu核心数
     */
    public static int getNumberOfCPUCores() {
        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.GINGERBREAD_MR1) {
            return 1;
        }
        int cores;
        try {
            cores = new File(CPU_FILE_PATH).listFiles(CPU_FILTER).length;
        } catch (Exception e) {
            cores = 0;
        }
        return cores;
    }

    private static final FileFilter CPU_FILTER = new FileFilter() {
        @Override
        public boolean accept(File pathname) {
            String path = pathname.getName();
            if (path.startsWith("cpu")) {
                for (int i = 3; i < path.length(); i++) {
                    if (path.charAt(i) < '0' || path.charAt(i) > '9') {
                        return false;
                    }
                }
                return true;
            }
            return false;
        }
    };

    /**
     * @param coreIndex cpu哪个核心
     * @return cpu某个核的当前主频，单位KHZ
     */
    public static int getCpuCurrentFrequency(int coreIndex) {
        if (coreIndex >= 0 && coreIndex <= getNumberOfCPUCores() - 1 && coreIndex >= 0) {
            int frequency = 0;
            FileReader fr = null;
            BufferedReader br = null;
            try {
                fr = new FileReader(CPU_FILE_PATH + "cpu" + coreIndex + CPU_CUR_FREQ_FILE_PATH);
                br = new BufferedReader(fr);
                String text = br.readLine();
                frequency = Integer.parseInt(text.trim());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fr != null)
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (br != null)
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return frequency;
        }
        return 0;

    }

    /**
     * @param coreIndex cpu的哪个核心
     * @return cpu的某个核可提供的最大频率，单位KHZ
     */
    public static int getCpuMaxFrequency(int coreIndex) {
        if (coreIndex >= 0 && coreIndex <= getNumberOfCPUCores() - 1 && coreIndex >= 0) {
            int frequency = 0;
            FileReader fr = null;
            BufferedReader br = null;
            try {
                fr = new FileReader(CPU_FILE_PATH + "cpu" + coreIndex + CPU_MAX_FREQ_FILE_PATH);
                br = new BufferedReader(fr);
                String text = br.readLine();
                frequency = Integer.parseInt(text.trim());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fr != null)
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (br != null)
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return frequency;
        }
        return 0;
    }

    /**
     * @param coreIndex cpu的哪个核
     * @return cpu核心能提供的最小频率，单位KHZ
     */
    public static int getCpuMinFrequency(int coreIndex) {
        if (coreIndex >= 0 && coreIndex <= getNumberOfCPUCores() - 1 && coreIndex >= 0) {
            int frequency = 0;
            FileReader fr = null;
            BufferedReader br = null;
            try {
                fr = new FileReader(CPU_FILE_PATH + "cpu" + coreIndex + CPU_MIN_FREQ_FILE_PATH);
                br = new BufferedReader(fr);
                String text = br.readLine();
                frequency = Integer.parseInt(text.trim());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (fr != null)
                    try {
                        fr.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                if (br != null)
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
            }
            return frequency;
        }
        return 0;

    }


    /**
     * 从cpu全部的核心中选择最大主频最大的那个核心（每个核心的最大主频可能不同）
     *
     * @return cpu最大的频率，单位KHZ
     */
    public static int getCPUMaxFreq() {
        int maxFreq = 0;
        try {
            for (int i = 0; i < getNumberOfCPUCores(); i++) {
                String filename = CPU_FILE_PATH + "cpu" + i + CPU_MAX_FREQ_FILE_PATH;
                File cpuInfoMaxFreqFile = new File(filename);
                if (cpuInfoMaxFreqFile.exists()) {
                    byte[] buffer = new byte[128];
                    FileInputStream stream = new FileInputStream(cpuInfoMaxFreqFile);
                    try {
                        stream.read(buffer);
                        int endIndex = 0;
                        while (buffer[endIndex] >= '0' && buffer[endIndex] <= '9' && endIndex < buffer.length)
                            endIndex++;
                        String str = new String(buffer, 0, endIndex);
                        Integer freqBound = Integer.parseInt(str);
                        if (freqBound > maxFreq) maxFreq = freqBound;
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        stream.close();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxFreq;
    }

    /**
     * @param coreIndex cpu的哪个核心
     * @return cpu该核心能提供的频率值
     */
    public static List<Long> getCpuAvailableFrequencies(int coreIndex) {
        if (getNumberOfCPUCores() > 0 && coreIndex <= getNumberOfCPUCores() - 1 && coreIndex >= 0) {
            List<Long> list = null;
            try {
                String line;
                BufferedReader br = new BufferedReader(new FileReader(CPU_FILE_PATH + "cpu" + coreIndex + CPU_AVAILABLE_FREQS_FILE_PATH));
                if ((line = br.readLine()) != null) {
                    String[] availableFreqs = line.split(" ");
                    if (availableFreqs != null) {
                        int length = availableFreqs.length;
                        if (length > 0) {
                            list = new ArrayList<>(length);
                            for (int i = 0; i < length; i++) {
                                long value = Long.parseLong(availableFreqs[i]);
                                list.add(value);
                            }
                        }
                    }
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return list;
        }

        return null;

    }

    /**
     * @param coreIndex cpu的哪个核心
     * @return 该核心当前的调频策略
     */
    public static String getCpuGovernor(int coreIndex) {
        if (getNumberOfCPUCores() > 0 && coreIndex <= getNumberOfCPUCores() - 1 && coreIndex >= 0) {
            String result = null;
            try {
                String line;
                BufferedReader br = new BufferedReader(new FileReader(CPU_FILE_PATH + "cpu" + coreIndex + CPU_GOVERNOR_FILE_PATH));
                if ((line = br.readLine()) != null) {
                    result = line;
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
        return null;

    }

    /**
     * @param coreIndex 哪个核心
     * @return CPU该核心支持的调频策略
     */
    public static String[] getCpuAvailableGovernors(int coreIndex) {
        if (getNumberOfCPUCores() > 0 && coreIndex <= getNumberOfCPUCores() - 1 && coreIndex >= 0) {
            String[] governors = null;
            try {
                String line;
                BufferedReader br = new BufferedReader(new FileReader(CPU_FILE_PATH + "cpu" + coreIndex + CPU_AVAILABLE_GOVERNORS_FILE_PATH));
                if ((line = br.readLine()) != null) {
                    governors = line.split(" ");
                }
                br.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return governors;
        }
        return null;
    }

    /**
     * 获取cpu在某段时间内的总体使用率
     *
     * @return cpu的总体使用率
     */
    public static float getCpuUsagePercent() {
        String[] cpuStat1 = getCpuStat();
        long cpuTotal1 = 0;
        long idle1 = 0;
        if (cpuStat1 != null) {
            int length = cpuStat1.length;
            if (length > 0) {
                idle1 = Long.parseLong(cpuStat1[5]);
                for (int i = 2; i < length; i++) {
                    cpuTotal1 = cpuTotal1 + Long.parseLong(cpuStat1[i]);
                }
            }
        }
        try {
            Thread.sleep(200);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String[] cpuStat2 = getCpuStat();
        long cpuTotal2 = 0;
        long idle2 = 0;
        if (cpuStat2 != null) {
            int length = cpuStat2.length;
            if (length > 0) {
                idle2 = Long.parseLong(cpuStat2[5]);
                for (int i = 2; i < length; i++) {
                    cpuTotal2 = cpuTotal2 + Long.parseLong(cpuStat2[i]);
                }
            }
        }
        return 100 * ((cpuTotal2 - idle2) - (cpuTotal1 - idle1)) / (cpuTotal2 - cpuTotal1);
    }


    private static String[] getCpuStat() {
        String[] cpuStat = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(CPU_STATE_FILE_PATH)));
            String load = br.readLine();
            br.close();
            cpuStat = load.split(" ");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cpuStat;
    }

    /**
     * @param pid 进程pid
     * @return 该进程在某段时间内的cpu占用率
     */
    public static float getCpuUsagePercentByProcess(int pid) {
        String[] cpuStat1 = getCpuStat();
        String[] processCpuStat1 = getProcessCpuStat(pid);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String[] cpuStat2 = getCpuStat();
        String[] processCpuStat2 = getProcessCpuStat(pid);
        long cpuTotal1 = 0;
        if (cpuStat1 != null) {
            int length = cpuStat1.length;
            if (length > 0) {
                for (int i = 2; i < length; i++) {
                    cpuTotal1 = cpuTotal1 + Long.parseLong(cpuStat1[i]);
                }
            }
        }
        long cpuTotal2 = 0;
        if (cpuStat2 != null) {
            int length = cpuStat2.length;
            if (length > 0) {
                for (int i = 2; i < length; i++) {
                    cpuTotal2 = cpuTotal2 + Long.parseLong(cpuStat2[i]);
                }
            }
        }

        long processCpuTotal1 = 0;
        if (processCpuStat1 != null && processCpuStat1.length > 15) {
            processCpuTotal1 = Long.parseLong(processCpuStat1[13]) + Long.parseLong(processCpuStat1[14]);
        }
        long processCpuTotal2 = 0;
        if (processCpuStat2 != null && processCpuStat2.length > 15) {
            processCpuTotal2 = Long.parseLong(processCpuStat2[13]) + Long.parseLong(processCpuStat2[14]);
        }
        if (cpuTotal2-cpuTotal1>0) {
            return 100 * (processCpuTotal2 - processCpuTotal1) / (cpuTotal2 - cpuTotal1);
        }
        return -1;


    }

    private static String[] getProcessCpuStat(int pid) {
        if (isPidAvailable(pid)) {
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/proc/" + pid + "/stat")));
                String load = br.readLine();
                br.close();
                String[] cpuStat = load.split(" ");
                for (String each:cpuStat) {
                    LogUtil.log(TAG,each);
                }
                return cpuStat;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return null;
    }

    /**
     * @param pid 进程pid
     * @return pid在系统中是否存在
     */
    public static boolean isPidAvailable(int pid) {
        List pidList = getAllRunningProcessesId();
        if (pidList != null && pidList.size() > 0) {
            return pidList.contains(pid);
        }
        return false;
    }

    /**
     *
     * @return 系统中所有运行进程的pid
     */
    public static List<Integer> getAllRunningProcessesId() {
        File[] files = new File(PROCESS_FILE_PATH).listFiles();
        List<Integer> pidList = new ArrayList<>();
        for (File file : files) {
            if (file.isDirectory()) {
                try {
                    int pid = Integer.parseInt(file.getName());
                    pidList.add(pid);
                } catch (NumberFormatException e) {
                    continue;
                }
            }
        }
        return pidList;
    }


    /**
     *
     * @return 当前进程在某段时间内的cpu占用率
     */
    public static float getCpuUsagePercentByCurPro() {
        return getCpuUsagePercentByProcess(android.os.Process.myPid());

    }

    /**
     * @param context
     * @return 系统当前可用的运行内存，单位B
     */
    public static long getAvailableRamMemory(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(outInfo);
            long availMem = outInfo.availMem;
            return availMem;
        }
        return 0;
    }

    /**
     * @param context
     * @return 系统总共的运行内存，单位为B
     */
    public static long getTotalRamMemory(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(outInfo);
            long totalMem = outInfo.totalMem;
            return totalMem;
        }
        return 0;

    }

    /**
     * @param context
     * @return 系统可运行内存是否到达了阈值
     */
    public static boolean isRamLowMemory(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(outInfo);
            return outInfo.lowMemory;
        }
        return false;

    }

    /**
     * @param context
     * @return 系统运行内存不足的阀值，即临界值。单位B
     */
    public static long getRamThresholdMemory(Context context) {
        if (context != null) {
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            ActivityManager.MemoryInfo outInfo = new ActivityManager.MemoryInfo();
            am.getMemoryInfo(outInfo);
            long threshold = outInfo.threshold;
            return threshold;
        }
        return 0;

    }

    /**
     * @return JVM能从操作系统申请到的最大堆内存，单位B。在没有设置-Xmx参数的情况下，通常为64M（低端手机）、128M（高端手机）
     */
    public static long getMaxHeapMemory() {
        return Runtime.getRuntime().maxMemory();
    }

    /**
     * @return JVM进程当前占用的所有堆内存(应用程序已经申请到的堆内存)，单位B。
     * 在程序使用过程中totalMemory会变化的，随用随时申请，最大值为totalSize
     */
    public static long getTotalHeapMemory() {
        return Runtime.getRuntime().totalMemory();
    }

    /**
     * @return 应用程序进程在申请内存时总会多申请一点，freeMemory是没有用到的内存，freeMemory通常都很小，单位B。
     * totalMemory-freeMemory是应用程序实际使用的内存。
     */
    public static long getFreeHeapMemory() {
        return Runtime.getRuntime().freeMemory();
    }

    /**
     * @return 应用程序当前的堆内存使用率
     */
    public static int getMemoryUsagePercent() {
        long totalSize = getMaxHeapMemory() >> 10;
        long totalMemory = getTotalHeapMemory() >> 10;
        long freeMemory = getFreeHeapMemory() >> 10;
        long memoryUsage = (totalMemory - freeMemory);
        int memoryUsagePercent = (int) (memoryUsage * 100 / totalSize);
        return memoryUsagePercent;
    }

    /**
     * @return 磁盘BlockSize，单位B
     */
    public static long getSdCardBlockSize() {
        File sdCardRoot = Environment.getExternalStorageDirectory();
        return getDirBlockSize(sdCardRoot.getPath());
    }

    /**
     * @return 磁盘总容量，单位B
     */
    public static long getSdCardTotalSize() {
        File sdCardRoot = Environment.getExternalStorageDirectory();
        return getDirTotalSize(sdCardRoot.getPath());
    }

    /**
     * @return 磁盘可用容量，单位B
     */
    public static long getSdCardAvailableSize() {
        File sdCardRoot = Environment.getExternalStorageDirectory();
        return getDirAvailableSize(sdCardRoot.getPath());
    }

    private static long getDirBlockSize(String dirPath) {
        if (!TextUtils.isEmpty(dirPath)) {
            StatFs sdCardStatFs = new StatFs(dirPath);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                return sdCardStatFs.getBlockSizeLong();
            } else {
                return sdCardStatFs.getBlockSize();
            }
        }
        return 0;

    }

    private static long getDirTotalSize(String dirPath) {
        if (!TextUtils.isEmpty(dirPath)) {
            StatFs sdCardStatFs = new StatFs(dirPath);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                return sdCardStatFs.getBlockCountLong() * getSdCardBlockSize();
            } else {
                return sdCardStatFs.getBlockCount() * getSdCardBlockSize();
            }
        }
        return 0;

    }

    private static long getDirAvailableSize(String dirPath) {
        if (!TextUtils.isEmpty(dirPath)) {
            StatFs sdCardStatFs = new StatFs(dirPath);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2) {
                return sdCardStatFs.getAvailableBlocksLong() * getSdCardBlockSize();
            } else {
                return sdCardStatFs.getAvailableBlocks() * getSdCardBlockSize();
            }
        }

        return 0;

    }


    /**
     * @return 获取分辨率
     */
    public static String getResolution(Activity activity) {
        if (activity != null) {
            WindowManager windowManager = activity.getWindowManager();
            Display display = windowManager.getDefaultDisplay();
            int screenWidth = display.getWidth();
            int screenHeight = display.getHeight();
            return screenWidth + "*" + screenHeight;
        }
        return null;

    }


}

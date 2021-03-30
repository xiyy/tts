package com.xi.liuliu.tts.util;

import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

/**
 * Created by Administrator on 2020/4/17.
 */

public class AudioUtil {
    private static final String TAG = AudioUtil.class.getSimpleName();
    private static int mOutputStreamSize;
    private static ByteArrayOutputStream[] mByteArrayOutputStreams;
    private static boolean isOutputStreamInitialized = false;
    public static final int SAMPLE_RATE_16K = 16000;
    public static final int SAMPLE_RATE_8K = 8000;

    public static String wavToPcm(String wavFilePath, String pcmFilePath) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(wavFilePath);
            fileOutputStream = new FileOutputStream(pcmFilePath);
            byte[] wavByte = InputStreamToByte(fileInputStream);
            byte[] pcmByte = Arrays.copyOfRange(wavByte, 44, wavByte.length);
            fileOutputStream.write(pcmByte);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream!=null) {
                    fileInputStream.close();
                }
                if (fileOutputStream!=null) {
                    fileOutputStream.close();
                }
            }catch (Exception e) {

            }


        }
        return pcmFilePath;
    }

    public static String pcmToWav(String pcmFilePath, String wavFilePath, int targetSampleRate, int targetChannels) {
        FileInputStream fileInputStream = null;
        FileOutputStream fileOutputStream = null;
        try {
            fileInputStream = new FileInputStream(pcmFilePath);
            fileOutputStream = new FileOutputStream(wavFilePath);
            byte[] pcmByte = InputStreamToByte(fileInputStream);
            WavUtil.constructWav(fileOutputStream, pcmByte, targetSampleRate, targetChannels);
            fileInputStream.close();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                if (fileInputStream!=null) {
                    fileInputStream.close();
                }
                if (fileOutputStream!=null) {
                    fileOutputStream.close();
                }
            }catch (Exception e) {

            }


        }
        return wavFilePath;
    }

    /**
     * 输入流转byte二进制数据
     *
     * @param fis
     * @return
     * @throws IOException
     */
    private static byte[] InputStreamToByte(FileInputStream fis) throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        long size = fis.getChannel().size();
        byte[] buffer = null;
        if (size <= Integer.MAX_VALUE) {
            buffer = new byte[(int) size];
        } else {
            buffer = new byte[8];
            for (int ix = 0; ix < 8; ++ix) {
                int offset = 64 - (ix + 1) * 8;
                buffer[ix] = (byte) ((size >> offset) & 0xff);
            }
        }
        int len;
        while ((len = fis.read(buffer)) != -1) {
            byteStream.write(buffer, 0, len);
        }
        byte[] data = byteStream.toByteArray();
        byteStream.close();
        return data;
    }

    public static byte[] convert16KTo8K(byte[] bytes) {
        if (bytes == null || bytes.length == 0) return null;
        byte[] resultBytes = new byte[bytes.length / 2];
        int i = 0;
        while (i + 4 < bytes.length) {
            for (int j = 0; j < 2; j++) {
                resultBytes[2 * (i / 4) + j] = bytes[i + j];
            }
            i = i + 4;
        }
        return resultBytes;
    }

    /**
     * @param buffer
     * @return 分贝值（相对分贝，并不是物理分贝，计算出的分贝值正常值域为0 dB 到90.3 dB）
     */
    public static double getVoiceDecibel(short[] buffer) {
        if (buffer != null) {
            int length = buffer.length;
            if (length > 0) {
                long sum = 0;
                for (int i = 0; i < length; i++) {
                    sum += buffer[i] * buffer[i];
                }
                // 平方和除以数据总长度，得到音量大小。
                double mean = sum / (double) length;
                if (mean > 0) {
                    double volume = 10 * Math.log10(mean);
                    return volume;
                }

            }

        }
        return 0;
    }

    /**
     * 初始化输出流数组
     *
     * @param outputStreamSize 输出流数组大小
     */
    public static void initOutputStreamSize(int outputStreamSize) {
        if (isOutputStreamInitialized) {
            return;
        } else {
            if (outputStreamSize > 0) {
                mOutputStreamSize = outputStreamSize;
            } else {
                throw new IllegalArgumentException("outputStreamSize must > 0");
            }
            mByteArrayOutputStreams = new ByteArrayOutputStream[outputStreamSize];
            isOutputStreamInitialized = true;

        }
    }

    /**
     * 缓存音频数据到outputStream中
     *
     * @param outputStreamIndex 指定哪个输出流
     * @param data              音频数据
     */
    public static synchronized void storeDataToStream(int outputStreamIndex, short[] data) {
        if (!isOutputStreamInitialized) {
            throw new IllegalStateException("未调用initOutputStreamSize进行初始化");
        }
        if (outputStreamIndex >= 0 && outputStreamIndex < mOutputStreamSize) {
            if (data == null || data.length == 0)
                throw new IllegalArgumentException("AudioSaver # empty audio data");
            ByteArrayOutputStream byteArrayOutputStream = mByteArrayOutputStreams[outputStreamIndex];
            if (byteArrayOutputStream != null) {
                byte[] byteData = new byte[data.length * 2];
                ByteBuffer.wrap(byteData).order(ByteOrder.nativeOrder()).asShortBuffer().put(data);
                try {
                    byteArrayOutputStream.write(byteData);
                    byteArrayOutputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.logError(TAG, "storeDataToStream Exception");
                }
                finally {
                    try {
                        byteArrayOutputStream.close();
                    }catch (Exception e) {

                    }

                }
            }
        }

    }


    public static synchronized void storeDataToStream(int outputStreamIndex, byte[] data) {
        if (!isOutputStreamInitialized) {
            throw new IllegalStateException("未调用initOutputStreamSize进行初始化");
        }
        if (outputStreamIndex >= 0 && outputStreamIndex < mOutputStreamSize) {
            if (data == null || data.length == 0)
                throw new IllegalArgumentException("AudioSaver # empty audio data");
            ByteArrayOutputStream byteArrayOutputStream = mByteArrayOutputStreams[outputStreamIndex];
            if (byteArrayOutputStream != null) {
                try {
                    byteArrayOutputStream.write(data);
                    byteArrayOutputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.logError(TAG, "storeDataToStream Exception");
                }
                finally {
                    try {
                        byteArrayOutputStream.close();
                    }catch (Exception e) {

                    }

                }
            }
        }
    }


    public static synchronized String storeWav(int outputStreamIndex, String wavPath) {
        if (!isOutputStreamInitialized) {
            throw new IllegalStateException("未调用initOutputStreamSize进行初始化");
        }
        if (!TextUtils.isEmpty(wavPath)) {
            if (!wavPath.endsWith(".wav")) {
                throw new IllegalArgumentException("wavPath does not contain .wav");
            }
        } else {
            throw new IllegalArgumentException("wavPath is empty");
        }
        if (outputStreamIndex >= 0 && outputStreamIndex < mOutputStreamSize) {
            ByteArrayOutputStream byteArrayOutputStream = mByteArrayOutputStreams[outputStreamIndex];
            if (byteArrayOutputStream != null) {
                FileUtil.createNewFile(wavPath);
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File(wavPath));
                    byte[] byteData = byteArrayOutputStream.toByteArray();
                    WavUtil.constructWav(fileOutputStream, byteData, 16000, 1);

                    return wavPath;
                } catch (Exception e) {
                    e.printStackTrace();
                    LogUtil.logError(TAG, "storeWav() Exception");
                }
                finally {
                    try {
                        if (fileOutputStream != null) {
                            fileOutputStream.close();
                        }
                        byteArrayOutputStream.close();
                    }catch (Exception e) {

                    }
                }

            }
        }
        return null;

    }


    public static synchronized String storePcm(int outputStreamIndex, String pcmPath) {
        return storePcm(outputStreamIndex, SAMPLE_RATE_16K, pcmPath);
    }

    /**
     * @param outputStreamIndex 哪个输出流
     * @param sampleRate        采样率
     * @param pcmPath           路径
     * @return 路径
     */
    public static synchronized String storePcm(int outputStreamIndex, int sampleRate, String pcmPath) {
        if (!isOutputStreamInitialized) {
            throw new IllegalStateException("未调用initOutputStreamSize进行初始化");
        }
        if (!TextUtils.isEmpty(pcmPath)) {
            if (!pcmPath.endsWith(".pcm")) {
                throw new IllegalArgumentException("pcmPath does not contain .pcm");
            }
        } else {
            throw new IllegalArgumentException("pcmPath is empty");
        }
        if (outputStreamIndex >= 0 && outputStreamIndex < mOutputStreamSize) {
            ByteArrayOutputStream byteArrayOutputStream = mByteArrayOutputStreams[outputStreamIndex];
            if (sampleRate == SAMPLE_RATE_8K || sampleRate == SAMPLE_RATE_16K) {
                if (byteArrayOutputStream != null) {
                    FileUtil.createNewFile(pcmPath);
                    FileOutputStream fileOutputStream = null;
                    try {
                        fileOutputStream = new FileOutputStream(new File(pcmPath));
                        byte[] byteData = byteArrayOutputStream.toByteArray();
                        fileOutputStream.write(byteData);
                        return pcmPath;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    finally {
                        try {
                            if (fileOutputStream!=null) {
                                fileOutputStream.close();
                            }
                            byteArrayOutputStream.close();
                        }catch (Exception e) {

                        }
                    }
                }
            } else {
                throw new IllegalArgumentException("sampleRate不合法，只能8k或者16k");
            }
        }

        return null;
    }

    /**
     * @param outputStreamIndex 哪个输出流
     * @param filePath          文件的绝对路径，包括路径、文件名、文件格式
     * @return 文件的绝对路径
     */
    public static synchronized String storeFile(int outputStreamIndex, String filePath) {
        if (!isOutputStreamInitialized) {
            throw new IllegalStateException("未调用initOutputStreamSize进行初始化");
        }
        if (TextUtils.isEmpty(filePath)) {
            throw new IllegalArgumentException("filePath is empty");
        }
        if (outputStreamIndex >= 0 && outputStreamIndex < mOutputStreamSize) {
            ByteArrayOutputStream byteArrayOutputStream = mByteArrayOutputStreams[outputStreamIndex];
            if (byteArrayOutputStream != null) {
                FileUtil.createNewFile(filePath);
                FileOutputStream fileOutputStream = null;
                try {
                    fileOutputStream = new FileOutputStream(new File(filePath));
                    byte[] byteData = byteArrayOutputStream.toByteArray();
                    fileOutputStream.write(byteData);

                    return filePath;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                finally {
                    try {
                        if (fileOutputStream!=null) {
                            fileOutputStream.close();
                        }
                        byteArrayOutputStream.close();
                    }catch (Exception e) {

                    }
                }
            }
        }

        return null;
    }
}

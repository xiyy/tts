package com.xi.liuliu.tts.util;

import java.io.OutputStream;

/**
 * Created by Administrator on 2020/4/17.
 */

public class WavUtil {
    public static void constructWav(OutputStream outputStream, byte[] pcmData, int targetSampleRate, int targetChannels) {
        addWavHeader(outputStream, pcmData.length, targetSampleRate, targetChannels);
        try {
            outputStream.write(pcmData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param outputStream     输出流
     * @param pcmDataLength    pcm数据的字节数
     * @param targetSampleRate 采样率，16000
     * @param targetChannels   声道数，单声道1
     */
    private static void addWavHeader(OutputStream outputStream, int pcmDataLength, int targetSampleRate, int targetChannels) {
        addWavHeadChars(outputStream, "RIFF".toCharArray());//固定
        addWavHeadInt(outputStream, pcmDataLength + 44 - 8);//存储文件的字节数（不包含ChunkID和ChunkSize这8个字节）
        addWavHeadChars(outputStream, "WAVE".toCharArray());//固定
        addWavHeadChars(outputStream, "fmt".toCharArray());//固定
        addWavHeadByte(outputStream, (byte) 0x20);
        addWavHeadInt(outputStream, 0x10);
        addWavHeadShort(outputStream, (short) 0x01);//存储音频文件的编码格式，若为PCM则其存储值为1
        addWavHeadShort(outputStream, (short) targetChannels);//声道数
        addWavHeadInt(outputStream, targetSampleRate);//采样率
        addWavHeadInt(outputStream, targetSampleRate * 2);//码率，采样率*采样位数*声道数/8=16000*16*1/8
        addWavHeadShort(outputStream, (short) 2);///块对齐大小，声道数*采样位数/8 = 1*16/8=2
        addWavHeadShort(outputStream, (short) 16);//采样位数
        addWavHeadChars(outputStream, "data".toCharArray());//固定
        addWavHeadInt(outputStream, pcmDataLength);//音频数据长度
    }

    private static void addWavHeadInt(OutputStream outputStream, int value) {
        try {
            outputStream.write((value >> 0) & 0x000000ff);
            outputStream.write((value >> 8) & 0x000000ff);
            outputStream.write((value >> 16) & 0x000000ff);
            outputStream.write((value >> 24) & 0x000000ff);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addWavHeadByte(OutputStream outputStream, byte value) {
        try {
            outputStream.write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addWavHeadChar(OutputStream outputStream, char value) {
        try {
            outputStream.write(value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void addWavHeadChars(OutputStream outputStream, char[] chars) {
        for (char c : chars) {
            try {
                outputStream.write(c);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void addWavHeadShort(OutputStream outputStream, short value) {
        try {
            outputStream.write((value >> 0) & 0x000000ff);
            outputStream.write((value >> 8) & 0x000000ff);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

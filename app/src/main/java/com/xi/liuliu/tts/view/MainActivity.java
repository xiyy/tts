package com.xi.liuliu.tts.view;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.sogou.tts.offline.TTSPlayer;
import com.sogou.tts.offline.listener.TTSPlayerListener;
import com.xi.liuliu.tts.R;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    static final String TAG = MainActivity.class.getSimpleName();
     TTSPlayer ttsPlayer;
    private List<String> textList = new ArrayList<>(4);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.loadLibrary("ttsoff");
        textList.add("春眠不觉晓，处处闻啼鸟，夜来风雨声，花落知多少。hello ,what's the weather like today?");

        ttsPlayer = new TTSPlayer(false);
        int initResult = ttsPlayer.initFromAssets(this.getApplicationContext(),new TTSEventListener(),"dict.dat","snd-zsh.dat");
        if (initResult>=0) {
            ttsPlayer.setStreamType(TTSPlayer.STREAM_MUSIC);
            ttsPlayer.setSpeed(4);
            ttsPlayer.play(textList,"");
        }
    }

     class TTSEventListener implements TTSPlayerListener {

        @Override
        public void onInit(boolean b) {
            Log.d(TAG,"onInit,result:"+b);
        }

        @Override
        public void onStart() {
            Log.d(TAG,"onStart");
        }

        @Override
        public void onSpeakProgress(Float aFloat) {
            Log.d(TAG,"onSpeakProgress,"+aFloat);
        }

        @Override
        public void onSpeakSentenceProgress(List<String> list, int i) {
            Log.d(TAG,"onSpeakSentenceProgress,currentSentence:"+list.get(i));
        }

        @Override
        public void onSegSyn(byte[] bytes) {
            Log.d(TAG,"onSegSyn,bytes:"+bytes);
        }

        @Override
        public void onEnd() {
            Log.d(TAG,"onEnd");
        }

        @Override
        public void onError(int i) {
            Log.e(TAG,"onError,i:"+i);
        }

        @Override
        public void onPause() {
            Log.d(TAG,"onPause");
        }

        @Override
        public void onSynEnd(Float aFloat) {
            Log.d(TAG,"onSynEnd,"+aFloat);
        }

        @Override
        public void onRelease(boolean b) {
            Log.d(TAG,"onRelease,"+b);
        }
    }
}
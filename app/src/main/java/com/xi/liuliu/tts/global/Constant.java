package com.xi.liuliu.tts.global;


import java.util.HashMap;

/**
 * 考点1：Java接口  2：HashMap原理，HashMap与HashTable、ConcurrentHashMap、LinkedHashMap区别
 */
public interface Constant {
    //第一层括弧实际是定义了一个匿名内部类 (Anonymous Inner Class)，第二层括弧实际上是一个实例初始化块 (instance initializer block)，这个块在内部匿名类构造时被执行
    HashMap<String, String> soundModel = new HashMap<String, String>(8) {
        {

            put("姗姗","snd-f24");
            put("云云","snd-lhy");
            put("甜甜","snd-yzh");
            put("萌萌","snd-zj");
            put("兰兰","snd-zsh");
            put("龙龙","snd-ybh");
            put("磊磊","snd-zjc");
        }
    };


}

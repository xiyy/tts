package com.xi.liuliu.tts.global;


import com.xi.liuliu.tts.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 考点1：Java接口  2：HashMap原理，HashMap与HashTable、ConcurrentHashMap、LinkedHashMap区别 3、范型
 */
public interface Constant {
    //第一层括弧实际是定义了一个匿名内部类 (Anonymous Inner Class)，第二层括弧实际上是一个实例初始化块 (instance initializer block)，这个块在内部匿名类构造时被执行
    HashMap<String, String> soundModelMap = new HashMap<String, String>(8) {
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
    List<String> ttsCategoryList = new ArrayList<String>() {
        {
            add("美食餐饮");
            add("家电卖场");
            add("教育培训");
            add("地产家居");
            add("医疗保健");
            add("节日活动");
            add("手机通讯");
            add("黄金珠宝");
        }
    };
    List<Integer> ttsCategoryDrawableList = new ArrayList<Integer>() {
        {
            add(R.drawable.make_fragment_category_1);
            add(R.drawable.make_fragment_category_2);
            add(R.drawable.make_fragment_category_3);
            add(R.drawable.make_fragment_category_4);
            add(R.drawable.make_fragment_category_5);
            add(R.drawable.make_fragment_category_6);
            add(R.drawable.make_fragment_category_7);
            add(R.drawable.make_fragment_category_8);

        }

    };

    String KEY_USER_PROTOCOL = "key_user_protocol";


}

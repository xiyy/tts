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


    List<String> soundModelNameList = new ArrayList<String>() {
        {
            add("姗姗");
            add("云云");
            add("甜甜");
            add("萌萌");
            add("兰兰");
            add("龙龙");
            add("磊磊");
        }
    };
    List<Integer> soundModelDrawableList = new ArrayList<Integer>() {
        {
            add(R.drawable.model_women_lan);
            add(R.drawable.model_women_meng);
            add(R.drawable.model_women_shan);
            add(R.drawable.model_women_tian);
            add(R.drawable.model_women_yun);
            add(R.drawable.model_man_lei);
            add(R.drawable.model_man_long);

        }

    };

    List<String> categoryExampleBgmList = new ArrayList<String>() {
        {
            add("背景音乐1");
            add("背景音乐2");
            add("背景音乐3");
            add("背景音乐4");
            add("背景音乐5");
            add("背景音乐6");
            add("背景音乐7");
        }
    };

    //餐饮美食列表
    List<String> categoryDelicacyList = new ArrayList<String>() {
        {
            add("烧烤广告");
            add("新疆美食");
            add("奶茶店");
            add("火锅店");
            add("久久鸭脖");
            add("重庆小面");
            add("冰糖雪梨");
        }
    };

    List<String> categoryMarketList = new ArrayList<String>() {
        {
            add("苏宁家电特价风暴");
            add("电器家具周年庆");
            add("欧派智能厨房电器");
            add("格力空调");
            add("美的电器");
            add("联想电脑");
            add("华为手机");
        }
    };

    List<String> categoryTrainList = new ArrayList<String>() {
        {
            add("驾校招生");
            add("幼儿园招生");
            add("美术培训");
            add("文博教育");
            add("微云琴行");
            add("私塾暑假培训");
            add("桃子美术");
        }
    };
    List<String> categoryHouseList = new ArrayList<String>() {
        {
            add("房屋中介广告");
            add("全有家居广告");
            add("宏大门业广告");
            add("衣柜广告");
            add("床单被罩清仓处理");
            add("家纺十一促销");
            add("学区房");
        }
    };
    List<String> categoryMedicalList = new ArrayList<String>() {
        {
            add("大药房广告");
            add("母婴店宣传");
            add("药房新店宣传");
            add("减肥机构宣传");
            add("美容院广告");
            add("诚信大药房");
            add("医疗器械甩卖");
        }
    };
    List<String> categoryFestivalList = new ArrayList<String>() {
        {
            add("春节年货促销");
            add("超市年货宣传");
            add("圣诞节运营");
            add("七夕红豆家纺");
            add("端午品牌让利");
            add("中秋月饼");
            add("国庆超市促销");
        }
    };
    List<String> categoryPhoneList = new ArrayList<String>() {
        {
            add("手机商城宣传");
            add("手机品牌连锁店广告");
            add("苏宁手机广场");
            add("宽带限时优化办理");
            add("有限电视免初装费");
            add("路由器特惠");
            add("老年手机清仓处理");
        }
    };
    List<String> categoryJewelryList = new ArrayList<String>() {
        {
            add("老凤祥珠宝周年庆");
            add("周大生店庆");
            add("金六福以旧换新");
            add("老庙黄金促销");
            add("饰品店广告");
            add("七夕珠宝店广告");
            add("纯银饰品45元两件");
        }
    };

}

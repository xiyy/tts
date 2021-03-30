package com.xi.liuliu.tts.view;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.xi.liuliu.tts.R;
import com.xi.liuliu.tts.adapter.CategoryExampleAdapter;
import com.xi.liuliu.tts.global.Constant;

import java.util.List;

public class CategoryListActivity extends Activity implements View.OnClickListener {
    private Button mBackBtn;
    private TextView mCategoryTitleTv;
    private RecyclerView mListRv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        init();

    }

    private void init() {
        mBackBtn = findViewById(R.id.cla_btn_go_back);
        mBackBtn.setOnClickListener(this);
        mCategoryTitleTv = findViewById(R.id.cla_tv_title);
        int position = getIntent().getIntExtra(Constant.KEY_CATEGORY_POSITION,0);
        mCategoryTitleTv.setText(Constant.ttsCategoryList.get(position));
        mListRv = findViewById(R.id.cla_rv_list);
        mListRv.setLayoutManager(new LinearLayoutManager(this));
        CategoryExampleAdapter categoryExampleAdapter = new CategoryExampleAdapter(getCategoryExampleList(position));
        mListRv.setAdapter(categoryExampleAdapter);

    }

    @Override
    public void onClick(View v) {
        finish();
    }

    private List<String> getCategoryExampleList(int position) {
        List<String> list = Constant.categoryDelicacyList;
        switch (position) {
            case 0:
                list = Constant.categoryDelicacyList;
                break;
            case 1:
                list = Constant.categoryMarketList;
                break;
            case 2:
                list = Constant.categoryTrainList;
                break;
            case 3:
                list = Constant.categoryHouseList;
                break;
            case 4:
                list = Constant.categoryMedicalList;
                break;
            case 5:
                list = Constant.categoryFestivalList;
                break;
            case 6:
                list = Constant.categoryPhoneList;
                break;
            case 7:
                list = Constant.categoryJewelryList;
                break;
        }
        return list;
    }
}
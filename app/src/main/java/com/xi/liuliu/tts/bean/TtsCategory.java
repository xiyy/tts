package com.xi.liuliu.tts.bean;

public class TtsCategory {
    private String mCategoryName;
    private int mCategoryDrawableId;

    public TtsCategory(String categoryName, int categoryDrawableId) {
        mCategoryName = categoryName;
        mCategoryDrawableId = categoryDrawableId;
    }

    public String getCategoryName() {
        return mCategoryName;
    }

    public void setCategoryName(String categoryName) {
        this.mCategoryName = categoryName;
    }

    public int getCategoryDrawableId() {
        return mCategoryDrawableId;
    }

    public void setCategoryDrawableId(int categoryDrawableId) {
        this.mCategoryDrawableId = mCategoryDrawableId;
    }
}

package com.xi.liuliu.tts.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xi.liuliu.tts.R;
import com.xi.liuliu.tts.global.Constant;
import com.xi.liuliu.tts.impl.OnItemClickListener;

public class TtsCategoryItemAdapter extends RecyclerView.Adapter implements View.OnClickListener {
    static final int mCount = Constant.ttsCategoryList.size();
    private OnItemClickListener mOnItemClickListener;

    public TtsCategoryItemAdapter(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tts_category, parent, false);
        view.setOnClickListener(this);
        CategoryItemViewHolder categoryItemViewHolder = new CategoryItemViewHolder(view);
        categoryItemViewHolder.categoryIconIv = view.findViewById(R.id.item_tts_category_iv);
        categoryItemViewHolder.categoryNameTv = view.findViewById(R.id.item_tts_category_tv);
        categoryItemViewHolder.toMakeBtn = view.findViewById(R.id.item_tts_category_bt);
        return categoryItemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CategoryItemViewHolder categoryItemViewHolder = (CategoryItemViewHolder) holder;
        categoryItemViewHolder.categoryNameTv.setText(Constant.ttsCategoryList.get(position));
        categoryItemViewHolder.categoryIconIv.setImageResource(Constant.ttsCategoryDrawableList.get(position));
        categoryItemViewHolder.itemView.setTag(position);

    }

    @Override
    public int getItemCount() {
        return mCount;
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.OnItemClick(v, (int) v.getTag());
        }
    }

    static class CategoryItemViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryIconIv;
        TextView categoryNameTv;
        Button toMakeBtn;
        View itemView;

        public CategoryItemViewHolder(@NonNull View itemView) {
            super(itemView);
            this.itemView = itemView;
        }
    }


}

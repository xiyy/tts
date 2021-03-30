package com.xi.liuliu.tts.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xi.liuliu.tts.R;
import com.xi.liuliu.tts.global.Constant;

import java.util.List;

public class CategoryExampleAdapter extends RecyclerView.Adapter {
    private List<String> mCategoryExampleList;

    public CategoryExampleAdapter(List<String> list) {
        mCategoryExampleList = list;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_categoty_example, parent, false);
        ItemViewHolder itemViewHolder = new ItemViewHolder(view);
        itemViewHolder.portraitIv = view.findViewById(R.id.item_category_example_portrait_iv);
        itemViewHolder.titleTv = view.findViewById(R.id.item_category_example_title_tv);
        itemViewHolder.modelNameTv = view.findViewById(R.id.item_category_example_model_tv);
        itemViewHolder.bgmTv = view.findViewById(R.id.item_category_example_bgm_tv);
        itemViewHolder.uploadIv = view.findViewById(R.id.item_category_example_upload_iv);
        itemViewHolder.auditionIv = view.findViewById(R.id.item_category_example__audition_iv);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemViewHolder itemViewHolder = (ItemViewHolder)holder;
        itemViewHolder.portraitIv.setImageResource(Constant.soundModelDrawableList.get(position));
        itemViewHolder.modelNameTv.setText("配音人："+Constant.soundModelNameList.get(position));
        itemViewHolder.bgmTv.setText("音乐："+Constant.categoryExampleBgmList.get(position));
        itemViewHolder.titleTv.setText(mCategoryExampleList.get(position));
    }

    @Override
    public int getItemCount() {
        return Constant.soundModelDrawableList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder{
        ImageView portraitIv;
        TextView titleTv;
        TextView modelNameTv;
        TextView bgmTv;
        ImageView uploadIv;
        ImageView auditionIv;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}

package com.xi.liuliu.tts.view;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xi.liuliu.tts.R;
import com.xi.liuliu.tts.adapter.TtsCategoryItemAdapter;
import com.xi.liuliu.tts.impl.OnItemClickListener;
import com.xi.liuliu.tts.util.LogUtil;

public class MakeFragment extends Fragment implements View.OnClickListener, OnItemClickListener {
    private static final String TAG = MakeFragment.class.getSimpleName();
    private TextView mContactUsTv;
    private RelativeLayout mTtsRl;
    private RelativeLayout mOcrCameraRl;
    private RecyclerView mTtsCategoryRv;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigator_make, container, false);
        mContactUsTv = view.findViewById(R.id.mf_tv_contact_us);
        mContactUsTv.setOnClickListener(this);
        mTtsRl = view.findViewById(R.id.fnm_rl_tts);
        mTtsRl.setOnClickListener(this);
        mOcrCameraRl = view.findViewById(R.id.fnm_rl_ocr);
        mOcrCameraRl.setOnClickListener(this);
        mTtsCategoryRv = view.findViewById(R.id.fnm_rv_category_list);
        mTtsCategoryRv.setLayoutManager(new LinearLayoutManager(getActivity().getApplication()));
        TtsCategoryItemAdapter ttsCategoryItemAdapter = new TtsCategoryItemAdapter(this);
        mTtsCategoryRv.setAdapter(ttsCategoryItemAdapter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fnm_rl_tts:

                break;
            case R.id.fnm_rl_ocr:

                break;
            case R.id.mf_tv_contact_us:
                Intent intent = new Intent(getActivity(),ContactUsActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void OnItemClick(View view, int position) {
        LogUtil.log(TAG,"OnItemClick,position:"+position);
        Intent intent = new Intent(getActivity(),CategoryListActivity.class);
        intent.putExtra("categoryPosition",position);
        startActivity(intent);
    }
}

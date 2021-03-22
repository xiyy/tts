package com.xi.liuliu.tts.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.xi.liuliu.tts.R;

public class WorkFragment extends Fragment implements View.OnClickListener {
    private ImageView mGuideToRecordIv;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_navigator_work, container, false);
        mGuideToRecordIv = view.findViewById(R.id.wf_iv_no_record);
        mGuideToRecordIv.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.wf_iv_no_record:


                break;
        }
    }
}

package com.beike.sample.limengsample.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.activity.TestListActivity;

/**
 * 创建时间: 2018/08/29 09:15 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class TestFragment extends Fragment {
  public static final int REQUEST_CODE = 100;
  @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return inflater.inflate(R.layout.layout_relative, container, false);
  }

  @Override
  public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    ImageView ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
    ivIcon.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(TestFragment.this.getContext(), TestListActivity.class);
        getActivity().startActivityForResult(intent, REQUEST_CODE);
      }
    });
  }

  @Override
  public void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }
}

package com.beike.sample.limengsample.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beike.sample.limengsample.R;

/**
 * 创建时间: 2018/07/31 14:31 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class SpacerViewHolder extends RecyclerView.ViewHolder {
  public static SpacerViewHolder newInstance(ViewGroup parent) {
    View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_spacer_feed, parent, false);
    return new SpacerViewHolder(v);
  }

  public SpacerViewHolder(View itemView) {
    super(itemView);
  }
}

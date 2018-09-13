package com.beike.sample.limengsample.viewholder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beike.sample.limengsample.R;

/**
 * 创建时间: 2018/07/31 16:11 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class FeedNoPicViewHolder extends FeedBaseViewHolder {
  public static FeedNoPicViewHolder newInstance(Activity activity, ViewGroup parent) {
    View v = LayoutInflater.from(activity).inflate(R.layout.layout_no_pic_feed, parent, false);
    return new FeedNoPicViewHolder(v, activity);
  }

  public FeedNoPicViewHolder(View itemView, Activity activity) {
    super(itemView, activity);
  }
}

package com.beike.sample.limengsample.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.model.FeedBean;
import java.util.List;

/**
 * 创建时间: 2018/07/31 14:42 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class FeedSinPicViewHolder extends FeedBaseViewHolder {
  ImageView ivFeedPic;

  public static FeedSinPicViewHolder newInstance(Activity activity, ViewGroup parent) {
    View v = LayoutInflater.from(activity).inflate(R.layout.layout_sin_pic_feed, parent, false);
    return new FeedSinPicViewHolder(v, activity);
  }

  public FeedSinPicViewHolder(View itemView, Activity activity) {
    super(itemView, activity);
    ivFeedPic = (ImageView) itemView.findViewById(R.id.iv_feed_pic);
  }

  @Override
  public void updateView(@NonNull FeedBean feedBean) {
    super.updateView(feedBean);
    List<String> picList = feedBean.getPic();
    if (picList != null && picList.size() > 0) {
      String picUrl = picList.get(0);
      if (!TextUtils.isEmpty(picUrl)) {
        //LJImageLoader.getInstance().loadImage(picUrl, ivFeedPic, new ImageOptions().setType(
        //    ImageOptions.Type.ROUND_COMMON).setRoundRadius(3).setMaxMbSize(1));
      }
    }
  }
}

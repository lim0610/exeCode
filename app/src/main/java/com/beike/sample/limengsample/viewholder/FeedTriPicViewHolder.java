package com.beike.sample.limengsample.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.model.FeedBean;
import com.beike.sample.limengsample.utils.DensityUtil;
import com.beike.sample.limengsample.view.RoundImageView;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: 2018/07/31 14:35 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class FeedTriPicViewHolder extends FeedBaseViewHolder {
  int[] idArr = { R.id.iv_feed_pic_1, R.id.iv_feed_pic_2, R.id.iv_feed_pic_3 };
  List<RoundImageView> mPicViews;

  public static FeedTriPicViewHolder newInstance(Activity activity, ViewGroup parent) {
    View v = LayoutInflater.from(activity).inflate(R.layout.layout_tri_pic_feed, parent, false);
    return new FeedTriPicViewHolder(v, activity);
  }

  public FeedTriPicViewHolder(View itemView, Activity activity) {
    super(itemView, activity);
    mPicViews = new ArrayList<>();
    for (int id : idArr) {
      mPicViews.add((RoundImageView) itemView.findViewById(id));
    }
  }

  @Override
  public void updateView(@NonNull FeedBean feedBean) {
    super.updateView(feedBean);

    if (feedBean.getPic() != null && feedBean.getPic().size() >= 3) {
      int picWidth = calcPicWidth();
      int picHeight = picWidth > 0 ? picWidth * 3 / 4 : 0;

      for (int i = 0; i < mPicViews.size(); i++) {
        //LJImageLoader.getInstance()
        //    .loadImage(feedBean.getPic().get(i), mPicViews.get(i));
        if (picHeight > 0) {
          mPicViews.get(i).getLayoutParams().height = picHeight;
        }
      }
    }
  }

  private int calcPicWidth() {
    if (mActivity == null) {
      return 0;
    }
    return (DensityUtil.getScreenWidth(mActivity) - DensityUtil.dip2px(mActivity, 30) * 2) / 3;
  }
}

package com.beike.sample.limengsample.viewholder;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.beike.sample.limengsample.utils.DensityUtil;

/**
 * 创建时间: 2018/08/06 17:15 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class FeedItemDecoration extends RecyclerView.ItemDecoration {
  public static final int PADDING = 24;
  private Drawable mDivider;

  private Rect mBounds = new Rect();

  public FeedItemDecoration(Drawable divider) {
    mDivider = divider;
  }

  @Override
  public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
      RecyclerView.State state) {

    outRect.set(0, 0, 0, mDivider.getIntrinsicHeight());
  }

  @Override
  public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
    c.save();
    if (mDivider != null) {
      final int childCount = parent.getChildCount();
      for (int i = 0; i < childCount; i++) {
        final View child = parent.getChildAt(i);
        parent.getDecoratedBoundsWithMargins(child, mBounds);
        final int bottom = mBounds.bottom + Math.round(ViewCompat.getTranslationY(child));
        final int top = bottom - mDivider.getIntrinsicHeight();
        int padding = DensityUtil.dip2px(parent.getContext(), PADDING);
        mDivider.setBounds(padding, top, parent.getWidth() - padding, bottom);
        mDivider.draw(c);
      }
    }
    c.restore();
  }
}

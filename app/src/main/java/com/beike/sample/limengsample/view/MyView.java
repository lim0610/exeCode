package com.beike.sample.limengsample.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import com.beike.sample.limengsample.utils.ConstantUtil;

public class MyView extends View {
  public MyView(Context context) {
    super(context);
  }

  public MyView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override
  public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    Log.e(ConstantUtil.TAG_TEST, "MyView::onMeasure");
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  @Override
  public void onLayout(boolean changed, int left, int top, int right, int bottom) {
    Log.e(ConstantUtil.TAG_TEST, "MyView::onLayout");
    super.onLayout(changed, left, top, right, bottom);
  }

  @Override
  public void onDraw(Canvas canvas) {
    Log.e(ConstantUtil.TAG_TEST, "MyView::onDraw");
    super.onDraw(canvas);
  }
}

package com.beike.sample.limengsample.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ListView;
import com.beike.sample.limengsample.utils.ConstantUtil;

public class MyListView extends ListView {
  public MyListView(Context context) {
    super(context);
  }

  public MyListView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    Log.e(ConstantUtil.TAG_TEST, getName());
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
  }

  public String getName() {
    return getClass().getSimpleName() + "::" + Thread.currentThread().getStackTrace()[3].getMethodName();
  }
}

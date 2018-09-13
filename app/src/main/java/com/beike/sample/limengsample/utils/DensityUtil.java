package com.beike.sample.limengsample.utils;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;

/**
 * 创建时间: 2018/08/14 15:36 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class DensityUtil {
  public static int dip2px(Context context, float dpValue) {
    final float scale = context.getResources().getDisplayMetrics().density;
    return (int) (dpValue * scale + 0.5f);
  }

  public static int getScreenWidth(Activity activityContext) {
    DisplayMetrics dm = new DisplayMetrics();
    activityContext.getWindowManager().getDefaultDisplay().getMetrics(dm);
    return dm.widthPixels;
  }
}

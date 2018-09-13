package com.beike.sample.limengsample.helper;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.utils.DensityUtil;
import java.lang.reflect.Field;

/**
 * 创建时间: 2017/10/26 11:23 <br>
 * 作者: chengjunnan <br>
 * 描述: 带动画的自定义toast
 */

public class AnimToast {
  public static void show(Context context, String msg) {
    show(context, msg, Toast.LENGTH_LONG, 0);
  }

  private static void show(Context context, String message, int length, float dp) {
    View view =
        LayoutInflater.from(context)
            .inflate(R.layout.layout_feed_anim_toast, null);
    TextView tvToast = (TextView) view.findViewById(R.id.tv_toast);
    tvToast.setText(message);
    Toast toast = new Toast(context);
    toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.TOP, 0, DensityUtil.dip2px(context, -55));
    toast.setView(view);

    //float curTranslationY = toast.getView().getTranslationY();
    //ObjectAnimator translationYAnimator =
    //    ObjectAnimator.ofFloat(toast.getView(), "translationY", 0, DensityUtil.dip2px(55));
    //ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(toast.getView(), "alpha", 0f, 1f);
    //AnimatorSet animatorSet = new AnimatorSet();
    //animatorSet.play(translationYAnimator).with(alphaAnimator);
    toast.setDuration(length);
    setAnimation(toast, R.style.toast_anim);
    //animatorSet.start();
    toast.show();
  }

  private static void setAnimation(Toast toast, int animations) {
    try {
      Field tnField = toast.getClass().getDeclaredField("mTN");
      tnField.setAccessible(true);
      Object mTN = tnField.get(toast);

      // 设置动画
      if (animations != -1) {
        Field tnParamsField = mTN.getClass().getDeclaredField("mParams");
        tnParamsField.setAccessible(true);
        WindowManager.LayoutParams params = (WindowManager.LayoutParams) tnParamsField.get(mTN);
        params.windowAnimations = animations;
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

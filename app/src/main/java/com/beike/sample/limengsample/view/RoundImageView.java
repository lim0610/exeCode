package com.beike.sample.limengsample.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import com.beike.sample.limengsample.R;

/**
 * 作者：xiongmin
 * 创建时间：2018.7.22
 * 描述：设置圆角imageview
 * corners_top_left：左上角是否为圆角 true->圆角；false->非圆角
 * corners_top_right：右上角是否为圆角
 * corners_bottom_left：左下角是否为圆角
 * corners_bottom_right：右下角是否为圆角
 * borderRadius：圆角半径
 */

public class RoundImageView extends android.support.v7.widget.AppCompatImageView {

  private static final int BODER_RADIUS_DEFAULT = 0;
  private static final String STATE_INSTANCE = "state_instance";
  private static final String STATE_BORDER_RADIUS = "state_border_radius";
  private boolean mTopLeft = true;
  private boolean mTopRight = true;
  private boolean mBottomLeft = true;
  private boolean mBottomRight = true;
  private int borderRadius;
  private Paint mPaint;
  private RectF roundRect;
  private Matrix matrix;
  private BitmapShader mBitmapShader;

  public RoundImageView(Context context) {
    this(context, null);
  }

  public RoundImageView(Context context, AttributeSet attrs) {
    this(context, attrs, -1);
  }

  public RoundImageView(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);

    matrix = new Matrix();
    mPaint = new Paint();
    mPaint.setAntiAlias(true);

    TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RoundImageView);
    mTopLeft = array.getBoolean(R.styleable.RoundImageView_top_left, true);
    mTopRight = array.getBoolean(R.styleable.RoundImageView_top_right, true);
    mBottomLeft = array.getBoolean(R.styleable.RoundImageView_bottom_left, true);
    mBottomRight = array.getBoolean(R.styleable.RoundImageView_bottom_right, true);
    borderRadius = array.getDimensionPixelSize(R.styleable.RoundImageView_borderRadius, 0);
    array.recycle();
  }

  private void setShader() {
    Drawable drawable = getDrawable();
    if (drawable == null) {
      return;
    }
    Bitmap bitmap = drawableToBitamp(drawable);
    mBitmapShader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
    float scaleWidth = getWidth() * 1.0f / bitmap.getWidth();
    float scaleHeight = getHeight() * 1.0f / bitmap.getHeight();
    matrix.setScale(scaleWidth, scaleHeight);
    mBitmapShader.setLocalMatrix(matrix);
    mPaint.setShader(mBitmapShader);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    if (getDrawable() == null) {
      return;
    }
    setShader();
    canvas.drawRoundRect(roundRect, borderRadius, borderRadius, mPaint);
    if (!mTopLeft) {
      canvas.drawRect(0, 0, borderRadius, borderRadius, mPaint);
    }
    if (!mTopRight) {
      canvas.drawRect(roundRect.right - borderRadius, 0, roundRect.right, borderRadius, mPaint);
    }
    if (!mBottomLeft) {
      canvas.drawRect(0, roundRect.bottom - borderRadius, borderRadius, roundRect.bottom, mPaint);
    }
    if (!mBottomRight) {
      canvas.drawRect(roundRect.right - borderRadius, roundRect.bottom - borderRadius,
          roundRect.right, roundRect.bottom, mPaint);
    }
  }

  @Override
  protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    super.onSizeChanged(w, h, oldw, oldh);
    roundRect = new RectF(0, 0, w, h);
  }

  private Bitmap drawableToBitamp(Drawable drawable) {
    if (drawable instanceof BitmapDrawable) {
      BitmapDrawable bd = (BitmapDrawable) drawable;
      return bd.getBitmap();
    }
    int w = drawable.getIntrinsicWidth();
    int h = drawable.getIntrinsicHeight();
    Bitmap bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
    Canvas canvas = new Canvas(bitmap);
    drawable.setBounds(0, 0, w, h);
    drawable.draw(canvas);
    return bitmap;
  }

  @Override
  protected Parcelable onSaveInstanceState() {
    Bundle bundle = new Bundle();
    bundle.putParcelable(STATE_INSTANCE, super.onSaveInstanceState());
    bundle.putInt(STATE_BORDER_RADIUS, borderRadius);
    return bundle;
  }

  @Override
  protected void onRestoreInstanceState(Parcelable state) {
    if (state instanceof Bundle) {
      Bundle bundle = (Bundle) state;
      super.onRestoreInstanceState(((Bundle) state).getParcelable(STATE_INSTANCE));
      this.borderRadius = bundle.getInt(STATE_BORDER_RADIUS);
    } else {
      super.onRestoreInstanceState(state);
    }
  }
}

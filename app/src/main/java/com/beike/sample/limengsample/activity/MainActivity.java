package com.beike.sample.limengsample.activity;

import android.content.Intent;
import android.os.Build;
import android.os.SystemClock;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.ViewServer;
import com.beike.sample.limengsample.helper.AnimToast;

public class MainActivity extends BaseActivity {
  private long mTimeBegin;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ViewServer.get(this).addWindow(this);

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
      Log.e("test-tag", "version upper than Android O");
    } else {
      Log.e("test-tag", "version lower than Android O");
    }

    TextView tvTip = (TextView) findViewById(R.id.tv_tip);
    if (tvTip != null) {
      tvTip.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          showTestActivity();
          //showListActivity();
        }
      });
    }

    Button btnGotoList = (Button) findViewById(R.id.btn_goto_list);
    if (btnGotoList != null) {
      btnGotoList.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
          //showListActivity();
          //ExToast toast = ExToast.makeText(MainActivity.this, "toast test", 5);
          //toast.setAnimations(R.style.toast_anim);
          //toast.show();
          AnimToast.show(MainActivity.this, "test toast");
        }
      });
    }

    mTimeBegin = SystemClock.currentThreadTimeMillis();
    FrameLayout flContainer = (FrameLayout) findViewById(R.id.fl_container);
    if (flContainer != null) {
      for (int i = 0; i < 1000; i++) {
        View view1 =
            LayoutInflater.from(this).inflate(R.layout.layout_relative, flContainer, false);
        flContainer.addView(view1);
      }

      flContainer.getViewTreeObserver().addOnGlobalLayoutListener(
          new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override public void onGlobalLayout() {
              long off = SystemClock.currentThreadTimeMillis() - mTimeBegin;
              Log.e("test-tag", "used time of layout: " + off);
            }
          });

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
        flContainer.getViewTreeObserver().addOnDrawListener(new ViewTreeObserver.OnDrawListener() {
          @Override public void onDraw() {
            long off = SystemClock.currentThreadTimeMillis() - mTimeBegin;
            Log.e("test-tag", "used time of draw: " + off);
          }
        });
      }
    }
  }

  public void onDestroy() {
    super.onDestroy();
    ViewServer.get(this).removeWindow(this);
  }

  public void onResume() {
    super.onResume();
    ViewServer.get(this).setFocusedWindow(this);
  }

  private void showTestActivity() {
    Intent intent = new Intent(this, TestActivity.class);
    startActivity(intent);
  }

  private void showListActivity() {
    Intent intent = new Intent(this, TestListActivity.class);
    startActivity(intent);
  }
}

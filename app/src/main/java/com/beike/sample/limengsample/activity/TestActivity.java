package com.beike.sample.limengsample.activity;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.fragment.TestFragment;

public class TestActivity extends FragmentActivity {

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test);
    //ViewServer.get(this).addWindow(this);
    FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
    transaction.replace(R.id.fl_content, new TestFragment());
    transaction.commitAllowingStateLoss();
  }

  public void onDestroy() {
    super.onDestroy();
    //ViewServer.get(this).removeWindow(this);
  }

  public void onResume() {
    super.onResume();
    //ViewServer.get(this).setFocusedWindow(this);
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
  }
}

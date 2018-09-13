package com.beike.sample.limengsample.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class BaseActivity extends Activity {
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // Set content view, etc.
    //ViewServer.get(this).addWindow(this);
  }

  //public void onDestroy() {
  //  super.onDestroy();
  //  ViewServer.get(this).removeWindow(this);
  //}
  //
  //public void onResume() {
  //  super.onResume();
  //  ViewServer.get(this).setFocusedWindow(this);
  //}
}

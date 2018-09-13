package com.beike.sample.limengsample.activity;

import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.adapter.TestRVAdapter;
import com.beike.sample.limengsample.helper.FeedCacheHelper;
import com.beike.sample.limengsample.model.FeedBean;
import com.beike.sample.limengsample.viewholder.FeedItemDecoration;
import java.util.ArrayList;
import java.util.List;

public class TestRecyclerActivity extends AppCompatActivity {
  private RecyclerView mRecyclerView;
  private TestRVAdapter mAdapter;

  private LinearLayoutManager mLinearLayoutManager;
  private GridLayoutManager mGridLayoutManager;
  private StaggeredGridLayoutManager mStaggerLayoutManager;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_recycler);

    mRecyclerView = (RecyclerView) findViewById(R.id.rv_test);
    mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    mRecyclerView.setLayoutManager(mLinearLayoutManager);
    mAdapter = new TestRVAdapter(this);
    mRecyclerView.setAdapter(mAdapter);
    Drawable dividerDrawable = this.getResources().getDrawable(R.drawable.divider_pic);
    mRecyclerView.addItemDecoration(new FeedItemDecoration(dividerDrawable));
  }

  @Override
  public void onResume() {
    super.onResume();

    mRecyclerView.postDelayed(new Runnable() {
      @Override
      public void run() {
        List<FeedBean> feedList = new FeedCacheHelper(TestRecyclerActivity.this).getFeedList();
        mAdapter.setData(feedList);
      }
    }, 1500);
  }
}

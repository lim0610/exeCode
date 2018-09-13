package com.beike.sample.limengsample.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.beike.sample.limengsample.model.FeedBean;
import com.beike.sample.limengsample.viewholder.FeedBaseViewHolder;
import com.beike.sample.limengsample.viewholder.FeedNoPicViewHolder;
import com.beike.sample.limengsample.viewholder.FeedSinPicViewHolder;
import com.beike.sample.limengsample.viewholder.FeedTriPicViewHolder;
import com.beike.sample.limengsample.viewholder.SpacerViewHolder;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: 2018/08/22 00:14 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class TestRVAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements
    FeedBaseViewHolder.OnItemClickListener {
  private static final int VH_TYPE_SPACER = 1;
  private static final int VH_TYPE_TRI_PIC_CARD = 2;
  private static final int VH_TYPE_SIN_PIC_CARD = 3;
  private static final int VH_TYPE_NO_PIC_CARD = 4;

  private Activity mActivity;
  private List<FeedBean> mDatas;

  public void setData(List<FeedBean> feedList) {
    mDatas = feedList;
    notifyDataSetChanged();
  }

  public TestRVAdapter(Activity activity) {
    super();
    mActivity = activity;
    mDatas = new ArrayList<>();
  }

  @Override
  public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case VH_TYPE_SPACER:
        return SpacerViewHolder.newInstance(parent);
      case VH_TYPE_TRI_PIC_CARD:
        return FeedTriPicViewHolder.newInstance(mActivity, parent).setOnItemClickListener(this);
      case VH_TYPE_SIN_PIC_CARD:
        return FeedSinPicViewHolder.newInstance(mActivity, parent).setOnItemClickListener(this);
      case VH_TYPE_NO_PIC_CARD:
        return FeedNoPicViewHolder.newInstance(mActivity, parent).setOnItemClickListener(this);
    }
    return null;
  }

  @Override
  public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    if (isEmptyStyle()
        || position < 0
        || position >= mDatas.size()
        || (holder instanceof FeedBaseViewHolder) == false) {
      return;
    }

    FeedBean feedBean = mDatas.get(position);
    ((FeedBaseViewHolder) holder).updateView(feedBean);
  }

  @Override
  public int getItemViewType(int position) {
    if (!isEmptyStyle()) {
      FeedBean feedBean = mDatas.get(position);
      String type = feedBean.getType();
      switch (type) {
        case "1":
          return VH_TYPE_TRI_PIC_CARD;
        case "2":
          return VH_TYPE_SIN_PIC_CARD;
        case "3":
          return VH_TYPE_NO_PIC_CARD;
      }
    }

    return VH_TYPE_SPACER;
  }

  @Override
  public int getItemCount() {
    return isEmptyStyle() ? 10 : mDatas.size();
  }

  public boolean isEmptyStyle() {
    return mDatas == null || mDatas.size() == 0;
  }

  @Override
  public void onClick(FeedBean feed) {

  }
}

package com.beike.sample.limengsample.viewholder;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.model.FeedBean;

/**
 * 创建时间: 2018/07/31 14:36 <br>
 * 作者: limeng <br>
 * 描述:
 */
public abstract class FeedBaseViewHolder extends RecyclerView.ViewHolder {
  TextView tvFeedTitle;
  TextView tvFeedName;

  int mNormalColor;
  int mHasReadColor;

  protected Activity mActivity;
  protected OnItemClickListener mClickListener;
  protected FeedBean mFeed;

  public FeedBaseViewHolder(View itemView, final Activity activity) {
    super(itemView);
    mActivity = activity;

    tvFeedTitle = (TextView) itemView.findViewById(R.id.tv_feed_title);
    tvFeedName = (TextView) itemView.findViewById(R.id.tv_feed_name);

    mNormalColor = activity.getResources().getColor(R.color.main_text);
    mHasReadColor = activity.getResources().getColor(R.color.color_9399A5);

    itemView.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // open detail
        if (activity == null || activity.isFinishing() || mFeed == null) {
          return;
        }

        //UrlSchemeUtils.goToTargetActivity(mFeed.getUrl(), activity);
        //if (!mFeed.getReadFlag()) {
        //  mFeed.setReadFlag(true);
        //  FeedReadFlagDataHelper.getInstance().handleClickRead(mFeed);
        //  updateReadStatus(true);
        //}

        if (mClickListener != null) {
          mClickListener.onClick(mFeed);
        }
      }
    });
  }

  public FeedBaseViewHolder setOnItemClickListener(OnItemClickListener clickListener) {
    mClickListener = clickListener;
    return this;
  }

  public void updateView(@NonNull FeedBean feedBean) {
    mFeed = feedBean;

    if (!"0".equals(feedBean.getType())) {
      tvFeedTitle.setText(decodeUnicode(feedBean.getTitle()));
      tvFeedName.setText(decodeUnicode(feedBean.getBeikeName()));
      updateReadStatus(feedBean.getReadFlag());
    }
  }

  public void updateReadStatus(boolean hasRead) {
    tvFeedTitle.setTextColor(hasRead ? mHasReadColor : mNormalColor);
  }

  public interface OnItemClickListener {
    void onClick(FeedBean feed);
  }

  public static String decodeUnicode(String theString) {
    char aChar;
    int len = theString.length();
    StringBuffer outBuffer = new StringBuffer(len);
    for (int x = 0; x < len;) {
      aChar = theString.charAt(x++);
      if (aChar == '\\') {
        aChar = theString.charAt(x++);
        if (aChar == 'u') {
          // Read the xxxx
          int value = 0;
          for (int i = 0; i < 4; i++) {
            aChar = theString.charAt(x++);
            switch (aChar) {
              case '0':
              case '1':
              case '2':
              case '3':
              case '4':
              case '5':
              case '6':
              case '7':
              case '8':
              case '9':
                value = (value << 4) + aChar - '0';
                break;
              case 'a':
              case 'b':
              case 'c':
              case 'd':
              case 'e':
              case 'f':
                value = (value << 4) + 10 + aChar - 'a';
                break;
              case 'A':
              case 'B':
              case 'C':
              case 'D':
              case 'E':
              case 'F':
                value = (value << 4) + 10 + aChar - 'A';
                break;
              default:
                throw new IllegalArgumentException(
                    "Malformed   \\uxxxx   encoding.");
            }

          }
          outBuffer.append((char) value);
        } else {
          if (aChar == 't')
            aChar = '\t';
          else if (aChar == 'r')
            aChar = '\r';
          else if (aChar == 'n')
            aChar = '\n';
          else if (aChar == 'f')
            aChar = '\f';
          outBuffer.append(aChar);
        }
      } else
        outBuffer.append(aChar);
    }
    return outBuffer.toString();
  }

}

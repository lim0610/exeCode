package com.beike.sample.limengsample.helper;

import android.content.Context;
import com.beike.sample.limengsample.model.FeedBean;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建时间: 2018/08/22 16:13 <br>
 * 作者: limeng <br>
 * 描述:
 */
public class FeedCacheHelper {
  private String mCachedData = "";
  public FeedCacheHelper(Context context) {
    try {
      InputStream is = context.getAssets().open("data.json");
      byte[] data = FeedCacheHelper.streamToBytes(is);
      mCachedData = new String(data, "utf-8");
    } catch (IOException e) {
      e.printStackTrace();
    }

  }

  public List<FeedBean> getFeedList() {
    List<FeedBean> feedList = new ArrayList<>();
    Type type =  new TypeToken<List<FeedBean>>() {}.getType();
    try {
      feedList = new Gson().fromJson(mCachedData, type);
    } catch (JsonSyntaxException e) {
      e.printStackTrace();
    }
    return feedList;
  }

  public static byte[] streamToBytes(InputStream is) {
    byte[] b = null;
    ByteArrayOutputStream os = new ByteArrayOutputStream(1024);
    byte[] buffer = new byte[1024];
    int len;
    try {
      while ((len = is.read(buffer)) >= 0) {
        os.write(buffer, 0, len);
      }
      b = os.toByteArray();
      os.flush();
      os.close();
      is.close();
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
    return b;
  }
}

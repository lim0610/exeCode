package com.beike.sample.limengsample.activity;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;
import com.beike.sample.limengsample.R;
import com.beike.sample.limengsample.utils.ConstantUtil;
import com.beike.sample.limengsample.view.MyListView;
import java.util.ArrayList;
import java.util.List;

public class TestListActivity extends AppCompatActivity {
  private MyListView mTestListView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_test_list);

    mTestListView = (MyListView) findViewById(R.id.lv_test);

    mTestListView.postDelayed(new Runnable() {
      @Override public void run() {
        Toast.makeText(TestListActivity.this, Build.MANUFACTURER + ", " + Build.MODEL,
            Toast.LENGTH_SHORT);
      }
    }, 1000);

    // array adapter
    //mTestListView.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mNames));
    //mTestListView.setAdapter(new ArrayAdapter<>(this, R.layout.item_friend_name, R.id.tv_name, mNames));

    // simple adapter

    // base adapter
    //mTestListView.setAdapter(new FriendAdapter(mFriends));
    //
    //mTestListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    //  @Override public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    //    Object data = mTestListView.getAdapter().getItem(i);
    //    if (data instanceof String) {
    //      Toast.makeText(TestListActivity.this, "clicked: " + data, Toast.LENGTH_SHORT);
    //    } else if (data instanceof Friend) {
    //      Toast.makeText(TestListActivity.this, "clicked: " + ((Friend) data).name, Toast.LENGTH_SHORT);
    //    }
    //  }
    //});
  }

  private List<String> mNames = new ArrayList<String>() {
    {
      add("小明");
      add("小丽");
      add("小红");
      add("小白");
      add("小绿");
      add("小紫");
      add("王二");
      add("张三");
      add("李四");
      add("喵喵");
      add("冬冬");
      add("哈哈");
    }
  };

  private List<Friend> mFriends = new ArrayList<Friend>() {
    {
      add(new Friend("小明", "18612039283", "", 20));
      add(new Friend("小A", "18683878477", "", 13));
      add(new Friend("小B", "18618878786", "", 24));
      add(new Friend("小C", "18624696565", "", 33));
      add(new Friend("小D", "18616239835", "", 13));
      add(new Friend("小E", "18627552887", "", 32));
      add(new Friend("小F", "18612860356", "", 54));
      add(new Friend("小G", "18623456787", "", 35));
      add(new Friend("小H", "18687655677", "", 35));
      add(new Friend("小I", "18613456544", "", 24));
      add(new Friend("小J", "18645654345", "", 12));
      add(new Friend("小K", "18623454334", "", 89));
    }
  };

  class FriendAdapter extends BaseAdapter {
    private List<Friend> mDatas;

    public FriendAdapter(List<Friend> friends) {
      mDatas = friends;
    }

    @Override public int getCount() {
      return mDatas.size();
    }

    @Override public Object getItem(int pos) {
      return mDatas.get(pos);
    }

    @Override public long getItemId(int pos) {
      return pos;
    }

    @Override public View getView(int pos, View convertView, ViewGroup container) {
      Log.e(ConstantUtil.TAG_TEST, "getView, pos = " + pos);

      /* 写法1 */
      //convertView = LayoutInflater.from(TestListActivity.this)
      //    .inflate(R.layout.item_friend_info_detail, container, false);
      //if (convertView != null) {
      //  Friend friend = (Friend) getItem(pos);
      //  TextView tvName = convertView.findViewById(R.id.tv_name);
      //  tvName.setText(friend.name);
      //  TextView tvAge = convertView.findViewById(R.id.tv_age);
      //  tvAge.setText("" + friend.age);
      //  TextView tvPhone = convertView.findViewById(R.id.tv_phone);
      //  tvPhone.setText(friend.phone);
      //}

      /* 写法2 */
      ViewHolder holder;

      if (convertView == null) {
        convertView = LayoutInflater.from(TestListActivity.this)
            .inflate(R.layout.item_friend_info_detail, container, false);
        if (convertView == null) {
          return null;
        }

        holder = new ViewHolder();
        holder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
        holder.tvAge = (TextView) convertView.findViewById(R.id.tv_age);
        holder.tvPhone = (TextView) convertView.findViewById(R.id.tv_phone);
        convertView.setTag(holder);
      } else {
        holder = (ViewHolder) convertView.getTag();
      }

      Friend friend = (Friend) getItem(pos);
      holder.tvName.setText(friend.name);
      holder.tvAge.setText("" + friend.age);
      holder.tvPhone.setText(friend.phone);

      return convertView;
    }
  }

  static class ViewHolder {
    private TextView tvName;
    private TextView tvAge;
    private TextView tvPhone;
  }

  static class Friend {
    private String name;
    private String phone;
    private String avatar;
    private int age;

    public Friend(String name, String phone, String avatar, int age) {
      this.name = name;
      this.phone = phone;
      this.avatar = avatar;
      this.age = age;
    }
  }
}

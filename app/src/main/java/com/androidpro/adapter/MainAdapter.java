package com.androidpro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.androidpro.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhiwei
 * @version 1.0
 * @date 2019/3/11 下午3:12
 * @description
 */
public class MainAdapter extends BaseAdapter {
  private Context mContext;
  private ArrayList<String> source = new ArrayList<>();

  public MainAdapter(Context context) {
    this.mContext = context;
  }

  public void bindData(List<String> data) {
    source.clear();
    source.addAll(data);
  }

  @Override
  public int getCount() {
    return source.size();
  }

  @Override
  public String getItem(int position) {
    if (position >= getCount()) {
      return null;
    }
    return source.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    ViewHolder holder;
    if (convertView == null) {
      convertView = LayoutInflater.from(mContext).inflate(R.layout.item_single_text, parent, false);
      holder = new ViewHolder(convertView);
      convertView.setTag(R.id.view_holder, holder);
    } else {
      holder = (ViewHolder) convertView.getTag(R.id.view_holder);
    }
    holder.fillData(getItem(position));
    return convertView;
  }
  // viewHolder
  class ViewHolder {
    private TextView title;

    ViewHolder(View root) {
      title = root.findViewById(R.id.tv_title);
    }

    void fillData(String item) {
      if (item == null) {
        return;
      }
      title.setText(item);
    }
  }
}

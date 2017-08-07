package com.listviewproject.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwei on 2017/8/7.
 */
public class RNListView extends ListView implements ViewGroupBuilder {

  private List<RNListItemView> cacheViews = new ArrayList<>();
  private List<RNLayoutParams> layoutParamsList = new ArrayList<>();

  private int sHeight;
  private int minRowHeight;

  public RNListView(Context context) {
    this(context, null);
  }

  public RNListView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public RNListView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    sHeight = context.getResources().getDisplayMetrics().heightPixels;
    minRowHeight = sHeight / 30;

    Log.i("RNList", "RNListView:create");
    buildAdapter();
  }

  public void setMinHeight(int minRowHeight) {
    this.minRowHeight = minRowHeight;
  }

  public int getMinHeight() {
    return minRowHeight;
  }

  public void setLayoutParamsList(List<RNLayoutParams> layoutParamsList) {
    this.layoutParamsList = layoutParamsList;
  }

  /**
   * 设置Adapter
   */
  public void buildAdapter() {
    Log.i("RNList", "RNListView:buildAdapter");
    if (getAdapter() == null) {
      setAdapter(innerAdapter);
    }
  }

  @Override public void requestLayout() {
    super.requestLayout();
    forceLayout();
  }

  @Override public void addView(View child, int index) {

    Log.i("RNList", "RNListView:addView" + index);

    if (child == null || !(child instanceof RNListItemView)) {
      return;
    }

    RNListItemView itemView = (RNListItemView) child;

    //不允许itemView的高度小于minHeight
    if (itemView.getRowHeight() < minRowHeight) {
      itemView.setRowHeight(minRowHeight, false);
    }

    //缓存足够的itemView
    if (cacheViews.size() < sHeight / minRowHeight + 2) {
      cacheViews.add(itemView);
    }

    notifyDataSetChanged();
  }

  /**
   * 刷新
   */
  public void notifyDataSetChanged() {
    if (getAdapter() != null) {
      innerAdapter.notifyDataSetChanged();
    }
  }

  /**
   * 修改子View的RNLayoutParams
   */
  public void setItemLayoutParams(int index, RNLayoutParams layoutParams) {
    if (index >= 0 && index < layoutParamsList.size()) {
      layoutParamsList.set(index, layoutParams);
      notifyDataSetChanged();
    }
  }

  public RNLayoutParams getItemLayoutParams(int index) {
    if (index >= 0 && index < layoutParamsList.size()) {
      return layoutParamsList.get(index);
    }
    return null;
  }

  @Override public int getChildCount() {
    return super.getChildCount();
  }

  @Override public void removeAllViews() {
    cacheViews.clear();
    notifyDataSetChanged();
  }

  /**
   * ADAPTER
   */
  private BaseAdapter innerAdapter = new BaseAdapter() {

    @Override public int getCount() {
      return layoutParamsList.size();
    }

    @Override public Object getItem(int position) {
      return null;
    }

    @Override public long getItemId(int position) {
      return 0;
    }

    @Override public View getView(int position, View convertView, ViewGroup parent) {

      if (convertView == null) {
        convertView = cacheViews.get(position % cacheViews.size());
      }

      //界面参数RNLayoutParams设置
      if (layoutParamsList != null && position < layoutParamsList.size()) {
        ((RNListItemView) convertView).setRowHeight(layoutParamsList.get(position).rowHeight, true);
      }

      ((RNListItemView) convertView).updateItemView(position);
      return convertView;
    }
  };
}

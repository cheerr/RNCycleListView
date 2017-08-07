package com.listviewproject.listview;

import android.view.View;

/**
 * Created by chenwei on 2017/8/7.
 */
public interface ViewGroupBuilder {

  void addView(View child, int index);

  int getChildCount();

  void removeAllViews();
}

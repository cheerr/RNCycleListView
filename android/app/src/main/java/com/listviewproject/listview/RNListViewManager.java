package com.listviewproject.listview;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenwei on 2017/8/7.
 */
public class RNListViewManager extends ViewGroupManager<RNListView> {

  @Override public String getName() {
    return "RNListView";
  }

  @Override protected RNListView createViewInstance(ThemedReactContext themedReactContext) {
    RNListView view = new RNListView(themedReactContext);
    view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.MATCH_PARENT));
    view.buildAdapter();
    return view;
  }

  @ReactProp(name = "minRowHeight") public void setMinRowHeight(RNListView view, int val) {
    Log.i("RNList", "RNListView:setMinRowHeight " + val);
    view.setMinHeight(val);
  }

  @ReactProp(name = "rowSizes") public void setRowSizes(RNListView view, ReadableArray array) {
    Log.i("RNList", "RNListView:setRowSizes " + array);

    List<RNLayoutParams> rowSizes = new ArrayList<>();

    for (int i = 0; i < array.size(); i++) {
      RNLayoutParams params = new RNLayoutParams();
      if (array.getMap(i).hasKey("rowHeight")) {
        params.rowHeight = Math.max(view.getMinHeight(), array.getMap(i).getInt("rowHeight"));
        rowSizes.add(params);
      }
    }
    view.setLayoutParamsList(rowSizes);
  }

  @Override public void addView(RNListView parent, View child, int index) {
    parent.addView(child, index);
  }

  @Override public int getChildCount(RNListView parent) {
    return parent.getChildCount();
  }

  @Override public void removeAllViews(RNListView parent) {
    parent.removeAllViews();
  }
}

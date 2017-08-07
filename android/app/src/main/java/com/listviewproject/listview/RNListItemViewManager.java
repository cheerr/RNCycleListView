package com.listviewproject.listview;

import android.util.Log;
import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewGroupManager;
import com.facebook.react.uimanager.annotations.ReactProp;
import java.util.Map;
import javax.annotation.Nullable;

/**
 * Created by chenwei on 2017/8/7.
 */
public class RNListItemViewManager extends ViewGroupManager<RNListItemView> {

  @Override public String getName() {
    return "RNListItemView";
  }

  @Override protected RNListItemView createViewInstance(ThemedReactContext themedReactContext) {
    return new RNListItemView(themedReactContext);
  }

  @ReactProp(name = "rowHeight") public void setRowHeight(RNListItemView view, int val) {
    Log.i("RNList", "RNListView:setRowHeight " + val);
    view.setRowHeight(val, false);
  }

  @Override public @Nullable Map getExportedCustomDirectEventTypeConstants() {
    return MapBuilder.builder()
        .put("UpdateRNListViewEvent", MapBuilder.of("registrationName", "onRNListViewUpdateView"))
        .build();
  }
}

package com.listviewproject.listview;

import android.content.Context;
import android.util.Log;
import android.view.View;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.UIManagerModule;
import com.facebook.react.uimanager.events.Event;
import com.facebook.react.uimanager.events.EventDispatcher;
import com.facebook.react.uimanager.events.RCTEventEmitter;
import com.facebook.react.views.view.ReactViewGroup;

/**
 * Created by chenwei on 2017/8/7.
 */
public class RNListItemView extends ReactViewGroup implements ViewOperationDelegate {

  final EventDispatcher mEventDispatcher;

  private int rowHeight = 10;

  public RNListItemView(Context context) {
    super(context);
    mEventDispatcher =
        ((ReactContext) getContext()).getNativeModule(UIManagerModule.class).getEventDispatcher();
  }

  @Override public void requestLayout() {
    super.requestLayout();
    forceLayout();
  }

  @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    int w = MeasureSpec.getSize(widthMeasureSpec), h = rowHeight;
    if (rowHeight < 1 && getChildCount() > 0) {
      final View child = getChildAt(0);
      LayoutParams lp = child.getLayoutParams();
      if (lp == null) {
        lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
      }
      int w1 = MeasureSpec.makeMeasureSpec(lp.width, MeasureSpec.AT_MOST), h1 =
          MeasureSpec.makeMeasureSpec(lp.height, MeasureSpec.AT_MOST);
      child.measure(w1, h1);
      h = child.getHeight();
    }
    setMeasuredDimension(w, h);
  }

  /**
   * 设置高度
   */
  public void setRowHeight(int rowHeight, boolean refresh) {
    if (this.rowHeight != rowHeight) {
      this.rowHeight = Math.max(10, rowHeight);
      if (refresh) requestLayout();
    }
  }

  public int getRowHeight() {
    return rowHeight;
  }

  /**
   * 刷新ItemView
   */
  public void updateItemView(final int pos) {
    Log.i("RNList", "RNListView:updateItemView" + pos);
    mEventDispatcher.dispatchEvent(new Event(getId()) {
      @Override public String getEventName() {
        return "UpdateRNListViewEvent";
      }

      @Override public void dispatch(RCTEventEmitter rctEventEmitter) {
        WritableMap eventData = Arguments.createMap();
        eventData.putInt("position", pos);
        rctEventEmitter.receiveEvent(getViewTag(), getEventName(), eventData);
      }
    });
  }

  @Override public boolean intercept() {
    return true;
  }
}

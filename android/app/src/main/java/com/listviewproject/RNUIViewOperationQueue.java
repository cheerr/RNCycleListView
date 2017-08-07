package com.listviewproject;

import android.view.View;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.listviewproject.listview.ViewOperationDelegate;

/**
 * Created by chenwei on 2017/8/7.
 */
public class RNUIViewOperationQueue extends UIViewOperationQueue {
  private final NativeViewHierarchyManager mNativeViewHierarchyManager;

  public RNUIViewOperationQueue(ReactApplicationContext reactContext,
      NativeViewHierarchyManager nativeViewHierarchyManager) {
    super(reactContext, nativeViewHierarchyManager);
    mNativeViewHierarchyManager = nativeViewHierarchyManager;
  }

  @Override public void enqueueUpdateLayout(int parentTag, int reactTag, int x, int y, int width,
      int height) {
    final View view = resolveView(reactTag);
    if (!(view != null
        && (view instanceof ViewOperationDelegate)
        && ((ViewOperationDelegate) view).intercept())) {
      super.enqueueUpdateLayout(parentTag, reactTag, x, y, width, height);
    }
  }

  private View resolveView(int reactTag) {
    try {
      return mNativeViewHierarchyManager.resolveView(reactTag);
    } catch (Exception e) {
    }
    return null;
  }
}

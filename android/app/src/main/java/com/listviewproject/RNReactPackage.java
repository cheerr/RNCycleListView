package com.listviewproject;

import com.facebook.react.ReactPackage;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.ViewManager;
import com.listviewproject.listview.RNListItemViewManager;
import com.listviewproject.listview.RNListViewManager;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by chenwei on 2017/8/7.
 */
public class RNReactPackage implements ReactPackage {

  @Override public List<NativeModule> createNativeModules(ReactApplicationContext reactContext) {
    return Collections.emptyList();
  }

  @Override public List<ViewManager> createViewManagers(ReactApplicationContext reactContext) {
    return Arrays.<ViewManager>asList(new RNListViewManager(), new RNListItemViewManager());
  }
}


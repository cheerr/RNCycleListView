package com.listviewproject;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIImplementationProvider;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.List;

/**
 * Created by chenwei on 2017/8/7.
 */
public class RNUIImplementationProvider extends UIImplementationProvider {
    @Override
    public UIImplementation createUIImplementation(ReactApplicationContext reactContext, List<ViewManager> viewManagers, EventDispatcher eventDispatcher) {
        return new RNUIImplementation(reactContext, viewManagers,eventDispatcher);
    }
}

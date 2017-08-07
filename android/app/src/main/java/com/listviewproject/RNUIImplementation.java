package com.listviewproject;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.uimanager.NativeViewHierarchyManager;
import com.facebook.react.uimanager.UIImplementation;
import com.facebook.react.uimanager.UIViewOperationQueue;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.uimanager.ViewManagerRegistry;
import com.facebook.react.uimanager.events.EventDispatcher;
import java.util.List;

/**
 * Created by chenwei on 2017/8/7.
 */
public class RNUIImplementation extends UIImplementation {
    public RNUIImplementation(ReactApplicationContext reactContext, List<ViewManager> viewManagers, EventDispatcher eventDispatcher) {
        this(reactContext, new ViewManagerRegistry(viewManagers),eventDispatcher);
    }

    private RNUIImplementation(ReactApplicationContext reactContext, ViewManagerRegistry viewManagers, EventDispatcher eventDispatcher) {
        this(reactContext,viewManagers, null,eventDispatcher);
    }

    protected RNUIImplementation(ReactApplicationContext reactContext, ViewManagerRegistry viewManagers, UIViewOperationQueue operationsQueue, EventDispatcher eventDispatcher) {
        super(reactContext, viewManagers, new RNUIViewOperationQueue(reactContext, new NativeViewHierarchyManager(viewManagers)),eventDispatcher);
    }
}

package com.listviewproject;

import android.app.Application;
import com.facebook.infer.annotation.Assertions;
import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactInstanceManagerBuilder;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.shell.MainReactPackage;
import java.util.Arrays;
import java.util.List;

/**
 * Created by chenwei on 2017/8/7.
 */
public class MainApplication extends Application implements ReactApplication {

  private final ReactNativeHost mReactNativeHost = new ReactNativeHost(this) {
    @Override public boolean getUseDeveloperSupport() {
      return BuildConfig.DEBUG;
    }

    @Override protected ReactInstanceManager createReactInstanceManager() {
      ReactInstanceManagerBuilder builder = ReactInstanceManager.builder()
          .setApplication(MainApplication.this)
          .setJSMainModuleName(getJSMainModuleName())
          .setUseDeveloperSupport(getUseDeveloperSupport())
          .setUIImplementationProvider(new RNUIImplementationProvider())
          .setInitialLifecycleState(LifecycleState.BEFORE_CREATE);

      for (ReactPackage reactPackage : getPackages()) {
        builder.addPackage(reactPackage);
      }

      String jsBundleFile = getJSBundleFile();
      if (jsBundleFile != null) {
        builder.setJSBundleFile(jsBundleFile);
      } else {
        builder.setBundleAssetName(Assertions.assertNotNull(getBundleAssetName()));
      }

      return builder.build();
    }

    @Override protected List<ReactPackage> getPackages() {
      return Arrays.<ReactPackage>asList(new MainReactPackage(), new RNReactPackage());
    }
  };

  @Override public ReactNativeHost getReactNativeHost() {
    return mReactNativeHost;
  }
}

package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeIntentAndroidSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void canOpenURL(String str, Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void getInitialURL(Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void openSettings(Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void openURL(String str, Promise promise);

    @DoNotStrip
    @ReactMethod
    public abstract void sendIntent(String str, ReadableArray readableArray, Promise promise);

    public NativeIntentAndroidSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

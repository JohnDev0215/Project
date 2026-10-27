package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeNetworkingAndroidSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void abortRequest(double d);

    @DoNotStrip
    @ReactMethod
    public abstract void addListener(String str);

    @DoNotStrip
    @ReactMethod
    public abstract void clearCookies(Callback callback);

    @DoNotStrip
    @ReactMethod
    public abstract void removeListeners(double d);

    @DoNotStrip
    @ReactMethod
    public abstract void sendRequest(String str, String str2, double d, ReadableArray readableArray, ReadableMap readableMap, String str3, boolean z, double d2, boolean z2);

    public NativeNetworkingAndroidSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

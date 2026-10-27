package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeAccessibilityInfoSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void announceForAccessibility(String str);

    @DoNotStrip
    @ReactMethod
    public void getRecommendedTimeoutMillis(double d, Callback callback) {
    }

    @DoNotStrip
    @ReactMethod
    public void isAccessibilityServiceEnabled(Callback callback) {
    }

    @DoNotStrip
    @ReactMethod
    public abstract void isReduceMotionEnabled(Callback callback);

    @DoNotStrip
    @ReactMethod
    public abstract void isTouchExplorationEnabled(Callback callback);

    @DoNotStrip
    @ReactMethod
    public abstract void setAccessibilityFocus(double d);

    public NativeAccessibilityInfoSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

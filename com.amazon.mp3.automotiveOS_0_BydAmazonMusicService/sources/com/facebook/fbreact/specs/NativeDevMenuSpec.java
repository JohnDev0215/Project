package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeDevMenuSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void debugRemotely(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void reload();

    @DoNotStrip
    @ReactMethod
    public abstract void setHotLoadingEnabled(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void setProfilingEnabled(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void show();

    public NativeDevMenuSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

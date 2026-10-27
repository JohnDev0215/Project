package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeBugReportingSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void setCategoryID(String str);

    @DoNotStrip
    @ReactMethod
    public abstract void setExtraData(ReadableMap readableMap, ReadableMap readableMap2);

    @DoNotStrip
    @ReactMethod
    public abstract void startReportAProblemFlow();

    public NativeBugReportingSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

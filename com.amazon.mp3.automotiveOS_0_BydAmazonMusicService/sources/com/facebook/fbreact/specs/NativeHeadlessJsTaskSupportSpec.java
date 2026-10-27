package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeHeadlessJsTaskSupportSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void notifyTaskFinished(double d);

    @DoNotStrip
    @ReactMethod
    public abstract void notifyTaskRetry(double d, Promise promise);

    public NativeHeadlessJsTaskSupportSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

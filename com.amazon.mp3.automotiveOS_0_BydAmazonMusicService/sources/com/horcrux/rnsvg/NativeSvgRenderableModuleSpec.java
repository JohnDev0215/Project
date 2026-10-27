package com.horcrux.rnsvg;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

/* JADX INFO: loaded from: classes7.dex */
public abstract class NativeSvgRenderableModuleSpec extends ReactContextBaseJavaModule {
    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getBBox(Double d, ReadableMap readableMap);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getCTM(Double d);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getPointAtLength(Double d, ReadableMap readableMap);

    @DoNotStrip
    @ReactMethod
    public abstract void getRawResource(String str, Promise promise);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract WritableMap getScreenCTM(Double d);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract double getTotalLength(Double d);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract boolean isPointInFill(Double d, ReadableMap readableMap);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    public abstract boolean isPointInStroke(Double d, ReadableMap readableMap);

    public NativeSvgRenderableModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeBlobModuleSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void addNetworkingHandler();

    @DoNotStrip
    @ReactMethod
    public abstract void addWebSocketHandler(double d);

    @DoNotStrip
    @ReactMethod
    public abstract void createFromParts(ReadableArray readableArray, String str);

    protected abstract Map<String, Object> getTypedExportedConstants();

    @DoNotStrip
    @ReactMethod
    public abstract void release(String str);

    @DoNotStrip
    @ReactMethod
    public abstract void removeWebSocketHandler(double d);

    @DoNotStrip
    @ReactMethod
    public abstract void sendOverSocket(ReadableMap readableMap, double d);

    public NativeBlobModuleSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @DoNotStrip
    @Nullable
    public final Map<String, Object> getConstants() {
        return getTypedExportedConstants();
    }
}

package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeI18nManagerSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void allowRTL(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void forceRTL(boolean z);

    protected abstract Map<String, Object> getTypedExportedConstants();

    @DoNotStrip
    @ReactMethod
    public abstract void swapLeftAndRightInRTL(boolean z);

    public NativeI18nManagerSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @DoNotStrip
    @Nullable
    public final Map<String, Object> getConstants() {
        return getTypedExportedConstants();
    }
}

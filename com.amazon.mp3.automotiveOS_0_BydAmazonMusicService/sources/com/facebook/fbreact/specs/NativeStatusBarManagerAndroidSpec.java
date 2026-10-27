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
public abstract class NativeStatusBarManagerAndroidSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    protected abstract Map<String, Object> getTypedExportedConstants();

    @DoNotStrip
    @ReactMethod
    public abstract void setColor(double d, boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void setHidden(boolean z);

    @DoNotStrip
    @ReactMethod
    public abstract void setStyle(@Nullable String str);

    @DoNotStrip
    @ReactMethod
    public abstract void setTranslucent(boolean z);

    public NativeStatusBarManagerAndroidSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @DoNotStrip
    @Nullable
    public final Map<String, Object> getConstants() {
        return getTypedExportedConstants();
    }
}

package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeAppearanceSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    @DoNotStrip
    @ReactMethod
    public abstract void addListener(String str);

    @DoNotStrip
    @ReactMethod(isBlockingSynchronousMethod = true)
    @Nullable
    public abstract String getColorScheme();

    @DoNotStrip
    @ReactMethod
    public abstract void removeListeners(double d);

    public NativeAppearanceSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }
}

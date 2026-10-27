package com.facebook.fbreact.specs;

import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactModuleWithSpec;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import java.util.Map;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class NativeSourceCodeSpec extends ReactContextBaseJavaModule implements ReactModuleWithSpec, TurboModule {
    protected abstract Map<String, Object> getTypedExportedConstants();

    public NativeSourceCodeSpec(ReactApplicationContext reactApplicationContext) {
        super(reactApplicationContext);
    }

    @Override // com.facebook.react.bridge.BaseJavaModule
    @DoNotStrip
    @Nullable
    public final Map<String, Object> getConstants() {
        return getTypedExportedConstants();
    }
}

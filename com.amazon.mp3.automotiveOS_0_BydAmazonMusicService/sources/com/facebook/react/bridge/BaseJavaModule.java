package com.facebook.react.bridge;

import androidx.annotation.Nullable;
import java.util.Map;

/* JADX INFO: loaded from: classes6.dex */
public abstract class BaseJavaModule implements NativeModule {
    public static final String METHOD_TYPE_ASYNC = "async";
    public static final String METHOD_TYPE_PROMISE = "promise";
    public static final String METHOD_TYPE_SYNC = "sync";

    @Override // com.facebook.react.bridge.NativeModule
    public boolean canOverrideExistingModule() {
        return false;
    }

    @Nullable
    public Map<String, Object> getConstants() {
        return null;
    }

    public boolean hasConstants() {
        return false;
    }

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void initialize() {
    }

    @Override // com.facebook.react.bridge.NativeModule
    public void onCatalystInstanceDestroy() {
    }

    @Override // com.facebook.react.bridge.NativeModule, com.facebook.react.turbomodule.core.interfaces.TurboModule
    public void invalidate() {
        onCatalystInstanceDestroy();
    }
}

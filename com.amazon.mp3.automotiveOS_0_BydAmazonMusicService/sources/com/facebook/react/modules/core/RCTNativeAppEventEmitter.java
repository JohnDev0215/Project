package com.facebook.react.modules.core;

import androidx.annotation.Nullable;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.JavaScriptModule;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public interface RCTNativeAppEventEmitter extends JavaScriptModule {
    void emit(String str, @Nullable Object obj);
}

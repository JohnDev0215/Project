package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
class JSCJavaScriptExecutor extends JavaScriptExecutor {
    private static native HybridData initHybrid(ReadableNativeMap readableNativeMap);

    @Override // com.facebook.react.bridge.JavaScriptExecutor
    public String getName() {
        return "JSCJavaScriptExecutor";
    }

    static {
        ReactBridge.staticInit();
    }

    JSCJavaScriptExecutor(ReadableNativeMap readableNativeMap) {
        super(initHybrid(readableNativeMap));
    }
}

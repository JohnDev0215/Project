package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public abstract class NativeArray implements NativeArrayInterface {

    @DoNotStrip
    private HybridData mHybridData;

    @Override // com.facebook.react.bridge.NativeArrayInterface
    public native String toString();

    static {
        ReactBridge.staticInit();
    }

    protected NativeArray(HybridData hybridData) {
        this.mHybridData = hybridData;
    }
}

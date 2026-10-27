package com.facebook.react.bridge;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class CxxCallbackImpl implements Callback {

    @DoNotStrip
    private final HybridData mHybridData;

    private native void nativeInvoke(NativeArray nativeArray);

    @DoNotStrip
    private CxxCallbackImpl(HybridData hybridData) {
        this.mHybridData = hybridData;
    }

    @Override // com.facebook.react.bridge.Callback
    public void invoke(Object... objArr) {
        nativeInvoke(Arguments.fromJavaArgs(objArr));
    }
}

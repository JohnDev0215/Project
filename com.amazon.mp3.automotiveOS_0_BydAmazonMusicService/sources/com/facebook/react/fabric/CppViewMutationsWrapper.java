package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@SuppressLint({"MissingNativeLoadLibrary"})
public class CppViewMutationsWrapper {

    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    private static native HybridData initHybrid();

    public native void runCppViewMutations();

    static {
        FabricSoLoader.staticInit();
    }

    private CppViewMutationsWrapper() {
    }
}

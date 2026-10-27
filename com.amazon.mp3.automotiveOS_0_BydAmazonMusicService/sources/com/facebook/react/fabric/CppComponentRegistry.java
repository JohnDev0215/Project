package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class CppComponentRegistry {
    private static native HybridData initHybrid();

    static {
        FabricSoLoader.staticInit();
    }
}

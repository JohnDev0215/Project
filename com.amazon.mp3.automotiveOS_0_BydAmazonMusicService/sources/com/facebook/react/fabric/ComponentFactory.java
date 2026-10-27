package com.facebook.react.fabric;

import androidx.annotation.NonNull;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class ComponentFactory {

    @NonNull
    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    @DoNotStrip
    private static native HybridData initHybrid();

    static {
        FabricSoLoader.staticInit();
    }

    @DoNotStrip
    public ComponentFactory() {
    }
}

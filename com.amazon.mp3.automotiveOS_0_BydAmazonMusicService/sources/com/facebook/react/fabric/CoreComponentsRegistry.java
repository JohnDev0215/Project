package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class CoreComponentsRegistry {

    @DoNotStrip
    private final HybridData mHybridData;

    @DoNotStrip
    private native HybridData initHybrid(ComponentFactory componentFactory);

    static {
        FabricSoLoader.staticInit();
    }

    @DoNotStrip
    private CoreComponentsRegistry(ComponentFactory componentFactory) {
        this.mHybridData = initHybrid(componentFactory);
    }

    @DoNotStrip
    public static CoreComponentsRegistry register(ComponentFactory componentFactory) {
        return new CoreComponentsRegistry(componentFactory);
    }
}

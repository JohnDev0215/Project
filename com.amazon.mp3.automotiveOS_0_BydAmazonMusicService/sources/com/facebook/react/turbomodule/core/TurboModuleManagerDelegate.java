package com.facebook.react.turbomodule.core;

import androidx.annotation.Nullable;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.CxxModuleWrapper;
import com.facebook.react.turbomodule.core.interfaces.TurboModule;
import com.facebook.soloader.SoLoader;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public abstract class TurboModuleManagerDelegate {
    private static volatile boolean sIsSoLibraryLoaded;

    @DoNotStrip
    private final HybridData mHybridData;

    @Nullable
    public abstract CxxModuleWrapper getLegacyCxxModule(String str);

    @Nullable
    public abstract TurboModule getModule(String str);

    protected abstract HybridData initHybrid();

    protected TurboModuleManagerDelegate() {
        maybeLoadOtherSoLibraries();
        maybeLoadSoLibrary();
        this.mHybridData = initHybrid();
    }

    public List<String> getEagerInitModuleNames() {
        return new ArrayList();
    }

    private static synchronized void maybeLoadSoLibrary() {
        if (!sIsSoLibraryLoaded) {
            SoLoader.loadLibrary("turbomodulejsijni");
            sIsSoLibraryLoaded = true;
        }
    }

    protected synchronized void maybeLoadOtherSoLibraries() {
    }
}

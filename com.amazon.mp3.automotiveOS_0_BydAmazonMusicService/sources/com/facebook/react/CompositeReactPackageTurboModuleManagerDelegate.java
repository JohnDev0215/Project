package com.facebook.react;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.turbomodule.core.TurboModuleManagerDelegate;
import com.facebook.soloader.SoLoader;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class CompositeReactPackageTurboModuleManagerDelegate extends ReactPackageTurboModuleManagerDelegate {
    private static volatile boolean sIsSoLibraryLoaded;

    private native void addTurboModuleManagerDelegate(TurboModuleManagerDelegate turboModuleManagerDelegate);

    @Override // com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    protected native HybridData initHybrid();

    @Override // com.facebook.react.turbomodule.core.TurboModuleManagerDelegate
    protected synchronized void maybeLoadOtherSoLibraries() {
        if (!sIsSoLibraryLoaded) {
            SoLoader.loadLibrary("turbomodulejsijni");
            sIsSoLibraryLoaded = true;
        }
    }
}

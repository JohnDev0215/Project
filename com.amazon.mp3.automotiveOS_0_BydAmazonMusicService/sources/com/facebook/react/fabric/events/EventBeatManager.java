package com.facebook.react.fabric.events;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.fabric.FabricSoLoader;
import com.facebook.react.uimanager.events.BatchEventDispatchedListener;

/* JADX INFO: loaded from: classes6.dex */
@SuppressLint({"MissingNativeLoadLibrary"})
public class EventBeatManager implements BatchEventDispatchedListener {

    @DoNotStrip
    private final HybridData mHybridData = initHybrid();
    private final ReactApplicationContext mReactApplicationContext;

    private static native HybridData initHybrid();

    private native void tick();

    static {
        FabricSoLoader.staticInit();
    }

    public EventBeatManager(@NonNull ReactApplicationContext reactApplicationContext) {
        this.mReactApplicationContext = reactApplicationContext;
    }

    @Override // com.facebook.react.uimanager.events.BatchEventDispatchedListener
    public void onBatchEventDispatched() {
        tick();
    }
}

package com.facebook.react.fabric;

import android.annotation.SuppressLint;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.NativeMap;
import com.facebook.react.bridge.ReadableNativeMap;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.common.mapbuffer.ReadableMapBuffer;
import com.facebook.react.uimanager.StateWrapper;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
@SuppressLint({"MissingNativeLoadLibrary"})
public class StateWrapperImpl implements StateWrapper {
    private volatile boolean mDestroyed = false;

    @DoNotStrip
    private final HybridData mHybridData = initHybrid();

    private native ReadableNativeMap getStateDataImpl();

    private native ReadableMapBuffer getStateMapBufferDataImpl();

    private static native HybridData initHybrid();

    public native void updateStateImpl(@NonNull NativeMap nativeMap);

    static {
        FabricSoLoader.staticInit();
    }

    private StateWrapperImpl() {
    }

    @Override // com.facebook.react.uimanager.StateWrapper
    @Nullable
    public ReadableMapBuffer getStateDataMapBuffer() {
        if (this.mDestroyed) {
            FLog.e("StateWrapperImpl", "Race between StateWrapperImpl destruction and getState");
            return null;
        }
        return getStateMapBufferDataImpl();
    }

    @Override // com.facebook.react.uimanager.StateWrapper
    @Nullable
    public ReadableNativeMap getStateData() {
        if (this.mDestroyed) {
            FLog.e("StateWrapperImpl", "Race between StateWrapperImpl destruction and getState");
            return null;
        }
        return getStateDataImpl();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.facebook.react.uimanager.StateWrapper
    public void updateState(@NonNull WritableMap writableMap) {
        if (this.mDestroyed) {
            FLog.e("StateWrapperImpl", "Race between StateWrapperImpl destruction and updateState");
        } else {
            updateStateImpl((NativeMap) writableMap);
        }
    }

    @Override // com.facebook.react.uimanager.StateWrapper
    public void destroyState() {
        if (this.mDestroyed) {
            return;
        }
        this.mDestroyed = true;
        this.mHybridData.resetNative();
    }
}

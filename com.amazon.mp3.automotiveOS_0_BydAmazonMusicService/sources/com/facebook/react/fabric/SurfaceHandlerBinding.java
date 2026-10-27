package com.facebook.react.fabric;

import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;
import com.facebook.react.bridge.NativeMap;

/* JADX INFO: loaded from: classes6.dex */
public class SurfaceHandlerBinding implements SurfaceHandler {

    @DoNotStrip
    private final HybridData mHybridData;

    private native String getModuleNameNative();

    private native int getSurfaceIdNative();

    private static native HybridData initHybrid(int i, String str);

    private native boolean isRunningNative();

    private native void setDisplayModeNative(int i);

    private native void setLayoutConstraintsNative(float f, float f2, float f3, float f4, float f5, float f6, boolean z, boolean z2, float f7);

    private native void setPropsNative(NativeMap nativeMap);

    private native void setSurfaceIdNative(int i);

    private native void startNative();

    private native void stopNative();

    static {
        FabricSoLoader.staticInit();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public int getSurfaceId() {
        return getSurfaceIdNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void setSurfaceId(int i) {
        setSurfaceIdNative(i);
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public String getModuleName() {
        return getModuleNameNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void start() {
        startNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void stop() {
        stopNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public boolean isRunning() {
        return isRunningNative();
    }

    @Override // com.facebook.react.fabric.SurfaceHandler
    public void setMountable(boolean z) {
        setDisplayModeNative(!z ? 1 : 0);
    }
}

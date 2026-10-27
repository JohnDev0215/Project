package com.facebook.react.bridge;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.infer.annotation.Assertions;
import com.facebook.jni.HybridData;
import com.facebook.proguard.annotations.DoNotStrip;

/* JADX INFO: loaded from: classes6.dex */
@DoNotStrip
public class WritableNativeMap extends ReadableNativeMap implements WritableMap {
    private static native HybridData initHybrid();

    private native void mergeNativeMap(ReadableNativeMap readableNativeMap);

    private native void putNativeArray(String str, ReadableNativeArray readableNativeArray);

    private native void putNativeMap(String str, ReadableNativeMap readableNativeMap);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putBoolean(@NonNull String str, boolean z);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putDouble(@NonNull String str, double d);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putInt(@NonNull String str, int i);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putNull(@NonNull String str);

    @Override // com.facebook.react.bridge.WritableMap
    public native void putString(@NonNull String str, @Nullable String str2);

    static {
        ReactBridge.staticInit();
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putMap(@NonNull String str, @Nullable ReadableMap readableMap) {
        Assertions.assertCondition(readableMap == null || (readableMap instanceof ReadableNativeMap), "Illegal type provided");
        putNativeMap(str, (ReadableNativeMap) readableMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void putArray(@NonNull String str, @Nullable ReadableArray readableArray) {
        Assertions.assertCondition(readableArray == null || (readableArray instanceof ReadableNativeArray), "Illegal type provided");
        putNativeArray(str, (ReadableNativeArray) readableArray);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public void merge(@NonNull ReadableMap readableMap) {
        Assertions.assertCondition(readableMap instanceof ReadableNativeMap, "Illegal type provided");
        mergeNativeMap((ReadableNativeMap) readableMap);
    }

    @Override // com.facebook.react.bridge.WritableMap
    public WritableMap copy() {
        WritableNativeMap writableNativeMap = new WritableNativeMap();
        writableNativeMap.merge(this);
        return writableNativeMap;
    }

    public WritableNativeMap() {
        super(initHybrid());
    }
}

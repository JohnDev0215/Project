package com.facebook.react.fabric.events;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.uimanager.events.RCTModernEventEmitter;
import com.facebook.react.uimanager.events.TouchEvent;
import com.facebook.react.uimanager.events.TouchesHelper;
import com.facebook.systrace.Systrace;

/* JADX INFO: loaded from: classes6.dex */
public class FabricEventEmitter implements RCTModernEventEmitter {
    private static final String TAG = "FabricEventEmitter";

    @NonNull
    private final FabricUIManager mUIManager;

    public FabricEventEmitter(@NonNull FabricUIManager fabricUIManager) {
        this.mUIManager = fabricUIManager;
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveEvent(int i, @NonNull String str, @Nullable WritableMap writableMap) {
        receiveEvent(-1, i, str, writableMap);
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int i, int i2, String str, @Nullable WritableMap writableMap) {
        receiveEvent(i, i2, str, false, 0, writableMap, 2);
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveEvent(int i, int i2, String str, boolean z, int i3, @Nullable WritableMap writableMap, int i4) {
        Systrace.beginSection(0L, "FabricEventEmitter.receiveEvent('" + str + "')");
        this.mUIManager.receiveEvent(i, i2, str, z, i3, writableMap, i4);
        Systrace.endSection(0L);
    }

    @Override // com.facebook.react.uimanager.events.RCTEventEmitter
    public void receiveTouches(@NonNull String str, @NonNull WritableArray writableArray, @NonNull WritableArray writableArray2) {
        throw new IllegalStateException("EventEmitter#receiveTouches is not supported by Fabric");
    }

    @Override // com.facebook.react.uimanager.events.RCTModernEventEmitter
    public void receiveTouches(TouchEvent touchEvent) {
        TouchesHelper.sendTouchEvent(this, touchEvent);
    }
}

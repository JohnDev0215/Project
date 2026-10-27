package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.fabric.mounting.MountingManager;

/* JADX INFO: loaded from: classes6.dex */
public class DispatchStringCommandMountItem extends DispatchCommandMountItem {

    @Nullable
    private final ReadableArray mCommandArgs;

    @NonNull
    private final String mCommandId;
    private final int mReactTag;
    private final int mSurfaceId;

    public DispatchStringCommandMountItem(int i, int i2, @NonNull String str, @Nullable ReadableArray readableArray) {
        this.mSurfaceId = i;
        this.mReactTag = i2;
        this.mCommandId = str;
        this.mCommandArgs = readableArray;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.DispatchCommandMountItem, com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        mountingManager.receiveCommand(this.mSurfaceId, this.mReactTag, this.mCommandId, this.mCommandArgs);
    }

    public String toString() {
        return "DispatchStringCommandMountItem [" + this.mReactTag + "] " + this.mCommandId;
    }
}

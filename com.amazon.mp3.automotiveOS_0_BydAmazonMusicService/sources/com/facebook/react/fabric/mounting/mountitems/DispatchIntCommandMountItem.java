package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.react.bridge.ReadableArray;
import com.facebook.react.fabric.mounting.MountingManager;

/* JADX INFO: loaded from: classes6.dex */
public class DispatchIntCommandMountItem extends DispatchCommandMountItem {

    @Nullable
    private final ReadableArray mCommandArgs;
    private final int mCommandId;
    private final int mReactTag;
    private final int mSurfaceId;

    public DispatchIntCommandMountItem(int i, int i2, int i3, @Nullable ReadableArray readableArray) {
        this.mSurfaceId = i;
        this.mReactTag = i2;
        this.mCommandId = i3;
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
        return "DispatchIntCommandMountItem [" + this.mReactTag + "] " + this.mCommandId;
    }
}

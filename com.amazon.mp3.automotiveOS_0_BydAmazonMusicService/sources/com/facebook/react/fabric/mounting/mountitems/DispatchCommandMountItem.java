package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.AnyThread;
import androidx.annotation.UiThread;

/* JADX INFO: loaded from: classes6.dex */
public abstract class DispatchCommandMountItem implements MountItem {
    private int mNumRetries = 0;

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    @AnyThread
    public abstract /* synthetic */ int getSurfaceId();

    @UiThread
    public void incrementRetries() {
        this.mNumRetries++;
    }

    @UiThread
    public int getRetries() {
        return this.mNumRetries;
    }
}

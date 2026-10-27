package com.facebook.react.fabric.mounting.mountitems;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.facebook.common.logging.FLog;
import com.facebook.react.fabric.FabricUIManager;
import com.facebook.react.fabric.events.EventEmitterWrapper;
import com.facebook.react.fabric.mounting.MountingManager;
import com.facebook.react.fabric.mounting.SurfaceMountingManager;
import com.facebook.react.uimanager.StateWrapper;

/* JADX INFO: loaded from: classes6.dex */
public class PreAllocateViewMountItem implements MountItem {

    @NonNull
    private final String mComponent;

    @Nullable
    private final EventEmitterWrapper mEventEmitterWrapper;
    private final boolean mIsLayoutable;

    @Nullable
    private final Object mProps;
    private final int mReactTag;

    @Nullable
    private final StateWrapper mStateWrapper;
    private final int mSurfaceId;

    public PreAllocateViewMountItem(int i, int i2, @NonNull String str, @Nullable Object obj, @NonNull StateWrapper stateWrapper, @Nullable EventEmitterWrapper eventEmitterWrapper, boolean z) {
        this.mComponent = str;
        this.mSurfaceId = i;
        this.mProps = obj;
        this.mStateWrapper = stateWrapper;
        this.mEventEmitterWrapper = eventEmitterWrapper;
        this.mReactTag = i2;
        this.mIsLayoutable = z;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public int getSurfaceId() {
        return this.mSurfaceId;
    }

    @Override // com.facebook.react.fabric.mounting.mountitems.MountItem
    public void execute(@NonNull MountingManager mountingManager) {
        SurfaceMountingManager surfaceManager = mountingManager.getSurfaceManager(this.mSurfaceId);
        if (surfaceManager == null) {
            FLog.e(FabricUIManager.TAG, "Skipping View PreAllocation; no SurfaceMountingManager found for [" + this.mSurfaceId + "]");
            return;
        }
        surfaceManager.preallocateView(this.mComponent, this.mReactTag, this.mProps, this.mStateWrapper, this.mEventEmitterWrapper, this.mIsLayoutable);
    }

    public String toString() {
        return "PreAllocateViewMountItem [" + this.mReactTag + "] - component: " + this.mComponent + " surfaceId: " + this.mSurfaceId + " isLayoutable: " + this.mIsLayoutable;
    }
}

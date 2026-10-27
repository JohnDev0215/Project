package com.amazon.mp3.cloudqueue;

import androidx.annotation.Nullable;
import com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy;
import com.amazon.musicplayqueueservice.client.v2.track.BooleanActionStrategy;
import com.amazon.musicplayqueueservice.client.v2.track.Capability;
import com.amazon.musicplayqueueservice.client.v2.track.SkipLimitIotActionStrategy;

/* JADX INFO: loaded from: classes3.dex */
public class PlaybackActionCapability {
    private Capability mCapability;

    public PlaybackActionCapability(Capability capability) {
        this.mCapability = capability;
    }

    public boolean canDisplay() {
        return this.mCapability != null;
    }

    public int getActionStatus() {
        Capability capability = this.mCapability;
        if (capability == null) {
            return 1;
        }
        AbstractActionStrategy actionStrategy = capability.getActionStrategy();
        return actionStrategy instanceof BooleanActionStrategy ? !((BooleanActionStrategy) actionStrategy).isActionAvailable() ? 1 : 0 : actionStrategy instanceof SkipLimitIotActionStrategy ? 2 : 1;
    }

    @Nullable
    public String getIotThingShadowName() {
        Capability capability = this.mCapability;
        if (capability == null) {
            return null;
        }
        AbstractActionStrategy actionStrategy = capability.getActionStrategy();
        if (actionStrategy instanceof SkipLimitIotActionStrategy) {
            return ((SkipLimitIotActionStrategy) actionStrategy).getThingShadowName();
        }
        return null;
    }
}

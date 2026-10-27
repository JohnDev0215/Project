package com.amazon.avod.media.playback.support;

import com.google.common.base.Preconditions;

/* JADX INFO: loaded from: classes2.dex */
public class RendererCapabilities {
    public static final RendererCapabilities NONE = new RendererCapabilities(false, false, false, false);
    private final boolean mIsAudioTrackRecreationSupported;
    private final boolean mIsBackgroundAudioPlaybackSupported;
    private final boolean mIsPlaybackSpeedAdjustmentSupported;
    private final boolean mIsVideoTrackRecreationSupported;

    public RendererCapabilities(boolean z, boolean z2, boolean z3, boolean z4) {
        this.mIsAudioTrackRecreationSupported = z;
        this.mIsVideoTrackRecreationSupported = z2;
        this.mIsBackgroundAudioPlaybackSupported = z3;
        this.mIsPlaybackSpeedAdjustmentSupported = z4;
        if (z3) {
            Preconditions.checkState(z2, "Background audio playback needs video track recreation support.");
        }
    }

    public boolean isAudioTrackRecreationSupported() {
        return this.mIsAudioTrackRecreationSupported;
    }

    public boolean isVideoTrackRecreationSupported() {
        return this.mIsVideoTrackRecreationSupported;
    }

    public boolean isBackgroundAudioPlaybackSupported() {
        return this.mIsBackgroundAudioPlaybackSupported;
    }

    public boolean isPlaybackSpeedAdjustmentSupported() {
        return this.mIsPlaybackSpeedAdjustmentSupported;
    }
}

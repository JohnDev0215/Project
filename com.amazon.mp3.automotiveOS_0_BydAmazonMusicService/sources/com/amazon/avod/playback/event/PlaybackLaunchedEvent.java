package com.amazon.avod.playback.event;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.media.playback.SurfaceHandlingMode;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackLaunchedEvent extends PlaybackEvent {
    private final SurfaceHandlingMode mSurfaceHandlingMode;
    private final boolean mWasPreparedAtLaunch;

    public PlaybackLaunchedEvent(TimeSpan timeSpan, boolean z, @Nonnull SurfaceHandlingMode surfaceHandlingMode) {
        super(timeSpan);
        this.mWasPreparedAtLaunch = z;
        this.mSurfaceHandlingMode = (SurfaceHandlingMode) Preconditions.checkNotNull(surfaceHandlingMode, "surfaceHandlingMode");
    }

    public boolean wasPreparedAtLaunch() {
        return this.mWasPreparedAtLaunch;
    }

    @Nonnull
    public SurfaceHandlingMode getSurfaceHandlingMode() {
        return this.mSurfaceHandlingMode;
    }
}

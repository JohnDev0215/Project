package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.avod.playback.player.actions.SeekAction;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class LiveManifestRefreshEvent extends PlaybackEvent {
    private final boolean mIsInCachedRegion;
    private final TimeSpan mManifestRefreshDuration;
    private final SeekAction mSeekAction;
    private final TimeSpan mTargetTimeStamp;

    public LiveManifestRefreshEvent(@Nonnull TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2, @Nonnull SeekAction seekAction, @Nonnull TimeSpan timeSpan3, boolean z) {
        super((TimeSpan) Preconditions.checkNotNull(timeSpan, "eventTimeStamp"));
        this.mTargetTimeStamp = (TimeSpan) Preconditions.checkNotNull(timeSpan2, "targetTimeStamp");
        this.mSeekAction = (SeekAction) Preconditions.checkNotNull(seekAction, "seekAction");
        this.mManifestRefreshDuration = (TimeSpan) Preconditions.checkNotNull(timeSpan3, "manifestRefreshDuration");
        this.mIsInCachedRegion = z;
    }

    public boolean isInCachedRegion() {
        return this.mIsInCachedRegion;
    }

    @Nonnull
    public TimeSpan getTargetTime() {
        return this.mTargetTimeStamp;
    }

    @Nonnull
    public SeekAction getSeekAction() {
        return this.mSeekAction;
    }

    @Nonnull
    public TimeSpan getManifestRefreshDuration() {
        return this.mManifestRefreshDuration;
    }
}

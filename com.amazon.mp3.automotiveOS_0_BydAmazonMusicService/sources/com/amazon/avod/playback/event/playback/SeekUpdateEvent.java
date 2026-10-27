package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SeekUpdateEvent extends PlaybackEvent {
    private final boolean mNewInCachedRegion;
    private final TimeSpan mNewSeekTime;
    private final boolean mOldInCachedRegion;
    private final TimeSpan mOldSeekTime;

    public SeekUpdateEvent(TimeSpan timeSpan, @Nonnull TimeSpan timeSpan2, boolean z, @Nonnull TimeSpan timeSpan3, boolean z2) {
        super(timeSpan);
        this.mOldSeekTime = (TimeSpan) Preconditions.checkNotNull(timeSpan2);
        this.mOldInCachedRegion = z;
        this.mNewSeekTime = (TimeSpan) Preconditions.checkNotNull(timeSpan3);
        this.mNewInCachedRegion = z2;
    }

    public boolean wasInCachedRegion() {
        return this.mOldInCachedRegion;
    }

    public boolean isNowInCachedRegion() {
        return this.mNewInCachedRegion;
    }
}

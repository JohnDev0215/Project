package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SeekEndEvent extends PlaybackEvent {
    private final boolean mIsInCachedRegion;
    private final String mSeekCause;
    private final TimeSpan mTargetSeekTime;

    public SeekEndEvent(TimeSpan timeSpan, TimeSpan timeSpan2, boolean z, @Nonnull String str) {
        super(timeSpan);
        this.mTargetSeekTime = timeSpan2;
        this.mIsInCachedRegion = z;
        this.mSeekCause = (String) Preconditions.checkNotNull(str, "seekCause");
    }

    public String getSeekCause() {
        return this.mSeekCause;
    }

    public TimeSpan getTargetSeekTime() {
        return this.mTargetSeekTime;
    }

    public boolean isInCachedRegion() {
        return this.mIsInCachedRegion;
    }
}

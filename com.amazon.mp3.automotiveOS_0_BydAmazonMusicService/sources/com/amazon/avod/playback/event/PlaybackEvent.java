package com.amazon.avod.playback.event;

import com.amazon.avod.media.TimeSpan;
import com.google.common.base.Preconditions;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackEvent {
    private final String mConsumptionId;
    private final TimeSpan mEpochTimeStamp;
    private final TimeSpan mEventTimeStamp;

    public PlaybackEvent(@Nonnull TimeSpan timeSpan) {
        this(timeSpan, null);
    }

    public PlaybackEvent(@Nonnull TimeSpan timeSpan, @Nullable String str) {
        this.mEventTimeStamp = (TimeSpan) Preconditions.checkNotNull(timeSpan, "eventTimeStamp");
        this.mEpochTimeStamp = TimeSpan.now();
        this.mConsumptionId = str;
    }

    @Nonnull
    public TimeSpan getEpochTimeStamp() {
        return this.mEpochTimeStamp;
    }

    @Nonnull
    public TimeSpan getEventTimeStamp() {
        return this.mEventTimeStamp;
    }

    @Nullable
    public String getConsumptionId() {
        return this.mConsumptionId;
    }
}

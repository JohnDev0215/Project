package com.amazon.avod.media.events.model;

import com.amazon.avod.media.events.model.MediaEvent;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.Preconditions;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnegative;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEventEnvelop {
    private final MediaEvent mMediaEvent;
    private final long mRelativeTimeInMillis;
    private final long mSequence;
    private final long mTimestamp;

    public MediaEventEnvelop(@Nonnegative long j, @Nonnull MediaEvent mediaEvent) {
        this(j, System.currentTimeMillis(), mediaEvent);
    }

    public MediaEventEnvelop(@Nonnegative long j, @Nonnegative long j2, @Nonnull MediaEvent mediaEvent) {
        Preconditions.checkArgument(j >= 0, "sequence must be non-negative");
        Preconditions.checkArgument(j2 >= 0, "timestamp must be non-negative");
        this.mSequence = j;
        this.mTimestamp = j2;
        this.mRelativeTimeInMillis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime());
        this.mMediaEvent = (MediaEvent) Preconditions.checkNotNull(mediaEvent, "mediaEvent");
    }

    @Nonnegative
    public long getSequence() {
        return this.mSequence;
    }

    @Nonnegative
    public long getTimestamp() {
        return this.mTimestamp;
    }

    @JsonProperty("reltime")
    public long getRelativeTimeInMillis() {
        return this.mRelativeTimeInMillis;
    }

    @JsonProperty("_type")
    @Nonnull
    public MediaEvent.MediaEventType getType() {
        return this.mMediaEvent.getMediaEventType();
    }

    @JsonProperty("body")
    @Nonnull
    public MediaEvent getMediaEvent() {
        return this.mMediaEvent;
    }
}

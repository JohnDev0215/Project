package com.amazon.avod.playback.event.playback;

import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackCompletedEvent extends PlaybackEvent {
    private final ContentSessionContext mContentSessionContext;
    private final boolean mIsPlayerCloseDriven;

    public PlaybackCompletedEvent(TimeSpan timeSpan) {
        this(timeSpan, false, null);
    }

    public PlaybackCompletedEvent(TimeSpan timeSpan, boolean z, @Nullable ContentSessionContext contentSessionContext) {
        super(timeSpan);
        this.mIsPlayerCloseDriven = z;
        this.mContentSessionContext = contentSessionContext;
    }

    public boolean isPlayerCloseDriven() {
        return this.mIsPlayerCloseDriven;
    }

    @Nullable
    public ContentSessionContext getContext() {
        return this.mContentSessionContext;
    }
}

package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class TrackingUriRefreshEvent extends PlaybackEvent {
    public TrackingUriRefreshEvent(@Nonnull TimeSpan timeSpan) {
        super(timeSpan);
    }
}

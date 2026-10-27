package com.amazon.avod.playback.sye.events;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class SyePlaybackStartEvent extends PlaybackEvent {
    public SyePlaybackStartEvent(@Nonnull TimeSpan timeSpan) {
        super(timeSpan);
    }
}

package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;

/* JADX INFO: loaded from: classes2.dex */
public class PauseEvent extends PlaybackEvent {
    public PauseEvent(TimeSpan timeSpan) {
        super(timeSpan);
    }
}

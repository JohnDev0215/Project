package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;

/* JADX INFO: loaded from: classes2.dex */
public class PlaybackLoadedEvent extends PlaybackEvent {
    public PlaybackLoadedEvent(TimeSpan timeSpan, String str) {
        super(timeSpan, str);
    }
}

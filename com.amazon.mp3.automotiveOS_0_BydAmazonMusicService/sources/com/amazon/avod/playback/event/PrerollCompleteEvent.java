package com.amazon.avod.playback.event;

import com.amazon.avod.media.TimeSpan;

/* JADX INFO: loaded from: classes2.dex */
public class PrerollCompleteEvent extends PlaybackEvent {
    public PrerollCompleteEvent() {
        super(TimeSpan.now());
    }
}

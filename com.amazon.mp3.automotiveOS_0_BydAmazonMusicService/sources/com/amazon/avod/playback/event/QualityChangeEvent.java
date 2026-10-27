package com.amazon.avod.playback.event;

import com.amazon.avod.media.TimeSpan;

/* JADX INFO: loaded from: classes2.dex */
public abstract class QualityChangeEvent extends PlaybackEvent {
    private final int mBitrate;

    public QualityChangeEvent(TimeSpan timeSpan, int i) {
        super(timeSpan);
        this.mBitrate = i;
    }

    public int getBitrate() {
        return this.mBitrate;
    }
}

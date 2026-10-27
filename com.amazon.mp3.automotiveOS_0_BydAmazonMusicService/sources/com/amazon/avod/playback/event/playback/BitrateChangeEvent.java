package com.amazon.avod.playback.event.playback;

import com.amazon.avod.media.TimeSpan;
import com.amazon.avod.playback.event.PlaybackEvent;
import com.amazon.avod.playback.sampling.SampleType;

/* JADX INFO: loaded from: classes2.dex */
public class BitrateChangeEvent extends PlaybackEvent {
    private final int mNewBitrate;
    private final int mOldBitrate;
    private final SampleType mSampleType;

    public BitrateChangeEvent(TimeSpan timeSpan, int i, int i2, SampleType sampleType) {
        super(timeSpan);
        this.mNewBitrate = i;
        this.mOldBitrate = i2;
        this.mSampleType = sampleType;
    }

    public int getNewBitrate() {
        return this.mNewBitrate;
    }

    public int getOldBitrate() {
        return this.mOldBitrate;
    }

    public SampleType getSampleType() {
        return this.mSampleType;
    }
}

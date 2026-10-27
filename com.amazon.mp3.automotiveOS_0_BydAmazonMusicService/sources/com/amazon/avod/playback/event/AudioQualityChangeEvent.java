package com.amazon.avod.playback.event;

import com.amazon.avod.media.AudioFormat;
import com.amazon.avod.media.TimeSpan;

/* JADX INFO: loaded from: classes2.dex */
public class AudioQualityChangeEvent extends QualityChangeEvent {
    private final AudioFormat mAudioFormat;

    public AudioQualityChangeEvent(TimeSpan timeSpan, int i, AudioFormat audioFormat) {
        super(timeSpan, i);
        this.mAudioFormat = audioFormat;
    }

    public AudioFormat getAudioFormat() {
        return this.mAudioFormat;
    }
}

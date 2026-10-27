package com.amazon.avod.playback.sampling;

/* JADX INFO: loaded from: classes2.dex */
public enum SampleReadResult {
    SAMPLE_READY,
    NO_MORE_DATA,
    WAITING_FOR_IO,
    SAMPLE_LARGER_THAN_DESTINATION,
    NO_STREAM_AVAILABLE
}

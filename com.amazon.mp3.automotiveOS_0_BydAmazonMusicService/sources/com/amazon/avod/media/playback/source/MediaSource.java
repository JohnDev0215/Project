package com.amazon.avod.media.playback.source;

import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.renderer.shared.SampleMetadata;
import com.amazon.avod.playback.sampling.SampleHolder;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface MediaSource {
    void advance();

    void dispose();

    void flush();

    long getBufferedBytes();

    long getBufferedTimeUs();

    long getCapacityBytes();

    long getCapacityUs();

    long getLastSubmittedTimeInNanos();

    long getSampleTimeUs();

    boolean hasCapacityForMoreSamples();

    boolean hasNext();

    boolean hasReadEndOfStream();

    SampleMetadata readSampleData(@Nonnull ByteBuffer byteBuffer) throws PlaybackException;

    void submitSample(@Nonnull SampleHolder sampleHolder) throws PlaybackException;
}

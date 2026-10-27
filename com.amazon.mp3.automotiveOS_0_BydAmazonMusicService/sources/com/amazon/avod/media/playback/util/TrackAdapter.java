package com.amazon.avod.media.playback.util;

import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface TrackAdapter {
    long writeAdaptedSample(@Nonnull ByteBuffer byteBuffer, @Nonnull CircularByteBuffer circularByteBuffer, long j);

    void writeAdaptedSample(@Nonnull ByteBuffer byteBuffer, @Nonnull ByteBuffer byteBuffer2, long j, @Nonnull TrackAdaptionStatus trackAdaptionStatus);
}

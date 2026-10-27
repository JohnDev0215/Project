package com.amazon.avod.media.playback.util;

import com.amazon.avod.playback.PlaybackException;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class AudioTrackAdapter implements TrackAdapter {
    @Override // com.amazon.avod.media.playback.util.TrackAdapter
    public void writeAdaptedSample(@Nonnull ByteBuffer byteBuffer, @Nonnull ByteBuffer byteBuffer2, long j, @Nonnull TrackAdaptionStatus trackAdaptionStatus) {
        Preconditions.checkNotNull(byteBuffer, "src");
        Preconditions.checkNotNull(byteBuffer, "destination");
        int iMin = (int) Math.min(byteBuffer.remaining(), j);
        if (iMin > byteBuffer2.capacity()) {
            trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_DST_BUFFER_SIZE_TOO_SMALL, 0L, -1L, byteBuffer.remaining(), byteBuffer2.remaining(), -1L);
            return;
        }
        byteBuffer.limit(iMin);
        byteBuffer2.put(byteBuffer);
        trackAdaptionStatus.set(null, iMin, -1L, byteBuffer.remaining(), byteBuffer2.remaining(), -1L);
    }

    @Override // com.amazon.avod.media.playback.util.TrackAdapter
    public long writeAdaptedSample(@Nonnull ByteBuffer byteBuffer, @Nonnull CircularByteBuffer circularByteBuffer, long j) {
        Preconditions.checkNotNull(byteBuffer, "src");
        Preconditions.checkNotNull(byteBuffer, "destination");
        int iMin = (int) Math.min(byteBuffer.remaining(), j);
        if (iMin > circularByteBuffer.capacity()) {
            return 0L;
        }
        circularByteBuffer.put(byteBuffer, iMin);
        return iMin;
    }
}

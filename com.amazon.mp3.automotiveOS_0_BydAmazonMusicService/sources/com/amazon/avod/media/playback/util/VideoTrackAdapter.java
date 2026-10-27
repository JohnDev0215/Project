package com.amazon.avod.media.playback.util;

import com.amazon.avod.media.ExternalFourCCMapper;
import com.amazon.avod.media.VideoStreamType;
import com.amazon.avod.playback.PlaybackException;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class VideoTrackAdapter implements TrackAdapter {
    private static final byte[] NAL_UNIT_PREFIX = {0, 0, 0, 1};
    private final VideoStreamType mVideoStreamType;

    public VideoTrackAdapter(@Nonnull String str) {
        Preconditions.checkNotNull(str, "fourCC");
        this.mVideoStreamType = (VideoStreamType) Preconditions.checkNotNull(ExternalFourCCMapper.INSTANCE.mapVideoCodecToStreamType(str), "videoStreamType");
    }

    @Override // com.amazon.avod.media.playback.util.TrackAdapter
    public void writeAdaptedSample(@Nonnull ByteBuffer byteBuffer, @Nonnull ByteBuffer byteBuffer2, long j, @Nonnull TrackAdaptionStatus trackAdaptionStatus) {
        long jLimit;
        Preconditions.checkNotNull(byteBuffer, "src");
        Preconditions.checkNotNull(byteBuffer2, "destination");
        if (byteBuffer.remaining() > byteBuffer2.capacity()) {
            trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_DST_BUFFER_SIZE_TOO_SMALL, 0L, j, byteBuffer.remaining(), byteBuffer2.remaining(), -1L);
            return;
        }
        if (byteBuffer.remaining() < j) {
            trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_SRC_BUFFER_SIZE_TOO_SMALL, 0L, j, byteBuffer.remaining(), byteBuffer2.remaining(), -1L);
            return;
        }
        if (this.mVideoStreamType != VideoStreamType.AV01) {
            jLimit = 0;
            while (true) {
                int iRemaining = byteBuffer.remaining();
                byte[] bArr = NAL_UNIT_PREFIX;
                if (iRemaining <= bArr.length) {
                    break;
                }
                int i = byteBuffer.getInt();
                if (i <= 0) {
                    trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_NAL_SIZE_EMPTY, jLimit, j, byteBuffer.remaining(), byteBuffer2.remaining(), i);
                    return;
                }
                int length = bArr.length + i;
                long j2 = ((long) length) + jLimit;
                if (j2 > j) {
                    trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_SIZE_AFTER_COPY_GREATER_THAN_DST_BUFFER_SIZE, jLimit, j, byteBuffer.remaining(), byteBuffer2.remaining(), i);
                    return;
                }
                if (i > byteBuffer.remaining()) {
                    trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_NAL_SIZE_GREATER_THAN_SAMPLE_SIZE, jLimit, j, byteBuffer.remaining(), byteBuffer2.remaining(), i);
                    return;
                }
                if (length > byteBuffer2.remaining()) {
                    trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_NAL_SIZE_GREATER_THAN_DST_BUFFER_SIZE, jLimit, j, byteBuffer.remaining(), byteBuffer2.remaining(), i);
                    return;
                }
                byteBuffer2.put(bArr, 0, bArr.length);
                byteBuffer.limit(byteBuffer.position() + i);
                byteBuffer2.put(byteBuffer);
                byteBuffer.limit((int) j);
                jLimit = j2;
            }
        } else {
            byteBuffer2.put(byteBuffer);
            jLimit = byteBuffer.limit();
            byteBuffer.limit((int) j);
        }
        if (jLimit != j) {
            trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_BYTES_COPIED_NOT_EQUAL_TO_SRC_BUFFER_SIZE, jLimit, j, byteBuffer.remaining(), byteBuffer2.remaining(), -1L);
        } else if (jLimit == 0) {
            trackAdaptionStatus.set(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE_SRC_BUFFER_SIZE_ZERO, jLimit, j, byteBuffer.remaining(), byteBuffer2.remaining(), -1L);
        } else {
            trackAdaptionStatus.set(null, jLimit, j, byteBuffer.remaining(), byteBuffer2.remaining(), -1L);
        }
    }

    @Override // com.amazon.avod.media.playback.util.TrackAdapter
    public long writeAdaptedSample(@Nonnull ByteBuffer byteBuffer, @Nonnull CircularByteBuffer circularByteBuffer, long j) {
        long jLimit;
        Preconditions.checkNotNull(byteBuffer, "src");
        Preconditions.checkNotNull(circularByteBuffer, "destination");
        if (byteBuffer.remaining() > circularByteBuffer.capacity() || byteBuffer.remaining() < j) {
            return 0L;
        }
        if (this.mVideoStreamType == VideoStreamType.AV01) {
            circularByteBuffer.put(byteBuffer, byteBuffer.limit());
            jLimit = byteBuffer.limit();
        } else {
            long j2 = 0;
            while (byteBuffer.remaining() > 4) {
                int i = byteBuffer.getInt();
                if (i <= 0) {
                    return 0L;
                }
                byte[] bArr = NAL_UNIT_PREFIX;
                int length = bArr.length + i;
                j2 += (long) length;
                if (j2 > j || i > byteBuffer.remaining() || length > circularByteBuffer.remaining()) {
                    return 0L;
                }
                circularByteBuffer.put(bArr, 0, bArr.length);
                circularByteBuffer.put(byteBuffer, i);
            }
            jLimit = j2;
        }
        if (jLimit != j) {
            return 0L;
        }
        return jLimit;
    }
}

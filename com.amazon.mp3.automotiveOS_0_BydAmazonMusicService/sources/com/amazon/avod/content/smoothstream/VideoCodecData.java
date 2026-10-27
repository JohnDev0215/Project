package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.media.ExternalFourCCMapper;
import com.amazon.avod.media.VideoStreamType;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playback.sampling.SampleType;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class VideoCodecData implements CodecData {
    private static final byte[] NAL_UNIT_PREFIX = {0, 0, 0, 1};
    private final VideoStreamType mVideoStreamType;

    public VideoCodecData(@Nonnull String str) {
        Preconditions.checkNotNull(str, "fourCC");
        this.mVideoStreamType = (VideoStreamType) Preconditions.checkNotNull(ExternalFourCCMapper.INSTANCE.mapVideoCodecToStreamType(str), "videoStreamType");
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    public long validateSample(@Nonnull ByteBuffer byteBuffer, long j, @Nullable URL url) throws ContentException {
        long length;
        if (this.mVideoStreamType == VideoStreamType.AV01) {
            length = byteBuffer.limit();
        } else {
            length = 0;
            while (byteBuffer.remaining() > 4) {
                int i = byteBuffer.getInt();
                if (i <= 0) {
                    throw new ContentException(ContentException.ContentError.CANNOT_WRITE_ADAPTED_SAMPLE, String.format(Locale.US, "Read bogus NAL size from sample! %s", Integer.valueOf(i)), null, url);
                }
                length += (long) (NAL_UNIT_PREFIX.length + i);
                if (length > j) {
                    throw new ContentException(ContentException.ContentError.CANNOT_WRITE_ADAPTED_SAMPLE, String.format(Locale.US, "NAL copy exceeds sample size (size after copy = %d, max sample size = %d)", Long.valueOf(length), Long.valueOf(j)), null, url);
                }
                if (i > byteBuffer.remaining()) {
                    throw new ContentException(ContentException.ContentError.CANNOT_WRITE_ADAPTED_SAMPLE, String.format(Locale.US, "NAL copy exceeds buffer size (NAL length = %d, src length = %d", Integer.valueOf(i), Integer.valueOf(byteBuffer.remaining())), null, url);
                }
                byteBuffer.position(byteBuffer.position() + i);
            }
        }
        if (length != j) {
            throw new ContentException(ContentException.ContentError.CANNOT_WRITE_ADAPTED_SAMPLE, String.format(Locale.US, "Sample copy expected and actual size did not match (actual size = %d, expected size = %d)", Long.valueOf(length), Long.valueOf(j)), null, url);
        }
        byteBuffer.rewind();
        return length;
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nullable
    public SampleCodecData getSampleCodecData(QualityLevel qualityLevel) {
        String codecData = qualityLevel.getCodecData();
        if (Strings.isNullOrEmpty(codecData)) {
            return null;
        }
        return SampleCodecData.fromHexString(codecData);
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nullable
    public SampleCodecData getSampleCodecData(SmoothStreamingURI smoothStreamingURI, Fragment fragment) {
        String codecPrivateData = fragment.getCodecPrivateData();
        if (Strings.isNullOrEmpty(codecPrivateData)) {
            return null;
        }
        return SampleCodecData.fromHexString(codecPrivateData);
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nonnull
    public SampleType getSampleType() {
        return SampleType.VIDEO_SAMPLE;
    }
}

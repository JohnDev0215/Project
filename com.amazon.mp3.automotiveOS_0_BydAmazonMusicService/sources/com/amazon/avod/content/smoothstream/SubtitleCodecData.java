package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playback.sampling.SampleType;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class SubtitleCodecData implements CodecData {
    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nullable
    public SampleCodecData getSampleCodecData(SmoothStreamingURI smoothStreamingURI, Fragment fragment) {
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nullable
    public SampleCodecData getSampleCodecData(QualityLevel qualityLevel) {
        return null;
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    public long validateSample(@Nonnull ByteBuffer byteBuffer, long j, @Nullable URL url) throws ContentException {
        return j;
    }

    SubtitleCodecData() {
    }

    @Override // com.amazon.avod.content.smoothstream.CodecData
    @Nonnull
    public SampleType getSampleType() {
        return SampleType.SUBTITLE_SAMPLE;
    }
}

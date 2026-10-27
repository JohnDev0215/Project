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
interface CodecData {
    @Nullable
    SampleCodecData getSampleCodecData(SmoothStreamingURI smoothStreamingURI, Fragment fragment);

    @Nullable
    SampleCodecData getSampleCodecData(QualityLevel qualityLevel);

    @Nonnull
    SampleType getSampleType();

    long validateSample(@Nonnull ByteBuffer byteBuffer, long j, @Nullable URL url) throws ContentException;
}

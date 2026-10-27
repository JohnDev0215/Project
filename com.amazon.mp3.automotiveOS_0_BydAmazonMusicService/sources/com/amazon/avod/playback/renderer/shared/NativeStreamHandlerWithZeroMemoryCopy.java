package com.amazon.avod.playback.renderer.shared;

import com.amazon.avod.media.playback.util.CodecConfig;
import com.amazon.avod.media.playback.util.TrackAdapter;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.renderer.BufferFlags;
import com.amazon.avod.playback.renderer.StreamHandlerBase;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleType;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NativeStreamHandlerWithZeroMemoryCopy extends StreamHandlerBase {
    public NativeStreamHandlerWithZeroMemoryCopy(@Nonnull PlaybackConfig playbackConfig) {
        super(playbackConfig);
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public void initialize(SampleType sampleType, @Nonnull TrackAdapter trackAdapter, @Nonnull CodecConfig codecConfig, @Nonnull NativeRendererJniBase nativeRendererJniBase) throws PlaybackException {
        this.mNativeWrapper = (NativeRendererJniBase) Preconditions.checkNotNull(nativeRendererJniBase, "nativeWrapper");
        Preconditions.checkNotNull(codecConfig, "codecConfig");
        super.initialize(sampleType, codecConfig);
        flush();
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public synchronized void flush() {
        super.flush();
        this.mFrameIndex = 0L;
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public void submitEos(long j) throws PlaybackException {
        submitSampleMetaData(null, isVideo(), 4, 4, BufferFlags.END_OF_STREAM, TimeUnit.NANOSECONDS.toMicros(j), this.mFrameIndex, 0);
        this.mNativeWrapper.setEndOfStream();
    }

    private ByteBuffer getSampleByteBuffer(SampleHolder sampleHolder) {
        ByteBuffer data = sampleHolder.getData();
        if (!sampleHolder.getNeedsBacking()) {
            return data;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(data.capacity());
        data.rewind();
        byteBufferAllocate.put(data);
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public void submitSampleContent(@Nonnull SampleHolder sampleHolder) throws PlaybackException {
        boolean z;
        SampleMetadata sampleMetadataNewSampleMetaData;
        int i;
        int size = sampleHolder.getSize();
        ByteBuffer sampleByteBuffer = getSampleByteBuffer(sampleHolder);
        int iAlignToWidth = StreamHandlerBase.alignToWidth(size, 4);
        SampleEncryptionInfo encryptionInfo = sampleHolder.getEncryptionInfo();
        ByteBuffer byteBuffer = null;
        if (encryptionInfo != null) {
            ByteBuffer encryptionMetadata = getEncryptionMetadata(null, encryptionInfo, 0);
            int encryptionMetadataLength = getEncryptionMetadataLength(encryptionInfo);
            byteBuffer = encryptionMetadata;
            z = false;
            i = iAlignToWidth + encryptionMetadataLength;
            sampleMetadataNewSampleMetaData = SampleMetadata.newSampleMetaDataWithRawEncryptionInfo(size, sampleHolder.getPresentationTime(), BufferFlags.getFlags(encryptionMetadata != null), sampleHolder.getType(), 0, encryptionMetadata, encryptionMetadataLength, sampleHolder.getBitrate(), null, sampleByteBuffer, null);
        } else {
            z = false;
            sampleMetadataNewSampleMetaData = SampleMetadata.newSampleMetaData(size, sampleHolder.getPresentationTime(), 0, sampleHolder.getType(), sampleHolder.getBitrate(), null, sampleByteBuffer, null);
            i = iAlignToWidth;
        }
        submitSampleMetaData(sampleMetadataNewSampleMetaData, isVideo(), i, size, BufferFlags.getFlags(byteBuffer == null ? z : true), TimeUnit.NANOSECONDS.toMicros(sampleHolder.getPresentationTime()), this.mFrameIndex, sampleHolder.getBitrate());
        this.mFrameIndex++;
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public void submitCodecData(SampleCodecData sampleCodecData, long j) throws PlaybackException {
        int length = sampleCodecData.getRawBytes().length;
        int iAlignToWidth = StreamHandlerBase.alignToWidth(length, 4);
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        long micros = timeUnit.toMicros(j);
        int i = BufferFlags.CODEC_CONFIG;
        submitSampleMetaData(SampleMetadata.newSampleMetaData(0, micros, i, isVideo() ? SampleType.VIDEO_SAMPLE : SampleType.AUDIO_SAMPLE, 0, sampleCodecData.getRawBytes(), null, null), isVideo(), iAlignToWidth, length, i, timeUnit.toMicros(j), this.mFrameIndex, 0);
        this.mFrameIndex++;
    }

    private void submitSampleMetaData(SampleMetadata sampleMetadata, boolean z, int i, int i2, int i3, long j, long j2, int i4) throws PlaybackException {
        if (i < i2) {
            throw new IllegalStateException("Buffer size " + i + " < frame size " + i2);
        }
        this.mNativeWrapper.queueToSampleMetaDataList(sampleMetadata, z, i, i2, i3, j, j2, i4);
    }
}

package com.amazon.avod.playback.renderer.shared;

import com.amazon.avod.media.framework.memory.DoublingGrowableBuffer;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.playback.util.CodecConfig;
import com.amazon.avod.media.playback.util.TrackAdapter;
import com.amazon.avod.media.playback.util.TrackAdaptionStatus;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.renderer.BufferFlags;
import com.amazon.avod.playback.renderer.StreamHandlerBase;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NativeStreamHandler extends StreamHandlerBase {
    private final GrowableBuffer mEncryptionMetaData;
    private long mFrameIndex;
    private TrackAdapter mTrackAdapter;
    private final TrackAdaptionStatus mTrackAdaptionStatus;
    private ByteBuffer mTransferBuffer;

    public NativeStreamHandler(@Nonnull PlaybackConfig playbackConfig) {
        super(playbackConfig);
        this.mFrameIndex = 0L;
        this.mEncryptionMetaData = new DoublingGrowableBuffer(1024);
        this.mTrackAdaptionStatus = new TrackAdaptionStatus();
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public void initialize(SampleType sampleType, @Nonnull TrackAdapter trackAdapter, CodecConfig codecConfig, NativeRendererJniBase nativeRendererJniBase) throws PlaybackException {
        this.mTrackAdapter = (TrackAdapter) Preconditions.checkNotNull(trackAdapter, "trackAdapter");
        super.initialize(sampleType, codecConfig);
        this.mNativeWrapper = nativeRendererJniBase;
        int bufferSize = getBufferSize();
        DLog.logf("Allocating %s-byte buffer for stream %s (fourCC %s)", Integer.valueOf(bufferSize), sampleType, codecConfig.getFourCC());
        if (!isVideo()) {
            this.mTransferBuffer = nativeRendererJniBase.allocateCyclicAudioBuffer(bufferSize);
        } else {
            this.mTransferBuffer = nativeRendererJniBase.allocateCyclicVideoBuffer(bufferSize);
        }
        flush();
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public synchronized void flush() {
        super.flush();
        this.mFrameIndex = 0L;
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public void submitEos(long j) throws PlaybackException {
        submitSample(isVideo(), getWritableRegion(4), 4, 4, BufferFlags.END_OF_STREAM, TimeUnit.NANOSECONDS.toMicros(j), this.mFrameIndex, 0);
        this.mNativeWrapper.setEndOfStream();
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public void submitSampleContent(SampleHolder sampleHolder) throws PlaybackException {
        ByteBuffer encryptionMetadata;
        int encryptionMetadataLength;
        int size = sampleHolder.getSize();
        int iAlignToWidth = StreamHandlerBase.alignToWidth(size, 4);
        SampleEncryptionInfo encryptionInfo = sampleHolder.getEncryptionInfo();
        if (encryptionInfo != null) {
            encryptionMetadata = getEncryptionMetadata(this.mEncryptionMetaData, encryptionInfo, 0);
            encryptionMetadataLength = getEncryptionMetadataLength(encryptionInfo);
            iAlignToWidth += encryptionMetadataLength;
        } else {
            encryptionMetadata = null;
            encryptionMetadataLength = 0;
        }
        ByteBuffer byteBuffer = encryptionMetadata;
        int i = iAlignToWidth;
        int writableRegion = getWritableRegion(i);
        if (writableRegion % 4 != 0) {
            throw new IllegalStateException("Target offset not 4-byte-aligned");
        }
        this.mTransferBuffer.position(writableRegion);
        long presentationTime = sampleHolder.getPresentationTime();
        this.mTrackAdapter.writeAdaptedSample(sampleHolder.getData(), this.mTransferBuffer, size, this.mTrackAdaptionStatus);
        if (this.mTrackAdaptionStatus.getError() != null) {
            throw new PlaybackException(this.mTrackAdaptionStatus.getError(), String.format(Locale.US, "Failed to adapt sample @ %d, Error: %d %d %d %d %d", Long.valueOf(presentationTime), Long.valueOf(this.mTrackAdaptionStatus.getFragmentBufferSize()), Long.valueOf(this.mTrackAdaptionStatus.getSrcBufferRemaining()), Long.valueOf(this.mTrackAdaptionStatus.getDstBufferRemaining()), Long.valueOf(this.mTrackAdaptionStatus.getBytesWritten()), Long.valueOf(this.mTrackAdaptionStatus.getNALSampleSize())));
        }
        if (byteBuffer != null) {
            for (int i2 = size; i2 % 4 != 0; i2++) {
                this.mTransferBuffer.put((byte) 0);
            }
            byteBuffer.limit(encryptionMetadataLength);
            this.mTransferBuffer.put(byteBuffer);
        }
        submitSample(isVideo(), writableRegion, i, size, BufferFlags.getFlags(byteBuffer != null), TimeUnit.NANOSECONDS.toMicros(sampleHolder.getPresentationTime()), this.mFrameIndex, sampleHolder.getBitrate());
        this.mFrameIndex++;
    }

    @Override // com.amazon.avod.playback.renderer.StreamHandlerBase
    public void submitCodecData(SampleCodecData sampleCodecData, long j) throws PlaybackException {
        int length = sampleCodecData.getRawBytes().length;
        int iAlignToWidth = StreamHandlerBase.alignToWidth(length, 4);
        int writableRegion = getWritableRegion(iAlignToWidth);
        if (writableRegion % 4 != 0) {
            throw new IllegalStateException("Target offset not 4-byte-aligned");
        }
        this.mTransferBuffer.position(writableRegion);
        this.mTransferBuffer.put(sampleCodecData.getRawBytes(), 0, length);
        submitSample(isVideo(), writableRegion, iAlignToWidth, length, BufferFlags.CODEC_CONFIG, TimeUnit.NANOSECONDS.toMicros(j), this.mFrameIndex, 0);
        this.mFrameIndex++;
    }

    private void submitSample(boolean z, int i, int i2, int i3, int i4, long j, long j2, int i5) throws PlaybackException {
        if (i + i2 > getBufferSize()) {
            throw new IllegalStateException("Target offset " + i + " + size " + i2 + " > buffer size " + getBufferSize());
        }
        if (i < 0) {
            throw new IllegalStateException("Target offset < 0");
        }
        if (i2 < i3) {
            throw new IllegalStateException("Buffer size " + i2 + " < frame size " + i3);
        }
        this.mNativeWrapper.queueSample(z, i, i2, i3, i4, j, j2, i5);
    }

    private int getWritableRegion(int i) throws PlaybackException {
        return isVideo() ? this.mNativeWrapper.getWritableVideoRegion(i) : this.mNativeWrapper.getWritableAudioRegion(i);
    }
}

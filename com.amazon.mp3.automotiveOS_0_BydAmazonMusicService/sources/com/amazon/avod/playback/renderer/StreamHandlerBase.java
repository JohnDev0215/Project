package com.amazon.avod.playback.renderer;

import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.playback.util.CodecConfig;
import com.amazon.avod.media.playback.util.TrackAdapter;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.drm.EncryptedBufferRegion;
import com.amazon.avod.playback.player.PlaybackConfig;
import com.amazon.avod.playback.renderer.shared.NativeCodecTypes;
import com.amazon.avod.playback.renderer.shared.NativeDrmTypes;
import com.amazon.avod.playback.renderer.shared.NativeRendererJniBase;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.amazon.identity.auth.device.utils.AccountConstants;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public abstract class StreamHandlerBase {
    public static final int BUFFER_ALIGNMENT = 4;
    public static final int DRMMETADATABLOCK_FOOTER_SIZE = 20;
    public static final int DRMMETADATABLOCK_PER_ENCRYPTED_REGION_STRUCTURE_SIZE = 48;
    public static final int INITIAL_ENCRYPTION_METADATA_SIZE = 1024;
    public static final int MINIMUM_FRAME_SIZE = 4;
    private final int mAudioBufferSizeBytes;
    private final int mAvcVideoBufferSizeBytes;
    private int mCodecType;
    private final int mHevcVideoBufferSizeBytes;
    private SampleCodecData mLastCodecData;
    protected NativeRendererJniBase mNativeWrapper;
    private SampleType mType;
    private volatile long mLastSubmittedDecodeTimeInNanoseconds = 0;
    protected boolean mIsDone = false;
    protected long mFrameIndex = 0;

    public abstract void initialize(SampleType sampleType, TrackAdapter trackAdapter, CodecConfig codecConfig, NativeRendererJniBase nativeRendererJniBase) throws PlaybackException;

    public abstract void submitCodecData(SampleCodecData sampleCodecData, long j) throws PlaybackException;

    public abstract void submitEos(long j) throws PlaybackException;

    public abstract void submitSampleContent(SampleHolder sampleHolder) throws PlaybackException;

    public StreamHandlerBase(@Nonnull PlaybackConfig playbackConfig) {
        Preconditions.checkNotNull(playbackConfig, "playbackConfig");
        DataUnit dataUnit = DataUnit.MEGABYTES;
        this.mAudioBufferSizeBytes = (int) dataUnit.toBytes(playbackConfig.getNativeAudioBufferSizeMb());
        this.mAvcVideoBufferSizeBytes = (int) dataUnit.toBytes(playbackConfig.getNativeAvcVideoBufferSizeMb());
        this.mHevcVideoBufferSizeBytes = (int) dataUnit.toBytes(playbackConfig.getNativeHevcVideoBufferSizeMb());
    }

    public void initialize(SampleType sampleType, CodecConfig codecConfig) throws PlaybackException {
        this.mType = (SampleType) Preconditions.checkNotNull(sampleType, AccountConstants.SUB_AUTHENTICATOR_TYPE_ATTRIBUTE);
        this.mCodecType = NativeCodecTypes.valueOf(codecConfig.getFourCC());
    }

    public static int alignToWidth(int i, int i2) {
        Preconditions.checkArgument(i >= 0);
        Preconditions.checkArgument(i2 > 0);
        return (((i + i2) - 1) / i2) * i2;
    }

    public boolean isDone() {
        return this.mIsDone;
    }

    public long getLastSubmittedDecodeTimeInNanos() {
        return this.mLastSubmittedDecodeTimeInNanoseconds;
    }

    public synchronized void flush() {
        this.mLastCodecData = null;
        this.mIsDone = false;
        this.mLastSubmittedDecodeTimeInNanoseconds = 0L;
    }

    public int getBufferSize() {
        if (isVideo()) {
            return this.mCodecType == 613 ? this.mHevcVideoBufferSizeBytes : this.mAvcVideoBufferSizeBytes;
        }
        return this.mAudioBufferSizeBytes;
    }

    public synchronized void submitSample(SampleHolder sampleHolder) throws PlaybackException {
        if (sampleHolder.getCodecData() == null || sampleHolder.getCodecData().getRawBytes() == null) {
            DLog.errorf("Missing codecData");
            throw new IllegalArgumentException("Missing codecData");
        }
        SampleCodecData sampleCodecData = this.mLastCodecData;
        if (sampleCodecData == null || !sampleCodecData.equals(sampleHolder.getCodecData())) {
            submitCodecData(sampleHolder.getCodecData(), sampleHolder.getPresentationTime());
            this.mLastCodecData = sampleHolder.getCodecData();
        }
        this.mLastSubmittedDecodeTimeInNanoseconds = sampleHolder.getDecodeTime();
        submitSampleContent(sampleHolder);
        if (sampleHolder.getIsLastInStream()) {
            submitEos(sampleHolder.getPresentationTime());
            this.mIsDone = true;
        }
    }

    public int getEncryptionMetadataLength(SampleEncryptionInfo sampleEncryptionInfo) {
        EncryptedBufferRegion[] encryptedRegions;
        if (sampleEncryptionInfo == null || (encryptedRegions = sampleEncryptionInfo.getEncryptedRegions()) == null || encryptedRegions.length == 0) {
            return 0;
        }
        return (encryptedRegions.length * 48) + 20;
    }

    public ByteBuffer getEncryptionMetadata(@Nullable GrowableBuffer growableBuffer, SampleEncryptionInfo sampleEncryptionInfo, int i) {
        EncryptedBufferRegion[] encryptedRegions;
        ByteBuffer byteBuffer;
        if (sampleEncryptionInfo == null || (encryptedRegions = sampleEncryptionInfo.getEncryptedRegions()) == null || encryptedRegions.length == 0) {
            return null;
        }
        byte[] initializationVector = sampleEncryptionInfo.getInitializationVector();
        int encryptionMetadataLength = getEncryptionMetadataLength(sampleEncryptionInfo);
        if (growableBuffer != null) {
            growableBuffer.ensureCapacity(encryptionMetadataLength);
        }
        if (growableBuffer == null) {
            byteBuffer = ByteBuffer.allocate(encryptionMetadataLength);
        } else {
            byteBuffer = growableBuffer.getByteBuffer();
        }
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        for (EncryptedBufferRegion encryptedBufferRegion : encryptedRegions) {
            byteBuffer.putInt(45);
            byteBuffer.putInt(1);
            byteBuffer.putInt(i);
            byteBuffer.putInt(NativeDrmTypes.EXTRA_DATA_FLAG_IV_DATA);
            byteBuffer.putInt(25);
            byteBuffer.putInt(encryptedBufferRegion.getOffset());
            byteBuffer.putInt(encryptedBufferRegion.getLength());
            byteBuffer.put(initializationVector);
            byteBuffer.putLong(0L);
            byteBuffer.putInt(0);
        }
        byteBuffer.putInt(0);
        byteBuffer.putInt(1);
        byteBuffer.putInt(i);
        byteBuffer.putInt(0);
        byteBuffer.putInt(0);
        byteBuffer.clear();
        return byteBuffer;
    }

    protected boolean isVideo() {
        return this.mType == SampleType.VIDEO_SAMPLE;
    }
}

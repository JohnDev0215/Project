package com.amazon.avod.media.drm;

import android.media.MediaCodec;
import android.media.MediaCrypto;
import com.amazon.avod.drm.SoftwarePlayReadyDrmFramework;
import com.amazon.avod.drm.playready.PlayReadyException;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.media.framework.error.LicenseError;
import com.amazon.avod.media.framework.memory.DoublingGrowableBuffer;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.playback.drm.EncryptedBufferRegion;
import com.amazon.avod.playback.renderer.shared.NativeDrmTypes;
import com.amazon.avod.playback.renderer.shared.SampleMetadata;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class SoftwarePlayReadyCryptoSession implements DrmCryptoSession {
    private final SoftwarePlayReadyDrmFramework mSoftwarePlayReadyDrmFramework;
    private final Object mMutex = new Object();
    private final GrowableBuffer mEncryptionMetadata = new DoublingGrowableBuffer(1024);

    @Override // com.amazon.avod.media.drm.DrmCryptoSession
    public MediaCrypto getMediaCrypto() {
        return null;
    }

    @Override // com.amazon.avod.media.drm.DrmCryptoSession
    public void release() {
    }

    public SoftwarePlayReadyCryptoSession(@Nonnull SoftwarePlayReadyDrmFramework softwarePlayReadyDrmFramework) {
        this.mSoftwarePlayReadyDrmFramework = (SoftwarePlayReadyDrmFramework) Preconditions.checkNotNull(softwarePlayReadyDrmFramework, "softwarePlayReadyDrmFramework");
    }

    @Override // com.amazon.avod.media.drm.DrmCryptoSession
    public MediaCodec.CryptoInfo decryptSample(@Nonnull ByteBuffer byteBuffer, @Nonnull SampleMetadata sampleMetadata) throws DrmLicensingException {
        synchronized (this.mMutex) {
            Preconditions.checkNotNull(byteBuffer, "buffer");
            Preconditions.checkNotNull(sampleMetadata, "sampleMetadata");
            SampleType sampleType = sampleMetadata.getSampleType();
            int size = sampleMetadata.getSize();
            Preconditions.checkNotNull(sampleType, "sampleType");
            Preconditions.checkArgument(sampleType == SampleType.AUDIO_SAMPLE || sampleType == SampleType.VIDEO_SAMPLE, "sample type should be audio or video");
            Preconditions.checkArgument(size > 0, "size without extra data should be greater than 0");
            SampleEncryptionInfo sampleEncryptionInfo = sampleMetadata.getSampleEncryptionInfo();
            if (sampleEncryptionInfo == null) {
                return null;
            }
            EncryptedBufferRegion[] encryptedRegions = sampleEncryptionInfo.getEncryptedRegions();
            if (encryptedRegions.length <= 0) {
                DLog.warnf("Encrypted region length should be greater than 0, length = %s", Integer.valueOf(encryptedRegions.length));
                return null;
            }
            int encryptionMetadataLength = getEncryptionMetadataLength(sampleEncryptionInfo);
            byte[] encryptionMetaData = getEncryptionMetaData(sampleMetadata, 0, encryptionMetadataLength);
            Preconditions.checkNotNull(encryptionMetaData, "EncryptionMetadata cannot be null");
            int iAlignToWidth = alignToWidth(size, 4) + encryptionMetadataLength;
            if (encryptionMetadataLength <= 0) {
                DLog.warnf("Received a request to decrypt content with encryption metadata of length %s. Returning without performing decryption", Integer.valueOf(encryptionMetadataLength));
                return null;
            }
            try {
                ByteBuffer sampleBuffer = this.mSoftwarePlayReadyDrmFramework.getSampleBuffer(sampleType, iAlignToWidth);
                if (sampleBuffer == null) {
                    throw new DrmLicensingException(LicenseError.SOFTWARE_PLAYREADY_NATIVE_BUFFER_ALLOCATION_FAILURE, String.format("Unable to allocate native buffer of size %s, sampleType %s", Integer.valueOf(iAlignToWidth), sampleType));
                }
                byteBuffer.flip();
                sampleBuffer.clear();
                sampleBuffer.position(0);
                sampleBuffer.put(byteBuffer);
                for (int i = size; i % 4 != 0; i++) {
                    sampleBuffer.put((byte) 0);
                }
                if (sampleBuffer.remaining() < encryptionMetadataLength) {
                    throw new DrmLicensingException(LicenseError.SOFTWARE_PLAYREADY_NATIVE_BUFFER_OVERFLOW, String.format("Error EncryptionMetadataLength = %s, BufferLimit() = %s, FourByteAlignedLength() = %s RemainingBuffer = %s", Integer.valueOf(encryptionMetadataLength), Integer.valueOf(byteBuffer.limit()), Integer.valueOf(iAlignToWidth), Integer.valueOf(sampleBuffer.remaining())));
                }
                sampleBuffer.put(encryptionMetaData, 0, encryptionMetadataLength);
                sampleBuffer.position(0);
                this.mSoftwarePlayReadyDrmFramework.decrypt(sampleType, sampleBuffer, iAlignToWidth, size);
                byteBuffer.clear();
                sampleBuffer.flip();
                sampleBuffer.limit(size);
                byteBuffer.put(sampleBuffer);
                return null;
            } catch (PlayReadyException e) {
                throw new DrmLicensingException(LicenseError.SOFTWARE_PLAYREADY_NATIVE_BUFFER_ALLOCATION_FAILURE, "Failed to allocate native buffer", e);
            }
        }
    }

    private int alignToWidth(int i, int i2) {
        Preconditions.checkArgument(i >= 0);
        Preconditions.checkArgument(i2 > 0);
        return (((i + i2) - 1) / i2) * i2;
    }

    private int getEncryptionMetadataLength(@Nonnull SampleEncryptionInfo sampleEncryptionInfo) {
        int length = (sampleEncryptionInfo.getEncryptedRegions().length * 48) + 20;
        Preconditions.checkState(length % 4 == 0, "Encryption region length not %d-byte-aligned", 4);
        return length;
    }

    private byte[] getEncryptionMetaData(@Nonnull SampleMetadata sampleMetadata, int i, int i2) {
        SampleEncryptionInfo sampleEncryptionInfo = sampleMetadata.getSampleEncryptionInfo();
        EncryptedBufferRegion[] encryptedRegions = sampleEncryptionInfo.getEncryptedRegions();
        if (i2 == 0) {
            return null;
        }
        byte[] initializationVector = sampleEncryptionInfo.getInitializationVector();
        this.mEncryptionMetadata.ensureCapacity(i2);
        ByteBuffer byteBuffer = this.mEncryptionMetadata.getByteBuffer();
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        for (EncryptedBufferRegion encryptedBufferRegion : encryptedRegions) {
            byteBuffer.putInt(45);
            byteBuffer.putInt(1);
            byteBuffer.putInt(i);
            byteBuffer.putInt(NativeDrmTypes.EXTRA_DATA_FLAG_IV_DATA);
            byteBuffer.putInt(25);
            byteBuffer.putInt(encryptedBufferRegion.getOffset() + sampleMetadata.getSampleCodecDataLength());
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
        return byteBuffer.array();
    }
}

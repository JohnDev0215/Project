package com.amazon.avod.media.drm;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import com.amazon.avod.media.framework.error.DrmLicensingException;
import com.amazon.avod.playback.drm.EncryptedBufferRegion;
import com.amazon.avod.playback.renderer.shared.SampleMetadata;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
@TargetApi(18)
public class MediaCryptoSession implements DrmCryptoSession {
    private final byte[] mAudioKeyId;
    private final boolean mGenerateCryptoInfoForClearSamples;
    private final MediaCrypto mMediaCrypto;
    private final byte[] mVideoKeyId;

    public MediaCryptoSession(@Nonnull byte[] bArr, @Nullable byte[] bArr2, @Nonnull MediaCrypto mediaCrypto, boolean z) {
        this.mVideoKeyId = (byte[]) ((byte[]) Preconditions.checkNotNull(bArr, "videoKeyId")).clone();
        if (bArr2 != null) {
            this.mAudioKeyId = (byte[]) bArr2.clone();
        } else {
            this.mAudioKeyId = null;
        }
        this.mMediaCrypto = (MediaCrypto) Preconditions.checkNotNull(mediaCrypto, "mediaCrypto");
        this.mGenerateCryptoInfoForClearSamples = z;
    }

    @Override // com.amazon.avod.media.drm.DrmCryptoSession
    public void release() {
        MediaCrypto mediaCrypto = this.mMediaCrypto;
        if (mediaCrypto != null) {
            mediaCrypto.release();
        }
    }

    @Override // com.amazon.avod.media.drm.DrmCryptoSession
    @Nullable
    public MediaCrypto getMediaCrypto() {
        return this.mMediaCrypto;
    }

    @Override // com.amazon.avod.media.drm.DrmCryptoSession
    public MediaCodec.CryptoInfo decryptSample(@Nonnull ByteBuffer byteBuffer, @Nonnull SampleMetadata sampleMetadata) throws DrmLicensingException {
        Preconditions.checkNotNull(byteBuffer, "sample");
        Preconditions.checkNotNull(sampleMetadata, "sampleMetadata");
        return generateCryptoInfo(sampleMetadata);
    }

    private byte[] getInitializationVector(SampleEncryptionInfo sampleEncryptionInfo) {
        byte[] initializationVector = sampleEncryptionInfo.getInitializationVector();
        byte[] bArr = new byte[16];
        int i = 0;
        while (i < initializationVector.length) {
            int i2 = i + 1;
            bArr[i] = initializationVector[initializationVector.length - i2];
            i = i2;
        }
        return bArr;
    }

    private MediaCodec.CryptoInfo generateCryptoInfo(@Nonnull SampleMetadata sampleMetadata) {
        int i;
        SampleEncryptionInfo sampleEncryptionInfo = sampleMetadata.getSampleEncryptionInfo();
        if (sampleEncryptionInfo != null) {
            i = 0;
        } else {
            if (!this.mGenerateCryptoInfoForClearSamples) {
                return null;
            }
            sampleEncryptionInfo = SampleEncryptionInfo.generateClearTextEncryptionInfo(sampleMetadata.getSize());
            i = 1;
        }
        EncryptedBufferRegion[] encryptedRegions = sampleEncryptionInfo.getEncryptedRegions();
        if (encryptedRegions == null || encryptedRegions.length <= 0) {
            DLog.warnf("EncryptedRegion[] was null or empty!");
            return null;
        }
        int length = encryptedRegions.length;
        int[] iArr = new int[length];
        int[] iArr2 = new int[length];
        MediaCodec.CryptoInfo cryptoInfo = new MediaCodec.CryptoInfo();
        int i2 = 0;
        int offset = 0;
        while (i2 < length) {
            iArr[i2] = (encryptedRegions[i2].getOffset() - offset) + (i2 == 0 ? sampleMetadata.getSampleCodecDataLength() : 0);
            iArr2[i2] = encryptedRegions[i2].getLength();
            offset = encryptedRegions[i2].getOffset() + encryptedRegions[i2].getLength();
            i2++;
        }
        cryptoInfo.set(length, iArr, iArr2, sampleMetadata.getSampleType().equals(SampleType.AUDIO_SAMPLE) ? this.mAudioKeyId : this.mVideoKeyId, getInitializationVector(sampleEncryptionInfo), i ^ 1);
        return cryptoInfo;
    }
}

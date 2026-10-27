package com.amazon.avod.playback.renderer.shared;

import com.amazon.avod.playback.drm.EncryptedBufferRegion;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.playback.sampling.SampleType;
import com.google.common.base.Preconditions;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import java.net.URL;
import java.nio.ByteBuffer;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SampleMetadata {
    private byte[] mCodecData;
    private ByteBuffer mEncryptionMetaData;
    private int mEncryptionMetadataLength;
    private final int mFlags;
    private final URL mFragmentUrl;
    private final long mPresentationTimeUs;
    private final int mSampleBitrate;
    private final int mSampleCodecDataLength;
    private ByteBuffer mSampleData;
    private final SampleEncryptionInfo mSampleEncryptionInfo;
    private final SampleType mSampleType;
    private final int mSize;

    @SuppressFBWarnings(justification = "To enforce immutability of codec data array, it is best to either clone it or use ImmutableList, but then we will be copying it every time we need to use it, which is extra allocation at a time where we care greatly about performance", value = {"EI_EXPOSE_REP2"})
    private SampleMetadata(int i, long j, int i2, SampleType sampleType, int i3, SampleEncryptionInfo sampleEncryptionInfo, int i4, @Nullable byte[] bArr, @Nullable ByteBuffer byteBuffer, @Nullable URL url) {
        this.mSampleType = (SampleType) Preconditions.checkNotNull(sampleType, "sampleType");
        this.mSize = i;
        this.mPresentationTimeUs = j;
        this.mFlags = i2;
        this.mSampleCodecDataLength = i3;
        this.mSampleEncryptionInfo = sampleEncryptionInfo;
        this.mSampleBitrate = i4;
        this.mCodecData = bArr;
        this.mSampleData = byteBuffer;
        this.mEncryptionMetaData = null;
        this.mEncryptionMetadataLength = 0;
        this.mFragmentUrl = url;
    }

    @SuppressFBWarnings(justification = "To enforce immutability of codec data array, it is best to either clone it or use ImmutableList, but then we will be copying it every time we need to use it, which is extra allocation at a time where we care greatly about performance", value = {"EI_EXPOSE_REP2"})
    public SampleMetadata(int i, long j, int i2, SampleType sampleType, int i3, @Nonnull ByteBuffer byteBuffer, int i4, int i5, byte[] bArr, ByteBuffer byteBuffer2, @Nullable URL url) {
        this.mSampleType = (SampleType) Preconditions.checkNotNull(sampleType, "sampleType");
        this.mSize = i;
        this.mPresentationTimeUs = j;
        this.mFlags = i2;
        this.mSampleCodecDataLength = i3;
        this.mEncryptionMetaData = (ByteBuffer) Preconditions.checkNotNull(byteBuffer, "encryptionMetaData");
        this.mEncryptionMetadataLength = i4;
        this.mSampleBitrate = i5;
        this.mCodecData = bArr;
        this.mSampleData = byteBuffer2;
        this.mSampleEncryptionInfo = null;
        this.mFragmentUrl = url;
    }

    public static SampleMetadata newSampleMetaDataWithEncryptionInfo(int i, long j, int i2, SampleType sampleType, int i3, SampleEncryptionInfo sampleEncryptionInfo, int i4, @Nullable URL url) {
        Preconditions.checkNotNull(sampleEncryptionInfo, "sampleEncryptionInfo");
        return newSampleMetaData(i, j, i2, sampleType, i3, sampleEncryptionInfo, i4, null, null, url);
    }

    public static SampleMetadata newSampleMetaData(int i, long j, int i2, SampleType sampleType, int i3, @Nullable URL url) {
        return newSampleMetaData(i, j, i2, sampleType, 0, null, i3, null, null, url);
    }

    public static SampleMetadata newSampleMetaDataWithEncryptionInfo(int i, long j, int i2, SampleType sampleType, int i3, SampleEncryptionInfo sampleEncryptionInfo, int i4, byte[] bArr, ByteBuffer byteBuffer, @Nullable URL url) {
        Preconditions.checkNotNull(sampleEncryptionInfo, "sampleEncryptionInfo");
        return newSampleMetaData(i, j, i2, sampleType, i3, sampleEncryptionInfo, i4, bArr, byteBuffer, url);
    }

    public static SampleMetadata newSampleMetaDataWithRawEncryptionInfo(int i, long j, int i2, SampleType sampleType, int i3, ByteBuffer byteBuffer, int i4, int i5, byte[] bArr, ByteBuffer byteBuffer2, @Nullable URL url) {
        Preconditions.checkNotNull(byteBuffer, "encryptionMetaData");
        return new SampleMetadata(i, j, i2, sampleType, i3, byteBuffer, i4, i5, bArr, byteBuffer2, url);
    }

    public static SampleMetadata newSampleMetaData(int i, long j, int i2, SampleType sampleType, int i3, byte[] bArr, ByteBuffer byteBuffer, @Nullable URL url) {
        return newSampleMetaData(i, j, i2, sampleType, 0, null, i3, bArr, byteBuffer, url);
    }

    public int getSize() {
        return this.mSize;
    }

    public long getPresentationTimeUs() {
        return this.mPresentationTimeUs;
    }

    public int getFlags() {
        return this.mFlags;
    }

    @Nonnull
    public SampleType getSampleType() {
        return this.mSampleType;
    }

    @Nullable
    public ByteBuffer getSampleData() {
        return this.mSampleData;
    }

    public void clearSampledata() {
        this.mSampleData = null;
        this.mCodecData = null;
        this.mEncryptionMetaData = null;
        this.mEncryptionMetadataLength = 0;
    }

    @Nullable
    public ByteBuffer getRawEncryptionMetaData() {
        return this.mEncryptionMetaData;
    }

    public int getRawEncryptionMetaDataLength() {
        return this.mEncryptionMetadataLength;
    }

    @SuppressFBWarnings(justification = "To enforce immutability of this array, it is best to either clone it or use ImmutableList, but then we will be copying it every time we need to use it, which is extra allocation at a time where we care greatly about performance", value = {"EI_EXPOSE_REP"})
    @Nullable
    public byte[] getCodecData() {
        return this.mCodecData;
    }

    public int getSampleCodecDataLength() {
        return this.mSampleCodecDataLength;
    }

    public SampleEncryptionInfo getSampleEncryptionInfo() {
        return this.mSampleEncryptionInfo;
    }

    public int getSampleBitrate() {
        return this.mSampleBitrate;
    }

    @Nullable
    public URL getFragmentUrl() {
        return this.mFragmentUrl;
    }

    private static SampleMetadata newSampleMetaData(int i, long j, int i2, SampleType sampleType, int i3, SampleEncryptionInfo sampleEncryptionInfo, int i4, byte[] bArr, ByteBuffer byteBuffer, @Nullable URL url) {
        SampleEncryptionInfo sampleEncryptionInfo2;
        if (sampleEncryptionInfo != null) {
            EncryptedBufferRegion[] encryptedRegions = sampleEncryptionInfo.getEncryptedRegions();
            EncryptedBufferRegion[] encryptedBufferRegionArr = new EncryptedBufferRegion[encryptedRegions.length];
            for (int i5 = 0; i5 < encryptedRegions.length; i5++) {
                encryptedBufferRegionArr[i5] = new EncryptedBufferRegion(encryptedRegions[i5].getOffset(), encryptedRegions[i5].getLength());
            }
            byte[] initializationVector = sampleEncryptionInfo.getInitializationVector();
            int length = initializationVector.length;
            byte[] bArr2 = new byte[length];
            System.arraycopy(initializationVector, 0, bArr2, 0, length);
            sampleEncryptionInfo2 = new SampleEncryptionInfo(encryptedBufferRegionArr, bArr2, sampleEncryptionInfo.getInitializationVectorLength());
        } else {
            sampleEncryptionInfo2 = null;
        }
        return new SampleMetadata(i, j, i2, sampleType, i3, sampleEncryptionInfo2, i4, bArr, byteBuffer, url);
    }
}

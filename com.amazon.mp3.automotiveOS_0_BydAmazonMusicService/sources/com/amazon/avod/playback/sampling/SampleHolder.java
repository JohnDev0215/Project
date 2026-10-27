package com.amazon.avod.playback.sampling;

import java.net.URL;
import java.nio.ByteBuffer;
import java.util.Locale;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SampleHolder {
    private long mAvSyncOffsetInNanoseconds;
    private int mBitrate;
    private SampleCodecData mCodecData;
    private ByteBuffer mData;
    private long mDecodeTime;
    private long mDuration;
    private SampleEncryptionInfo mEncryptionInfo;
    private URL mFragmentUrl;
    private boolean mIsLastInStream;
    private boolean mIsLastinFragment;
    private boolean mNeedsBacking;
    private long mPresentationTime;
    private int mSize;
    private SampleType mType;

    public ByteBuffer getData() {
        return this.mData;
    }

    public int getSize() {
        return this.mSize;
    }

    public long getDecodeTime() {
        return this.mDecodeTime;
    }

    public long getPresentationTime() {
        return this.mPresentationTime;
    }

    public long getAvSyncOffsetInNanoseconds() {
        return this.mAvSyncOffsetInNanoseconds;
    }

    public long getDuration() {
        return this.mDuration;
    }

    public boolean getNeedsBacking() {
        return this.mNeedsBacking;
    }

    @Nullable
    public URL getFragmentUrl() {
        return this.mFragmentUrl;
    }

    public SampleEncryptionInfo getEncryptionInfo() {
        return this.mEncryptionInfo;
    }

    public void setEncryptionInfo(SampleEncryptionInfo sampleEncryptionInfo) {
        this.mEncryptionInfo = sampleEncryptionInfo;
    }

    public boolean getIsLastInStream() {
        return this.mIsLastInStream;
    }

    public boolean getIsLastinFragment() {
        return this.mIsLastinFragment;
    }

    public SampleCodecData getCodecData() {
        return this.mCodecData;
    }

    public SampleType getType() {
        return this.mType;
    }

    public int getBitrate() {
        return this.mBitrate;
    }

    public void releaseSample() {
        this.mData = null;
        this.mSize = 0;
    }

    public void setSample(ByteBuffer byteBuffer, int i, long j, long j2, long j3, long j4, boolean z, boolean z2, SampleCodecData sampleCodecData, int i2, SampleType sampleType, boolean z3, @Nullable URL url) {
        this.mData = byteBuffer;
        this.mSize = i;
        this.mDecodeTime = j;
        this.mPresentationTime = j2;
        this.mDuration = j3;
        this.mAvSyncOffsetInNanoseconds = j4;
        this.mIsLastInStream = z;
        this.mIsLastinFragment = z2;
        this.mBitrate = i2;
        this.mType = sampleType;
        this.mCodecData = sampleCodecData;
        this.mNeedsBacking = z3;
        this.mFragmentUrl = url;
    }

    public String toString() {
        return String.format(Locale.US, "{type=%s bitrate=%s codecData=%s decodeTime=%s duration=%s presentationTime=%s encryptionInfo=%s avSyncOffsetInNanoseconds=%s fragmentUri=%s}", this.mType, Integer.valueOf(this.mBitrate), this.mCodecData, Long.valueOf(this.mDecodeTime), Long.valueOf(this.mDuration), Long.valueOf(this.mPresentationTime), this.mEncryptionInfo, Long.valueOf(this.mAvSyncOffsetInNanoseconds), this.mFragmentUrl);
    }
}

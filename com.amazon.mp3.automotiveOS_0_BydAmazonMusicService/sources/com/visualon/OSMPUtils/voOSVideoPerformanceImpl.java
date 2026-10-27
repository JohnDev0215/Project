package com.visualon.OSMPUtils;

import android.os.Parcel;

/* JADX INFO: loaded from: classes7.dex */
public class voOSVideoPerformanceImpl implements voOSVideoPerformance {
    int mAverageDecodeTime;
    int mAverageRenderTime;
    int mCPULoad;
    int mCodecDropNum;
    int[] mCodecErrors;
    int mCodecErrorsNum;
    int mCodecTimeNum;
    int mDecodedNum;
    int mFrequency;
    int mJitterNum;
    int mLastTime;
    int mMaxFrequency;
    int mRenderDropNum;
    int mRenderNum;
    int mRenderTimeNum;
    int mSourceDropNum;
    int mSourceTimeNum;
    int mTotalCPULoad;
    int mTotalCodecDropNum;
    int mTotalDecodedNum;
    int mTotalPlaybackDuration;
    int mTotalRenderDropNum;
    int mTotalRenderNum;
    int mTotalSourceDropNum;
    int mWorstDecodeTime;
    int mWorstRenderTime;

    public voOSVideoPerformanceImpl() {
    }

    public void parser(Parcel parcel) {
        this.mLastTime = parcel.readInt();
        this.mSourceDropNum = parcel.readInt();
        this.mCodecDropNum = parcel.readInt();
        this.mRenderDropNum = parcel.readInt();
        this.mDecodedNum = parcel.readInt();
        this.mRenderNum = parcel.readInt();
        this.mSourceTimeNum = parcel.readInt();
        this.mCodecTimeNum = parcel.readInt();
        this.mRenderTimeNum = parcel.readInt();
        this.mJitterNum = parcel.readInt();
        int i = parcel.readInt();
        this.mCodecErrorsNum = i;
        this.mCodecErrors = new int[i];
        for (int i2 = 0; i2 < this.mCodecErrorsNum; i2++) {
            this.mCodecErrors[i2] = parcel.readInt();
        }
        this.mCPULoad = parcel.readInt();
        this.mFrequency = parcel.readInt();
        this.mMaxFrequency = parcel.readInt();
        this.mWorstDecodeTime = parcel.readInt();
        this.mWorstRenderTime = parcel.readInt();
        this.mAverageDecodeTime = parcel.readInt();
        this.mAverageRenderTime = parcel.readInt();
        this.mTotalCPULoad = parcel.readInt();
        this.mTotalPlaybackDuration = parcel.readInt();
        this.mTotalSourceDropNum = parcel.readInt();
        this.mTotalCodecDropNum = parcel.readInt();
        this.mTotalRenderDropNum = parcel.readInt();
        this.mTotalDecodedNum = parcel.readInt();
        this.mTotalRenderNum = parcel.readInt();
    }

    public voOSVideoPerformanceImpl(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int[] iArr, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25) {
        this.mLastTime = i;
        this.mSourceDropNum = i2;
        this.mCodecDropNum = i3;
        this.mRenderDropNum = i4;
        this.mDecodedNum = i5;
        this.mRenderNum = i6;
        this.mSourceTimeNum = i7;
        this.mCodecTimeNum = i8;
        this.mRenderTimeNum = i9;
        this.mJitterNum = i10;
        this.mCodecErrorsNum = i11;
        this.mCodecErrors = iArr;
        this.mCPULoad = i12;
        this.mFrequency = i13;
        this.mMaxFrequency = i14;
        this.mWorstDecodeTime = i15;
        this.mWorstRenderTime = i16;
        this.mAverageDecodeTime = i17;
        this.mAverageRenderTime = i18;
        this.mTotalCPULoad = i19;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int LastTime() {
        return this.mLastTime;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int SourceDropNum() {
        return this.mSourceDropNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int CodecDropNum() {
        return this.mCodecDropNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int RenderDropNum() {
        return this.mRenderDropNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int DecodedNum() {
        return this.mDecodedNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int RenderNum() {
        return this.mRenderNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int SourceTimeNum() {
        return this.mSourceTimeNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int CodecTimeNum() {
        return this.mCodecTimeNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int RenderTimeNum() {
        return this.mRenderTimeNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int JitterNum() {
        return this.mJitterNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int CodecErrorsNum() {
        return this.mCodecErrorsNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int[] CodecErrors() {
        return this.mCodecErrors;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int CPULoad() {
        return this.mCPULoad;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int Frequency() {
        return this.mFrequency;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int MaxFrequency() {
        return this.mMaxFrequency;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int WorstDecodeTime() {
        return this.mWorstDecodeTime;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int WorstRenderTime() {
        return this.mWorstRenderTime;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int AverageDecodeTime() {
        return this.mAverageDecodeTime;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int AverageRenderTime() {
        return this.mAverageRenderTime;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int TotalCPULoad() {
        return this.mTotalCPULoad;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int TotalPlaybackDuration() {
        return this.mTotalPlaybackDuration;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int TotalSourceDropNum() {
        return this.mTotalSourceDropNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int TotalCodecDropNum() {
        return this.mTotalCodecDropNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int TotalRenderDropNum() {
        return this.mTotalRenderDropNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int TotalDecodedNum() {
        return this.mTotalDecodedNum;
    }

    @Override // com.visualon.OSMPUtils.voOSVideoPerformance
    public int TotalRenderNum() {
        return this.mTotalRenderNum;
    }
}

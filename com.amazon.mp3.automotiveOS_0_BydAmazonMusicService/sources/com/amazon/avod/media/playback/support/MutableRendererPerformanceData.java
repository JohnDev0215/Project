package com.amazon.avod.media.playback.support;

/* JADX INFO: loaded from: classes2.dex */
public class MutableRendererPerformanceData {
    private int mAverageDecodeTime;
    private int mAverageRenderTime;
    private int mCodecDropNum;
    private int mCodecErrorsNum;
    private int mCodecTimeNum;
    private int mCpuLoad;
    private int mDecodedNum;
    private int mFrequency;
    private int mJitterNum;
    private int mLastTime;
    private int mMaxFrequency;
    private int mRenderDropNum;
    private int mRenderNum;
    private int mRenderTimeNum;
    private int mSourceDropNum;
    private int mSourceTimeNum;
    private int mTotalCPULoad;
    private int mTotalCodecDropNum;
    private int mTotalDecodedNum;
    private int mTotalPlaybackDuration;
    private int mTotalRenderDropNum;
    private int mTotalRenderNum;
    private int mTotalSourceDropNum;
    private int mWorstDecodeTime;
    private int mWorstRenderTime;

    public void setPerformanceData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25) {
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
        this.mCpuLoad = i12;
        this.mFrequency = i13;
        this.mMaxFrequency = i14;
        this.mWorstDecodeTime = i15;
        this.mWorstRenderTime = i16;
        this.mAverageDecodeTime = i17;
        this.mAverageRenderTime = i18;
        this.mTotalCPULoad = i19;
        this.mTotalPlaybackDuration = i20;
        this.mTotalSourceDropNum = i21;
        this.mTotalCodecDropNum = i22;
        this.mTotalRenderDropNum = i23;
        this.mTotalDecodedNum = i24;
        this.mTotalRenderNum = i25;
    }

    public void reset() {
        setPerformanceData(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
    }

    public MutableRendererPerformanceData(MutableRendererPerformanceData mutableRendererPerformanceData) {
        setPerformanceData(mutableRendererPerformanceData.mLastTime, mutableRendererPerformanceData.mSourceDropNum, mutableRendererPerformanceData.mCodecDropNum, mutableRendererPerformanceData.mRenderDropNum, mutableRendererPerformanceData.mDecodedNum, mutableRendererPerformanceData.mRenderNum, mutableRendererPerformanceData.mSourceTimeNum, mutableRendererPerformanceData.mCodecTimeNum, mutableRendererPerformanceData.mRenderTimeNum, mutableRendererPerformanceData.mJitterNum, mutableRendererPerformanceData.mCodecErrorsNum, mutableRendererPerformanceData.mCpuLoad, mutableRendererPerformanceData.mFrequency, mutableRendererPerformanceData.mMaxFrequency, mutableRendererPerformanceData.mWorstDecodeTime, mutableRendererPerformanceData.mWorstRenderTime, mutableRendererPerformanceData.mAverageDecodeTime, mutableRendererPerformanceData.mAverageRenderTime, mutableRendererPerformanceData.mTotalCPULoad, mutableRendererPerformanceData.mTotalPlaybackDuration, mutableRendererPerformanceData.mTotalSourceDropNum, mutableRendererPerformanceData.mTotalCodecDropNum, mutableRendererPerformanceData.mTotalRenderDropNum, mutableRendererPerformanceData.mTotalDecodedNum, mutableRendererPerformanceData.mTotalRenderNum);
    }

    public MutableRendererPerformanceData(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15, int i16, int i17, int i18, int i19, int i20, int i21, int i22, int i23, int i24, int i25) {
        setPerformanceData(i, i2, i3, i4, i5, i6, i7, i8, i9, i10, i11, i12, i13, i14, i15, i16, i17, i18, i19, i20, i21, i22, i23, i24, i25);
    }

    public MutableRendererPerformanceData() {
    }

    public int getLastTime() {
        return this.mLastTime;
    }

    public int getSourceDropNum() {
        return this.mSourceDropNum;
    }

    public int getCodecDropNum() {
        return this.mCodecDropNum;
    }

    public int getRenderDropNum() {
        return this.mRenderDropNum;
    }

    public int getDecodedNum() {
        return this.mDecodedNum;
    }

    public int getRenderNum() {
        return this.mRenderNum;
    }

    public int getSourceTimeNum() {
        return this.mSourceTimeNum;
    }

    public int getCodecTimeNum() {
        return this.mCodecTimeNum;
    }

    public int getRenderTimeNum() {
        return this.mRenderTimeNum;
    }

    public int getJitterNum() {
        return this.mJitterNum;
    }

    public int getCodecErrorsNum() {
        return this.mCodecErrorsNum;
    }

    public int getCpuLoad() {
        return this.mCpuLoad;
    }

    public int getFrequency() {
        return this.mFrequency;
    }

    public int getMaxFrequency() {
        return this.mMaxFrequency;
    }

    public int getWorstDecodeTime() {
        return this.mWorstDecodeTime;
    }

    public int getWorstRenderTime() {
        return this.mWorstRenderTime;
    }

    public int getAverageDecodeTime() {
        return this.mAverageDecodeTime;
    }

    public int getAverageRenderTime() {
        return this.mAverageRenderTime;
    }

    public int getTotalCPULoad() {
        return this.mTotalCPULoad;
    }

    public int getTotalPlaybackDuration() {
        return this.mTotalPlaybackDuration;
    }

    public int getTotalSourceDropNum() {
        return this.mTotalSourceDropNum;
    }

    public int getTotalCodecDropNum() {
        return this.mTotalCodecDropNum;
    }

    public int getTotalRenderDropNum() {
        return this.mTotalRenderDropNum;
    }

    public int getTotalDecodedNum() {
        return this.mTotalDecodedNum;
    }

    public int getTotalRenderNum() {
        return this.mTotalRenderNum;
    }

    public boolean isLowPerformanceDetected() {
        return this.mSourceDropNum > 0 || this.mCodecDropNum > 0 || this.mRenderDropNum > 0 || this.mSourceTimeNum > 0 || this.mCodecTimeNum > 0 || this.mRenderTimeNum > 0 || this.mJitterNum > 0 || this.mCodecErrorsNum > 0;
    }
}

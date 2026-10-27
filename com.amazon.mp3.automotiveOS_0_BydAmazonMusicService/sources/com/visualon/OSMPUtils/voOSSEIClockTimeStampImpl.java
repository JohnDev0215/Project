package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSSEIClockTimeStampImpl implements voOSSEIClockTimeStamp {
    private int mClockTimestampFlag;
    private int mCntDroppedFlag;
    private int mCountingType;
    private int mCtType;
    private int mDiscontinuityFlag;
    private int mFrames;
    private int mFullTimestampFlag;
    private int mHoursFlag;
    private int mHoursValue;
    private int mMinutesFlag;
    private int mMinutesValue;
    private int mNuitFieldBasedFlag;
    private int mSecondsFlag;
    private int mSecondsValue;
    private int mTimeOffset;

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getClockTimestampFlag() {
        return this.mClockTimestampFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getCtType() {
        return this.mCtType;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getNuitFieldBasedFlag() {
        return this.mNuitFieldBasedFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getCountingType() {
        return this.mCountingType;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getFullTimestampFlag() {
        return this.mFullTimestampFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getDiscontinuityFlag() {
        return this.mDiscontinuityFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getCntDroppedFlag() {
        return this.mCntDroppedFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getFrames() {
        return this.mFrames;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getSecondsValue() {
        return this.mSecondsValue;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getMinutesValue() {
        return this.mMinutesValue;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getHoursValue() {
        return this.mHoursValue;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getSecondsFlag() {
        return this.mSecondsFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getMinutesFlag() {
        return this.mMinutesFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getHoursFlag() {
        return this.mHoursFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIClockTimeStamp
    public int getTimeOffset() {
        return this.mTimeOffset;
    }

    public voOSSEIClockTimeStampImpl(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11, int i12, int i13, int i14, int i15) {
        this.mClockTimestampFlag = i;
        this.mCtType = i2;
        this.mNuitFieldBasedFlag = i3;
        this.mCountingType = i4;
        this.mFullTimestampFlag = i5;
        this.mDiscontinuityFlag = i6;
        this.mCntDroppedFlag = i7;
        this.mFrames = i8;
        this.mSecondsValue = i9;
        this.mMinutesValue = i10;
        this.mHoursValue = i11;
        this.mSecondsFlag = i12;
        this.mMinutesFlag = i13;
        this.mHoursFlag = i14;
        this.mTimeOffset = i15;
    }
}

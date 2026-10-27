package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSSEIPicTimingImpl implements voOSSEIPicTiming {
    private static String TAG = "@@@voOSSEIPicTimingImpl";
    private int mCpbDpbDelaysPresentFlag;
    private int mCpbRemovalDelay;
    private int mDpbOutputDelay;
    private int mNumClockTs;
    private voOSSEIClockTimeStampImpl[] mOSClockArr;
    private int mPictureStructure;
    private int mPictureStructurePresentFlag;

    public voOSSEIPicTimingImpl(int i, int i2, int i3, int i4, int i5, int i6, voOSSEIClockTimeStampImpl[] voosseiclocktimestampimplArr) {
        this.mCpbDpbDelaysPresentFlag = i;
        this.mCpbRemovalDelay = i2;
        this.mDpbOutputDelay = i3;
        this.mPictureStructurePresentFlag = i4;
        this.mPictureStructure = i5;
        this.mNumClockTs = i6;
        this.mOSClockArr = voosseiclocktimestampimplArr;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIPicTiming
    public int getCpbDpbDelaysPresentFlag() {
        return this.mCpbDpbDelaysPresentFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIPicTiming
    public int getCpbRemovalDelay() {
        return this.mCpbRemovalDelay;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIPicTiming
    public int getDpbOutputDelay() {
        return this.mDpbOutputDelay;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIPicTiming
    public int getPictureStructurePresentFlag() {
        return this.mPictureStructurePresentFlag;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIPicTiming
    public int getPictureStructure() {
        return this.mPictureStructure;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIPicTiming
    public int getNumClockTs() {
        return this.mNumClockTs;
    }

    @Override // com.visualon.OSMPUtils.voOSSEIPicTiming
    public voOSSEIClockTimeStamp[] getClock() {
        return this.mOSClockArr;
    }

    public static voOSSEIPicTimingImpl parse(int[] iArr) {
        if (iArr == null || iArr.length < 51) {
            if (!voLog.enablePrintLog()) {
                return null;
            }
            voLog.e(TAG, "voOSSEIPicTimingImpl parse failed, data is " + iArr, new Object[0]);
            return null;
        }
        return new voOSSEIPicTimingImpl(iArr[0], iArr[1], iArr[2], iArr[3], iArr[4], iArr[5], new voOSSEIClockTimeStampImpl[]{new voOSSEIClockTimeStampImpl(iArr[6], iArr[7], iArr[8], iArr[9], iArr[10], iArr[11], iArr[12], iArr[13], iArr[14], iArr[15], iArr[16], iArr[17], iArr[18], iArr[19], iArr[20]), new voOSSEIClockTimeStampImpl(iArr[21], iArr[22], iArr[23], iArr[24], iArr[25], iArr[26], iArr[27], iArr[28], iArr[29], iArr[30], iArr[31], iArr[32], iArr[33], iArr[34], iArr[35]), new voOSSEIClockTimeStampImpl(iArr[36], iArr[37], iArr[38], iArr[39], iArr[40], iArr[41], iArr[42], iArr[43], iArr[44], iArr[45], iArr[46], iArr[47], iArr[48], iArr[49], iArr[50])});
    }
}

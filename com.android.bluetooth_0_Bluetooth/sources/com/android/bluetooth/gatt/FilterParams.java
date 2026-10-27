package com.android.bluetooth.gatt;

/* JADX INFO: loaded from: classes.dex */
public class FilterParams {
    private int mClientIf;
    private int mDelyMode;
    private int mFeatSeln;
    private int mFiltIndex;
    private int mFiltLogicType;
    private int mFoundTimeOut;
    private int mFoundTimeOutCnt;
    private int mListLogicType;
    private int mLostTimeOut;
    private int mNumOfTrackEntries;
    private int mRssiHighValue;
    private int mRssiLowValue;

    public FilterParams(int clientIf, int filtIndex, int featSeln, int listLogicType, int filtLogicType, int rssiHighThres, int rssiLowThres, int delyMode, int foundTimeout, int lostTimeout, int foundTimeoutCnt, int numOfTrackingEntries) {
        this.mClientIf = clientIf;
        this.mFiltIndex = filtIndex;
        this.mFeatSeln = featSeln;
        this.mListLogicType = listLogicType;
        this.mFiltLogicType = filtLogicType;
        this.mRssiHighValue = rssiHighThres;
        this.mRssiLowValue = rssiLowThres;
        this.mDelyMode = delyMode;
        this.mFoundTimeOut = foundTimeout;
        this.mLostTimeOut = lostTimeout;
        this.mFoundTimeOutCnt = foundTimeoutCnt;
        this.mNumOfTrackEntries = numOfTrackingEntries;
    }

    public int getClientIf() {
        return this.mClientIf;
    }

    public int getFiltIndex() {
        return this.mFiltIndex;
    }

    public int getFeatSeln() {
        return this.mFeatSeln;
    }

    public int getDelyMode() {
        return this.mDelyMode;
    }

    public int getListLogicType() {
        return this.mListLogicType;
    }

    public int getFiltLogicType() {
        return this.mFiltLogicType;
    }

    public int getRSSIHighValue() {
        return this.mRssiHighValue;
    }

    public int getRSSILowValue() {
        return this.mRssiLowValue;
    }

    public int getFoundTimeout() {
        return this.mFoundTimeOut;
    }

    public int getFoundTimeOutCnt() {
        return this.mFoundTimeOutCnt;
    }

    public int getLostTimeout() {
        return this.mLostTimeOut;
    }

    public int getNumOfTrackEntries() {
        return this.mNumOfTrackEntries;
    }
}

package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSPerformanceDataImpl implements voOSPerformanceData {
    int mBitRate;
    int mCodecType;
    int mFPS;
    int mProfileLevel;
    int mVideoHeight;
    int mVideoWidth;

    @Override // com.visualon.OSMPUtils.voOSPerformanceData
    public int CodecType() {
        return this.mCodecType;
    }

    @Override // com.visualon.OSMPUtils.voOSPerformanceData
    public int BitRate() {
        return this.mBitRate;
    }

    @Override // com.visualon.OSMPUtils.voOSPerformanceData
    public int VideoWidth() {
        return this.mVideoWidth;
    }

    @Override // com.visualon.OSMPUtils.voOSPerformanceData
    public int VideoHeight() {
        return this.mVideoHeight;
    }

    @Override // com.visualon.OSMPUtils.voOSPerformanceData
    public int ProfileLevel() {
        return this.mProfileLevel;
    }

    @Override // com.visualon.OSMPUtils.voOSPerformanceData
    public int FPS() {
        return this.mFPS;
    }

    public voOSPerformanceDataImpl(int i, int i2, int i3, int i4, int i5, int i6) {
        this.mCodecType = i;
        this.mBitRate = i2;
        this.mVideoWidth = i3;
        this.mVideoHeight = i4;
        this.mProfileLevel = i5;
        this.mFPS = i6;
    }
}

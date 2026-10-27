package com.visualon.OSMPUtils;

/* JADX INFO: loaded from: classes7.dex */
public class voOSDVRInfoImpl implements voOSDVRInfo {
    long mEndTime;
    long mLiveTime;
    long mPlayingTime;
    long mStartTime;

    public voOSDVRInfoImpl() {
    }

    public voOSDVRInfoImpl(long j, long j2, long j3, long j4) {
        this.mStartTime = j;
        this.mEndTime = j2;
        this.mPlayingTime = j3;
        this.mLiveTime = j4;
    }

    @Override // com.visualon.OSMPUtils.voOSDVRInfo
    public long getStartTime() {
        return this.mStartTime;
    }

    @Override // com.visualon.OSMPUtils.voOSDVRInfo
    public long getEndTime() {
        return this.mEndTime;
    }

    @Override // com.visualon.OSMPUtils.voOSDVRInfo
    public long getPlayingTime() {
        return this.mPlayingTime;
    }

    @Override // com.visualon.OSMPUtils.voOSDVRInfo
    public long getLiveTime() {
        return this.mLiveTime;
    }
}

package com.amazon.avod.media.events.dao;

/* JADX INFO: loaded from: classes2.dex */
public class MediaEventRecord {
    private final String mAppInstanceId;
    private final String mPayload;
    private final long mSequence;
    private final long mTimeStamp;
    private final String mType;

    public MediaEventRecord(long j, String str, long j2, String str2, String str3) {
        this.mSequence = j;
        this.mAppInstanceId = str;
        this.mTimeStamp = j2;
        this.mType = str2;
        this.mPayload = str3;
    }

    public long getSequence() {
        return this.mSequence;
    }

    public String getAppInstanceId() {
        return this.mAppInstanceId;
    }

    public long getTimeStamp() {
        return this.mTimeStamp;
    }

    public String getType() {
        return this.mType;
    }

    public String getPayload() {
        return this.mPayload;
    }
}

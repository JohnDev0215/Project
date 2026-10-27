package com.amazon.avod.media.events.clientapi;

import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class MediaReport {
    private final String mAppInstanceId;
    private final List<String> mEvents;
    private final long mTimestamp;

    public MediaReport(String str, long j, List<String> list) {
        this.mAppInstanceId = str;
        this.mTimestamp = j;
        this.mEvents = list;
    }

    public String getAppInstanceId() {
        return this.mAppInstanceId;
    }

    public long getTimestamp() {
        return this.mTimestamp;
    }

    public List<String> getEvents() {
        return this.mEvents;
    }
}

package com.byd.event_upload;

/* JADX INFO: loaded from: classes2.dex */
public class EventInfo {
    private String eventId;
    private long eventTime;
    private String eventValue;
    private int id;

    public EventInfo() {
    }

    public EventInfo(String str, long j, String str2) {
        this.eventId = str;
        this.eventTime = j;
        this.eventValue = str2;
    }

    public EventInfo(String str, long j, String str2, int i) {
        this(str, j, str2);
        this.id = i;
    }

    public String getEventId() {
        return this.eventId;
    }

    public long getEventTime() {
        return this.eventTime;
    }

    public String getEventValue() {
        return this.eventValue;
    }

    public int getId() {
        return this.id;
    }

    public void setEventId(String str) {
        this.eventId = str;
    }

    public void setEventTime(long j) {
        this.eventTime = j;
    }

    public void setEventValue(String str) {
        this.eventValue = str;
    }

    public void setId(int i) {
        this.id = i;
    }

    public String toString() {
        return "EventInfo{eventId='" + this.eventId + "', eventTime=" + this.eventTime + ", eventValue='" + this.eventValue + "'}";
    }
}

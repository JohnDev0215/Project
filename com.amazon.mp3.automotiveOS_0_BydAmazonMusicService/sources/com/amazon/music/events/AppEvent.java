package com.amazon.music.events;

/* JADX INFO: loaded from: classes4.dex */
public class AppEvent {
    static final AppEventPublisher appEventPublisher = new AppEventPublisher();
    private long eventTime = -1;

    public long getEventTime() {
        return this.eventTime;
    }

    public void setEventTime(long j) {
        this.eventTime = j;
    }
}

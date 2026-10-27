package com.spotify.eventsender.internalerrors;

/* JADX INFO: loaded from: classes.dex */
public enum EventSenderInternalError {
    TOO_MANY_CLONED_EVENTS("TOO_MANY_CLONED_EVENTS"),
    EVENTS_QUEUE_RESTARTED("EVENTS_QUEUE_RESTARTED"),
    EVENTS_QUEUE_PERMANENTLY_STOPPED("EVENTS_QUEUE_PERMANENTLY_STOPPED"),
    DISK_OP_READ_FAILED("DISK_OP_READ_FAILED"),
    DISK_OP_WRITE_FAILED("DISK_OP_WRITE_FAILED");

    private final String typeName;

    EventSenderInternalError(String str) {
        this.typeName = str;
    }

    public final String a() {
        return this.typeName;
    }
}

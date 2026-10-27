package com.spotify.eventsender.eventsender.observability;

import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class EventSenderMonitorBackendError extends Exception {
    private final String message;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public EventSenderMonitorBackendError(String str) {
        super(str);
        v03.j(str, "message");
        this.message = str;
    }

    @Override // java.lang.Throwable
    public final String getMessage() {
        return this.message;
    }
}

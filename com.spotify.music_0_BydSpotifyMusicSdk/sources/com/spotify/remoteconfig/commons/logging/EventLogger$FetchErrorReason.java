package com.spotify.remoteconfig.commons.logging;

/* JADX INFO: loaded from: classes.dex */
public enum EventLogger$FetchErrorReason {
    UNKNOWN("unknown"),
    TIMEOUT("timeout"),
    /* JADX INFO: Fake field, exist only in values array */
    STREAM_TIMEOUT("stream_timeout"),
    CLIENT_ERROR("client_error"),
    SERVER_ERROR("server_error"),
    NOT_FOUND("not_found");

    private final String value;

    EventLogger$FetchErrorReason(String str) {
        this.value = str;
    }

    public final String a() {
        return this.value;
    }
}

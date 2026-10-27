package com.amazon.music.skyfire.core.network;

/* JADX INFO: loaded from: classes4.dex */
public enum NetworkTaskError {
    INVALID_URL("invalidUrl"),
    INVALID_METHOD_TYPE("invalidMethodType"),
    SERVER_ERROR("serverError"),
    REQUEST_TIMEOUT("requestTimeout"),
    DECODING_ERROR("decodingError"),
    AUTHENTICATION_ERROR("authenticationError");

    public final String label;

    NetworkTaskError(String str) {
        this.label = str;
    }
}

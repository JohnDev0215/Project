package com.apollographql.apollo3.exception;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloWebSocketClosedException extends ApolloException {
    private final int code;
    private final String reason;

    public ApolloWebSocketClosedException(int i, String str) {
        super("WebSocket Closed code='" + i + "' reason='" + str + '\'', null);
        this.code = i;
        this.reason = str;
    }
}

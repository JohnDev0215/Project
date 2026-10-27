package com.apollographql.apollo3.exception;

/* JADX INFO: loaded from: classes.dex */
public final class ApolloNetworkException extends ApolloException {
    private final Object platformCause;

    public ApolloNetworkException(Object obj, String str) {
        super(str, obj instanceof Throwable ? (Throwable) obj : null);
        this.platformCause = obj;
    }
}

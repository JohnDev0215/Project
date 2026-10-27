package com.apollographql.apollo3.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\f¢\u0006\u0004\b\u000e\u0010\u000fR\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0019\u0010\b\u001a\u0004\u0018\u00010\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b¨\u0006\u0010"}, d2 = {"Lcom/apollographql/apollo3/exception/ApolloWebSocketClosedException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "", "code", "I", "getCode", "()I", "", "reason", "Ljava/lang/String;", "getReason", "()Ljava/lang/String;", "", "cause", "<init>", "(ILjava/lang/String;Ljava/lang/Throwable;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class ApolloWebSocketClosedException extends ApolloException {
    private final int code;
    private final String reason;

    public /* synthetic */ ApolloWebSocketClosedException(int i, String str, Throwable th, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, (i2 & 2) != 0 ? null : str, (i2 & 4) != 0 ? null : th);
    }

    public final int getCode() {
        return this.code;
    }

    public ApolloWebSocketClosedException(int i, String str, Throwable th) {
        super("WebSocket Closed code='" + i + "' reason='" + ((Object) str) + '\'', th);
        this.code = i;
        this.reason = str;
    }
}

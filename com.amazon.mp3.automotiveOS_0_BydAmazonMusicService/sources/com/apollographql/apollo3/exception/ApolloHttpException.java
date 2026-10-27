package com.apollographql.apollo3.exception;

import com.apollographql.apollo3.api.http.HttpHeader;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okio.BufferedSource;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0004\u0018\u00002\u00020\u0001B;\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\b\u0010\u000e\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u0013\u001a\u00020\u0012\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0014¢\u0006\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u001d\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u00078\u0006¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0019\u0010\u000e\u001a\u0004\u0018\u00010\r8\u0006¢\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0018"}, d2 = {"Lcom/apollographql/apollo3/exception/ApolloHttpException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "", "statusCode", "I", "getStatusCode", "()I", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "Ljava/util/List;", "getHeaders", "()Ljava/util/List;", "Lokio/BufferedSource;", "body", "Lokio/BufferedSource;", "getBody", "()Lokio/BufferedSource;", "", "message", "", "cause", "<init>", "(ILjava/util/List;Lokio/BufferedSource;Ljava/lang/String;Ljava/lang/Throwable;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class ApolloHttpException extends ApolloException {
    private final BufferedSource body;
    private final List<HttpHeader> headers;
    private final int statusCode;

    public /* synthetic */ ApolloHttpException(int i, List list, BufferedSource bufferedSource, String str, Throwable th, int i2, DefaultConstructorMarker defaultConstructorMarker) {
        this(i, list, bufferedSource, str, (i2 & 16) != 0 ? null : th);
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ApolloHttpException(int i, List<HttpHeader> headers, BufferedSource bufferedSource, String message, Throwable th) {
        super(message, th);
        Intrinsics.checkNotNullParameter(headers, "headers");
        Intrinsics.checkNotNullParameter(message, "message");
        this.statusCode = i;
        this.headers = headers;
        this.body = bufferedSource;
    }
}

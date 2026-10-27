package com.apollographql.apollo3.exception;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: Exceptions.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u001f\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\u0003\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nR\u0019\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/apollographql/apollo3/exception/ApolloNetworkException;", "Lcom/apollographql/apollo3/exception/ApolloException;", "", "platformCause", "Ljava/lang/Object;", "getPlatformCause", "()Ljava/lang/Object;", "", "message", "<init>", "(Ljava/lang/String;Ljava/lang/Object;)V", "apollo-api"}, k = 1, mv = {1, 5, 1})
public final class ApolloNetworkException extends ApolloException {
    private final Object platformCause;

    public ApolloNetworkException() {
        this(null, 0 == true ? 1 : 0, 3, 0 == true ? 1 : 0);
    }

    public /* synthetic */ ApolloNetworkException(String str, Object obj, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : str, (i & 2) != 0 ? null : obj);
    }

    public ApolloNetworkException(String str, Object obj) {
        super(str, obj instanceof Throwable ? (Throwable) obj : null);
        this.platformCause = obj;
    }
}

package com.apollographql.apollo3.network.http;

import com.apollographql.apollo3.api.ExecutionContext;
import com.apollographql.apollo3.api.http.HttpHeader;
import java.util.List;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: HttpExecutionContext.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB/\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\u0006\u0010\u0007\u001a\u00020\u0002\u0012\u0006\u0010\n\u001a\u00020\t\u0012\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0018\u0010\u0019R\u0017\u0010\u0003\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\u00020\u00028\u0006¢\u0006\f\n\u0004\b\u0007\u0010\u0004\u001a\u0004\b\b\u0010\u0006R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u0018\u0010\u0017\u001a\u0006\u0012\u0002\b\u00030\u00148VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpInfo;", "Lcom/apollographql/apollo3/api/ExecutionContext$Element;", "", "startMillis", "J", "getStartMillis", "()J", "endMillis", "getEndMillis", "", "statusCode", "I", "getStatusCode", "()I", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "headers", "Ljava/util/List;", "getHeaders", "()Ljava/util/List;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "getKey", "()Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "key", "<init>", "(JJILjava/util/List;)V", "Key", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class HttpInfo implements ExecutionContext.Element {

    /* JADX INFO: renamed from: Key, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long endMillis;
    private final List<HttpHeader> headers;
    private final long startMillis;
    private final int statusCode;

    @Deprecated(message = "HttpInfo is only to be constructed internally. Declare your own class if needed")
    public HttpInfo(long j, long j2, int i, List<HttpHeader> headers) {
        Intrinsics.checkNotNullParameter(headers, "headers");
        this.startMillis = j;
        this.endMillis = j2;
        this.statusCode = i;
        this.headers = headers;
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public <R> R fold(R r, Function2<? super R, ? super ExecutionContext.Element, ? extends R> function2) {
        return (R) ExecutionContext.Element.DefaultImpls.fold(this, r, function2);
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext.Element, com.apollographql.apollo3.api.ExecutionContext
    public <E extends ExecutionContext.Element> E get(ExecutionContext.Key<E> key) {
        return (E) ExecutionContext.Element.DefaultImpls.get(this, key);
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public ExecutionContext minusKey(ExecutionContext.Key<?> key) {
        return ExecutionContext.Element.DefaultImpls.minusKey(this, key);
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext
    public ExecutionContext plus(ExecutionContext executionContext) {
        return ExecutionContext.Element.DefaultImpls.plus(this, executionContext);
    }

    public final int getStatusCode() {
        return this.statusCode;
    }

    @Override // com.apollographql.apollo3.api.ExecutionContext.Element
    public ExecutionContext.Key<?> getKey() {
        return INSTANCE;
    }

    /* JADX INFO: renamed from: com.apollographql.apollo3.network.http.HttpInfo$Key, reason: from kotlin metadata */
    /* JADX INFO: compiled from: HttpExecutionContext.kt */
    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/apollographql/apollo3/network/http/HttpInfo$Key;", "Lcom/apollographql/apollo3/api/ExecutionContext$Key;", "Lcom/apollographql/apollo3/network/http/HttpInfo;", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
    public static final class Companion implements ExecutionContext.Key<HttpInfo> {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}

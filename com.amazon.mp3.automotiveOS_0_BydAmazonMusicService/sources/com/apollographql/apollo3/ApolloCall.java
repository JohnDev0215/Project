package com.apollographql.apollo3;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.ExecutionContext;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Operation.Data;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: ApolloCall.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u000f\u0018\u0000*\b\b\u0000\u0010\u0002*\u00020\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00000\u0003B\u001f\b\u0000\u0012\u0006\u0010\n\u001a\u00020\t\u0012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e¢\u0006\u0004\b6\u00107J\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004J\u0019\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005H\u0086@ø\u0001\u0000¢\u0006\u0004\b\u0007\u0010\bR\u001a\u0010\n\u001a\u00020\t8\u0000X\u0080\u0004¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\"\u0010\u0014\u001a\u00020\u00138\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R$\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R*\u0010#\u001a\n\u0012\u0004\u0012\u00020\"\u0018\u00010!8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b#\u0010$\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R$\u0010*\u001a\u0004\u0018\u00010)8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b*\u0010+\u001a\u0004\b,\u0010-\"\u0004\b.\u0010/R$\u00100\u001a\u0004\u0018\u00010)8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b0\u0010+\u001a\u0004\b1\u0010-\"\u0004\b2\u0010/R$\u00103\u001a\u0004\u0018\u00010)8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b3\u0010+\u001a\u0004\b4\u0010-\"\u0004\b5\u0010/\u0082\u0002\u0004\n\u0002\b\u0019¨\u00068"}, d2 = {"Lcom/apollographql/apollo3/ApolloCall;", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "toFlow", "execute", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/apollographql/apollo3/ApolloClient;", "apolloClient", "Lcom/apollographql/apollo3/ApolloClient;", "getApolloClient$apollo_runtime", "()Lcom/apollographql/apollo3/ApolloClient;", "Lcom/apollographql/apollo3/api/Operation;", "operation", "Lcom/apollographql/apollo3/api/Operation;", "getOperation", "()Lcom/apollographql/apollo3/api/Operation;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "setExecutionContext", "(Lcom/apollographql/apollo3/api/ExecutionContext;)V", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethod", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "setHttpMethod", "(Lcom/apollographql/apollo3/api/http/HttpMethod;)V", "", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "httpHeaders", "Ljava/util/List;", "getHttpHeaders", "()Ljava/util/List;", "setHttpHeaders", "(Ljava/util/List;)V", "", "sendApqExtensions", "Ljava/lang/Boolean;", "getSendApqExtensions", "()Ljava/lang/Boolean;", "setSendApqExtensions", "(Ljava/lang/Boolean;)V", "sendDocument", "getSendDocument", "setSendDocument", "enableAutoPersistedQueries", "getEnableAutoPersistedQueries", "setEnableAutoPersistedQueries", "<init>", "(Lcom/apollographql/apollo3/ApolloClient;Lcom/apollographql/apollo3/api/Operation;)V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class ApolloCall<D extends Operation.Data> {
    private final ApolloClient apolloClient;
    private Boolean enableAutoPersistedQueries;
    private ExecutionContext executionContext;
    private List<HttpHeader> httpHeaders;
    private HttpMethod httpMethod;
    private final Operation<D> operation;
    private Boolean sendApqExtensions;
    private Boolean sendDocument;

    public ApolloCall(ApolloClient apolloClient, Operation<D> operation) {
        Intrinsics.checkNotNullParameter(apolloClient, "apolloClient");
        Intrinsics.checkNotNullParameter(operation, "operation");
        this.apolloClient = apolloClient;
        this.operation = operation;
        this.executionContext = ExecutionContext.Empty;
    }

    public ExecutionContext getExecutionContext() {
        return this.executionContext;
    }

    public HttpMethod getHttpMethod() {
        return this.httpMethod;
    }

    public List<HttpHeader> getHttpHeaders() {
        return this.httpHeaders;
    }

    public Boolean getSendApqExtensions() {
        return this.sendApqExtensions;
    }

    public Boolean getSendDocument() {
        return this.sendDocument;
    }

    public Boolean getEnableAutoPersistedQueries() {
        return this.enableAutoPersistedQueries;
    }

    public final Flow<ApolloResponse<D>> toFlow() {
        return this.apolloClient.executeAsFlow(new ApolloRequest.Builder(this.operation).executionContext(getExecutionContext()).httpMethod(getHttpMethod()).httpHeaders(getHttpHeaders()).sendApqExtensions(getSendApqExtensions()).sendDocument(getSendDocument()).enableAutoPersistedQueries(getEnableAutoPersistedQueries()).build());
    }

    public final Object execute(Continuation<? super ApolloResponse<D>> continuation) {
        return FlowKt.single(toFlow(), continuation);
    }
}

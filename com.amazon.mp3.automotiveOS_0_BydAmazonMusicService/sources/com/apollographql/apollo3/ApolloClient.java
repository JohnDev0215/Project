package com.apollographql.apollo3;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.CustomScalarAdapters;
import com.apollographql.apollo3.api.ExecutionContext;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.api.http.HttpHeader;
import com.apollographql.apollo3.api.http.HttpMethod;
import com.apollographql.apollo3.interceptor.ApolloInterceptor;
import com.apollographql.apollo3.interceptor.DefaultInterceptorChain;
import com.apollographql.apollo3.interceptor.NetworkInterceptor;
import com.apollographql.apollo3.internal.DispatchersKt;
import com.apollographql.apollo3.mpp.UtilsKt;
import com.apollographql.apollo3.network.NetworkTransport;
import com.apollographql.apollo3.network.http.HttpEngine;
import com.apollographql.apollo3.network.http.HttpInterceptor;
import com.apollographql.apollo3.network.http.HttpNetworkTransport;
import com.apollographql.apollo3.network.ws.WebSocketEngine;
import com.apollographql.apollo3.network.ws.WebSocketNetworkTransport;
import com.apollographql.apollo3.network.ws.WsProtocol;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.google.android.gms.actions.SearchIntents;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsKt;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: ApolloClient.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 F2\u00020\u0001:\u0002GFB\u008b\u0001\b\u0002\u0012\u0006\u0010\u000f\u001a\u00020\u000e\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\u0006\u0010\u0018\u001a\u00020\u000e\u0012\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a\u0012\u0006\u0010!\u001a\u00020 \u0012\b\u0010&\u001a\u0004\u0018\u00010%\u0012\b\u0010)\u001a\u0004\u0018\u00010(\u0012\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\u001a\u0012\b\u00101\u001a\u0004\u0018\u000100\u0012\b\u00105\u001a\u0004\u0018\u000100\u0012\b\u00107\u001a\u0004\u0018\u000100\u0012\b\u00109\u001a\u0004\u0018\u000100\u0012\u0006\u0010<\u001a\u00020;¢\u0006\u0004\bD\u0010EJ$\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003J$\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\"\b\b\u0000\u0010\u0002*\u00020\u00012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006J*\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\f0\u000b\"\b\b\u0000\u0010\u0002*\u00020\b2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\tR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u0018\u0010\u0010\u001a\u0004\b\u0019\u0010\u0012R\u001d\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a8\u0006¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001e\u0010\u001fR\u001a\u0010!\u001a\u00020 8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b#\u0010$R\u0016\u0010&\u001a\u0004\u0018\u00010%8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b&\u0010'R\u001c\u0010)\u001a\u0004\u0018\u00010(8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b)\u0010*\u001a\u0004\b+\u0010,R\"\u0010.\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010\u001a8\u0016X\u0096\u0004¢\u0006\f\n\u0004\b.\u0010\u001d\u001a\u0004\b/\u0010\u001fR\u001c\u00101\u001a\u0004\u0018\u0001008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b1\u00102\u001a\u0004\b3\u00104R\u001c\u00105\u001a\u0004\u0018\u0001008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b5\u00102\u001a\u0004\b6\u00104R\u001c\u00107\u001a\u0004\u0018\u0001008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b7\u00102\u001a\u0004\b8\u00104R\u001c\u00109\u001a\u0004\u0018\u0001008\u0016X\u0096\u0004¢\u0006\f\n\u0004\b9\u00102\u001a\u0004\b:\u00104R\u0014\u0010<\u001a\u00020;8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b<\u0010=R\u0014\u0010?\u001a\u00020>8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b?\u0010@R\u0014\u0010B\u001a\u00020A8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\bB\u0010C¨\u0006H"}, d2 = {"Lcom/apollographql/apollo3/ApolloClient;", "", "D", "Lcom/apollographql/apollo3/api/Query;", SearchIntents.EXTRA_QUERY, "Lcom/apollographql/apollo3/ApolloCall;", "Lcom/apollographql/apollo3/api/Mutation;", "mutation", "Lcom/apollographql/apollo3/api/Operation$Data;", "Lcom/apollographql/apollo3/api/ApolloRequest;", "apolloRequest", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "executeAsFlow", "Lcom/apollographql/apollo3/network/NetworkTransport;", "networkTransport", "Lcom/apollographql/apollo3/network/NetworkTransport;", "getNetworkTransport", "()Lcom/apollographql/apollo3/network/NetworkTransport;", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "customScalarAdapters", "Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "getCustomScalarAdapters", "()Lcom/apollographql/apollo3/api/CustomScalarAdapters;", "subscriptionNetworkTransport", "getSubscriptionNetworkTransport", "", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptor;", "interceptors", "Ljava/util/List;", "getInterceptors", "()Ljava/util/List;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "Lkotlinx/coroutines/CoroutineDispatcher;", "requestedDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethod", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "httpHeaders", "getHttpHeaders", "", "sendApqExtensions", "Ljava/lang/Boolean;", "getSendApqExtensions", "()Ljava/lang/Boolean;", "sendDocument", "getSendDocument", "enableAutoPersistedQueries", "getEnableAutoPersistedQueries", "canBeBatched", "getCanBeBatched", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "builder", "Lcom/apollographql/apollo3/ApolloClient$Builder;", "Lcom/apollographql/apollo3/ConcurrencyInfo;", "concurrencyInfo", "Lcom/apollographql/apollo3/ConcurrencyInfo;", "Lcom/apollographql/apollo3/interceptor/NetworkInterceptor;", "networkInterceptor", "Lcom/apollographql/apollo3/interceptor/NetworkInterceptor;", "<init>", "(Lcom/apollographql/apollo3/network/NetworkTransport;Lcom/apollographql/apollo3/api/CustomScalarAdapters;Lcom/apollographql/apollo3/network/NetworkTransport;Ljava/util/List;Lcom/apollographql/apollo3/api/ExecutionContext;Lkotlinx/coroutines/CoroutineDispatcher;Lcom/apollographql/apollo3/api/http/HttpMethod;Ljava/util/List;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Ljava/lang/Boolean;Lcom/apollographql/apollo3/ApolloClient$Builder;)V", "Companion", "Builder", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public final class ApolloClient {
    private final Builder builder;
    private final Boolean canBeBatched;
    private final ConcurrencyInfo concurrencyInfo;
    private final CustomScalarAdapters customScalarAdapters;
    private final Boolean enableAutoPersistedQueries;
    private final ExecutionContext executionContext;
    private final List<HttpHeader> httpHeaders;
    private final HttpMethod httpMethod;
    private final List<ApolloInterceptor> interceptors;
    private final NetworkInterceptor networkInterceptor;
    private final NetworkTransport networkTransport;
    private final CoroutineDispatcher requestedDispatcher;
    private final Boolean sendApqExtensions;
    private final Boolean sendDocument;
    private final NetworkTransport subscriptionNetworkTransport;

    public /* synthetic */ ApolloClient(NetworkTransport networkTransport, CustomScalarAdapters customScalarAdapters, NetworkTransport networkTransport2, List list, ExecutionContext executionContext, CoroutineDispatcher coroutineDispatcher, HttpMethod httpMethod, List list2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Builder builder, DefaultConstructorMarker defaultConstructorMarker) {
        this(networkTransport, customScalarAdapters, networkTransport2, list, executionContext, coroutineDispatcher, httpMethod, list2, bool, bool2, bool3, bool4, builder);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private ApolloClient(NetworkTransport networkTransport, CustomScalarAdapters customScalarAdapters, NetworkTransport networkTransport2, List<? extends ApolloInterceptor> list, ExecutionContext executionContext, CoroutineDispatcher coroutineDispatcher, HttpMethod httpMethod, List<HttpHeader> list2, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, Builder builder) {
        this.networkTransport = networkTransport;
        this.customScalarAdapters = customScalarAdapters;
        this.subscriptionNetworkTransport = networkTransport2;
        this.interceptors = list;
        this.executionContext = executionContext;
        this.requestedDispatcher = coroutineDispatcher;
        this.httpMethod = httpMethod;
        this.httpHeaders = list2;
        this.sendApqExtensions = bool;
        this.sendDocument = bool2;
        this.enableAutoPersistedQueries = bool3;
        this.canBeBatched = bool4;
        this.builder = builder;
        CoroutineDispatcher coroutineDispatcherDefaultDispatcher = DispatchersKt.defaultDispatcher(coroutineDispatcher);
        ConcurrencyInfo concurrencyInfo = new ConcurrencyInfo(coroutineDispatcherDefaultDispatcher, CoroutineScopeKt.CoroutineScope(coroutineDispatcherDefaultDispatcher));
        this.concurrencyInfo = concurrencyInfo;
        this.networkInterceptor = new NetworkInterceptor(networkTransport, networkTransport2, concurrencyInfo.getDispatcher());
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

    public final <D> ApolloCall<D> query(Query<D> query) {
        Intrinsics.checkNotNullParameter(query, "query");
        return new ApolloCall<>(this, query);
    }

    public final <D> ApolloCall<D> mutation(Mutation<D> mutation) {
        Intrinsics.checkNotNullParameter(mutation, "mutation");
        return new ApolloCall<>(this, mutation);
    }

    public final <D extends Operation.Data> Flow<ApolloResponse<D>> executeAsFlow(ApolloRequest<D> apolloRequest) {
        Intrinsics.checkNotNullParameter(apolloRequest, "apolloRequest");
        UtilsKt.assertMainThreadOnNative();
        ApolloRequest.Builder<D> builderEnableAutoPersistedQueries = new ApolloRequest.Builder(apolloRequest.getOperation()).addExecutionContext(this.concurrencyInfo).addExecutionContext(this.customScalarAdapters).addExecutionContext(this.concurrencyInfo.plus(this.customScalarAdapters).plus(getExecutionContext()).plus(apolloRequest.getExecutionContext())).addExecutionContext(apolloRequest.getExecutionContext()).httpMethod(getHttpMethod()).httpHeaders(getHttpHeaders()).sendApqExtensions(getSendApqExtensions()).sendDocument(getSendDocument()).enableAutoPersistedQueries(getEnableAutoPersistedQueries());
        if (apolloRequest.getHttpMethod() != null) {
            builderEnableAutoPersistedQueries.httpMethod(apolloRequest.getHttpMethod());
        }
        if (apolloRequest.getHttpHeaders() != null) {
            builderEnableAutoPersistedQueries.httpHeaders(apolloRequest.getHttpHeaders());
        }
        if (apolloRequest.getSendApqExtensions() != null) {
            builderEnableAutoPersistedQueries.sendApqExtensions(apolloRequest.getSendApqExtensions());
        }
        if (apolloRequest.getSendDocument() != null) {
            builderEnableAutoPersistedQueries.sendDocument(apolloRequest.getSendDocument());
        }
        if (apolloRequest.getEnableAutoPersistedQueries() != null) {
            builderEnableAutoPersistedQueries.enableAutoPersistedQueries(apolloRequest.getEnableAutoPersistedQueries());
        }
        return new DefaultInterceptorChain(CollectionsKt___CollectionsKt.plus((Collection<? extends NetworkInterceptor>) this.interceptors, this.networkInterceptor), 0).proceed(builderEnableAutoPersistedQueries.build());
    }

    /* JADX INFO: compiled from: ApolloClient.kt */
    @Metadata(bv = {}, d1 = {"\u0000\u009e\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0015\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007¢\u0006\u0004\bW\u0010XJ\u0018\u0010\u0005\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0004\u001a\u00020\u0002H\u0016J\u000e\u0010\u0006\u001a\u00020\u00002\u0006\u0010\u0006\u001a\u00020\u0002J\u0006\u0010\b\u001a\u00020\u0007R\u0018\u0010\n\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\n\u0010\u000bR\u0018\u0010\f\u001a\u0004\u0018\u00010\t8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\f\u0010\u000bR\u0014\u0010\u000e\u001a\u00020\r8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u000e\u0010\u000fR\u001a\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00110\u00148\u0006¢\u0006\f\n\u0004\b\u0015\u0010\u0013\u001a\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00108\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u0013R\u0018\u0010\u001b\u001a\u0004\u0018\u00010\u001a8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b\u001b\u0010\u001cR\"\u0010\u001e\u001a\u00020\u001d8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R\u0018\u0010$\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b$\u0010%R\u0018\u0010'\u001a\u0004\u0018\u00010&8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0018\u0010)\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b)\u0010%R\u0018\u0010+\u001a\u0004\u0018\u00010*8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010.\u001a\u0004\u0018\u00010-8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u0018\u00101\u001a\u0004\u0018\u0001008\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0018\u00104\u001a\u0004\u0018\u0001038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b4\u00105RL\u0010;\u001a5\b\u0001\u0012\u0004\u0012\u000207\u0012\u0013\u0012\u00110*¢\u0006\f\b8\u0012\b\b\u0003\u0012\u0004\b\b(9\u0012\n\u0012\b\u0012\u0004\u0012\u0002000:\u0012\u0006\u0012\u0004\u0018\u00010\u0001\u0018\u0001068\u0002@\u0002X\u0082\u000eø\u0001\u0000¢\u0006\u0006\n\u0004\b;\u0010<R$\u0010>\u001a\u0004\u0018\u00010=8\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\b>\u0010?\u001a\u0004\b@\u0010A\"\u0004\bB\u0010CR*\u0010E\u001a\n\u0012\u0004\u0012\u00020D\u0018\u00010\u00148\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bE\u0010\u0013\u001a\u0004\bF\u0010\u0017\"\u0004\bG\u0010HR$\u0010I\u001a\u0004\u0018\u0001008\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bI\u00102\u001a\u0004\bJ\u0010K\"\u0004\bL\u0010MR$\u0010N\u001a\u0004\u0018\u0001008\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bN\u00102\u001a\u0004\bO\u0010K\"\u0004\bP\u0010MR$\u0010Q\u001a\u0004\u0018\u0001008\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bQ\u00102\u001a\u0004\bR\u0010K\"\u0004\bS\u0010MR$\u0010T\u001a\u0004\u0018\u0001008\u0016@\u0016X\u0096\u000e¢\u0006\u0012\n\u0004\bT\u00102\u001a\u0004\bU\u0010K\"\u0004\bV\u0010M\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006Y"}, d2 = {"Lcom/apollographql/apollo3/ApolloClient$Builder;", "", "", "name", "value", "addHttpHeader", "serverUrl", "Lcom/apollographql/apollo3/ApolloClient;", JsonPOJOBuilder.DEFAULT_BUILD_METHOD, "Lcom/apollographql/apollo3/network/NetworkTransport;", "_networkTransport", "Lcom/apollographql/apollo3/network/NetworkTransport;", "subscriptionNetworkTransport", "Lcom/apollographql/apollo3/api/CustomScalarAdapters$Builder;", "customScalarAdaptersBuilder", "Lcom/apollographql/apollo3/api/CustomScalarAdapters$Builder;", "", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptor;", "_interceptors", "Ljava/util/List;", "", "interceptors", "getInterceptors", "()Ljava/util/List;", "Lcom/apollographql/apollo3/network/http/HttpInterceptor;", "httpInterceptors", "Lkotlinx/coroutines/CoroutineDispatcher;", "requestedDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "Lcom/apollographql/apollo3/api/ExecutionContext;", "executionContext", "Lcom/apollographql/apollo3/api/ExecutionContext;", "getExecutionContext", "()Lcom/apollographql/apollo3/api/ExecutionContext;", "setExecutionContext", "(Lcom/apollographql/apollo3/api/ExecutionContext;)V", "httpServerUrl", "Ljava/lang/String;", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "httpEngine", "Lcom/apollographql/apollo3/network/http/HttpEngine;", "webSocketServerUrl", "", "webSocketIdleTimeoutMillis", "Ljava/lang/Long;", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "wsProtocolFactory", "Lcom/apollographql/apollo3/network/ws/WsProtocol$Factory;", "", "httpExposeErrorBody", "Ljava/lang/Boolean;", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "webSocketEngine", "Lcom/apollographql/apollo3/network/ws/WebSocketEngine;", "Lkotlin/Function3;", "", "Lkotlin/ParameterName;", "attempt", "Lkotlin/coroutines/Continuation;", "webSocketReopenWhen", "Lkotlin/jvm/functions/Function3;", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "httpMethod", "Lcom/apollographql/apollo3/api/http/HttpMethod;", "getHttpMethod", "()Lcom/apollographql/apollo3/api/http/HttpMethod;", "setHttpMethod", "(Lcom/apollographql/apollo3/api/http/HttpMethod;)V", "Lcom/apollographql/apollo3/api/http/HttpHeader;", "httpHeaders", "getHttpHeaders", "setHttpHeaders", "(Ljava/util/List;)V", "sendApqExtensions", "getSendApqExtensions", "()Ljava/lang/Boolean;", "setSendApqExtensions", "(Ljava/lang/Boolean;)V", "sendDocument", "getSendDocument", "setSendDocument", "enableAutoPersistedQueries", "getEnableAutoPersistedQueries", "setEnableAutoPersistedQueries", "canBeBatched", "getCanBeBatched", "setCanBeBatched", "<init>", "()V", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        private final List<ApolloInterceptor> _interceptors;
        private NetworkTransport _networkTransport;
        private Boolean canBeBatched;
        private final CustomScalarAdapters.Builder customScalarAdaptersBuilder = new CustomScalarAdapters.Builder();
        private Boolean enableAutoPersistedQueries;
        private ExecutionContext executionContext;
        private HttpEngine httpEngine;
        private Boolean httpExposeErrorBody;
        private List<HttpHeader> httpHeaders;
        private final List<HttpInterceptor> httpInterceptors;
        private HttpMethod httpMethod;
        private String httpServerUrl;
        private final List<ApolloInterceptor> interceptors;
        private CoroutineDispatcher requestedDispatcher;
        private Boolean sendApqExtensions;
        private Boolean sendDocument;
        private NetworkTransport subscriptionNetworkTransport;
        private WebSocketEngine webSocketEngine;
        private Long webSocketIdleTimeoutMillis;
        private Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> webSocketReopenWhen;
        private String webSocketServerUrl;
        private WsProtocol.Factory wsProtocolFactory;

        public Builder() {
            ArrayList arrayList = new ArrayList();
            this._interceptors = arrayList;
            this.interceptors = arrayList;
            this.httpInterceptors = new ArrayList();
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

        public void setHttpHeaders(List<HttpHeader> list) {
            this.httpHeaders = list;
        }

        public Builder addHttpHeader(String name, String value) {
            Intrinsics.checkNotNullParameter(name, "name");
            Intrinsics.checkNotNullParameter(value, "value");
            List<HttpHeader> httpHeaders = getHttpHeaders();
            if (httpHeaders == null) {
                httpHeaders = CollectionsKt__CollectionsKt.emptyList();
            }
            setHttpHeaders(CollectionsKt___CollectionsKt.plus((Collection<? extends HttpHeader>) httpHeaders, new HttpHeader(name, value)));
            return this;
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

        public Boolean getCanBeBatched() {
            return this.canBeBatched;
        }

        public final Builder serverUrl(String serverUrl) {
            Intrinsics.checkNotNullParameter(serverUrl, "serverUrl");
            this.httpServerUrl = serverUrl;
            return this;
        }

        public final ApolloClient build() {
            NetworkTransport networkTransportBuild;
            NetworkTransport networkTransport;
            if (this._networkTransport != null) {
                if (!(this.httpServerUrl == null)) {
                    throw new IllegalStateException("Apollo: 'httpServerUrl' has no effect if 'networkTransport' is set".toString());
                }
                if (!(this.httpEngine == null)) {
                    throw new IllegalStateException("Apollo: 'httpEngine' has no effect if 'networkTransport' is set".toString());
                }
                if (!this.httpInterceptors.isEmpty()) {
                    throw new IllegalStateException("Apollo: 'addHttpInterceptor' has no effect if 'networkTransport' is set".toString());
                }
                if (!(this.httpExposeErrorBody == null)) {
                    throw new IllegalStateException("Apollo: 'httpExposeErrorBody' has no effect if 'networkTransport' is set".toString());
                }
                networkTransportBuild = this._networkTransport;
                Intrinsics.checkNotNull(networkTransportBuild);
            } else {
                if (!(this.httpServerUrl != null)) {
                    throw new IllegalStateException("Apollo: 'serverUrl' is required".toString());
                }
                HttpNetworkTransport.Builder builder = new HttpNetworkTransport.Builder();
                String str = this.httpServerUrl;
                Intrinsics.checkNotNull(str);
                HttpNetworkTransport.Builder builderServerUrl = builder.serverUrl(str);
                HttpEngine httpEngine = this.httpEngine;
                if (httpEngine != null) {
                    Intrinsics.checkNotNull(httpEngine);
                    builderServerUrl.httpEngine(httpEngine);
                }
                Boolean bool = this.httpExposeErrorBody;
                if (bool != null) {
                    Intrinsics.checkNotNull(bool);
                    builderServerUrl.exposeErrorBody(bool.booleanValue());
                }
                networkTransportBuild = builderServerUrl.interceptors(this.httpInterceptors).build();
            }
            NetworkTransport networkTransport2 = networkTransportBuild;
            NetworkTransport networkTransportBuild2 = this.subscriptionNetworkTransport;
            if (networkTransportBuild2 != null) {
                if (!(this.webSocketServerUrl == null)) {
                    throw new IllegalStateException("Apollo: 'webSocketServerUrl' has no effect if 'subscriptionNetworkTransport' is set".toString());
                }
                if (!(this.webSocketEngine == null)) {
                    throw new IllegalStateException("Apollo: 'webSocketEngine' has no effect if 'subscriptionNetworkTransport' is set".toString());
                }
                if (!(this.webSocketIdleTimeoutMillis == null)) {
                    throw new IllegalStateException("Apollo: 'webSocketIdleTimeoutMillis' has no effect if 'subscriptionNetworkTransport' is set".toString());
                }
                if (!(this.wsProtocolFactory == null)) {
                    throw new IllegalStateException("Apollo: 'wsProtocolFactory' has no effect if 'subscriptionNetworkTransport' is set".toString());
                }
                if (!(this.webSocketReopenWhen == null)) {
                    throw new IllegalStateException("Apollo: 'webSocketReopenWhen' has no effect if 'subscriptionNetworkTransport' is set".toString());
                }
                Intrinsics.checkNotNull(networkTransportBuild2);
            } else {
                String str2 = this.webSocketServerUrl;
                if (str2 == null) {
                    str2 = this.httpServerUrl;
                }
                if (str2 != null) {
                    WebSocketNetworkTransport.Builder builderServerUrl2 = new WebSocketNetworkTransport.Builder().serverUrl(str2);
                    WebSocketEngine webSocketEngine = this.webSocketEngine;
                    if (webSocketEngine != null) {
                        Intrinsics.checkNotNull(webSocketEngine);
                        builderServerUrl2.webSocketEngine(webSocketEngine);
                    }
                    Long l = this.webSocketIdleTimeoutMillis;
                    if (l != null) {
                        Intrinsics.checkNotNull(l);
                        builderServerUrl2.idleTimeoutMillis(l.longValue());
                    }
                    WsProtocol.Factory factory = this.wsProtocolFactory;
                    if (factory != null) {
                        Intrinsics.checkNotNull(factory);
                        builderServerUrl2.protocol(factory);
                    }
                    Function3<? super Throwable, ? super Long, ? super Continuation<? super Boolean>, ? extends Object> function3 = this.webSocketReopenWhen;
                    if (function3 != null) {
                        builderServerUrl2.reopenWhen(function3);
                    }
                    networkTransportBuild2 = builderServerUrl2.build();
                } else {
                    networkTransport = networkTransport2;
                    return new ApolloClient(networkTransport2, this.customScalarAdaptersBuilder.build(), networkTransport, this._interceptors, getExecutionContext(), this.requestedDispatcher, getHttpMethod(), getHttpHeaders(), getSendApqExtensions(), getSendDocument(), getEnableAutoPersistedQueries(), getCanBeBatched(), this, null);
                }
            }
            networkTransport = networkTransportBuild2;
            return new ApolloClient(networkTransport2, this.customScalarAdaptersBuilder.build(), networkTransport, this._interceptors, getExecutionContext(), this.requestedDispatcher, getHttpMethod(), getHttpHeaders(), getSendApqExtensions(), getSendDocument(), getEnableAutoPersistedQueries(), getCanBeBatched(), this, null);
        }
    }
}

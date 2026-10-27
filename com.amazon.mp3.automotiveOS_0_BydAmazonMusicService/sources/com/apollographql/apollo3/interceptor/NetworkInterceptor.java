package com.apollographql.apollo3.interceptor;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Operation;
import com.apollographql.apollo3.api.Query;
import com.apollographql.apollo3.network.NetworkTransport;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

/* JADX INFO: compiled from: NetworkInterceptor.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J4\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u000b0\n0\t\"\b\b\u0000\u0010\u000b*\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u000b0\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/apollographql/apollo3/interceptor/NetworkInterceptor;", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptor;", "networkTransport", "Lcom/apollographql/apollo3/network/NetworkTransport;", "subscriptionNetworkTransport", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Lcom/apollographql/apollo3/network/NetworkTransport;Lcom/apollographql/apollo3/network/NetworkTransport;Lkotlinx/coroutines/CoroutineDispatcher;)V", "intercept", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "D", "Lcom/apollographql/apollo3/api/Operation$Data;", "request", "Lcom/apollographql/apollo3/api/ApolloRequest;", "chain", "Lcom/apollographql/apollo3/interceptor/ApolloInterceptorChain;", "apollo-runtime"}, k = 1, mv = {1, 5, 1}, xi = 48)
public final class NetworkInterceptor implements ApolloInterceptor {
    private final CoroutineDispatcher dispatcher;
    private final NetworkTransport networkTransport;
    private final NetworkTransport subscriptionNetworkTransport;

    public NetworkInterceptor(NetworkTransport networkTransport, NetworkTransport subscriptionNetworkTransport, CoroutineDispatcher dispatcher) {
        Intrinsics.checkNotNullParameter(networkTransport, "networkTransport");
        Intrinsics.checkNotNullParameter(subscriptionNetworkTransport, "subscriptionNetworkTransport");
        Intrinsics.checkNotNullParameter(dispatcher, "dispatcher");
        this.networkTransport = networkTransport;
        this.subscriptionNetworkTransport = subscriptionNetworkTransport;
        this.dispatcher = dispatcher;
    }

    @Override // com.apollographql.apollo3.interceptor.ApolloInterceptor
    public <D extends Operation.Data> Flow<ApolloResponse<D>> intercept(ApolloRequest<D> request, ApolloInterceptorChain chain) {
        Intrinsics.checkNotNullParameter(request, "request");
        Intrinsics.checkNotNullParameter(chain, "chain");
        Operation<D> operation = request.getOperation();
        if (!(operation instanceof Query) && !(operation instanceof Mutation)) {
            throw new IllegalStateException("".toString());
        }
        Flow<ApolloResponse<D>> flowExecute = this.networkTransport.execute(request);
        return FlowKt.flowOn(flowExecute, this.dispatcher);
    }
}

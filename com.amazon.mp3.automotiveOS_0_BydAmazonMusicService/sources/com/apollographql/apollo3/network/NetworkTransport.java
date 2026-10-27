package com.apollographql.apollo3.network;

import com.apollographql.apollo3.api.ApolloRequest;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Operation;
import kotlin.Metadata;
import kotlinx.coroutines.flow.Flow;

/* JADX INFO: compiled from: NetworkTransport.kt */
/* JADX INFO: loaded from: classes5.dex */
@Metadata(bv = {}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001J,\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H&¨\u0006\t"}, d2 = {"Lcom/apollographql/apollo3/network/NetworkTransport;", "", "Lcom/apollographql/apollo3/api/Operation$Data;", "D", "Lcom/apollographql/apollo3/api/ApolloRequest;", "request", "Lkotlinx/coroutines/flow/Flow;", "Lcom/apollographql/apollo3/api/ApolloResponse;", "execute", "apollo-runtime"}, k = 1, mv = {1, 5, 1})
public interface NetworkTransport {
    <D extends Operation.Data> Flow<ApolloResponse<D>> execute(ApolloRequest<D> request);
}

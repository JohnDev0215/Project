package com.amazon.music.platform.providers;

import com.amazon.music.platform.Outcome;
import com.apollographql.apollo3.api.ApolloResponse;
import com.apollographql.apollo3.api.Mutation;
import com.apollographql.apollo3.api.Query;
import com.google.android.gms.actions.SearchIntents;
import java.util.Map;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

/* JADX INFO: compiled from: GraphQLServiceProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J7\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004H¦@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJO\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u00042\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b\u0018\u00010\nH¦@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\rJ7\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00070\u0006\"\b\b\u0000\u0010\u0003*\u00020\u00022\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u000eH¦@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/platform/providers/GraphQLServiceProvider;", "Lcom/amazon/music/platform/providers/InterfaceProvider;", "", "D", "Lcom/apollographql/apollo3/api/Query;", "queryData", "Lcom/amazon/music/platform/Outcome;", "Lcom/apollographql/apollo3/api/ApolloResponse;", SearchIntents.EXTRA_QUERY, "(Lcom/apollographql/apollo3/api/Query;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "", "httpHeaders", "(Lcom/apollographql/apollo3/api/Query;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lcom/apollographql/apollo3/api/Mutation;", "mutationData", "mutation", "(Lcom/apollographql/apollo3/api/Mutation;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "MusicPlatformCore_release"}, k = 1, mv = {1, 7, 1})
public interface GraphQLServiceProvider extends InterfaceProvider {
    <D> Object mutation(Mutation<D> mutation, Continuation<? super Outcome<ApolloResponse<D>>> continuation);

    <D> Object query(Query<D> query, Map<String, String> map, Continuation<? super Outcome<ApolloResponse<D>>> continuation);

    <D> Object query(Query<D> query, Continuation<? super Outcome<ApolloResponse<D>>> continuation);
}

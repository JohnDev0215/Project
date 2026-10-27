package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.rx.RxOperations;
import io.reactivex.rxjava3.core.Single;

/* JADX INFO: loaded from: classes5.dex */
public interface RxGraphQlBehavior {
    @NonNull
    <T> Single<GraphQLResponse<T>> mutate(@NonNull GraphQLRequest<T> graphQLRequest);

    @NonNull
    <T> Single<GraphQLResponse<T>> mutate(@NonNull String str, @NonNull GraphQLRequest<T> graphQLRequest);

    @NonNull
    <R> Single<GraphQLResponse<R>> query(@NonNull GraphQLRequest<R> graphQLRequest);

    @NonNull
    <T> Single<GraphQLResponse<T>> query(@NonNull String str, @NonNull GraphQLRequest<T> graphQLRequest);

    @NonNull
    <T> RxOperations.RxSubscriptionOperation<GraphQLResponse<T>> subscribe(@NonNull GraphQLRequest<T> graphQLRequest);

    @NonNull
    <R> RxOperations.RxSubscriptionOperation<GraphQLResponse<R>> subscribe(@NonNull String str, @NonNull GraphQLRequest<R> graphQLRequest);
}

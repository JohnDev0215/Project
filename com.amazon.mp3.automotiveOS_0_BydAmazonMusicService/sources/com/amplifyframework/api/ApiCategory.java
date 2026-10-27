package com.amplifyframework.api;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.api.graphql.GraphQLOperation;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.rest.RestOperation;
import com.amplifyframework.api.rest.RestOptions;
import com.amplifyframework.api.rest.RestResponse;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.category.Category;
import com.amplifyframework.core.category.CategoryType;

/* JADX INFO: loaded from: classes5.dex */
public final class ApiCategory extends Category<ApiPlugin<?>> implements ApiCategoryBehavior {
    @Override // com.amplifyframework.core.category.CategoryTypeable
    @NonNull
    public CategoryType getCategoryType() {
        return CategoryType.API;
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> query(@NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<GraphQLResponse<R>> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().query(graphQLRequest, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> query(@NonNull String str, @NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<GraphQLResponse<R>> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().query(str, graphQLRequest, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <R> GraphQLOperation<R> mutate(@NonNull GraphQLRequest<R> graphQLRequest, @NonNull Consumer<GraphQLResponse<R>> consumer, @NonNull Consumer<ApiException> consumer2) {
        return (GraphQLOperation<R>) getSelectedPlugin().mutate(graphQLRequest, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <T> GraphQLOperation<T> mutate(@NonNull String str, @NonNull GraphQLRequest<T> graphQLRequest, @NonNull Consumer<GraphQLResponse<T>> consumer, @NonNull Consumer<ApiException> consumer2) {
        return (GraphQLOperation<T>) getSelectedPlugin().mutate(str, graphQLRequest, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <T> GraphQLOperation<T> subscribe(@NonNull GraphQLRequest<T> graphQLRequest, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<T>> consumer2, @NonNull Consumer<ApiException> consumer3, @NonNull Action action) {
        return (GraphQLOperation<T>) getSelectedPlugin().subscribe(graphQLRequest, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.api.graphql.GraphQLBehavior
    @Nullable
    public <T> GraphQLOperation<T> subscribe(@NonNull String str, @NonNull GraphQLRequest<T> graphQLRequest, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<T>> consumer2, @NonNull Consumer<ApiException> consumer3, @NonNull Action action) {
        return (GraphQLOperation<T>) getSelectedPlugin().subscribe(str, graphQLRequest, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation get(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().get(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation get(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().get(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation put(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().put(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation put(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().put(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation post(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().post(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation post(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().post(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation delete(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().delete(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation delete(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().delete(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation head(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().head(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation head(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().head(str, restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation patch(@NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().patch(restOptions, consumer, consumer2);
    }

    @Override // com.amplifyframework.api.rest.RestBehavior
    @Nullable
    public RestOperation patch(@NonNull String str, @NonNull RestOptions restOptions, @NonNull Consumer<RestResponse> consumer, @NonNull Consumer<ApiException> consumer2) {
        return getSelectedPlugin().patch(str, restOptions, consumer, consumer2);
    }
}

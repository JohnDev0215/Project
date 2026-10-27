package com.amplifyframework.api.graphql.model;

import androidx.annotation.NonNull;
import com.amplifyframework.api.aws.AppSyncGraphQLRequestFactory;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelQuery {
    private ModelQuery() {
    }

    public static <M extends Model> GraphQLRequest<M> get(@NonNull Class<M> cls, @NonNull String str) {
        return AppSyncGraphQLRequestFactory.buildQuery(cls, str);
    }

    public static <M extends Model> GraphQLRequest<PaginatedResult<M>> list(@NonNull Class<M> cls, @NonNull QueryPredicate queryPredicate) {
        Objects.requireNonNull(cls);
        Objects.requireNonNull(queryPredicate);
        return AppSyncGraphQLRequestFactory.buildQuery(cls, queryPredicate);
    }

    public static <M extends Model> GraphQLRequest<PaginatedResult<M>> list(@NonNull Class<M> cls) {
        return list(cls, QueryPredicates.all());
    }

    public static <M extends Model> GraphQLRequest<PaginatedResult<M>> list(@NonNull Class<M> cls, @NonNull QueryPredicate queryPredicate, @NonNull ModelPagination modelPagination) {
        return AppSyncGraphQLRequestFactory.buildPaginatedResultQuery(cls, queryPredicate, modelPagination.getLimit());
    }

    public static <M extends Model> GraphQLRequest<PaginatedResult<M>> list(@NonNull Class<M> cls, @NonNull ModelPagination modelPagination) {
        return AppSyncGraphQLRequestFactory.buildPaginatedResultQuery(cls, QueryPredicates.all(), modelPagination.getLimit());
    }
}

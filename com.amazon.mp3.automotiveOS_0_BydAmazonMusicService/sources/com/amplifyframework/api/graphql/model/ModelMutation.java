package com.amplifyframework.api.graphql.model;

import androidx.annotation.NonNull;
import com.amplifyframework.api.aws.AppSyncGraphQLRequestFactory;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.MutationType;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;

/* JADX INFO: loaded from: classes5.dex */
public final class ModelMutation {
    private ModelMutation() {
    }

    public static <M extends Model> GraphQLRequest<M> create(@NonNull M m) {
        return AppSyncGraphQLRequestFactory.buildMutation(m, QueryPredicates.all(), MutationType.CREATE);
    }

    public static <M extends Model> GraphQLRequest<M> update(@NonNull M m, @NonNull QueryPredicate queryPredicate) {
        return AppSyncGraphQLRequestFactory.buildMutation(m, queryPredicate, MutationType.UPDATE);
    }

    public static <M extends Model> GraphQLRequest<M> update(@NonNull M m) {
        return AppSyncGraphQLRequestFactory.buildMutation(m, QueryPredicates.all(), MutationType.UPDATE);
    }

    public static <M extends Model> GraphQLRequest<M> delete(@NonNull M m, @NonNull QueryPredicate queryPredicate) {
        return AppSyncGraphQLRequestFactory.buildMutation(m, queryPredicate, MutationType.DELETE);
    }

    public static <M extends Model> GraphQLRequest<M> delete(@NonNull M m) {
        return AppSyncGraphQLRequestFactory.buildMutation(m, QueryPredicates.all(), MutationType.DELETE);
    }
}

package com.amplifyframework.datastore.appsync;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.datastore.DataStoreException;

/* JADX INFO: loaded from: classes5.dex */
public interface AppSync {
    @NonNull
    <T extends Model> GraphQLRequest<PaginatedResult<ModelWithMetadata<T>>> buildSyncRequest(@NonNull ModelSchema modelSchema, @Nullable Long l, @Nullable Integer num, @NonNull QueryPredicate queryPredicate) throws DataStoreException;

    @NonNull
    <T extends Model> Cancelable create(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    @NonNull
    <T extends Model> Cancelable delete(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Integer num, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    @NonNull
    <T extends Model> Cancelable delete(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Integer num, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    @NonNull
    <T extends Model> Cancelable onCreate(@NonNull ModelSchema modelSchema, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    @NonNull
    <T extends Model> Cancelable onDelete(@NonNull ModelSchema modelSchema, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    @NonNull
    <T extends Model> Cancelable onUpdate(@NonNull ModelSchema modelSchema, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);

    @NonNull
    <T extends Model> Cancelable sync(@NonNull GraphQLRequest<PaginatedResult<ModelWithMetadata<T>>> graphQLRequest, @NonNull Consumer<GraphQLResponse<PaginatedResult<ModelWithMetadata<T>>>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    @NonNull
    <T extends Model> Cancelable update(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Integer num, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2);

    @NonNull
    <T extends Model> Cancelable update(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Integer num, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2);
}

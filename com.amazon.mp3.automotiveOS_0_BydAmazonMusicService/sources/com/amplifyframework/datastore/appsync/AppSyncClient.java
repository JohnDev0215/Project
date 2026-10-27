package com.amplifyframework.datastore.appsync;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.aws.AuthModeStrategyType;
import com.amplifyframework.api.graphql.GraphQLBehavior;
import com.amplifyframework.api.graphql.GraphQLOperation;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.async.NoOpCancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.logging.Logger;

/* JADX INFO: loaded from: classes5.dex */
public final class AppSyncClient implements AppSync {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final GraphQLBehavior api;
    private final AuthModeStrategyType authModeStrategyType;

    private AppSyncClient(GraphQLBehavior graphQLBehavior, AuthModeStrategyType authModeStrategyType) {
        this.api = graphQLBehavior;
        this.authModeStrategyType = authModeStrategyType;
    }

    @NonNull
    public static AppSyncClient via(@NonNull GraphQLBehavior graphQLBehavior) {
        return new AppSyncClient(graphQLBehavior, AuthModeStrategyType.DEFAULT);
    }

    public static AppSyncClient via(@NonNull GraphQLBehavior graphQLBehavior, @NonNull AuthModeStrategyType authModeStrategyType) {
        return new AppSyncClient(graphQLBehavior, authModeStrategyType);
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> GraphQLRequest<PaginatedResult<ModelWithMetadata<T>>> buildSyncRequest(@NonNull ModelSchema modelSchema, @Nullable Long l, @Nullable Integer num, @NonNull QueryPredicate queryPredicate) throws DataStoreException {
        return AppSyncRequestFactory.buildSyncRequest(modelSchema, l, num, queryPredicate, this.authModeStrategyType);
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable sync(@NonNull GraphQLRequest<PaginatedResult<ModelWithMetadata<T>>> graphQLRequest, @NonNull final Consumer<GraphQLResponse<PaginatedResult<ModelWithMetadata<T>>>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        GraphQLOperation graphQLOperationQuery = this.api.query(graphQLRequest, new Consumer() { // from class: com.amplifyframework.datastore.appsync.AppSyncClient$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AppSyncClient.lambda$sync$0(consumer2, consumer, (GraphQLResponse) obj);
            }
        }, new Consumer() { // from class: com.amplifyframework.datastore.appsync.AppSyncClient$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AppSyncClient.lambda$sync$1(consumer2, (ApiException) obj);
            }
        });
        return graphQLOperationQuery != null ? graphQLOperationQuery : new NoOpCancelable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sync$0(Consumer consumer, Consumer consumer2, GraphQLResponse graphQLResponse) {
        if (graphQLResponse.hasErrors()) {
            consumer.accept(new DataStoreException("Failure performing sync query to AppSync: " + graphQLResponse.getErrors().toString(), AmplifyException.TODO_RECOVERY_SUGGESTION));
            return;
        }
        consumer2.accept(graphQLResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$sync$1(Consumer consumer, ApiException apiException) {
        consumer.accept(new DataStoreException("Failure performing sync query to AppSync.", apiException, AmplifyException.TODO_RECOVERY_SUGGESTION));
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable create(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        try {
            return mutation(AppSyncRequestFactory.buildCreationRequest(modelSchema, t, this.authModeStrategyType), consumer, consumer2);
        } catch (AmplifyException e) {
            consumer2.accept(new DataStoreException("Error encountered while creating model schema", e, "See attached exception for more details"));
            return new NoOpCancelable();
        }
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable update(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Integer num, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        return update(t, modelSchema, num, QueryPredicates.all(), consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable update(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Integer num, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        try {
            return mutation(AppSyncRequestFactory.buildUpdateRequest(modelSchema, t, num, queryPredicate, this.authModeStrategyType), consumer, consumer2);
        } catch (AmplifyException e) {
            consumer2.accept(new DataStoreException("Error encountered while creating model schema", e, "See attached exception for more details"));
            return new NoOpCancelable();
        }
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable delete(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Integer num, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        return delete(t, modelSchema, num, QueryPredicates.all(), consumer, consumer2);
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable delete(@NonNull T t, @NonNull ModelSchema modelSchema, @NonNull Integer num, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        try {
            return mutation(AppSyncRequestFactory.buildDeletionRequest(modelSchema, t, num, queryPredicate, this.authModeStrategyType), consumer, consumer2);
        } catch (DataStoreException e) {
            consumer2.accept(e);
            return new NoOpCancelable();
        }
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable onCreate(@NonNull ModelSchema modelSchema, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action) {
        return subscription(SubscriptionType.ON_CREATE, modelSchema, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable onUpdate(@NonNull ModelSchema modelSchema, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action) {
        return subscription(SubscriptionType.ON_UPDATE, modelSchema, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.datastore.appsync.AppSync
    @NonNull
    public <T extends Model> Cancelable onDelete(@NonNull ModelSchema modelSchema, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action) {
        return subscription(SubscriptionType.ON_DELETE, modelSchema, consumer, consumer2, consumer3, action);
    }

    private <T extends Model> Cancelable subscription(SubscriptionType subscriptionType, final ModelSchema modelSchema, Consumer<String> consumer, final Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer2, final Consumer<DataStoreException> consumer3, Action action) {
        try {
            GraphQLOperation graphQLOperationSubscribe = this.api.subscribe(AppSyncRequestFactory.buildSubscriptionRequest(modelSchema, subscriptionType, this.authModeStrategyType), consumer, new Consumer() { // from class: com.amplifyframework.datastore.appsync.AppSyncClient$$ExternalSyntheticLambda4
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    AppSyncClient.lambda$subscription$2(consumer3, modelSchema, consumer2, (GraphQLResponse) obj);
                }
            }, new Consumer() { // from class: com.amplifyframework.datastore.appsync.AppSyncClient$$ExternalSyntheticLambda5
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    AppSyncClient.lambda$subscription$3(consumer3, (ApiException) obj);
                }
            }, action);
            return graphQLOperationSubscribe != null ? graphQLOperationSubscribe : new NoOpCancelable();
        } catch (DataStoreException e) {
            consumer3.accept(e);
            return new NoOpCancelable();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$subscription$2(Consumer consumer, ModelSchema modelSchema, Consumer consumer2, GraphQLResponse graphQLResponse) {
        if (graphQLResponse.hasErrors()) {
            consumer.accept(new DataStoreException.GraphQLResponseException("Subscription error for " + modelSchema.getName() + ": " + graphQLResponse.getErrors(), graphQLResponse.getErrors()));
            return;
        }
        consumer2.accept(graphQLResponse);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$subscription$3(Consumer consumer, ApiException apiException) {
        consumer.accept(new DataStoreException("Error during subscription.", apiException, "Evaluate details."));
    }

    private <T extends Model> Cancelable mutation(GraphQLRequest<ModelWithMetadata<T>> graphQLRequest, final Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, final Consumer<DataStoreException> consumer2) {
        GraphQLOperation graphQLOperationMutate = this.api.mutate(graphQLRequest, new Consumer() { // from class: com.amplifyframework.datastore.appsync.AppSyncClient$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AppSyncClient.lambda$mutation$4(consumer, (GraphQLResponse) obj);
            }
        }, new Consumer() { // from class: com.amplifyframework.datastore.appsync.AppSyncClient$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AppSyncClient.lambda$mutation$5(consumer2, (ApiException) obj);
            }
        });
        return graphQLOperationMutate != null ? graphQLOperationMutate : new NoOpCancelable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$mutation$4(Consumer consumer, GraphQLResponse graphQLResponse) {
        if (graphQLResponse.hasErrors()) {
            consumer.accept(new GraphQLResponse(null, graphQLResponse.getErrors()));
        } else {
            consumer.accept(graphQLResponse);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$mutation$5(Consumer consumer, ApiException apiException) {
        consumer.accept(new DataStoreException("Failure during mutation.", apiException, "Check details."));
    }
}

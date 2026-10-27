package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.graphql.GraphQLRequest;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.PaginatedResult;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.AmplifyDisposables;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.DataStoreConfigurationProvider;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.appsync.AppSync;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.datastore.events.SyncQueriesStartedEvent;
import com.amplifyframework.datastore.storage.StorageItemChange;
import com.amplifyframework.datastore.storage.sqlite.PersistentModelVersion$$ExternalSyntheticLambda2;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.Time;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import io.reactivex.rxjava3.processors.BehaviorProcessor;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BinaryOperator;
import org.reactivestreams.Publisher;

/* JADX INFO: loaded from: classes5.dex */
final class SyncProcessor {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final AppSync appSync;
    private final DataStoreConfigurationProvider dataStoreConfigurationProvider;
    private final boolean isSyncRetryEnabled;
    private final Merger merger;
    private final ModelProvider modelProvider;
    private final QueryPredicateProvider queryPredicateProvider;
    private final RetryHandler requestRetry;
    private final SchemaRegistry schemaRegistry;
    private final SyncTimeRegistry syncTimeRegistry;

    interface AppSyncStep {
        @NonNull
        MergerStep appSync(@NonNull AppSync appSync);
    }

    interface BuildStep {
        @NonNull
        SyncProcessor build();
    }

    interface DataStoreConfigurationProviderStep {
        @NonNull
        QueryPredicateProviderStep dataStoreConfigurationProvider(DataStoreConfigurationProvider dataStoreConfigurationProvider);
    }

    interface MergerStep {
        @NonNull
        DataStoreConfigurationProviderStep merger(@NonNull Merger merger);
    }

    interface ModelProviderStep {
        @NonNull
        SchemaRegistryStep modelProvider(@NonNull ModelProvider modelProvider);
    }

    interface QueryPredicateProviderStep {
        @NonNull
        RetryHandlerStep queryPredicateProvider(QueryPredicateProvider queryPredicateProvider);
    }

    interface RetryHandlerStep {
        @NonNull
        SyncRetryStep retryHandler(RetryHandler retryHandler);
    }

    interface SchemaRegistryStep {
        @NonNull
        SyncTimeRegistryStep schemaRegistry(@NonNull SchemaRegistry schemaRegistry);
    }

    interface SyncRetryStep {
        @NonNull
        BuildStep isSyncRetryEnabled(boolean z);
    }

    interface SyncTimeRegistryStep {
        @NonNull
        AppSyncStep syncTimeRegistry(@NonNull SyncTimeRegistry syncTimeRegistry);
    }

    private SyncProcessor(Builder builder) {
        this.modelProvider = builder.modelProvider;
        this.schemaRegistry = builder.schemaRegistry;
        this.syncTimeRegistry = builder.syncTimeRegistry;
        this.appSync = builder.appSync;
        this.merger = builder.merger;
        this.dataStoreConfigurationProvider = builder.dataStoreConfigurationProvider;
        this.queryPredicateProvider = builder.queryPredicateProvider;
        this.requestRetry = builder.requestRetry;
        this.isSyncRetryEnabled = builder.isSyncRetryEnabled;
    }

    public static ModelProviderStep builder() {
        return new Builder();
    }

    Completable hydrate() {
        ArrayList arrayList = new ArrayList();
        ArrayList<ModelSchema> arrayList2 = new ArrayList(this.modelProvider.modelSchemas().values());
        final TopologicalOrdering topologicalOrderingForRegisteredModels = TopologicalOrdering.forRegisteredModels(this.schemaRegistry, this.modelProvider);
        topologicalOrderingForRegisteredModels.getClass();
        Collections.sort(arrayList2, new Comparator() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda9
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return topologicalOrderingForRegisteredModels.compare((ModelSchema) obj, (ModelSchema) obj2);
            }
        });
        final ArrayList arrayList3 = new ArrayList();
        for (ModelSchema modelSchema : arrayList2) {
            if (!QueryPredicates.none().equals(this.queryPredicateProvider.getPredicate(modelSchema.getName()))) {
                arrayList.add(createHydrationTask(modelSchema));
                arrayList3.add(modelSchema.getName());
            }
        }
        return Completable.concat(arrayList).doOnSubscribe(new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                SyncProcessor.lambda$hydrate$0(arrayList3, (Disposable) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda11
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                SyncProcessor.lambda$hydrate$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$hydrate$0(ArrayList arrayList, Disposable disposable) throws Throwable {
        Amplify.Hub.publish(HubChannel.DATASTORE, HubEvent.create(DataStoreChannelEventName.SYNC_QUERIES_STARTED, new SyncQueriesStartedEvent((String[]) arrayList.toArray(new String[0]))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$hydrate$1() throws Throwable {
        Amplify.Hub.publish(HubChannel.DATASTORE, HubEvent.create(DataStoreChannelEventName.SYNC_QUERIES_READY));
    }

    private Completable createHydrationTask(final ModelSchema modelSchema) {
        final ModelSyncMetricsAccumulator modelSyncMetricsAccumulator = new ModelSyncMetricsAccumulator(modelSchema.getName());
        return this.syncTimeRegistry.lookupLastSyncTime(modelSchema.getName()).map(new Function() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda14
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.filterOutOldSyncTimes((SyncTime) obj);
            }
        }).flatMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda15
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$createHydrationTask$4(modelSchema, modelSyncMetricsAccumulator, (SyncTime) obj);
            }
        }).flatMapCompletable(new Function() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda16
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$createHydrationTask$6(modelSchema, modelSyncMetricsAccumulator, (SyncType) obj);
            }
        }).doOnError(new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda17
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                this.f$0.lambda$createHydrationTask$7(modelSchema, (Throwable) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda18
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                SyncProcessor.lambda$createHydrationTask$8();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SingleSource lambda$createHydrationTask$4(ModelSchema modelSchema, final ModelSyncMetricsAccumulator modelSyncMetricsAccumulator, final SyncTime syncTime) throws Throwable {
        return syncModel(modelSchema, syncTime).observeOn(Schedulers.io()).concatMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return Flowable.fromIterable((List) obj);
            }
        }).concatMapCompletable(new Function() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$null$2(modelSyncMetricsAccumulator, (ModelWithMetadata) obj);
            }
        }).toSingle(new Supplier() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return SyncProcessor.lambda$null$3(syncTime);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$null$2(final ModelSyncMetricsAccumulator modelSyncMetricsAccumulator, ModelWithMetadata modelWithMetadata) throws Throwable {
        Merger merger = this.merger;
        modelSyncMetricsAccumulator.getClass();
        return merger.merge(modelWithMetadata, new com.amplifyframework.core.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                modelSyncMetricsAccumulator.increment((StorageItemChange.Type) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SyncType lambda$null$3(SyncTime syncTime) throws Throwable {
        return syncTime.exists() ? SyncType.DELTA : SyncType.BASE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$createHydrationTask$6(ModelSchema modelSchema, final ModelSyncMetricsAccumulator modelSyncMetricsAccumulator, final SyncType syncType) throws Throwable {
        Completable completableSaveLastBaseSyncTime;
        if (SyncType.DELTA.equals(syncType)) {
            completableSaveLastBaseSyncTime = this.syncTimeRegistry.saveLastDeltaSyncTime(modelSchema.getName(), SyncTime.now());
        } else {
            completableSaveLastBaseSyncTime = this.syncTimeRegistry.saveLastBaseSyncTime(modelSchema.getName(), SyncTime.now());
        }
        return completableSaveLastBaseSyncTime.andThen(Completable.fromAction(new Action() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda13
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                SyncProcessor.lambda$null$5(modelSyncMetricsAccumulator, syncType);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$5(ModelSyncMetricsAccumulator modelSyncMetricsAccumulator, SyncType syncType) throws Throwable {
        Amplify.Hub.publish(HubChannel.DATASTORE, modelSyncMetricsAccumulator.toModelSyncedEvent(syncType).toHubEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$createHydrationTask$7(ModelSchema modelSchema, Throwable th) throws Throwable {
        LOG.warn("Initial cloud sync failed for " + modelSchema.getName() + ".", th);
        this.dataStoreConfigurationProvider.getConfiguration().getErrorHandler().accept(new DataStoreException("Initial cloud sync failed for " + modelSchema.getName() + ".", th, "Check your internet connection."));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$createHydrationTask$8() throws Throwable {
        LOG.info("Successfully sync'd down model state from cloud.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public SyncTime filterOutOldSyncTimes(SyncTime syncTime) throws DataStoreException {
        if (syncTime.exists()) {
            return Time.now() - syncTime.toLong() <= this.dataStoreConfigurationProvider.getConfiguration().getSyncIntervalMs().longValue() ? syncTime : SyncTime.never();
        }
        return SyncTime.never();
    }

    private <T extends Model> Flowable<List<ModelWithMetadata<T>>> syncModel(final ModelSchema modelSchema, SyncTime syncTime) throws DataStoreException {
        Long lValueOf = syncTime.exists() ? Long.valueOf(syncTime.toLong()) : null;
        Integer syncPageSize = this.dataStoreConfigurationProvider.getConfiguration().getSyncPageSize();
        final Integer syncMaxRecords = this.dataStoreConfigurationProvider.getConfiguration().getSyncMaxRecords();
        final AtomicReference atomicReference = new AtomicReference(0);
        final BehaviorProcessor behaviorProcessorCreateDefault = BehaviorProcessor.createDefault(this.appSync.buildSyncRequest(modelSchema, lValueOf, syncPageSize, this.queryPredicateProvider.getPredicate(modelSchema.getName())));
        return behaviorProcessorCreateDefault.concatMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$syncModel$9((GraphQLRequest) obj);
            }
        }).doOnNext(new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                SyncProcessor.lambda$syncModel$10(behaviorProcessorCreateDefault, (PaginatedResult) obj);
            }
        }).map(new Function() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$syncModel$12(modelSchema, (PaginatedResult) obj);
            }
        }).takeUntil(new Predicate() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return SyncProcessor.lambda$syncModel$13(atomicReference, syncMaxRecords, (List) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Publisher lambda$syncModel$9(GraphQLRequest graphQLRequest) throws Throwable {
        if (this.isSyncRetryEnabled) {
            return syncPageWithRetry(graphQLRequest).toFlowable();
        }
        return syncPage(graphQLRequest).toFlowable();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$syncModel$10(BehaviorProcessor behaviorProcessor, PaginatedResult paginatedResult) throws Throwable {
        if (paginatedResult.hasNextResult()) {
            behaviorProcessor.onNext(paginatedResult.getRequestForNextResult());
        } else {
            behaviorProcessor.onComplete();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ List lambda$syncModel$12(final ModelSchema modelSchema, PaginatedResult paginatedResult) throws Throwable {
        return (List) Flowable.fromIterable(paginatedResult).map(new Function() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda19
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$null$11(modelSchema, (ModelWithMetadata) obj);
            }
        }).toList().blockingGet();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$syncModel$13(AtomicReference atomicReference, Integer num, List list) throws Throwable {
        return ((Integer) atomicReference.accumulateAndGet(Integer.valueOf(list.size()), new BinaryOperator() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda12
            @Override // java.util.function.BiFunction
            public final Object apply(Object obj, Object obj2) {
                return Integer.valueOf(Integer.sum(((Integer) obj).intValue(), ((Integer) obj2).intValue()));
            }
        })).intValue() >= num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hydrateSchemaIfNeeded, reason: merged with bridge method [inline-methods] */
    public <T extends Model> ModelWithMetadata<T> lambda$null$11(ModelWithMetadata<T> modelWithMetadata, ModelSchema modelSchema) {
        if (!(modelWithMetadata.getModel() instanceof SerializedModel)) {
            return modelWithMetadata;
        }
        return new ModelWithMetadata<>(SerializedModel.builder().modelSchema(modelSchema).serializedData(SerializedModel.parseSerializedData(((SerializedModel) modelWithMetadata.getModel()).getSerializedData(), modelSchema.getName(), this.schemaRegistry)).build(), modelWithMetadata.getSyncMetadata());
    }

    private <T extends Model> Single<PaginatedResult<ModelWithMetadata<T>>> syncPage(final GraphQLRequest<PaginatedResult<ModelWithMetadata<T>>> graphQLRequest) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda20
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                this.f$0.lambda$syncPage$15(graphQLRequest, singleEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$syncPage$15(GraphQLRequest graphQLRequest, final SingleEmitter singleEmitter) throws Throwable {
        AppSync appSync = this.appSync;
        com.amplifyframework.core.Consumer consumer = new com.amplifyframework.core.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SyncProcessor$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                SyncProcessor.lambda$null$14(singleEmitter, (GraphQLResponse) obj);
            }
        };
        singleEmitter.getClass();
        singleEmitter.setDisposable(AmplifyDisposables.fromCancelable(appSync.sync(graphQLRequest, consumer, new PersistentModelVersion$$ExternalSyntheticLambda2(singleEmitter))));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$14(SingleEmitter singleEmitter, GraphQLResponse graphQLResponse) {
        if (graphQLResponse.hasErrors()) {
            singleEmitter.onError(new DataStoreException(String.format("A model sync failed: %s", graphQLResponse.getErrors()), "Check your schema."));
        } else if (!graphQLResponse.hasData()) {
            singleEmitter.onError(new DataStoreException.IrRecoverableException("Empty response from AppSync.", "Report to AWS team."));
        } else {
            singleEmitter.onSuccess(graphQLResponse.getData());
        }
    }

    private <T extends Model> Single<PaginatedResult<ModelWithMetadata<T>>> syncPageWithRetry(GraphQLRequest<PaginatedResult<ModelWithMetadata<T>>> graphQLRequest) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DataStoreException.GraphQLResponseException.class);
        arrayList.add(ApiException.NonRetryableException.class);
        return this.requestRetry.retry(syncPage(graphQLRequest), arrayList);
    }

    public static final class Builder implements ModelProviderStep, SchemaRegistryStep, SyncTimeRegistryStep, AppSyncStep, MergerStep, DataStoreConfigurationProviderStep, QueryPredicateProviderStep, RetryHandlerStep, SyncRetryStep, BuildStep {
        private AppSync appSync;
        private DataStoreConfigurationProvider dataStoreConfigurationProvider;
        private boolean isSyncRetryEnabled;
        private Merger merger;
        private ModelProvider modelProvider;
        private QueryPredicateProvider queryPredicateProvider;
        private RetryHandler requestRetry;
        private SchemaRegistry schemaRegistry;
        private SyncTimeRegistry syncTimeRegistry;

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.ModelProviderStep
        @NonNull
        public SchemaRegistryStep modelProvider(@NonNull ModelProvider modelProvider) {
            Objects.requireNonNull(modelProvider);
            this.modelProvider = modelProvider;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.SchemaRegistryStep
        @NonNull
        public SyncTimeRegistryStep schemaRegistry(@NonNull SchemaRegistry schemaRegistry) {
            Objects.requireNonNull(schemaRegistry);
            this.schemaRegistry = schemaRegistry;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.SyncTimeRegistryStep
        @NonNull
        public AppSyncStep syncTimeRegistry(@NonNull SyncTimeRegistry syncTimeRegistry) {
            Objects.requireNonNull(syncTimeRegistry);
            this.syncTimeRegistry = syncTimeRegistry;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.AppSyncStep
        @NonNull
        public MergerStep appSync(@NonNull AppSync appSync) {
            Objects.requireNonNull(appSync);
            this.appSync = appSync;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.MergerStep
        @NonNull
        public DataStoreConfigurationProviderStep merger(@NonNull Merger merger) {
            Objects.requireNonNull(merger);
            this.merger = merger;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.DataStoreConfigurationProviderStep
        @NonNull
        public QueryPredicateProviderStep dataStoreConfigurationProvider(DataStoreConfigurationProvider dataStoreConfigurationProvider) {
            this.dataStoreConfigurationProvider = dataStoreConfigurationProvider;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.QueryPredicateProviderStep
        @NonNull
        public RetryHandlerStep queryPredicateProvider(QueryPredicateProvider queryPredicateProvider) {
            Objects.requireNonNull(queryPredicateProvider);
            this.queryPredicateProvider = queryPredicateProvider;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.SyncRetryStep
        @NonNull
        public BuildStep isSyncRetryEnabled(boolean z) {
            this.isSyncRetryEnabled = z;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.BuildStep
        @NonNull
        public SyncProcessor build() {
            return new SyncProcessor(this);
        }

        @Override // com.amplifyframework.datastore.syncengine.SyncProcessor.RetryHandlerStep
        @NonNull
        public SyncRetryStep retryHandler(RetryHandler retryHandler) {
            this.requestRetry = retryHandler;
            return this;
        }
    }
}

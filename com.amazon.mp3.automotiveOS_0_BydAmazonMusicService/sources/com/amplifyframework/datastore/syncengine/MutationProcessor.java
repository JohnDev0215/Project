package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.appsync.AppSync;
import com.amplifyframework.datastore.appsync.AppSyncConflictUnhandledError;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.datastore.events.OutboxStatusEvent;
import com.amplifyframework.datastore.syncengine.MutationOutbox;
import com.amplifyframework.datastore.syncengine.PendingMutation;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class MutationProcessor {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final AppSync appSync;
    private final ConflictResolver conflictResolver;
    private final Merger merger;
    private final MutationOutbox mutationOutbox;
    private final CompositeDisposable ongoingOperationsDisposable;
    private final RetryHandler retryHandler;
    private final SchemaRegistry schemaRegistry;
    private final VersionRepository versionRepository;

    interface BuilderSteps {

        public interface AppSyncStep {
            @NonNull
            ConflictResolverStep appSync(@NonNull AppSync appSync);
        }

        public interface BuildStep {
            @NonNull
            MutationProcessor build();
        }

        public interface ConflictResolverStep {
            @NonNull
            RetryHandlerStep conflictResolver(@NonNull ConflictResolver conflictResolver);
        }

        public interface MergerStep {
            @NonNull
            VersionRepositoryStep merger(@NonNull Merger merger);
        }

        public interface ModelSchemaRegistryStep {
            @NonNull
            MutationOutboxStep schemaRegistry(@NonNull SchemaRegistry schemaRegistry);
        }

        public interface MutationOutboxStep {
            @NonNull
            AppSyncStep mutationOutbox(@NonNull MutationOutbox mutationOutbox);
        }

        public interface RetryHandlerStep {
            @NonNull
            BuildStep retryHandler(@NonNull RetryHandler retryHandler);
        }

        public interface VersionRepositoryStep {
            @NonNull
            ModelSchemaRegistryStep versionRepository(@NonNull VersionRepository versionRepository);
        }
    }

    interface PublicationStrategy<T extends Model> {
        void publish(T t, Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer, Consumer<DataStoreException> consumer2);
    }

    /* synthetic */ MutationProcessor(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    private MutationProcessor(Builder builder) {
        Merger merger = builder.merger;
        Objects.requireNonNull(merger);
        this.merger = merger;
        VersionRepository versionRepository = builder.versionRepository;
        Objects.requireNonNull(versionRepository);
        this.versionRepository = versionRepository;
        SchemaRegistry schemaRegistry = builder.schemaRegistry;
        Objects.requireNonNull(schemaRegistry);
        this.schemaRegistry = schemaRegistry;
        MutationOutbox mutationOutbox = builder.mutationOutbox;
        Objects.requireNonNull(mutationOutbox);
        this.mutationOutbox = mutationOutbox;
        AppSync appSync = builder.appSync;
        Objects.requireNonNull(appSync);
        this.appSync = appSync;
        ConflictResolver conflictResolver = builder.conflictResolver;
        Objects.requireNonNull(conflictResolver);
        this.conflictResolver = conflictResolver;
        RetryHandler retryHandler = builder.retryHandler;
        Objects.requireNonNull(retryHandler);
        this.retryHandler = retryHandler;
        this.ongoingOperationsDisposable = new CompositeDisposable();
    }

    public static BuilderSteps.MergerStep builder() {
        return new Builder();
    }

    void startDrainingMutationOutbox() {
        this.ongoingOperationsDisposable.add(this.mutationOutbox.events().doOnSubscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                MutationProcessor.lambda$startDrainingMutationOutbox$0((Disposable) obj);
            }
        }).startWithItem(MutationOutbox.OutboxEvent.CONTENT_AVAILABLE).subscribeOn(Schedulers.single()).observeOn(Schedulers.single()).flatMapCompletable(new Function() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$startDrainingMutationOutbox$1((MutationOutbox.OutboxEvent) obj);
            }
        }).subscribe(new Action() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda8
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                MutationProcessor.lambda$startDrainingMutationOutbox$2();
            }
        }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda9
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                MutationProcessor.lambda$startDrainingMutationOutbox$3((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startDrainingMutationOutbox$0(Disposable disposable) throws Throwable {
        LOG.info("Started processing the mutation outbox. Pending mutations will be published to the cloud.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$startDrainingMutationOutbox$1(MutationOutbox.OutboxEvent outboxEvent) throws Throwable {
        return drainMutationOutbox();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startDrainingMutationOutbox$2() throws Throwable {
        LOG.warn("Observation of mutation outbox was completed.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startDrainingMutationOutbox$3(Throwable th) throws Throwable {
        LOG.warn("Error ended observation of mutation outbox: ", th);
    }

    private Completable drainMutationOutbox() {
        while (true) {
            PendingMutation<? extends Model> pendingMutationPeek = this.mutationOutbox.peek();
            if (pendingMutationPeek == null) {
                return Completable.complete();
            }
            try {
                processOutboxItem(pendingMutationPeek).blockingAwait();
            } catch (RuntimeException e) {
                return Completable.error(new DataStoreException("Failed to process " + e, "Check your internet connection."));
            }
        }
    }

    private <T extends Model> Completable processOutboxItem(final PendingMutation<T> pendingMutation) {
        return this.mutationOutbox.markInFlight(pendingMutation.getMutationId()).andThen(publishWithRetry(pendingMutation).map(new Function() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda13
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$processOutboxItem$4(pendingMutation, (ModelWithMetadata) obj);
            }
        }).flatMapCompletable(new Function() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda14
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$processOutboxItem$6(pendingMutation, (ModelWithMetadata) obj);
            }
        })).doOnComplete(new Action() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda15
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                this.f$0.lambda$processOutboxItem$7(pendingMutation);
            }
        }).onErrorResumeNext(new Function() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda16
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$processOutboxItem$9(pendingMutation, (Throwable) obj);
            }
        }).doOnError(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda17
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                MutationProcessor.lambda$processOutboxItem$10(pendingMutation, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$processOutboxItem$6(final PendingMutation pendingMutation, final ModelWithMetadata modelWithMetadata) throws Throwable {
        return this.mutationOutbox.remove(pendingMutation.getMutationId()).andThen(this.merger.merge(modelWithMetadata)).doOnComplete(new Action() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                this.f$0.lambda$null$5(pendingMutation, modelWithMetadata);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$5(PendingMutation pendingMutation, ModelWithMetadata modelWithMetadata) throws Throwable {
        announceMutationProcessed(pendingMutation.getModelSchema().getName(), modelWithMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$processOutboxItem$7(PendingMutation pendingMutation) throws Throwable {
        LOG.debug("Pending mutation was published to cloud successfully, and removed from the mutation outbox: " + pendingMutation);
        publishCurrentOutboxStatus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$processOutboxItem$9(final PendingMutation pendingMutation, Throwable th) throws Throwable {
        if (th instanceof DataStoreException.GraphQLResponseException) {
            final DataStoreException.GraphQLResponseException graphQLResponseException = (DataStoreException.GraphQLResponseException) th;
            return this.mutationOutbox.remove(pendingMutation.getMutationId()).doOnComplete(new Action() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda12
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() throws Throwable {
                    this.f$0.lambda$null$8(pendingMutation, graphQLResponseException);
                }
            });
        }
        return Completable.error(th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$processOutboxItem$10(PendingMutation pendingMutation, Throwable th) throws Throwable {
        LOG.warn("Failed to publish a local change = " + pendingMutation, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX INFO: renamed from: ensureModelHasSchema, reason: merged with bridge method [inline-methods] */
    public <T extends Model> ModelWithMetadata<? extends Model> lambda$processOutboxItem$4(PendingMutation<T> pendingMutation, ModelWithMetadata<T> modelWithMetadata) {
        return modelWithMetadata.getModel() instanceof SerializedModel ? modelWithSchemaAdded(modelWithMetadata, pendingMutation.getModelSchema()) : modelWithMetadata;
    }

    private <T extends Model> ModelWithMetadata<? extends Model> modelWithSchemaAdded(ModelWithMetadata<T> modelWithMetadata, ModelSchema modelSchema) {
        return new ModelWithMetadata<>(SerializedModel.builder().modelSchema(modelSchema).serializedData(SerializedModel.parseSerializedData(((SerializedModel) modelWithMetadata.getModel()).getSerializedData(), modelSchema.getName(), this.schemaRegistry)).build(), modelWithMetadata.getSyncMetadata());
    }

    private <T extends Model> void announceMutationProcessed(String str, ModelWithMetadata<T> modelWithMetadata) {
        Amplify.Hub.publish(HubChannel.DATASTORE, OutboxMutationEvent.create(str, modelWithMetadata).toHubEvent());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: announceMutationFailed, reason: merged with bridge method [inline-methods] */
    public <T extends Model> void lambda$null$8(PendingMutation<T> pendingMutation, DataStoreException.GraphQLResponseException graphQLResponseException) {
        Amplify.Hub.publish(HubChannel.DATASTORE, OutboxMutationFailedEvent.create(pendingMutation, graphQLResponseException.getErrors()).toHubEvent());
    }

    private void publishCurrentOutboxStatus() {
        Amplify.Hub.publish(HubChannel.DATASTORE, new OutboxStatusEvent(this.mutationOutbox.peek() == null).toHubEvent());
    }

    void stopDrainingMutationOutbox() {
        this.ongoingOperationsDisposable.clear();
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.syncengine.MutationProcessor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type;

        static {
            int[] iArr = new int[PendingMutation.Type.values().length];
            $SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type = iArr;
            try {
                iArr[PendingMutation.Type.UPDATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[PendingMutation.Type.CREATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[PendingMutation.Type.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private <T extends Model> Single<ModelWithMetadata<T>> publishToNetwork(PendingMutation<T> pendingMutation) {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$syncengine$PendingMutation$Type[pendingMutation.getMutationType().ordinal()];
        if (i == 1) {
            return update(pendingMutation);
        }
        if (i == 2) {
            return create(pendingMutation);
        }
        if (i == 3) {
            return delete(pendingMutation);
        }
        return Single.error(new DataStoreException("Unknown mutation type in storage = " + pendingMutation.getMutationType(), "This is likely a bug. Please file a ticket with AWS."));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends Model> Single<ModelWithMetadata<T>> update(final PendingMutation<T> pendingMutation) {
        Model mutatedItem = pendingMutation.getMutatedItem();
        final ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(mutatedItem.getModelName());
        return (Single<ModelWithMetadata<T>>) this.versionRepository.findModelVersion(mutatedItem).flatMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$update$12(pendingMutation, modelSchemaForModelClass, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SingleSource lambda$update$12(final PendingMutation pendingMutation, final ModelSchema modelSchema, final Integer num) throws Throwable {
        return publishWithStrategy(pendingMutation, new PublicationStrategy() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda18
            @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.PublicationStrategy
            public final void publish(Model model, Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$null$11(modelSchema, num, pendingMutation, model, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$11(ModelSchema modelSchema, Integer num, PendingMutation pendingMutation, Model model, Consumer consumer, Consumer consumer2) {
        this.appSync.update(model, modelSchema, num, pendingMutation.getPredicate(), consumer, consumer2);
    }

    private <T extends Model> Single<ModelWithMetadata<T>> create(PendingMutation<T> pendingMutation) {
        final ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(pendingMutation.getMutatedItem().getModelName());
        return publishWithStrategy(pendingMutation, new PublicationStrategy() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda11
            @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.PublicationStrategy
            public final void publish(Model model, Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$create$13(modelSchemaForModelClass, model, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$create$13(ModelSchema modelSchema, Model model, Consumer consumer, Consumer consumer2) {
        this.appSync.create(model, modelSchema, consumer, consumer2);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private <T extends Model> Single<ModelWithMetadata<T>> delete(final PendingMutation<T> pendingMutation) {
        final Model mutatedItem = pendingMutation.getMutatedItem();
        final ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(mutatedItem.getModelName());
        return (Single<ModelWithMetadata<T>>) this.versionRepository.findModelVersion(mutatedItem).flatMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$delete$15(pendingMutation, mutatedItem, modelSchemaForModelClass, (Integer) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SingleSource lambda$delete$15(final PendingMutation pendingMutation, final Model model, final ModelSchema modelSchema, final Integer num) throws Throwable {
        return publishWithStrategy(pendingMutation, new PublicationStrategy() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.PublicationStrategy
            public final void publish(Model model2, Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$null$14(model, modelSchema, num, pendingMutation, model2, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$14(Model model, ModelSchema modelSchema, Integer num, PendingMutation pendingMutation, Model model2, Consumer consumer, Consumer consumer2) {
        this.appSync.delete(model, modelSchema, num, pendingMutation.getPredicate(), consumer, consumer2);
    }

    @NonNull
    private <T extends Model> Single<ModelWithMetadata<T>> publishWithStrategy(@NonNull final PendingMutation<T> pendingMutation, @NonNull final PublicationStrategy<T> publicationStrategy) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                MutationProcessor.lambda$publishWithStrategy$17(publicationStrategy, pendingMutation, singleEmitter);
            }
        }).flatMap(new Function() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$publishWithStrategy$18(pendingMutation, (GraphQLResponse) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$publishWithStrategy$17(PublicationStrategy publicationStrategy, PendingMutation pendingMutation, final SingleEmitter singleEmitter) throws Throwable {
        Model mutatedItem = pendingMutation.getMutatedItem();
        singleEmitter.getClass();
        publicationStrategy.publish(mutatedItem, new ConflictResolver$$ExternalSyntheticLambda1(singleEmitter), new Consumer() { // from class: com.amplifyframework.datastore.syncengine.MutationProcessor$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                MutationProcessor.lambda$null$16(singleEmitter, (DataStoreException) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$16(SingleEmitter singleEmitter, DataStoreException dataStoreException) {
        if (singleEmitter.isDisposed()) {
            return;
        }
        singleEmitter.onError(dataStoreException);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SingleSource lambda$publishWithStrategy$18(PendingMutation pendingMutation, GraphQLResponse graphQLResponse) throws Throwable {
        if (!graphQLResponse.hasErrors() && graphQLResponse.hasData()) {
            return Single.just(graphQLResponse.getData());
        }
        return handleResponseErrors(pendingMutation, graphQLResponse.getErrors());
    }

    private <T extends Model> Single<ModelWithMetadata<T>> publishWithRetry(@NonNull PendingMutation<T> pendingMutation) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(DataStoreException.GraphQLResponseException.class);
        arrayList.add(ApiException.NonRetryableException.class);
        LOG.info("Started Publish with retry: " + pendingMutation);
        return this.retryHandler.retry(publishToNetwork(pendingMutation), arrayList);
    }

    private <T extends Model> Single<ModelWithMetadata<T>> handleResponseErrors(PendingMutation<T> pendingMutation, List<GraphQLResponse.Error> list) {
        AppSyncConflictUnhandledError<T> appSyncConflictUnhandledErrorFindFirst = AppSyncConflictUnhandledError.findFirst(pendingMutation.getModelSchema().getModelClass(), list);
        if (appSyncConflictUnhandledErrorFindFirst != null) {
            return this.conflictResolver.resolve(pendingMutation, appSyncConflictUnhandledErrorFindFirst);
        }
        return Single.error(new DataStoreException.GraphQLResponseException("Mutation failed. Failed mutation = " + pendingMutation + ". AppSync response contained errors = " + list, list));
    }

    static final class Builder implements BuilderSteps.MergerStep, BuilderSteps.VersionRepositoryStep, BuilderSteps.ModelSchemaRegistryStep, BuilderSteps.MutationOutboxStep, BuilderSteps.AppSyncStep, BuilderSteps.ConflictResolverStep, BuilderSteps.RetryHandlerStep, BuilderSteps.BuildStep {
        private AppSync appSync;
        private ConflictResolver conflictResolver;
        private Merger merger;
        private MutationOutbox mutationOutbox;
        private RetryHandler retryHandler;
        private SchemaRegistry schemaRegistry;
        private VersionRepository versionRepository;

        Builder() {
        }

        @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.BuilderSteps.MergerStep
        @NonNull
        public BuilderSteps.VersionRepositoryStep merger(@NonNull Merger merger) {
            Objects.requireNonNull(merger);
            this.merger = merger;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.BuilderSteps.VersionRepositoryStep
        @NonNull
        public BuilderSteps.ModelSchemaRegistryStep versionRepository(@NonNull VersionRepository versionRepository) {
            Objects.requireNonNull(versionRepository);
            this.versionRepository = versionRepository;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.BuilderSteps.ModelSchemaRegistryStep
        @NonNull
        public BuilderSteps.MutationOutboxStep schemaRegistry(@NonNull SchemaRegistry schemaRegistry) {
            Objects.requireNonNull(schemaRegistry);
            this.schemaRegistry = schemaRegistry;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.BuilderSteps.MutationOutboxStep
        @NonNull
        public BuilderSteps.AppSyncStep mutationOutbox(@NonNull MutationOutbox mutationOutbox) {
            Objects.requireNonNull(mutationOutbox);
            this.mutationOutbox = mutationOutbox;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.BuilderSteps.AppSyncStep
        @NonNull
        public BuilderSteps.ConflictResolverStep appSync(@NonNull AppSync appSync) {
            Objects.requireNonNull(appSync);
            this.appSync = appSync;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.BuilderSteps.ConflictResolverStep
        @NonNull
        public BuilderSteps.RetryHandlerStep conflictResolver(@NonNull ConflictResolver conflictResolver) {
            Objects.requireNonNull(conflictResolver);
            this.conflictResolver = conflictResolver;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.BuilderSteps.RetryHandlerStep
        @NonNull
        public BuilderSteps.BuildStep retryHandler(@NonNull RetryHandler retryHandler) {
            this.retryHandler = retryHandler;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.MutationProcessor.BuilderSteps.BuildStep
        @NonNull
        public MutationProcessor build() {
            return new MutationProcessor(this, null);
        }
    }
}

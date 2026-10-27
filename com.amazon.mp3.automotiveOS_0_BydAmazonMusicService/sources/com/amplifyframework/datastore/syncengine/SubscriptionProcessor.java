package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.graphql.GraphQLResponse;
import com.amplifyframework.api.graphql.SubscriptionType;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.datastore.AmplifyDisposables;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.appsync.AppSync;
import com.amplifyframework.datastore.appsync.AppSyncExtensions;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.datastore.syncengine.SubscriptionEvent;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.Empty;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import io.reactivex.rxjava3.subjects.ReplaySubject;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
final class SubscriptionProcessor {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private static final long NETWORK_OP_TIMEOUT_SECONDS = 60;
    private static final long TIMEOUT_SECONDS_PER_MODEL = 20;
    private final long adjustedTimeoutSeconds;
    private final AppSync appSync;
    private ReplaySubject<SubscriptionEvent<? extends Model>> buffer;
    private final Merger merger;
    private final ModelProvider modelProvider;
    private final Consumer<Throwable> onFailure;
    private final CompositeDisposable ongoingOperationsDisposable;
    private final QueryPredicateProvider queryPredicateProvider;
    private final SchemaRegistry schemaRegistry;

    interface AppSyncStep {
        @NonNull
        ModelProviderStep appSync(@NonNull AppSync appSync);
    }

    interface BuildStep {
        @NonNull
        SubscriptionProcessor build();
    }

    interface MergerStep {
        @NonNull
        QueryPredicateProviderStep merger(@NonNull Merger merger);
    }

    interface ModelProviderStep {
        @NonNull
        SchemaRegistryStep modelProvider(@NonNull ModelProvider modelProvider);
    }

    interface OnFailureStep {
        @NonNull
        BuildStep onFailure(Consumer<Throwable> consumer);
    }

    interface QueryPredicateProviderStep {
        @NonNull
        OnFailureStep queryPredicateProvider(QueryPredicateProvider queryPredicateProvider);
    }

    interface SchemaRegistryStep {
        @NonNull
        MergerStep schemaRegistry(@NonNull SchemaRegistry schemaRegistry);
    }

    interface SubscriptionMethod {
        <T extends Model> Cancelable subscribe(@NonNull ModelSchema modelSchema, @NonNull Consumer<String> consumer, @NonNull Consumer<GraphQLResponse<ModelWithMetadata<T>>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull Action action);
    }

    /* synthetic */ SubscriptionProcessor(Builder builder, AnonymousClass1 anonymousClass1) {
        this(builder);
    }

    private SubscriptionProcessor(Builder builder) {
        this.appSync = builder.appSync;
        ModelProvider modelProvider = builder.modelProvider;
        this.modelProvider = modelProvider;
        this.merger = builder.merger;
        this.queryPredicateProvider = builder.queryPredicateProvider;
        this.onFailure = builder.onFailure;
        this.schemaRegistry = builder.schemaRegistry;
        this.ongoingOperationsDisposable = new CompositeDisposable();
        this.adjustedTimeoutSeconds = Math.max(NETWORK_OP_TIMEOUT_SECONDS, ((long) Math.max(modelProvider.models().size(), modelProvider.modelSchemas().size())) * TIMEOUT_SECONDS_PER_MODEL);
    }

    public static AppSyncStep builder() {
        return new Builder();
    }

    synchronized void startSubscriptions() throws DataStoreException {
        AbortableCountDownLatch<DataStoreException> abortableCountDownLatch = new AbortableCountDownLatch<>(this.modelProvider.modelNames().size() * SubscriptionType.values().length);
        this.buffer = ReplaySubject.create();
        HashSet hashSet = new HashSet();
        Iterator<ModelSchema> it = this.modelProvider.modelSchemas().values().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            ModelSchema next = it.next();
            for (SubscriptionType subscriptionType : SubscriptionType.values()) {
                hashSet.add(subscriptionObservable(this.appSync, subscriptionType, abortableCountDownLatch, next));
            }
        }
        CompositeDisposable compositeDisposable = this.ongoingOperationsDisposable;
        Observable observableDoOnComplete = Observable.merge(hashSet).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).doOnSubscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda16
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                SubscriptionProcessor.lambda$startSubscriptions$0((Disposable) obj);
            }
        }).doOnError(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda17
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                SubscriptionProcessor.lambda$startSubscriptions$1((Throwable) obj);
            }
        }).doOnComplete(new io.reactivex.rxjava3.functions.Action() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda18
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                SubscriptionProcessor.lambda$startSubscriptions$2();
            }
        });
        final ReplaySubject<SubscriptionEvent<? extends Model>> replaySubject = this.buffer;
        replaySubject.getClass();
        io.reactivex.rxjava3.functions.Consumer consumer = new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda19
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                replaySubject.onNext((SubscriptionEvent) obj);
            }
        };
        final ReplaySubject<SubscriptionEvent<? extends Model>> replaySubject2 = this.buffer;
        replaySubject2.getClass();
        io.reactivex.rxjava3.functions.Consumer<? super Throwable> consumer2 = new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda20
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) {
                replaySubject2.onError((Throwable) obj);
            }
        };
        final ReplaySubject<SubscriptionEvent<? extends Model>> replaySubject3 = this.buffer;
        replaySubject3.getClass();
        compositeDisposable.add(observableDoOnComplete.subscribe(consumer, consumer2, new io.reactivex.rxjava3.functions.Action() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda21
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() {
                replaySubject3.onComplete();
            }
        }));
        try {
            Logger logger = LOG;
            logger.debug("Waiting for subscriptions to start.");
            if (abortableCountDownLatch.abortableAwait(this.adjustedTimeoutSeconds, TimeUnit.SECONDS)) {
                Amplify.Hub.publish(HubChannel.DATASTORE, HubEvent.create(DataStoreChannelEventName.SUBSCRIPTIONS_ESTABLISHED));
                logger.info(String.format(Locale.US, "Started subscription processor for models: %s of types %s.", this.modelProvider.modelNames(), Arrays.toString(SubscriptionType.values())));
            } else {
                throw new DataStoreException("Timed out waiting for subscription processor to start.", "Retry");
            }
        } catch (InterruptedException unused) {
            LOG.warn("Subscription operations were interrupted during setup.");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startSubscriptions$0(Disposable disposable) throws Throwable {
        LOG.info("Starting processing subscription events.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startSubscriptions$1(Throwable th) throws Throwable {
        LOG.warn("Reading subscription events has failed.", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startSubscriptions$2() throws Throwable {
        LOG.warn("Reading subscription events is completed.");
    }

    private boolean isExceptionType(DataStoreException dataStoreException, AppSyncExtensions.AppSyncErrorType appSyncErrorType) {
        if (!(dataStoreException instanceof DataStoreException.GraphQLResponseException)) {
            return false;
        }
        GraphQLResponse.Error error = ((DataStoreException.GraphQLResponseException) dataStoreException).getErrors().get(0);
        if (Empty.check(error.getExtensions())) {
            return false;
        }
        return appSyncErrorType.equals(new AppSyncExtensions(error.getExtensions()).getErrorType());
    }

    private <T extends Model> Observable<SubscriptionEvent<? extends Model>> subscriptionObservable(final AppSync appSync, final SubscriptionType subscriptionType, final AbortableCountDownLatch<DataStoreException> abortableCountDownLatch, final ModelSchema modelSchema) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                this.f$0.lambda$subscriptionObservable$6(appSync, subscriptionType, modelSchema, abortableCountDownLatch, observableEmitter);
            }
        }).doOnError(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                SubscriptionProcessor.lambda$subscriptionObservable$7(subscriptionType, modelSchema, (Throwable) obj);
            }
        }).subscribeOn(Schedulers.io()).observeOn(Schedulers.io()).map(new Function() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SubscriptionProcessor.unwrapResponse((GraphQLResponse) obj);
            }
        }).filter(new Predicate() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return this.f$0.lambda$subscriptionObservable$8(modelSchema, (ModelWithMetadata) obj);
            }
        }).map(new Function() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return SubscriptionProcessor.lambda$subscriptionObservable$9(subscriptionType, modelSchema, (ModelWithMetadata) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$subscriptionObservable$6(AppSync appSync, final SubscriptionType subscriptionType, final ModelSchema modelSchema, final AbortableCountDownLatch abortableCountDownLatch, final ObservableEmitter observableEmitter) throws Throwable {
        SubscriptionMethod subscriptionMethodSubscriptionMethodFor = subscriptionMethodFor(appSync, subscriptionType);
        final AtomicReference atomicReference = new AtomicReference();
        Consumer<String> consumer = new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                SubscriptionProcessor.lambda$null$3(subscriptionType, modelSchema, atomicReference, abortableCountDownLatch, (String) obj);
            }
        };
        observableEmitter.getClass();
        observableEmitter.setDisposable(AmplifyDisposables.fromCancelable(subscriptionMethodSubscriptionMethodFor.subscribe(modelSchema, consumer, new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda9
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                observableEmitter.onNext((GraphQLResponse) obj);
            }
        }, new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda10
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$null$4(abortableCountDownLatch, subscriptionType, modelSchema, (DataStoreException) obj);
            }
        }, new Action() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda11
            @Override // com.amplifyframework.core.Action
            public final void call() {
                SubscriptionProcessor.lambda$null$5(atomicReference, observableEmitter);
            }
        })));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$3(SubscriptionType subscriptionType, ModelSchema modelSchema, AtomicReference atomicReference, AbortableCountDownLatch abortableCountDownLatch, String str) {
        LOG.debug("Subscription started for " + subscriptionType.name() + " " + modelSchema.getName() + " subscriptionId: " + str);
        atomicReference.set(str);
        abortableCountDownLatch.countDown();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$4(AbortableCountDownLatch abortableCountDownLatch, SubscriptionType subscriptionType, ModelSchema modelSchema, DataStoreException dataStoreException) {
        if (isExceptionType(dataStoreException, AppSyncExtensions.AppSyncErrorType.UNAUTHORIZED)) {
            abortableCountDownLatch.countDown();
            LOG.warn("Unauthorized failure:" + subscriptionType.name() + " " + modelSchema.getName());
            return;
        }
        if (!isExceptionType(dataStoreException, AppSyncExtensions.AppSyncErrorType.OPERATION_DISABLED)) {
            if (abortableCountDownLatch.getCount() > 0) {
                abortableCountDownLatch.abort(dataStoreException);
                return;
            } else {
                this.onFailure.accept(dataStoreException);
                return;
            }
        }
        abortableCountDownLatch.countDown();
        LOG.warn("Operation disabled:" + subscriptionType.name() + " " + modelSchema.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$5(AtomicReference atomicReference, ObservableEmitter observableEmitter) {
        LOG.debug("Subscription completed:" + ((String) atomicReference.get()));
        observableEmitter.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$subscriptionObservable$7(SubscriptionType subscriptionType, ModelSchema modelSchema, Throwable th) throws Throwable {
        LOG.warn("An error occurred on the remote " + subscriptionType.name() + " subscription for model " + modelSchema.getName(), th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ boolean lambda$subscriptionObservable$8(ModelSchema modelSchema, ModelWithMetadata modelWithMetadata) throws Throwable {
        return this.queryPredicateProvider.getPredicate(modelSchema.getName()).evaluate(modelWithMetadata.getModel());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ SubscriptionEvent lambda$subscriptionObservable$9(SubscriptionType subscriptionType, ModelSchema modelSchema, ModelWithMetadata modelWithMetadata) throws Throwable {
        return SubscriptionEvent.builder().type(fromSubscriptionType(subscriptionType)).modelWithMetadata(modelWithMetadata).modelSchema(modelSchema).build();
    }

    void startDrainingMutationBuffer() {
        this.ongoingOperationsDisposable.add(this.buffer.doOnSubscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda12
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                SubscriptionProcessor.lambda$startDrainingMutationBuffer$10((Disposable) obj);
            }
        }).flatMapCompletable(new Function() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda13
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.mergeEvent((SubscriptionEvent) obj);
            }
        }).doOnError(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda14
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                SubscriptionProcessor.lambda$startDrainingMutationBuffer$11((Throwable) obj);
            }
        }).doOnComplete(new io.reactivex.rxjava3.functions.Action() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda15
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                SubscriptionProcessor.lambda$startDrainingMutationBuffer$12();
            }
        }).subscribe());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startDrainingMutationBuffer$10(Disposable disposable) throws Throwable {
        LOG.info("Starting processing subscription data buffer.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startDrainingMutationBuffer$11(Throwable th) throws Throwable {
        LOG.warn("Reading subscriptions buffer has failed.", th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startDrainingMutationBuffer$12() throws Throwable {
        LOG.warn("Reading from subscriptions buffer is completed.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Completable mergeEvent(SubscriptionEvent<? extends Model> subscriptionEvent) {
        ModelWithMetadata<T> modelWithMetadata = subscriptionEvent.modelWithMetadata();
        if (modelWithMetadata.getModel() instanceof SerializedModel) {
            return this.merger.merge(new ModelWithMetadata(SerializedModel.builder().modelSchema(subscriptionEvent.modelSchema()).serializedData(SerializedModel.parseSerializedData(((SerializedModel) modelWithMetadata.getModel()).getSerializedData(), subscriptionEvent.modelSchema().getName(), this.schemaRegistry)).build(), modelWithMetadata.getSyncMetadata()));
        }
        return this.merger.merge(modelWithMetadata);
    }

    synchronized void stopAllSubscriptionActivity() {
        Logger logger = LOG;
        logger.info("Stopping subscription processor.");
        this.ongoingOperationsDisposable.clear();
        logger.info("Stopped subscription processor.");
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$api$graphql$SubscriptionType;

        static {
            int[] iArr = new int[SubscriptionType.values().length];
            $SwitchMap$com$amplifyframework$api$graphql$SubscriptionType = iArr;
            try {
                iArr[SubscriptionType.ON_UPDATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$graphql$SubscriptionType[SubscriptionType.ON_DELETE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$api$graphql$SubscriptionType[SubscriptionType.ON_CREATE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @VisibleForTesting
    static SubscriptionMethod subscriptionMethodFor(final AppSync appSync, SubscriptionType subscriptionType) throws DataStoreException {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$api$graphql$SubscriptionType[subscriptionType.ordinal()];
        if (i == 1) {
            appSync.getClass();
            return new SubscriptionMethod() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda0
                @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.SubscriptionMethod
                public final Cancelable subscribe(ModelSchema modelSchema, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                    return appSync.onUpdate(modelSchema, consumer, consumer2, consumer3, action);
                }
            };
        }
        if (i == 2) {
            appSync.getClass();
            return new SubscriptionMethod() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda1
                @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.SubscriptionMethod
                public final Cancelable subscribe(ModelSchema modelSchema, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                    return appSync.onDelete(modelSchema, consumer, consumer2, consumer3, action);
                }
            };
        }
        if (i == 3) {
            appSync.getClass();
            return new SubscriptionMethod() { // from class: com.amplifyframework.datastore.syncengine.SubscriptionProcessor$$ExternalSyntheticLambda2
                @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.SubscriptionMethod
                public final Cancelable subscribe(ModelSchema modelSchema, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                    return appSync.onCreate(modelSchema, consumer, consumer2, consumer3, action);
                }
            };
        }
        throw new DataStoreException("Failed to establish a model subscription.", "Was a new subscription type created?");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static <T extends Model> ModelWithMetadata<T> unwrapResponse(GraphQLResponse<? extends ModelWithMetadata<T>> graphQLResponse) throws DataStoreException {
        String str;
        if (graphQLResponse.hasErrors()) {
            str = String.format("Errors on subscription: %s", graphQLResponse.getErrors());
        } else {
            str = !graphQLResponse.hasData() ? "Empty data received on subscription." : null;
        }
        if (str != null) {
            throw new DataStoreException(str, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION);
        }
        return graphQLResponse.getData();
    }

    private static SubscriptionEvent.Type fromSubscriptionType(SubscriptionType subscriptionType) {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$api$graphql$SubscriptionType[subscriptionType.ordinal()];
        if (i == 1) {
            return SubscriptionEvent.Type.UPDATE;
        }
        if (i == 2) {
            return SubscriptionEvent.Type.DELETE;
        }
        if (i == 3) {
            return SubscriptionEvent.Type.CREATE;
        }
        throw new IllegalArgumentException("Unknown subscription type: " + subscriptionType);
    }

    public static final class Builder implements AppSyncStep, ModelProviderStep, SchemaRegistryStep, MergerStep, QueryPredicateProviderStep, OnFailureStep, BuildStep {
        private AppSync appSync;
        private Merger merger;
        private ModelProvider modelProvider;
        private Consumer<Throwable> onFailure;
        private QueryPredicateProvider queryPredicateProvider;
        private SchemaRegistry schemaRegistry;

        @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.AppSyncStep
        @NonNull
        public ModelProviderStep appSync(@NonNull AppSync appSync) {
            Objects.requireNonNull(appSync);
            this.appSync = appSync;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.ModelProviderStep
        @NonNull
        public SchemaRegistryStep modelProvider(@NonNull ModelProvider modelProvider) {
            Objects.requireNonNull(modelProvider);
            this.modelProvider = modelProvider;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.SchemaRegistryStep
        @NonNull
        public MergerStep schemaRegistry(@NonNull SchemaRegistry schemaRegistry) {
            Objects.requireNonNull(schemaRegistry);
            this.schemaRegistry = schemaRegistry;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.MergerStep
        @NonNull
        public QueryPredicateProviderStep merger(@NonNull Merger merger) {
            Objects.requireNonNull(merger);
            this.merger = merger;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.QueryPredicateProviderStep
        @NonNull
        public OnFailureStep queryPredicateProvider(QueryPredicateProvider queryPredicateProvider) {
            Objects.requireNonNull(queryPredicateProvider);
            this.queryPredicateProvider = queryPredicateProvider;
            return this;
        }

        @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.BuildStep
        @NonNull
        public SubscriptionProcessor build() {
            return new SubscriptionProcessor(this, null);
        }

        @Override // com.amplifyframework.datastore.syncengine.SubscriptionProcessor.OnFailureStep
        @NonNull
        public BuildStep onFailure(Consumer<Throwable> consumer) {
            Objects.requireNonNull(consumer);
            this.onFailure = consumer;
            return this;
        }
    }
}

package com.amplifyframework.datastore;

import android.content.Context;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.annotation.WorkerThread;
import androidx.core.util.Supplier;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.api.ApiCategory;
import com.amplifyframework.api.aws.AuthModeStrategyType;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.InitializationStatus;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelIdentifier;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.appsync.AppSyncClient;
import com.amplifyframework.datastore.model.ModelProviderLocator;
import com.amplifyframework.datastore.storage.ItemChangeMapper;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.datastore.storage.StorageItemChange;
import com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter;
import com.amplifyframework.datastore.syncengine.Orchestrator;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.hub.HubEventFilter;
import com.amplifyframework.hub.HubSubscriber;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* JADX INFO: loaded from: classes5.dex */
public final class AWSDataStorePlugin extends DataStorePlugin<Void> {
    private final AuthModeStrategyType authModeStrategy;
    private final CountDownLatch categoryInitializationsPending;
    private final boolean isSyncRetryEnabled;
    private final Orchestrator orchestrator;
    private DataStoreConfiguration pluginConfiguration;
    private final LocalStorageAdapter sqliteStorageAdapter;
    private final DataStoreConfiguration userProvidedConfiguration;
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private static final long LIFECYCLE_TIMEOUT_MS = TimeUnit.SECONDS.toMillis(5);

    @Override // com.amplifyframework.core.plugin.Plugin
    @Nullable
    public Void getEscapeHatch() {
        return null;
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public String getPluginKey() {
        return "awsDataStorePlugin";
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    @NonNull
    public String getVersion() {
        return "1.37.11";
    }

    private AWSDataStorePlugin(@NonNull ModelProvider modelProvider, @NonNull SchemaRegistry schemaRegistry, @NonNull final ApiCategory apiCategory, @Nullable DataStoreConfiguration dataStoreConfiguration) {
        SQLiteStorageAdapter sQLiteStorageAdapterForModels = SQLiteStorageAdapter.forModels(schemaRegistry, modelProvider);
        this.sqliteStorageAdapter = sQLiteStorageAdapterForModels;
        this.categoryInitializationsPending = new CountDownLatch(1);
        this.authModeStrategy = AuthModeStrategyType.DEFAULT;
        this.userProvidedConfiguration = dataStoreConfiguration;
        boolean z = dataStoreConfiguration != null && dataStoreConfiguration.getDoSyncRetry().booleanValue();
        this.isSyncRetryEnabled = z;
        this.orchestrator = new Orchestrator(modelProvider, schemaRegistry, sQLiteStorageAdapterForModels, AppSyncClient.via(apiCategory), new DataStoreConfigurationProvider() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda27
            @Override // com.amplifyframework.datastore.DataStoreConfigurationProvider
            public final DataStoreConfiguration getConfiguration() {
                return this.f$0.lambda$new$0();
            }
        }, new Supplier() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda28
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return AWSDataStorePlugin.lambda$new$1(apiCategory);
            }
        }, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DataStoreConfiguration lambda$new$0() throws DataStoreException {
        return this.pluginConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Orchestrator.State lambda$new$1(ApiCategory apiCategory) {
        return apiCategory.getPlugins().isEmpty() ? Orchestrator.State.LOCAL_ONLY : Orchestrator.State.SYNC_VIA_API;
    }

    private AWSDataStorePlugin(@NonNull Builder builder) throws DataStoreException {
        SchemaRegistry schemaRegistryInstance;
        ModelProvider modelProviderLocate;
        LocalStorageAdapter localStorageAdapterForModels;
        if (builder.schemaRegistry == null) {
            schemaRegistryInstance = SchemaRegistry.instance();
        } else {
            schemaRegistryInstance = builder.schemaRegistry;
        }
        SchemaRegistry schemaRegistry = schemaRegistryInstance;
        if (builder.modelProvider == null) {
            modelProviderLocate = ModelProviderLocator.locate();
        } else {
            modelProviderLocate = builder.modelProvider;
        }
        ModelProvider modelProvider = modelProviderLocate;
        AuthModeStrategyType authModeStrategyType = builder.authModeStrategy == null ? AuthModeStrategyType.DEFAULT : builder.authModeStrategy;
        this.authModeStrategy = authModeStrategyType;
        boolean z = builder.isSyncRetryEnabled;
        this.isSyncRetryEnabled = z;
        final ApiCategory apiCategory = builder.apiCategory == null ? Amplify.API : builder.apiCategory;
        this.userProvidedConfiguration = builder.dataStoreConfiguration;
        if (builder.storageAdapter == null) {
            localStorageAdapterForModels = SQLiteStorageAdapter.forModels(schemaRegistry, modelProvider);
        } else {
            localStorageAdapterForModels = builder.storageAdapter;
        }
        LocalStorageAdapter localStorageAdapter = localStorageAdapterForModels;
        this.sqliteStorageAdapter = localStorageAdapter;
        this.categoryInitializationsPending = new CountDownLatch(1);
        this.orchestrator = new Orchestrator(modelProvider, schemaRegistry, localStorageAdapter, AppSyncClient.via(apiCategory, authModeStrategyType), new DataStoreConfigurationProvider() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.datastore.DataStoreConfigurationProvider
            public final DataStoreConfiguration getConfiguration() {
                return this.f$0.lambda$new$2();
            }
        }, new Supplier() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda9
            @Override // androidx.core.util.Supplier
            public final Object get() {
                return AWSDataStorePlugin.lambda$new$3(apiCategory);
            }
        }, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ DataStoreConfiguration lambda$new$2() throws DataStoreException {
        return this.pluginConfiguration;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Orchestrator.State lambda$new$3(ApiCategory apiCategory) {
        return apiCategory.getPlugins().isEmpty() ? Orchestrator.State.LOCAL_ONLY : Orchestrator.State.SYNC_VIA_API;
    }

    public AWSDataStorePlugin() throws DataStoreException {
        this(builder());
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @Deprecated
    public AWSDataStorePlugin(@NonNull DataStoreConfiguration dataStoreConfiguration) throws DataStoreException {
        ModelProvider modelProviderLocate = ModelProviderLocator.locate();
        SchemaRegistry schemaRegistryInstance = SchemaRegistry.instance();
        ApiCategory apiCategory = Amplify.API;
        Objects.requireNonNull(dataStoreConfiguration);
        this(modelProviderLocate, schemaRegistryInstance, apiCategory, dataStoreConfiguration);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    @Deprecated
    public AWSDataStorePlugin(@NonNull ModelProvider modelProvider) {
        this(modelProvider, Amplify.API, null);
        Objects.requireNonNull(modelProvider);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    @VisibleForTesting
    @Deprecated
    AWSDataStorePlugin(@NonNull ModelProvider modelProvider, @NonNull ApiCategory apiCategory, @Nullable DataStoreConfiguration dataStoreConfiguration) {
        Objects.requireNonNull(modelProvider);
        SchemaRegistry schemaRegistryInstance = SchemaRegistry.instance();
        Objects.requireNonNull(apiCategory);
        this(modelProvider, schemaRegistryInstance, apiCategory, dataStoreConfiguration);
    }

    @Override // com.amplifyframework.core.plugin.Plugin
    public void configure(@NonNull JSONObject jSONObject, @NonNull Context context) throws DataStoreException {
        try {
            this.pluginConfiguration = DataStoreConfiguration.builder(jSONObject, this.userProvidedConfiguration).build();
            Amplify.Hub.subscribe(HubChannel.forCategoryType(getCategoryType()), new HubEventFilter() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda33
                @Override // com.amplifyframework.hub.HubEventFilter
                public final boolean filter(HubEvent hubEvent) {
                    return AWSDataStorePlugin.lambda$configure$4(hubEvent);
                }
            }, new HubSubscriber() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda34
                @Override // com.amplifyframework.hub.HubSubscriber
                public final void onEvent(HubEvent hubEvent) {
                    this.f$0.lambda$configure$5(hubEvent);
                }
            });
        } catch (DataStoreException e) {
            throw new DataStoreException("There was an issue configuring the plugin from the amplifyconfiguration.json", e, "Check the attached exception for more details and be sure you are only calling Amplify.configure once");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$configure$4(HubEvent hubEvent) {
        return InitializationStatus.SUCCEEDED.toString().equals(hubEvent.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$configure$5(HubEvent hubEvent) {
        this.categoryInitializationsPending.countDown();
    }

    @Override // com.amplifyframework.datastore.DataStorePlugin, com.amplifyframework.core.plugin.Plugin
    @WorkerThread
    public void initialize(@NonNull Context context) throws AmplifyException {
        try {
            initializeStorageAdapter(context).blockingAwait(LIFECYCLE_TIMEOUT_MS, TimeUnit.MILLISECONDS);
        } catch (Throwable th) {
            throw new AmplifyException("Failed to initialize the local storage adapter for the DataStore plugin.", th, AmplifyException.TODO_RECOVERY_SUGGESTION);
        }
    }

    @WorkerThread
    private Completable initializeStorageAdapter(final Context context) {
        return Completable.defer(new io.reactivex.rxjava3.functions.Supplier() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return this.f$0.lambda$initializeStorageAdapter$8(context);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$initializeStorageAdapter$8(final Context context) throws Throwable {
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$null$7(context, completableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$7(Context context, final CompletableEmitter completableEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.sqliteStorageAdapter;
        Consumer<List<ModelSchema>> consumer = new Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda13
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                completableEmitter.onComplete();
            }
        };
        completableEmitter.getClass();
        localStorageAdapter.initialize(context, consumer, new AWSDataStorePlugin$$ExternalSyntheticLambda11(completableEmitter), this.pluginConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$waitForInitialization$9() throws Throwable {
        this.categoryInitializationsPending.await();
    }

    private Completable waitForInitialization() {
        return Completable.fromAction(new Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda31
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                this.f$0.lambda$waitForInitialization$9();
            }
        }).timeout(LIFECYCLE_TIMEOUT_MS, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.io()).doOnError(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda32
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                AWSDataStorePlugin.lambda$waitForInitialization$10((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$waitForInitialization$10(Throwable th) throws Throwable {
        LOG.error("DataStore initialization timed out.", th);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public void start(@NonNull com.amplifyframework.core.Action action, @NonNull final Consumer<DataStoreException> consumer) {
        Completable completableSubscribeOn = waitForInitialization().andThen(this.orchestrator.start()).subscribeOn(Schedulers.io());
        action.getClass();
        completableSubscribeOn.subscribe(new AWSDataStorePlugin$$ExternalSyntheticLambda20(action), new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda36
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                AWSDataStorePlugin.lambda$start$11(consumer, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$start$11(Consumer consumer, Throwable th) throws Throwable {
        consumer.accept(new DataStoreException("Failed to start DataStore.", th, "Retry."));
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public void stop(@NonNull com.amplifyframework.core.Action action, @NonNull final Consumer<DataStoreException> consumer) {
        Completable completableSubscribeOn = waitForInitialization().andThen(this.orchestrator.stop()).subscribeOn(Schedulers.io());
        action.getClass();
        completableSubscribeOn.subscribe(new AWSDataStorePlugin$$ExternalSyntheticLambda20(action), new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda25
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                AWSDataStorePlugin.lambda$stop$12(consumer, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$stop$12(Consumer consumer, Throwable th) throws Throwable {
        consumer.accept(new DataStoreException("Failed to stop DataStore.", th, "Retry."));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$clear$15(com.amplifyframework.core.Action action, final Consumer consumer) {
        Completable completableSubscribeOn = Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda19
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$null$13(completableEmitter);
            }
        }).subscribeOn(Schedulers.io());
        action.getClass();
        completableSubscribeOn.subscribe(new AWSDataStorePlugin$$ExternalSyntheticLambda20(action), new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda21
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                AWSDataStorePlugin.lambda$null$14(consumer, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$13(CompletableEmitter completableEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.sqliteStorageAdapter;
        completableEmitter.getClass();
        localStorageAdapter.clear(new AWSDataStorePlugin$$ExternalSyntheticLambda10(completableEmitter), new AWSDataStorePlugin$$ExternalSyntheticLambda11(completableEmitter));
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public void clear(@NonNull final com.amplifyframework.core.Action action, @NonNull final Consumer<DataStoreException> consumer) {
        stop(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$clear$15(action, consumer);
            }
        }, consumer);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$14(Consumer consumer, Throwable th) throws Throwable {
        consumer.accept(new DataStoreException("Clear operation failed", th, AmplifyException.REPORT_BUG_TO_AWS_SUGGESTION));
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void save(@NonNull T t, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        save(t, QueryPredicates.all(), consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$save$17(Model model, QueryPredicate queryPredicate, final Consumer consumer, final Consumer consumer2) {
        this.sqliteStorageAdapter.save(model, StorageItemChange.Initiator.DATA_STORE_API, queryPredicate, new Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda15
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AWSDataStorePlugin.lambda$null$16(consumer, consumer2, (StorageItemChange) obj);
            }
        }, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void save(@NonNull final T t, @NonNull final QueryPredicate queryPredicate, @NonNull final Consumer<DataStoreItemChange<T>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda22
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$save$17(t, queryPredicate, consumer, consumer2);
            }
        }, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$16(Consumer consumer, Consumer consumer2, StorageItemChange storageItemChange) {
        try {
            consumer.accept(ItemChangeMapper.map(storageItemChange));
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void delete(@NonNull T t, @NonNull Consumer<DataStoreItemChange<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        delete(t, QueryPredicates.all(), consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$19(Model model, QueryPredicate queryPredicate, final Consumer consumer, final Consumer consumer2) {
        this.sqliteStorageAdapter.delete(model, StorageItemChange.Initiator.DATA_STORE_API, queryPredicate, (Consumer<StorageItemChange<Model>>) new Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda16
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AWSDataStorePlugin.lambda$null$18(consumer, consumer2, (StorageItemChange) obj);
            }
        }, (Consumer<DataStoreException>) consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void delete(@NonNull final T t, @NonNull final QueryPredicate queryPredicate, @NonNull final Consumer<DataStoreItemChange<T>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda12
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$delete$19(t, queryPredicate, consumer, consumer2);
            }
        }, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$18(Consumer consumer, Consumer consumer2, StorageItemChange storageItemChange) {
        try {
            consumer.accept(ItemChangeMapper.map(storageItemChange));
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$20(Class cls, QueryPredicate queryPredicate, com.amplifyframework.core.Action action, Consumer consumer) {
        this.sqliteStorageAdapter.delete(cls, StorageItemChange.Initiator.DATA_STORE_API, queryPredicate, action, (Consumer<DataStoreException>) consumer);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void delete(@NonNull final Class<T> cls, @NonNull final QueryPredicate queryPredicate, @NonNull final com.amplifyframework.core.Action action, @NonNull final Consumer<DataStoreException> consumer) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda35
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$delete$20(cls, queryPredicate, action, consumer);
            }
        }, consumer);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void query(@NonNull final Class<T> cls, @NonNull final Consumer<Iterator<T>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda30
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$query$21(cls, consumer, consumer2);
            }
        }, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$21(Class cls, Consumer consumer, Consumer consumer2) {
        this.sqliteStorageAdapter.query(cls, Where.matchesAll(), consumer, (Consumer<DataStoreException>) consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$22(String str, QueryOptions queryOptions, Consumer consumer, Consumer consumer2) {
        this.sqliteStorageAdapter.query(str, queryOptions, (Consumer<Iterator<? extends Model>>) consumer, (Consumer<DataStoreException>) consumer2);
    }

    public void query(@NonNull final String str, @NonNull final QueryOptions queryOptions, @NonNull final Consumer<Iterator<? extends Model>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda24
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$query$22(str, queryOptions, consumer, consumer2);
            }
        }, consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void query(@NonNull Class<T> cls, @NonNull QueryPredicate queryPredicate, @NonNull Consumer<Iterator<T>> consumer, @NonNull Consumer<DataStoreException> consumer2) {
        query(cls, Where.matches(queryPredicate), consumer, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$23(Class cls, QueryOptions queryOptions, Consumer consumer, Consumer consumer2) {
        this.sqliteStorageAdapter.query(cls, queryOptions, consumer, (Consumer<DataStoreException>) consumer2);
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void query(@NonNull final Class<T> cls, @NonNull final QueryOptions queryOptions, @NonNull final Consumer<Iterator<T>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda4
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$query$23(cls, queryOptions, consumer, consumer2);
            }
        }, consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observe$25(Consumer consumer, final Consumer consumer2, final Consumer consumer3, com.amplifyframework.core.Action action) {
        consumer.accept(this.sqliteStorageAdapter.observe(new Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda6
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AWSDataStorePlugin.lambda$null$24(consumer2, consumer3, (StorageItemChange) obj);
            }
        }, consumer3, action));
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public void observe(@NonNull final Consumer<Cancelable> consumer, @NonNull final Consumer<DataStoreItemChange<? extends Model>> consumer2, @NonNull final Consumer<DataStoreException> consumer3, @NonNull final com.amplifyframework.core.Action action) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$observe$25(consumer, consumer2, consumer3, action);
            }
        }, consumer3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$24(Consumer consumer, Consumer consumer2, StorageItemChange storageItemChange) {
        try {
            consumer.accept(ItemChangeMapper.map(storageItemChange));
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void observeQuery(@NonNull final Class<T> cls, @NonNull final ObserveQueryOptions observeQueryOptions, @NonNull final Consumer<Cancelable> consumer, @NonNull final Consumer<DataStoreQuerySnapshot<T>> consumer2, @NonNull final Consumer<DataStoreException> consumer3, @NonNull final com.amplifyframework.core.Action action) {
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer3);
        Objects.requireNonNull(action);
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$observeQuery$26(cls, observeQueryOptions, consumer, consumer2, consumer3, action);
            }
        }, consumer3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observeQuery$26(Class cls, ObserveQueryOptions observeQueryOptions, Consumer consumer, Consumer consumer2, Consumer consumer3, com.amplifyframework.core.Action action) {
        this.sqliteStorageAdapter.observeQuery(cls, observeQueryOptions, consumer, consumer2, consumer3, action);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observe$28(Consumer consumer, final Class cls, final Consumer consumer2, final Consumer consumer3, com.amplifyframework.core.Action action) {
        consumer.accept(this.sqliteStorageAdapter.observe(new Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AWSDataStorePlugin.lambda$null$27(cls, consumer2, consumer3, (StorageItemChange) obj);
            }
        }, consumer3, action));
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void observe(@NonNull final Class<T> cls, @NonNull final Consumer<Cancelable> consumer, @NonNull final Consumer<DataStoreItemChange<T>> consumer2, @NonNull final Consumer<DataStoreException> consumer3, @NonNull final com.amplifyframework.core.Action action) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda26
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$observe$28(consumer, cls, consumer2, consumer3, action);
            }
        }, consumer3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$27(Class cls, Consumer consumer, Consumer consumer2, StorageItemChange storageItemChange) {
        try {
            if (storageItemChange.modelSchema().getName().equals(cls.getSimpleName())) {
                consumer.accept(ItemChangeMapper.map(storageItemChange));
            }
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observe$30(Consumer consumer, final String str, final Consumer consumer2, final Consumer consumer3, com.amplifyframework.core.Action action) {
        consumer.accept(this.sqliteStorageAdapter.observe(new Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda37
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AWSDataStorePlugin.lambda$null$29(str, consumer2, consumer3, (StorageItemChange) obj);
            }
        }, consumer3, action));
    }

    public void observe(@NonNull final String str, @NonNull final Consumer<Cancelable> consumer, @NonNull final Consumer<DataStoreItemChange<? extends Model>> consumer2, @NonNull final Consumer<DataStoreException> consumer3, @NonNull final com.amplifyframework.core.Action action) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda29
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$observe$30(consumer, str, consumer2, consumer3, action);
            }
        }, consumer3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$29(String str, Consumer consumer, Consumer consumer2, StorageItemChange storageItemChange) {
        try {
            if (storageItemChange.modelSchema().getModelClass().equals(SerializedModel.class) && ((SerializedModel) storageItemChange.item()).getModelName().equals(str)) {
                consumer.accept(ItemChangeMapper.map(storageItemChange));
            }
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observe$32(Consumer consumer, final Class cls, final Serializable serializable, final Consumer consumer2, final Consumer consumer3, com.amplifyframework.core.Action action) {
        consumer.accept(this.sqliteStorageAdapter.observe(new Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda14
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AWSDataStorePlugin.lambda$null$31(cls, serializable, consumer2, consumer3, (StorageItemChange) obj);
            }
        }, consumer3, action));
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void observe(@NonNull final Class<T> cls, @NonNull final Serializable serializable, @NonNull final Consumer<Cancelable> consumer, @NonNull final Consumer<DataStoreItemChange<T>> consumer2, @NonNull final Consumer<DataStoreException> consumer3, @NonNull final com.amplifyframework.core.Action action) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda18
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$observe$32(consumer, cls, serializable, consumer2, consumer3, action);
            }
        }, consumer3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$31(Class cls, Serializable serializable, Consumer consumer, Consumer consumer2, StorageItemChange storageItemChange) {
        try {
            if (storageItemChange.modelSchema().getName().equals(cls.getSimpleName()) && storageItemChange.item().getPrimaryKeyString().equals(ModelIdentifier.Helper.getUniqueKey(serializable))) {
                consumer.accept(ItemChangeMapper.map(storageItemChange));
            }
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observe$34(Consumer consumer, final Class cls, final QueryPredicate queryPredicate, final Consumer consumer2, final Consumer consumer3, com.amplifyframework.core.Action action) {
        consumer.accept(this.sqliteStorageAdapter.observe(new Consumer() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda23
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                AWSDataStorePlugin.lambda$null$33(cls, queryPredicate, consumer2, consumer3, (StorageItemChange) obj);
            }
        }, consumer3, action));
    }

    @Override // com.amplifyframework.datastore.DataStoreCategoryBehavior
    public <T extends Model> void observe(@NonNull final Class<T> cls, @NonNull final QueryPredicate queryPredicate, @NonNull final Consumer<Cancelable> consumer, @NonNull final Consumer<DataStoreItemChange<T>> consumer2, @NonNull final Consumer<DataStoreException> consumer3, @NonNull final com.amplifyframework.core.Action action) {
        start(new com.amplifyframework.core.Action() { // from class: com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda17
            @Override // com.amplifyframework.core.Action
            public final void call() {
                this.f$0.lambda$observe$34(consumer, cls, queryPredicate, consumer2, consumer3, action);
            }
        }, consumer3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$33(Class cls, QueryPredicate queryPredicate, Consumer consumer, Consumer consumer2, StorageItemChange storageItemChange) {
        try {
            if (storageItemChange.modelSchema().getName().equals(cls.getSimpleName()) && queryPredicate.evaluate(storageItemChange.item())) {
                consumer.accept(ItemChangeMapper.map(storageItemChange));
            }
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private ApiCategory apiCategory;
        private AuthModeStrategyType authModeStrategy;
        private DataStoreConfiguration dataStoreConfiguration;
        private boolean isSyncRetryEnabled;
        private ModelProvider modelProvider;
        private SchemaRegistry schemaRegistry;
        private LocalStorageAdapter storageAdapter;

        private Builder() {
        }

        public Builder dataStoreConfiguration(DataStoreConfiguration dataStoreConfiguration) {
            this.dataStoreConfiguration = dataStoreConfiguration;
            return this;
        }

        public Builder modelProvider(ModelProvider modelProvider) {
            this.modelProvider = modelProvider;
            return this;
        }

        public Builder schemaRegistry(SchemaRegistry schemaRegistry) {
            this.schemaRegistry = schemaRegistry;
            return this;
        }

        @VisibleForTesting
        Builder apiCategory(ApiCategory apiCategory) {
            this.apiCategory = apiCategory;
            return this;
        }

        @VisibleForTesting
        Builder storageAdapter(LocalStorageAdapter localStorageAdapter) {
            this.storageAdapter = localStorageAdapter;
            return this;
        }

        public Builder authModeStrategy(AuthModeStrategyType authModeStrategyType) {
            this.authModeStrategy = authModeStrategyType;
            return this;
        }

        public Builder isSyncRetryEnabled(Boolean bool) {
            this.isSyncRetryEnabled = bool.booleanValue();
            return this;
        }

        public AWSDataStorePlugin build() throws DataStoreException {
            return new AWSDataStorePlugin(this);
        }
    }
}

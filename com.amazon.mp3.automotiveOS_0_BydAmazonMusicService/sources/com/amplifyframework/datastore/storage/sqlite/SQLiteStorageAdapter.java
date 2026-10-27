package com.amplifyframework.datastore.storage.sqlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.core.util.ObjectsCompat;
import com.amazonaws.mobileconnectors.remoteconfiguration.Attributes;
import com.amplifyframework.AmplifyException;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.CustomTypeField;
import com.amplifyframework.core.model.CustomTypeSchema;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelAssociation;
import com.amplifyframework.core.model.ModelField;
import com.amplifyframework.core.model.ModelProvider;
import com.amplifyframework.core.model.ModelSchema;
import com.amplifyframework.core.model.SchemaRegistry;
import com.amplifyframework.core.model.SerializedCustomType;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicateOperation;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda20;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreQuerySnapshot;
import com.amplifyframework.datastore.model.CompoundModelProvider;
import com.amplifyframework.datastore.model.SystemModelsProviderFactory;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.datastore.storage.StorageItemChange;
import com.amplifyframework.datastore.storage.sqlite.adapter.SQLiteTable;
import com.amplifyframework.datastore.storage.sqlite.migrations.ModelMigrations;
import com.amplifyframework.logging.Logger;
import com.amplifyframework.util.GsonFactory;
import com.amplifyframework.util.Immutable;
import com.google.gson.Gson;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.subjects.PublishSubject;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public final class SQLiteStorageAdapter implements LocalStorageAdapter {
    private static final int DATABASE_VERSION = 1;

    @VisibleForTesting
    static final String DEFAULT_DATABASE_NAME = "AmplifyDatastore.db";
    private static final int THREAD_POOL_SIZE_MULTIPLIER = 20;
    private Context context;
    private DataStoreConfiguration dataStoreConfiguration;
    private SQLiteDatabase databaseConnectionHandle;
    private final String databaseName;
    private final Gson gson;
    private final Subject<StorageItemChange<? extends Model>> itemChangeSubject;
    private final ModelProvider modelsProvider;
    private final SchemaRegistry schemaRegistry;
    private SQLCommandFactory sqlCommandFactory;
    private SQLCommandProcessor sqlCommandProcessor;
    private SqlQueryProcessor sqlQueryProcessor;
    private SQLiteModelTree sqliteModelTree;
    private SQLiteStorageHelper sqliteStorageHelper;
    private SyncStatus syncStatus;
    private ExecutorService threadPool;
    private final CompositeDisposable toBeDisposed;
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private static final long THREAD_POOL_TERMINATE_TIMEOUT = TimeUnit.SECONDS.toMillis(5);

    private SQLiteStorageAdapter(SchemaRegistry schemaRegistry, ModelProvider modelProvider, ModelProvider modelProvider2) {
        this(schemaRegistry, modelProvider, modelProvider2, DEFAULT_DATABASE_NAME);
    }

    private SQLiteStorageAdapter(SchemaRegistry schemaRegistry, ModelProvider modelProvider, ModelProvider modelProvider2, String str) {
        this.schemaRegistry = schemaRegistry;
        this.modelsProvider = CompoundModelProvider.of(modelProvider2, modelProvider);
        this.gson = GsonFactory.instance();
        this.itemChangeSubject = PublishSubject.create().toSerialized();
        this.toBeDisposed = new CompositeDisposable();
        this.databaseName = str;
    }

    @NonNull
    public static SQLiteStorageAdapter forModels(@NonNull SchemaRegistry schemaRegistry, @NonNull ModelProvider modelProvider) {
        Objects.requireNonNull(modelProvider);
        return new SQLiteStorageAdapter(schemaRegistry, modelProvider, SystemModelsProviderFactory.create());
    }

    @NonNull
    static SQLiteStorageAdapter forModels(@NonNull SchemaRegistry schemaRegistry, @NonNull ModelProvider modelProvider, @NonNull String str) {
        Objects.requireNonNull(modelProvider);
        return new SQLiteStorageAdapter(schemaRegistry, modelProvider, SystemModelsProviderFactory.create(), str);
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public synchronized void initialize(@NonNull final Context context, @NonNull final Consumer<List<ModelSchema>> consumer, @NonNull final Consumer<DataStoreException> consumer2, @NonNull final DataStoreConfiguration dataStoreConfiguration) {
        Objects.requireNonNull(context);
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer2);
        ExecutorService executorServiceNewFixedThreadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 20);
        this.threadPool = executorServiceNewFixedThreadPool;
        this.context = context;
        this.dataStoreConfiguration = dataStoreConfiguration;
        executorServiceNewFixedThreadPool.submit(new Runnable() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda12
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$initialize$2(context, dataStoreConfiguration, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initialize$2(Context context, DataStoreConfiguration dataStoreConfiguration, final Consumer consumer, final Consumer consumer2) {
        try {
            this.schemaRegistry.clear();
            this.schemaRegistry.register(this.modelsProvider.modelSchemas(), this.modelsProvider.customTypeSchemas());
            this.sqlCommandFactory = new SQLiteCommandFactory(this.schemaRegistry, this.gson);
            SQLiteStorageHelper sQLiteStorageHelper = SQLiteStorageHelper.getInstance(context, this.databaseName, 1, getCreateCommands(this.modelsProvider.modelNames()));
            this.sqliteStorageHelper = sQLiteStorageHelper;
            this.databaseConnectionHandle = sQLiteStorageHelper.getWritableDatabase();
            this.sqliteModelTree = new SQLiteModelTree(this.schemaRegistry, this.databaseConnectionHandle);
            this.sqlCommandProcessor = new SQLCommandProcessor(this.databaseConnectionHandle);
            SqlQueryProcessor sqlQueryProcessor = new SqlQueryProcessor(this.sqlCommandProcessor, this.sqlCommandFactory, this.schemaRegistry);
            this.sqlQueryProcessor = sqlQueryProcessor;
            this.syncStatus = new SyncStatus(sqlQueryProcessor, dataStoreConfiguration);
            this.toBeDisposed.add(updateModels().subscribe(new Action() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda3
                @Override // io.reactivex.rxjava3.functions.Action
                public final void run() throws Throwable {
                    this.f$0.lambda$null$0(consumer);
                }
            }, new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda4
                @Override // io.reactivex.rxjava3.functions.Consumer
                public final void accept(Object obj) throws Throwable {
                    SQLiteStorageAdapter.lambda$null$1(consumer2, (Throwable) obj);
                }
            }));
        } catch (Exception e) {
            consumer2.accept(new DataStoreException("Error in initializing the SQLiteStorageAdapter", e, "See attached exception"));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$0(Consumer consumer) throws Throwable {
        consumer.accept(Immutable.of(new ArrayList(this.schemaRegistry.getModelSchemaMap().values())));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$1(Consumer consumer, Throwable th) throws Throwable {
        consumer.accept(new DataStoreException("Error in initializing the SQLiteStorageAdapter", th, AmplifyException.TODO_RECOVERY_SUGGESTION));
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public <T extends Model> void save(@NonNull final T t, @NonNull final StorageItemChange.Initiator initiator, @NonNull final QueryPredicate queryPredicate, @NonNull final Consumer<StorageItemChange<T>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(initiator);
        Objects.requireNonNull(queryPredicate);
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer2);
        this.threadPool.submit(new Runnable() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$save$3(t, queryPredicate, initiator, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$save$3(Model model, QueryPredicate queryPredicate, StorageItemChange.Initiator initiator, Consumer consumer, Consumer consumer2) {
        StorageItemChange.Type type;
        try {
            ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(model.getModelName());
            SerializedModel serializedModelCreate = null;
            if (this.sqlQueryProcessor.modelExists(model, QueryPredicates.all())) {
                type = StorageItemChange.Type.UPDATE;
                if (!QueryPredicates.all().equals(queryPredicate) && !this.sqlQueryProcessor.modelExists(model, queryPredicate)) {
                    throw new DataStoreException("Save failed because condition did not match existing model instance.", "The save will continue to fail until the model instance is updated.");
                }
                if (initiator == StorageItemChange.Initiator.DATA_STORE_API) {
                    serializedModelCreate = SerializedModel.create(model, modelSchemaForModelClass);
                }
            } else {
                if (!QueryPredicates.all().equals(queryPredicate)) {
                    throw new DataStoreException("Conditional update must be performed against an already existing data. Insertion is not permitted while using a predicate.", "Please save without specifying a predicate.");
                }
                type = StorageItemChange.Type.CREATE;
            }
            writeData(model, type);
            StorageItemChange.Builder builderItem = StorageItemChange.builder().item(model);
            if (serializedModelCreate == null) {
                serializedModelCreate = SerializedModel.create(model, modelSchemaForModelClass);
            }
            StorageItemChange<? extends Model> storageItemChangeBuild = builderItem.patchItem(serializedModelCreate).modelSchema(modelSchemaForModelClass).type(type).predicate(queryPredicate).initiator(initiator).build();
            this.itemChangeSubject.onNext(storageItemChangeBuild);
            consumer.accept(storageItemChangeBuild);
        } catch (DataStoreException e) {
            consumer2.accept(e);
        } catch (Exception e2) {
            consumer2.accept(new DataStoreException("Error in saving the model: " + (model.getModelName() + "[primaryKey =" + model.getPrimaryKeyString() + "]"), e2, "See attached exception for details."));
        }
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public <T extends Model> void query(@NonNull final Class<T> cls, @NonNull final QueryOptions queryOptions, @NonNull final Consumer<Iterator<T>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        Objects.requireNonNull(cls);
        Objects.requireNonNull(queryOptions);
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer2);
        this.threadPool.submit(new Runnable() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$query$4(cls, queryOptions, consumer2, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$4(Class cls, QueryOptions queryOptions, Consumer consumer, Consumer consumer2) {
        consumer2.accept(this.sqlQueryProcessor.queryOfflineData(cls, queryOptions, consumer).iterator());
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public void query(@NonNull final String str, @NonNull final QueryOptions queryOptions, @NonNull final Consumer<Iterator<? extends Model>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        Objects.requireNonNull(str);
        Objects.requireNonNull(queryOptions);
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer2);
        this.threadPool.submit(new Runnable() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda13
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$query$5(str, queryOptions, consumer2, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$5(String str, QueryOptions queryOptions, Consumer consumer, Consumer consumer2) {
        ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(str);
        try {
            Cursor cursorRawQuery = this.sqlCommandProcessor.rawQuery(this.sqlCommandFactory.queryFor(modelSchemaForModelClass, queryOptions));
            try {
                LOG.debug("Querying item for: " + str);
                ArrayList arrayList = new ArrayList();
                SQLiteModelFieldTypeConverter sQLiteModelFieldTypeConverter = new SQLiteModelFieldTypeConverter(modelSchemaForModelClass, this.schemaRegistry, this.gson);
                if (cursorRawQuery == null) {
                    consumer.accept(new DataStoreException("Error in getting a cursor to the table for class: " + str, AmplifyException.TODO_RECOVERY_SUGGESTION));
                    if (cursorRawQuery != null) {
                        cursorRawQuery.close();
                        return;
                    }
                    return;
                }
                if (cursorRawQuery.moveToFirst()) {
                    do {
                        arrayList.add(createSerializedModel(modelSchemaForModelClass, sQLiteModelFieldTypeConverter.buildMapForModel(cursorRawQuery)));
                    } while (cursorRawQuery.moveToNext());
                }
                consumer2.accept(arrayList.iterator());
                cursorRawQuery.close();
            } finally {
            }
        } catch (Exception e) {
            consumer.accept(new DataStoreException("Error in querying the model.", e, "See attached exception for details."));
        }
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public <T extends Model> void delete(@NonNull final T t, @NonNull final StorageItemChange.Initiator initiator, @NonNull final QueryPredicate queryPredicate, @NonNull final Consumer<StorageItemChange<T>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        Objects.requireNonNull(t);
        Objects.requireNonNull(initiator);
        Objects.requireNonNull(queryPredicate);
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer2);
        this.threadPool.submit(new Runnable() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$delete$6(t, consumer, queryPredicate, initiator, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$6(Model model, Consumer consumer, QueryPredicate queryPredicate, StorageItemChange.Initiator initiator, Consumer consumer2) {
        try {
            String modelName = model.getModelName();
            ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(modelName);
            if (!this.sqlQueryProcessor.modelExists(model, QueryPredicates.all())) {
                LOG.verbose(modelName + " model with id = " + model.getPrimaryKeyString() + " does not exist.");
                consumer.accept(StorageItemChange.builder().item(model).patchItem(SerializedModel.create(model, modelSchemaForModelClass)).modelSchema(modelSchemaForModelClass).type(StorageItemChange.Type.DELETE).predicate(queryPredicate).initiator(initiator).build());
                return;
            }
            if (!QueryPredicates.all().equals(queryPredicate) && !this.sqlQueryProcessor.modelExists(model, queryPredicate)) {
                throw new DataStoreException("Deletion failed because condition did not match existing model instance.", "The deletion will continue to fail until the model instance is updated.");
            }
            List<Model> listDescendantsOf = this.sqliteModelTree.descendantsOf(Collections.singleton(model));
            writeData(model, StorageItemChange.Type.DELETE);
            for (Model model2 : listDescendantsOf) {
                ModelSchema modelSchemaForModelClass2 = this.schemaRegistry.getModelSchemaForModelClass(model2.getModelName());
                this.itemChangeSubject.onNext(StorageItemChange.builder().item(model2).patchItem(SerializedModel.create(model2, modelSchemaForModelClass2)).modelSchema(modelSchemaForModelClass2).type(StorageItemChange.Type.DELETE).predicate(QueryPredicates.all()).initiator(initiator).build());
            }
            StorageItemChange<? extends Model> storageItemChangeBuild = StorageItemChange.builder().item(model).patchItem(SerializedModel.create(model, modelSchemaForModelClass)).modelSchema(modelSchemaForModelClass).type(StorageItemChange.Type.DELETE).predicate(queryPredicate).initiator(initiator).build();
            this.itemChangeSubject.onNext(storageItemChangeBuild);
            consumer.accept(storageItemChangeBuild);
        } catch (DataStoreException e) {
            consumer2.accept(e);
        } catch (Exception e2) {
            consumer2.accept(new DataStoreException("Error in deleting the model.", e2, "See attached exception for details."));
        }
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public <T extends Model> void delete(@NonNull final Class<T> cls, @NonNull final StorageItemChange.Initiator initiator, @NonNull final QueryPredicate queryPredicate, @NonNull final com.amplifyframework.core.Action action, @NonNull final Consumer<DataStoreException> consumer) {
        Objects.requireNonNull(cls);
        Objects.requireNonNull(initiator);
        Objects.requireNonNull(queryPredicate);
        Objects.requireNonNull(action);
        Objects.requireNonNull(consumer);
        this.threadPool.submit(new Runnable() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda11
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$delete$7(cls, queryPredicate, initiator, action, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$7(Class cls, QueryPredicate queryPredicate, StorageItemChange.Initiator initiator, com.amplifyframework.core.Action action, Consumer consumer) {
        ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(cls);
        try {
            Cursor cursorRawQuery = this.sqlCommandProcessor.rawQuery(this.sqlCommandFactory.queryFor(modelSchemaForModelClass, Where.matches(queryPredicate)));
            try {
                SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchemaForModelClass);
                List<String> primaryIndexFields = modelSchemaForModelClass.getPrimaryIndexFields();
                ArrayList arrayList = new ArrayList();
                if (cursorRawQuery != null && cursorRawQuery.moveToFirst()) {
                    do {
                        HashMap map = new HashMap();
                        for (String str : primaryIndexFields) {
                            map.put(str, cursorRawQuery.getString(cursorRawQuery.getColumnIndexOrThrow(sQLiteTableFromSchema.getName() + Attributes.PREDEFINED_ATTRIBUTE_PREFIX + str)));
                        }
                        arrayList.add((Model) this.gson.fromJson(this.gson.toJson(map), cls));
                    } while (cursorRawQuery.moveToNext());
                }
                ArrayList<Model> arrayList2 = new ArrayList(arrayList);
                arrayList2.addAll(this.sqliteModelTree.descendantsOf(arrayList));
                this.sqlCommandProcessor.execute(this.sqlCommandFactory.deleteFor(modelSchemaForModelClass, queryPredicate));
                for (Model model : arrayList2) {
                    ModelSchema modelSchemaForModelClass2 = this.schemaRegistry.getModelSchemaForModelClass(model.getModelName());
                    this.itemChangeSubject.onNext(StorageItemChange.builder().item(model).patchItem(SerializedModel.create(model, modelSchemaForModelClass2)).modelSchema(modelSchemaForModelClass2).type(StorageItemChange.Type.DELETE).predicate(QueryPredicates.all()).initiator(initiator).build());
                }
                action.call();
                if (cursorRawQuery != null) {
                    cursorRawQuery.close();
                }
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    if (cursorRawQuery != null) {
                        try {
                            cursorRawQuery.close();
                        } catch (Throwable th3) {
                            th.addSuppressed(th3);
                        }
                    }
                    throw th2;
                }
            }
        } catch (DataStoreException e) {
            consumer.accept(e);
        } catch (Exception e2) {
            consumer.accept(new DataStoreException("Error in deleting models.", e2, "See attached exception for details."));
        }
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    @NonNull
    public Cancelable observe(@NonNull Consumer<StorageItemChange<? extends Model>> consumer, @NonNull final Consumer<DataStoreException> consumer2, @NonNull com.amplifyframework.core.Action action) {
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer2);
        Objects.requireNonNull(action);
        final Disposable disposableSubscribe = this.itemChangeSubject.subscribe(new ObserveQueryExecutor$$ExternalSyntheticLambda4(consumer), new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                SQLiteStorageAdapter.lambda$observe$8(consumer2, (Throwable) obj);
            }
        }, new AWSDataStorePlugin$$ExternalSyntheticLambda20(action));
        disposableSubscribe.getClass();
        return new Cancelable() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.core.async.Cancelable
            public final void cancel() {
                disposableSubscribe.dispose();
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$observe$8(Consumer consumer, Throwable th) throws Throwable {
        if (th instanceof DataStoreException) {
            consumer.accept((DataStoreException) th);
        } else {
            consumer.accept(new DataStoreException("Failed to observe items in storage adapter.", th, "Inspect the failure details."));
        }
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public <T extends Model> void observeQuery(@NonNull Class<T> cls, @NonNull ObserveQueryOptions observeQueryOptions, @NonNull Consumer<Cancelable> consumer, @NonNull Consumer<DataStoreQuerySnapshot<T>> consumer2, @NonNull Consumer<DataStoreException> consumer3, @NonNull com.amplifyframework.core.Action action) {
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer3);
        Objects.requireNonNull(action);
        new ObserveQueryExecutor(this.itemChangeSubject, this.sqlQueryProcessor, this.threadPool, this.syncStatus, new ModelSorter(), this.dataStoreConfiguration).observeQuery(cls, observeQueryOptions, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public synchronized void terminate() throws DataStoreException {
        try {
            CompositeDisposable compositeDisposable = this.toBeDisposed;
            if (compositeDisposable != null) {
                compositeDisposable.clear();
            }
            Subject<StorageItemChange<? extends Model>> subject = this.itemChangeSubject;
            if (subject != null) {
                subject.onComplete();
            }
            ExecutorService executorService = this.threadPool;
            if (executorService != null) {
                executorService.shutdown();
            }
            SQLiteDatabase sQLiteDatabase = this.databaseConnectionHandle;
            if (sQLiteDatabase != null) {
                sQLiteDatabase.close();
            }
            SQLiteStorageHelper sQLiteStorageHelper = this.sqliteStorageHelper;
            if (sQLiteStorageHelper != null) {
                sQLiteStorageHelper.close();
            }
        } catch (Exception e) {
            throw new DataStoreException("Error in terminating the SQLiteStorageAdapter.", e, "See attached exception for details.");
        }
    }

    @Override // com.amplifyframework.datastore.storage.LocalStorageAdapter
    public synchronized void clear(@NonNull final com.amplifyframework.core.Action action, @NonNull final Consumer<DataStoreException> consumer) {
        try {
            Logger logger = LOG;
            logger.debug("Shutting down thread pool for the storage adapter.");
            this.threadPool.shutdown();
            if (!this.threadPool.awaitTermination(THREAD_POOL_TERMINATE_TIMEOUT, TimeUnit.MILLISECONDS)) {
                this.threadPool.shutdownNow();
            }
            logger.debug("Storage adapter thread pool shutdown.");
        } catch (InterruptedException e) {
            LOG.warn("Storage adapter thread pool was interrupted during shutdown.", e);
        }
        this.sqliteStorageHelper.close();
        this.databaseConnectionHandle.close();
        Logger logger2 = LOG;
        logger2.debug("Clearing DataStore.");
        if (!this.context.deleteDatabase(this.databaseName)) {
            consumer.accept(new DataStoreException("Error while trying to clear data from the local DataStore storage.", "See attached exception for details."));
        }
        logger2.debug("DataStore cleared. Re-initializing storage adapter.");
        initialize(this.context, new Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                action.call();
            }
        }, new Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda9
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                SQLiteStorageAdapter.lambda$clear$10(consumer, (DataStoreException) obj);
            }
        }, this.dataStoreConfiguration);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$clear$10(Consumer consumer, DataStoreException dataStoreException) {
        consumer.accept(new DataStoreException("Error occurred while trying to re-initialize the storage adapter", String.valueOf(dataStoreException.getMessage())));
    }

    private CreateSqlCommands getCreateCommands(@NonNull Set<String> set) {
        HashSet hashSet = new HashSet();
        HashSet hashSet2 = new HashSet();
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(it.next());
            hashSet.add(this.sqlCommandFactory.createTableFor(modelSchemaForModelClass));
            hashSet2.addAll(this.sqlCommandFactory.createIndexesFor(modelSchemaForModelClass));
            hashSet2.addAll(this.sqlCommandFactory.createIndexesForForeignKeys(modelSchemaForModelClass));
        }
        return new CreateSqlCommands(hashSet, hashSet2);
    }

    private <T extends Model> void writeData(T t, StorageItemChange.Type type) throws DataStoreException {
        String modelName = t.getModelName();
        ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(modelName);
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchemaForModelClass);
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type[type.ordinal()];
        if (i == 1) {
            LOG.verbose("Creating item in " + sQLiteTableFromSchema.getName() + " identified by ID: " + t.getPrimaryKeyString());
            this.sqlCommandProcessor.execute(this.sqlCommandFactory.insertFor(modelSchemaForModelClass, t));
            return;
        }
        if (i == 2) {
            LOG.verbose("Updating item in " + sQLiteTableFromSchema.getName() + " identified by ID: " + t.getPrimaryKeyString());
            this.sqlCommandProcessor.execute(this.sqlCommandFactory.updateFor(modelSchemaForModelClass, t));
            return;
        }
        if (i == 3) {
            LOG.verbose("Deleting item in " + sQLiteTableFromSchema.getName() + " identified by ID: " + t.getPrimaryKeyString());
            if (sQLiteTableFromSchema.getPrimaryKey() != null) {
                this.sqlCommandProcessor.execute(this.sqlCommandFactory.deleteFor(modelSchemaForModelClass, QueryField.field(modelName, sQLiteTableFromSchema.getPrimaryKey().getName()).eq(t.getPrimaryKeyString())));
                return;
            }
            return;
        }
        throw new DataStoreException("Unexpected change was requested: " + type.name(), "Valid storage changes are CREATE, UPDATE, and DELETE.");
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type;

        static {
            int[] iArr = new int[StorageItemChange.Type.values().length];
            $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type = iArr;
            try {
                iArr[StorageItemChange.Type.CREATE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type[StorageItemChange.Type.UPDATE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type[StorageItemChange.Type.DELETE.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    private boolean modelExists(Model model, QueryPredicate queryPredicate) throws DataStoreException {
        ModelSchema modelSchemaForModelClass = this.schemaRegistry.getModelSchemaForModelClass(model.getModelName());
        SQLiteTable sQLiteTableFromSchema = SQLiteTable.fromSchema(modelSchemaForModelClass);
        return this.sqlCommandProcessor.executeExists(this.sqlCommandFactory.existsFor(modelSchemaForModelClass, queryPredicate.and(QueryField.field(sQLiteTableFromSchema.getName(), sQLiteTableFromSchema.getPrimaryKey().getName()).eq(model.getPrimaryKeyString()))));
    }

    private Model query(final Model model) {
        String modelName = model.getModelName();
        final QueryPredicateOperation<Object> queryPredicateOperationEq = QueryField.field(modelName, SQLiteTable.fromSchema(this.schemaRegistry.getModelSchemaForModelClass(modelName)).getPrimaryKey().getName()).eq(model.getPrimaryKeyString());
        Iterator it = (Iterator) Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                this.f$0.lambda$query$11(model, queryPredicateOperationEq, singleEmitter);
            }
        }).blockingGet();
        if (it.hasNext()) {
            return (Model) it.next();
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$11(Model model, QueryPredicate queryPredicate, SingleEmitter singleEmitter) throws Throwable {
        if (model instanceof SerializedModel) {
            String modelName = model.getModelName();
            QueryOptions queryOptionsMatches = Where.matches(queryPredicate);
            singleEmitter.getClass();
            query(modelName, queryOptionsMatches, new PersistentModelVersion$$ExternalSyntheticLambda1(singleEmitter), new PersistentModelVersion$$ExternalSyntheticLambda2(singleEmitter));
            return;
        }
        Class<?> cls = model.getClass();
        QueryOptions queryOptionsMatches2 = Where.matches(queryPredicate);
        singleEmitter.getClass();
        query(cls, queryOptionsMatches2, new PersistentModelVersion$$ExternalSyntheticLambda1(singleEmitter), new PersistentModelVersion$$ExternalSyntheticLambda2(singleEmitter));
    }

    private Completable updateModels() {
        return PersistentModelVersion.fromLocalStorage(this).flatMap(new Function() { // from class: com.amplifyframework.datastore.storage.sqlite.SQLiteStorageAdapter$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$updateModels$12((Iterator) obj);
            }
        }).ignoreElement();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ SingleSource lambda$updateModels$12(Iterator it) throws Throwable {
        if (it.hasNext()) {
            Logger logger = LOG;
            logger.verbose("Successfully read model version from local storage. Checking if the model version need to be updated...");
            String version = ((PersistentModelVersion) it.next()).getVersion();
            String strVersion = this.modelsProvider.version();
            if (!ObjectsCompat.equals(version, strVersion)) {
                logger.debug("Updating version as it has changed from " + version + " to " + strVersion);
                Objects.requireNonNull(this.sqliteStorageHelper);
                Objects.requireNonNull(this.databaseConnectionHandle);
                this.sqliteStorageHelper.update(this.databaseConnectionHandle, version, strVersion);
            } else {
                logger.debug("Database up to date. Checking ModelMetadata.");
                new ModelMigrations(this.databaseConnectionHandle, this.modelsProvider).apply();
            }
        }
        return PersistentModelVersion.saveToLocalStorage(this, new PersistentModelVersion(this.modelsProvider.version()));
    }

    private SerializedModel createSerializedModel(ModelSchema modelSchema, Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            ModelField modelField = modelSchema.getFields().get(entry.getKey());
            if (modelField != null && entry.getValue() != null) {
                if (modelField.isModel()) {
                    ModelAssociation modelAssociation = modelSchema.getAssociations().get(entry.getKey());
                    if (modelAssociation != null) {
                        map2.put(entry.getKey(), createSerializedModel(this.schemaRegistry.getModelSchemaForModelClass(modelAssociation.getAssociatedType()), (Map) entry.getValue()));
                    }
                } else if (modelField.isCustomType()) {
                    if (modelField.isArray()) {
                        map2.put(entry.getKey(), getValueOfListCustomTypeField(modelField.getTargetType(), (List) entry.getValue()));
                    } else {
                        map2.put(entry.getKey(), createSerializedCustomType(this.schemaRegistry.getCustomTypeSchemaForCustomTypeClass(modelField.getTargetType()), (Map) entry.getValue()));
                    }
                } else {
                    map2.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return SerializedModel.builder().modelSchema(modelSchema).serializedData(map2).build();
    }

    private SerializedCustomType createSerializedCustomType(CustomTypeSchema customTypeSchema, Map<String, Object> map) {
        HashMap map2 = new HashMap();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            CustomTypeField customTypeField = customTypeSchema.getFields().get(entry.getKey());
            if (customTypeField != null) {
                if (customTypeField.isCustomType() && entry.getValue() != null) {
                    if (customTypeField.isArray()) {
                        map2.put(entry.getKey(), getValueOfListCustomTypeField(customTypeField.getTargetType(), (List) entry.getValue()));
                    } else {
                        map2.put(entry.getKey(), createSerializedCustomType(this.schemaRegistry.getCustomTypeSchemaForCustomTypeClass(customTypeField.getTargetType()), (Map) entry.getValue()));
                    }
                } else {
                    map2.put(entry.getKey(), entry.getValue());
                }
            }
        }
        return SerializedCustomType.builder().serializedData(map2).customTypeSchema(customTypeSchema).build();
    }

    private List<SerializedCustomType> getValueOfListCustomTypeField(String str, List<Map<String, Object>> list) {
        if (list == null) {
            return null;
        }
        CustomTypeSchema customTypeSchemaForCustomTypeClass = this.schemaRegistry.getCustomTypeSchemaForCustomTypeClass(str);
        ArrayList arrayList = new ArrayList();
        Iterator<Map<String, Object>> it = list.iterator();
        while (it.hasNext()) {
            arrayList.add(createSerializedCustomType(customTypeSchemaForCustomTypeClass, it.next()));
        }
        return arrayList;
    }
}

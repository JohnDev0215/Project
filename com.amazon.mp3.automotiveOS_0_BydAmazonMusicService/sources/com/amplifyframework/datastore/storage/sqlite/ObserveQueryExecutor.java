package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda20;
import com.amplifyframework.datastore.DataStoreConfiguration;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.DataStoreQuerySnapshot;
import com.amplifyframework.datastore.storage.ItemChangeMapper;
import com.amplifyframework.datastore.storage.StorageItemChange;
import com.amplifyframework.util.Immutable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.subjects.Subject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes5.dex */
public class ObserveQueryExecutor<T extends Model> implements Cancelable {
    private Disposable disposable;
    private final Subject<StorageItemChange<? extends Model>> itemChangeSubject;
    private final int maxRecords;
    private final long maxTimeSec;
    private final ModelSorter<T> modelSorter;
    private final SqlQueryProcessor sqlQueryProcessor;
    private final SyncStatus syncStatus;
    private final ExecutorService threadPool;
    private Timer timer;
    private final List<DataStoreItemChange<T>> changedItemList = new ArrayList();
    private boolean isCanceled = false;
    private boolean isSynced = false;
    private final Map<String, T> completeItemMap = new ConcurrentHashMap();

    public ObserveQueryExecutor(@NonNull Subject<StorageItemChange<? extends Model>> subject, @NonNull SqlQueryProcessor sqlQueryProcessor, @NonNull ExecutorService executorService, @NonNull SyncStatus syncStatus, @NonNull ModelSorter<T> modelSorter, @NonNull DataStoreConfiguration dataStoreConfiguration) {
        this.itemChangeSubject = subject;
        this.sqlQueryProcessor = sqlQueryProcessor;
        this.threadPool = executorService;
        this.syncStatus = syncStatus;
        this.maxRecords = dataStoreConfiguration.getObserveQueryMaxRecords().intValue();
        this.maxTimeSec = dataStoreConfiguration.getMaxTimeLapseForObserveQuery().longValue();
        this.modelSorter = modelSorter;
    }

    public ObserveQueryExecutor(@NonNull Subject<StorageItemChange<? extends Model>> subject, @NonNull SqlQueryProcessor sqlQueryProcessor, @NonNull ExecutorService executorService, @NonNull SyncStatus syncStatus, @NonNull ModelSorter<T> modelSorter, int i, int i2) {
        this.itemChangeSubject = subject;
        this.sqlQueryProcessor = sqlQueryProcessor;
        this.threadPool = executorService;
        this.syncStatus = syncStatus;
        this.modelSorter = modelSorter;
        this.maxRecords = i;
        this.maxTimeSec = i2;
    }

    public void observeQuery(@NonNull final Class<T> cls, @NonNull final ObserveQueryOptions observeQueryOptions, @NonNull Consumer<Cancelable> consumer, @NonNull final Consumer<DataStoreQuerySnapshot<T>> consumer2, @NonNull final Consumer<DataStoreException> consumer3, @NonNull Action action) {
        Objects.requireNonNull(consumer);
        Objects.requireNonNull(consumer3);
        Objects.requireNonNull(action);
        consumer.accept(this);
        Consumer consumer4 = new Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.ObserveQueryExecutor$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$observeQuery$0(observeQueryOptions, consumer2, cls, consumer3, obj);
            }
        };
        this.threadPool.submit(new Runnable() { // from class: com.amplifyframework.datastore.storage.sqlite.ObserveQueryExecutor$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$observeQuery$1(cls, observeQueryOptions, consumer2, consumer3);
            }
        });
        this.disposable = this.itemChangeSubject.filter(new Predicate() { // from class: com.amplifyframework.datastore.storage.sqlite.ObserveQueryExecutor$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return ObserveQueryExecutor.lambda$observeQuery$2(cls, (StorageItemChange) obj);
            }
        }).subscribe(new ObserveQueryExecutor$$ExternalSyntheticLambda4(consumer4), new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.ObserveQueryExecutor$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                ObserveQueryExecutor.lambda$observeQuery$3(consumer3, (Throwable) obj);
            }
        }, new AWSDataStorePlugin$$ExternalSyntheticLambda20(action));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observeQuery$0(ObserveQueryOptions observeQueryOptions, Consumer consumer, Class cls, Consumer consumer2, Object obj) {
        StorageItemChange<T> storageItemChange = (StorageItemChange) obj;
        try {
            if (this.sqlQueryProcessor.modelExists(storageItemChange.item(), observeQueryOptions.getQueryPredicate())) {
                updateCompleteItemMap(storageItemChange);
            } else if (storageItemChange.type() == StorageItemChange.Type.UPDATE) {
                this.completeItemMap.remove(storageItemChange.item().getPrimaryKeyString());
            }
            collect(storageItemChange, consumer, cls, observeQueryOptions, consumer2);
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$observeQuery$2(Class cls, StorageItemChange storageItemChange) throws Throwable {
        return storageItemChange.item().getClass().isAssignableFrom(cls);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$observeQuery$3(Consumer consumer, Throwable th) throws Throwable {
        if (th instanceof DataStoreException) {
            consumer.accept((DataStoreException) th);
        } else {
            consumer.accept(new DataStoreException("Failed to observe items in storage adapter.", th, "Inspect the failure details."));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: queryLocalData, reason: merged with bridge method [inline-methods] */
    public void lambda$observeQuery$1(@NonNull Class<T> cls, @NonNull ObserveQueryOptions observeQueryOptions, @NonNull Consumer<DataStoreQuerySnapshot<T>> consumer, @NonNull final Consumer<DataStoreException> consumer2) {
        List<T> listQueryOfflineData = this.sqlQueryProcessor.queryOfflineData(cls, Where.matchesAndSorts(observeQueryOptions.getQueryPredicate(), observeQueryOptions.getSortBy()), consumer2);
        callOnQuerySnapshot(consumer, cls, new Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.ObserveQueryExecutor$$ExternalSyntheticLambda6
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$queryLocalData$4(consumer2, (DataStoreException) obj);
            }
        }, listQueryOfflineData);
        for (T t : listQueryOfflineData) {
            this.completeItemMap.put(t.getPrimaryKeyString(), t);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$queryLocalData$4(Consumer consumer, DataStoreException dataStoreException) {
        cancel();
        consumer.accept(dataStoreException);
    }

    @Override // com.amplifyframework.core.async.Cancelable
    public void cancel() {
        this.isCanceled = true;
        resetTimer();
        this.completeItemMap.clear();
        this.changedItemList.clear();
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetTimer() {
        Timer timer = this.timer;
        if (timer != null) {
            timer.purge();
        }
        this.timer = null;
    }

    boolean getIsCancelled() {
        return this.isCanceled;
    }

    Map<String, T> getCompleteMap() {
        return Immutable.of(this.completeItemMap);
    }

    List<DataStoreItemChange<T>> getChangeList() {
        return Immutable.of(this.changedItemList);
    }

    private void collect(StorageItemChange<T> storageItemChange, @NonNull Consumer<DataStoreQuerySnapshot<T>> consumer, Class<T> cls, ObserveQueryOptions observeQueryOptions, Consumer<DataStoreException> consumer2) {
        try {
            this.changedItemList.add(ItemChangeMapper.map(storageItemChange));
            setTimerIfNeeded(consumer, cls, observeQueryOptions, consumer2);
            if (this.changedItemList.size() >= this.maxRecords || this.isSynced) {
                resetTimer();
                processQuerySnapshot(consumer, cls, observeQueryOptions, consumer2);
                this.changedItemList.clear();
            }
        } catch (DataStoreException e) {
            consumer2.accept(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processQuerySnapshot(@NonNull Consumer<DataStoreQuerySnapshot<T>> consumer, Class<T> cls, ObserveQueryOptions observeQueryOptions, Consumer<DataStoreException> consumer2) {
        ArrayList arrayList = new ArrayList(this.completeItemMap.values());
        sortIfNeeded(observeQueryOptions, arrayList, cls, consumer2);
        callOnQuerySnapshot(consumer, cls, consumer2, arrayList);
    }

    private void callOnQuerySnapshot(Consumer<DataStoreQuerySnapshot<T>> consumer, Class<T> cls, Consumer<DataStoreException> consumer2, List<T> list) {
        boolean z = this.syncStatus.get(cls.getSimpleName(), consumer2);
        this.isSynced = z;
        getListConsumer(consumer).accept(new DataStoreQuerySnapshot<>(list, z));
        this.changedItemList.clear();
    }

    private void sortIfNeeded(ObserveQueryOptions observeQueryOptions, List<T> list, Class<T> cls, Consumer<DataStoreException> consumer) {
        if (observeQueryOptions == null || observeQueryOptions.getSortBy() == null || observeQueryOptions.getSortBy().size() <= 0) {
            return;
        }
        this.modelSorter.sort(observeQueryOptions, list, cls, consumer);
    }

    private void updateCompleteItemMap(StorageItemChange<T> storageItemChange) {
        Model modelItem = storageItemChange.item();
        if (storageItemChange.type() == StorageItemChange.Type.DELETE) {
            this.completeItemMap.remove(modelItem.getPrimaryKeyString());
        } else {
            this.completeItemMap.put(modelItem.getPrimaryKeyString(), (T) modelItem);
        }
    }

    private void setTimerIfNeeded(final Consumer<DataStoreQuerySnapshot<T>> consumer, final Class<T> cls, final ObserveQueryOptions observeQueryOptions, final Consumer<DataStoreException> consumer2) {
        if (this.timer == null) {
            Timer timer = new Timer();
            this.timer = timer;
            timer.schedule(new TimerTask() { // from class: com.amplifyframework.datastore.storage.sqlite.ObserveQueryExecutor.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    ObserveQueryExecutor.this.processQuerySnapshot(consumer, cls, observeQueryOptions, consumer2);
                    ObserveQueryExecutor.this.resetTimer();
                }
            }, TimeUnit.SECONDS.toMillis(this.maxTimeSec));
        }
    }

    @NonNull
    private Consumer<DataStoreQuerySnapshot<T>> getListConsumer(final Consumer<DataStoreQuerySnapshot<T>> consumer) {
        return new Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.ObserveQueryExecutor$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$getListConsumer$5(consumer, (DataStoreQuerySnapshot) obj);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$getListConsumer$5(Consumer consumer, DataStoreQuerySnapshot dataStoreQuerySnapshot) {
        if (this.isCanceled) {
            return;
        }
        consumer.accept(dataStoreQuerySnapshot);
    }
}

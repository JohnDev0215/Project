package com.amplifyframework.datastore.syncengine;

import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;
import androidx.annotation.NonNull;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.NoOpConsumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda11;
import com.amplifyframework.datastore.DataStoreChannelEventName;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.appsync.ModelMetadata;
import com.amplifyframework.datastore.appsync.ModelWithMetadata;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.datastore.storage.StorageItemChange;
import com.amplifyframework.datastore.utils.ErrorInspector;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.functions.Supplier;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
final class Merger {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final LocalStorageAdapter localStorageAdapter;
    private final MutationOutbox mutationOutbox;
    private final VersionRepository versionRepository;

    Merger(@NonNull MutationOutbox mutationOutbox, @NonNull VersionRepository versionRepository, @NonNull LocalStorageAdapter localStorageAdapter) {
        Objects.requireNonNull(mutationOutbox);
        this.mutationOutbox = mutationOutbox;
        Objects.requireNonNull(versionRepository);
        this.versionRepository = versionRepository;
        Objects.requireNonNull(localStorageAdapter);
        this.localStorageAdapter = localStorageAdapter;
    }

    <T extends Model> Completable merge(ModelWithMetadata<T> modelWithMetadata) {
        return merge(modelWithMetadata, NoOpConsumer.create());
    }

    <T extends Model> Completable merge(final ModelWithMetadata<T> modelWithMetadata, final Consumer<StorageItemChange.Type> consumer) {
        final AtomicReference atomicReference = new AtomicReference();
        return Completable.defer(new Supplier() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Supplier
            public final Object get() {
                return this.f$0.lambda$merge$5(modelWithMetadata, consumer);
            }
        }).doOnSubscribe(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                Merger.lambda$merge$6(atomicReference, (Disposable) obj);
            }
        }).doOnTerminate(new Action() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                Merger.lambda$merge$7(atomicReference);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$merge$5(final ModelWithMetadata modelWithMetadata, final Consumer consumer) throws Throwable {
        final ModelMetadata syncMetadata = modelWithMetadata.getSyncMetadata();
        final boolean zEquals = Boolean.TRUE.equals(syncMetadata.isDeleted());
        final int iIntValue = syncMetadata.getVersion() == null ? -1 : syncMetadata.getVersion().intValue();
        final Model model = modelWithMetadata.getModel();
        return this.versionRepository.findModelVersion(model).onErrorReturnItem(-1).filter(new Predicate() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return Merger.lambda$null$0(iIntValue, (Integer) obj);
            }
        }).flatMapCompletable(new Function() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.lambda$null$1(model, zEquals, consumer, syncMetadata, (Integer) obj);
            }
        }).doOnComplete(new Action() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                this.f$0.lambda$null$2(modelWithMetadata);
            }
        }).onErrorComplete(new Predicate() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return Merger.lambda$null$3(modelWithMetadata, (Throwable) obj);
            }
        }).doOnError(new io.reactivex.rxjava3.functions.Consumer() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda7
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                Merger.lambda$null$4(modelWithMetadata, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$null$0(int i, Integer num) throws Throwable {
        return num.intValue() == -1 || i > num.intValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ CompletableSource lambda$null$1(Model model, boolean z, Consumer consumer, ModelMetadata modelMetadata, Integer num) throws Throwable {
        Completable completableDelete;
        if (this.mutationOutbox.hasPendingMutation(model.getPrimaryKeyString())) {
            LOG.info("Mutation outbox has pending mutation for " + model.resolveIdentifier() + ". Saving the metadata, but not model itself.");
            completableDelete = Completable.complete();
        } else {
            completableDelete = z ? delete(model, consumer) : save(model, consumer);
        }
        return completableDelete.andThen(save(modelMetadata, NoOpConsumer.create()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$2(ModelWithMetadata modelWithMetadata) throws Throwable {
        announceSuccessfulMerge(modelWithMetadata);
        LOG.debug("Remote model update was sync'd down into local storage: " + modelWithMetadata);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$null$3(ModelWithMetadata modelWithMetadata, Throwable th) throws Throwable {
        if (!ErrorInspector.contains(th, (Class<? extends Throwable>) SQLiteConstraintException.class)) {
            return false;
        }
        LOG.warn("Sync failed: foreign key constraint violation: " + modelWithMetadata, th);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$4(ModelWithMetadata modelWithMetadata, Throwable th) throws Throwable {
        LOG.warn("Failed to sync remote model into local storage: " + modelWithMetadata, th);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$merge$6(AtomicReference atomicReference, Disposable disposable) throws Throwable {
        atomicReference.set(Long.valueOf(System.currentTimeMillis()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$merge$7(AtomicReference atomicReference) throws Throwable {
        long jCurrentTimeMillis = System.currentTimeMillis() - ((Long) atomicReference.get()).longValue();
        LOG.verbose("Merged a single item in " + jCurrentTimeMillis + " ms.");
    }

    private <T extends Model> void announceSuccessfulMerge(ModelWithMetadata<T> modelWithMetadata) {
        Amplify.Hub.publish(HubChannel.DATASTORE, HubEvent.create(DataStoreChannelEventName.SUBSCRIPTION_DATA_PROCESSED, modelWithMetadata));
    }

    private <T extends Model> Completable delete(final T t, final Consumer<StorageItemChange.Type> consumer) {
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda9
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$delete$10(t, consumer, completableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$10(Model model, final Consumer consumer, final CompletableEmitter completableEmitter) throws Throwable {
        this.localStorageAdapter.delete(model, StorageItemChange.Initiator.SYNC_ENGINE, QueryPredicates.all(), (Consumer<StorageItemChange<Model>>) new Consumer() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda11
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                Merger.lambda$null$8(consumer, completableEmitter, (StorageItemChange) obj);
            }
        }, new Consumer() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda12
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                Merger.lambda$null$9(consumer, completableEmitter, (DataStoreException) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$8(Consumer consumer, CompletableEmitter completableEmitter, StorageItemChange storageItemChange) {
        consumer.accept(storageItemChange.type());
        completableEmitter.onComplete();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$9(Consumer consumer, CompletableEmitter completableEmitter, DataStoreException dataStoreException) {
        LOG.verbose("Failed to delete a model while merging. Perhaps it was already gone? " + Log.getStackTraceString(dataStoreException));
        consumer.accept(StorageItemChange.Type.DELETE);
        completableEmitter.onComplete();
    }

    private <T extends Model> Completable save(final T t, final Consumer<StorageItemChange.Type> consumer) {
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda10
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$save$12(t, consumer, completableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$save$12(Model model, final Consumer consumer, final CompletableEmitter completableEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.localStorageAdapter;
        StorageItemChange.Initiator initiator = StorageItemChange.Initiator.SYNC_ENGINE;
        QueryPredicate queryPredicateAll = QueryPredicates.all();
        Consumer consumer2 = new Consumer() { // from class: com.amplifyframework.datastore.syncengine.Merger$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                Merger.lambda$null$11(consumer, completableEmitter, (StorageItemChange) obj);
            }
        };
        completableEmitter.getClass();
        localStorageAdapter.save(model, initiator, queryPredicateAll, consumer2, new AWSDataStorePlugin$$ExternalSyntheticLambda11(completableEmitter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$11(Consumer consumer, CompletableEmitter completableEmitter, StorageItemChange storageItemChange) {
        consumer.accept(storageItemChange.type());
        completableEmitter.onComplete();
    }
}

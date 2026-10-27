package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryField;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicateOperation;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda11;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.datastore.storage.StorageItemChange;
import com.amplifyframework.datastore.storage.sqlite.PersistentModelVersion$$ExternalSyntheticLambda2;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.ArrayList;
import java.util.Iterator;

/* JADX INFO: loaded from: classes5.dex */
final class SyncTimeRegistry {
    private final LocalStorageAdapter localStorageAdapter;

    SyncTimeRegistry(LocalStorageAdapter localStorageAdapter) {
        this.localStorageAdapter = localStorageAdapter;
    }

    Single<SyncTime> lookupLastSyncTime(@NonNull final String str) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.SyncTimeRegistry$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                this.f$0.lambda$lookupLastSyncTime$1(str, singleEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$lookupLastSyncTime$1(final String str, final SingleEmitter singleEmitter) throws Throwable {
        QueryPredicateOperation<Object> queryPredicateOperationEq = QueryField.field("modelClassName").eq(str);
        LocalStorageAdapter localStorageAdapter = this.localStorageAdapter;
        QueryOptions queryOptionsMatches = Where.matches(queryPredicateOperationEq);
        Consumer consumer = new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SyncTimeRegistry$$ExternalSyntheticLambda5
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$null$0(str, singleEmitter, (Iterator) obj);
            }
        };
        singleEmitter.getClass();
        localStorageAdapter.query(LastSyncMetadata.class, queryOptionsMatches, consumer, new PersistentModelVersion$$ExternalSyntheticLambda2(singleEmitter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$0(String str, SingleEmitter singleEmitter, Iterator it) {
        try {
            singleEmitter.onSuccess(SyncTime.from(extractSingleResult(str, it).getLastSyncTime()));
        } catch (DataStoreException e) {
            singleEmitter.onError(e);
        }
    }

    Completable saveLastDeltaSyncTime(@NonNull String str, @Nullable SyncTime syncTime) {
        final LastSyncMetadata lastSyncMetadataNeverSynced;
        if (syncTime != null && syncTime.exists()) {
            lastSyncMetadataNeverSynced = LastSyncMetadata.deltaSyncedAt(str, syncTime.toLong());
        } else {
            lastSyncMetadataNeverSynced = LastSyncMetadata.neverSynced(str);
        }
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.SyncTimeRegistry$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$saveLastDeltaSyncTime$3(lastSyncMetadataNeverSynced, completableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveLastDeltaSyncTime$3(LastSyncMetadata lastSyncMetadata, final CompletableEmitter completableEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.localStorageAdapter;
        StorageItemChange.Initiator initiator = StorageItemChange.Initiator.SYNC_ENGINE;
        QueryPredicate queryPredicateAll = QueryPredicates.all();
        Consumer consumer = new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SyncTimeRegistry$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                completableEmitter.onComplete();
            }
        };
        completableEmitter.getClass();
        localStorageAdapter.save(lastSyncMetadata, initiator, queryPredicateAll, consumer, new AWSDataStorePlugin$$ExternalSyntheticLambda11(completableEmitter));
    }

    Completable saveLastBaseSyncTime(@NonNull String str, @Nullable SyncTime syncTime) {
        final LastSyncMetadata lastSyncMetadataNeverSynced;
        if (syncTime != null && syncTime.exists()) {
            lastSyncMetadataNeverSynced = LastSyncMetadata.baseSyncedAt(str, syncTime.toLong());
        } else {
            lastSyncMetadataNeverSynced = LastSyncMetadata.neverSynced(str);
        }
        return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.SyncTimeRegistry$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
            public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                this.f$0.lambda$saveLastBaseSyncTime$5(lastSyncMetadataNeverSynced, completableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$saveLastBaseSyncTime$5(LastSyncMetadata lastSyncMetadata, final CompletableEmitter completableEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.localStorageAdapter;
        StorageItemChange.Initiator initiator = StorageItemChange.Initiator.SYNC_ENGINE;
        QueryPredicate queryPredicateAll = QueryPredicates.all();
        Consumer consumer = new Consumer() { // from class: com.amplifyframework.datastore.syncengine.SyncTimeRegistry$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                completableEmitter.onComplete();
            }
        };
        completableEmitter.getClass();
        localStorageAdapter.save(lastSyncMetadata, initiator, queryPredicateAll, consumer, new AWSDataStorePlugin$$ExternalSyntheticLambda11(completableEmitter));
    }

    private LastSyncMetadata extractSingleResult(String str, Iterator<LastSyncMetadata> it) throws DataStoreException {
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        if (arrayList.size() > 1) {
            throw new DataStoreException("Wanted 1 sync time for model = " + str + ", but found " + arrayList.size() + ".", "This is likely a bug. Please report it to AWS.");
        }
        if (arrayList.size() == 1) {
            return (LastSyncMetadata) arrayList.get(0);
        }
        return LastSyncMetadata.neverSynced(str);
    }
}

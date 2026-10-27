package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.appsync.ModelMetadata;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.datastore.storage.sqlite.PersistentModelVersion$$ExternalSyntheticLambda2;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class VersionRepository {
    private final LocalStorageAdapter localStorageAdapter;

    VersionRepository(@NonNull LocalStorageAdapter localStorageAdapter) {
        Objects.requireNonNull(localStorageAdapter);
        this.localStorageAdapter = localStorageAdapter;
    }

    <T extends Model> Single<Integer> findModelVersion(final T t) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.VersionRepository$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                this.f$0.lambda$findModelVersion$1(t, singleEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$findModelVersion$1(final Model model, final SingleEmitter singleEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.localStorageAdapter;
        QueryOptions queryOptionsIdentifier = Where.identifier(ModelMetadata.class, model.getModelName() + "|" + model.getPrimaryKeyString());
        Consumer consumer = new Consumer() { // from class: com.amplifyframework.datastore.syncengine.VersionRepository$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                this.f$0.lambda$null$0(singleEmitter, model, (Iterator) obj);
            }
        };
        singleEmitter.getClass();
        localStorageAdapter.query(ModelMetadata.class, queryOptionsIdentifier, consumer, new PersistentModelVersion$$ExternalSyntheticLambda2(singleEmitter));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$0(SingleEmitter singleEmitter, Model model, Iterator it) {
        try {
            singleEmitter.onSuccess(Integer.valueOf(extractVersion(model, it)));
        } catch (DataStoreException e) {
            singleEmitter.onError(e);
        }
    }

    private <T extends Model> int extractVersion(T t, Iterator<ModelMetadata> it) throws DataStoreException {
        ArrayList arrayList = new ArrayList();
        while (it.hasNext()) {
            arrayList.add(it.next());
        }
        if (arrayList.size() != 1) {
            throw new DataStoreException("Wanted 1 metadata for item with id = " + t.getPrimaryKeyString() + ", but had " + arrayList.size() + ".", "This is likely a bug. please report to AWS.");
        }
        Integer version = ((ModelMetadata) arrayList.get(0)).getVersion();
        if (version == null) {
            throw new DataStoreException("Metadata for item with id = " + t.getPrimaryKeyString() + " had null version.", "This is likely a bug. Please report to AWS.");
        }
        return version.intValue();
    }
}

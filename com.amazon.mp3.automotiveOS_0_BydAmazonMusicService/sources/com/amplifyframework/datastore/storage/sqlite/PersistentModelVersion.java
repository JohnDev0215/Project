package com.amplifyframework.datastore.storage.sqlite;

import androidx.annotation.NonNull;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.Where;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.core.model.query.predicate.QueryPredicates;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.datastore.storage.StorageItemChange;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;
import java.util.Iterator;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
@ModelConfig(type = Model.Type.SYSTEM)
public final class PersistentModelVersion implements Model {
    private static final String STATIC_IDENTIFIER_FOR_VERSION = "1ae1b4d0-7894-4f23-831e-ccf6c8439e1c";

    @ModelField(isRequired = true, targetType = "ID")
    private final String id;

    @ModelField(isRequired = true, targetType = "String")
    private final String version;

    PersistentModelVersion(@NonNull String str) {
        Objects.requireNonNull(str);
        this.version = str;
        this.id = STATIC_IDENTIFIER_FOR_VERSION;
    }

    static Single<Iterator<PersistentModelVersion>> fromLocalStorage(@NonNull final LocalStorageAdapter localStorageAdapter) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.storage.sqlite.PersistentModelVersion$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                PersistentModelVersion.lambda$fromLocalStorage$0(localStorageAdapter, singleEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$fromLocalStorage$0(LocalStorageAdapter localStorageAdapter, SingleEmitter singleEmitter) throws Throwable {
        QueryOptions queryOptionsMatchesAll = Where.matchesAll();
        singleEmitter.getClass();
        localStorageAdapter.query(PersistentModelVersion.class, queryOptionsMatchesAll, new PersistentModelVersion$$ExternalSyntheticLambda1(singleEmitter), new PersistentModelVersion$$ExternalSyntheticLambda2(singleEmitter));
    }

    static Single<PersistentModelVersion> saveToLocalStorage(@NonNull final LocalStorageAdapter localStorageAdapter, @NonNull final PersistentModelVersion persistentModelVersion) {
        return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.datastore.storage.sqlite.PersistentModelVersion$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
            public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                PersistentModelVersion.lambda$saveToLocalStorage$2(localStorageAdapter, persistentModelVersion, singleEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$saveToLocalStorage$2(LocalStorageAdapter localStorageAdapter, final PersistentModelVersion persistentModelVersion, final SingleEmitter singleEmitter) throws Throwable {
        StorageItemChange.Initiator initiator = StorageItemChange.Initiator.DATA_STORE_API;
        QueryPredicate queryPredicateAll = QueryPredicates.all();
        Consumer consumer = new Consumer() { // from class: com.amplifyframework.datastore.storage.sqlite.PersistentModelVersion$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                singleEmitter.onSuccess(persistentModelVersion);
            }
        };
        singleEmitter.getClass();
        localStorageAdapter.save(persistentModelVersion, initiator, queryPredicateAll, consumer, new PersistentModelVersion$$ExternalSyntheticLambda2(singleEmitter));
    }

    @Override // com.amplifyframework.core.model.Model
    @NonNull
    public String resolveIdentifier() {
        return this.id;
    }

    @NonNull
    public String getVersion() {
        return this.version;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || PersistentModelVersion.class != obj.getClass()) {
            return false;
        }
        PersistentModelVersion persistentModelVersion = (PersistentModelVersion) obj;
        if (ObjectsCompat.equals(this.id, persistentModelVersion.id)) {
            return ObjectsCompat.equals(this.version, persistentModelVersion.version);
        }
        return false;
    }

    public int hashCode() {
        return (((this.id.hashCode() * 31) + this.id.hashCode()) * 31) + this.version.hashCode();
    }

    public String toString() {
        return "PersistentModelVersion{id='" + this.id + "', version='" + this.version + "'}";
    }
}

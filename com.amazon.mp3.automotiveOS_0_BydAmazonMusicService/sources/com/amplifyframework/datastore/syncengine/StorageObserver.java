package com.amplifyframework.datastore.syncengine;

import androidx.annotation.NonNull;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.SerializedModel;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.storage.LocalStorageAdapter;
import com.amplifyframework.datastore.storage.StorageItemChange;
import com.amplifyframework.logging.Logger;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import io.reactivex.rxjava3.schedulers.Schedulers;
import java.util.Objects;

/* JADX INFO: loaded from: classes5.dex */
final class StorageObserver {
    private static final Logger LOG = Amplify.Logging.forNamespace("amplify:aws-datastore");
    private final LocalStorageAdapter localStorageAdapter;
    private final MutationOutbox mutationOutbox;
    private final CompositeDisposable ongoingOperationsDisposable;

    StorageObserver(@NonNull LocalStorageAdapter localStorageAdapter, @NonNull MutationOutbox mutationOutbox) {
        Objects.requireNonNull(localStorageAdapter);
        this.localStorageAdapter = localStorageAdapter;
        Objects.requireNonNull(mutationOutbox);
        this.mutationOutbox = mutationOutbox;
        this.ongoingOperationsDisposable = new CompositeDisposable();
    }

    void startObservingStorageChanges(final Action action) {
        CompositeDisposable compositeDisposable = this.ongoingOperationsDisposable;
        Observable map = Observable.create(new ObservableOnSubscribe() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                this.f$0.lambda$startObservingStorageChanges$0(action, observableEmitter);
            }
        }).subscribeOn(Schedulers.single()).observeOn(Schedulers.single()).doOnSubscribe(new Consumer() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda1
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                StorageObserver.lambda$startObservingStorageChanges$1((Disposable) obj);
            }
        }).filter(new Predicate() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Predicate
            public final boolean test(Object obj) {
                return StorageObserver.lambda$startObservingStorageChanges$2((StorageItemChange) obj);
            }
        }).map(new Function() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return this.f$0.toPendingMutation((StorageItemChange) obj);
            }
        });
        final MutationOutbox mutationOutbox = this.mutationOutbox;
        mutationOutbox.getClass();
        compositeDisposable.add(map.flatMapCompletable(new Function() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda4
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return mutationOutbox.enqueue((PendingMutation) obj);
            }
        }).subscribe(new io.reactivex.rxjava3.functions.Action() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda5
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                StorageObserver.lambda$startObservingStorageChanges$3();
            }
        }, new Consumer() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda6
            @Override // io.reactivex.rxjava3.functions.Consumer
            public final void accept(Object obj) throws Throwable {
                StorageObserver.lambda$startObservingStorageChanges$4((Throwable) obj);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$startObservingStorageChanges$0(Action action, final ObservableEmitter observableEmitter) throws Throwable {
        LocalStorageAdapter localStorageAdapter = this.localStorageAdapter;
        observableEmitter.getClass();
        localStorageAdapter.observe(new com.amplifyframework.core.Consumer() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                observableEmitter.onNext((StorageItemChange) obj);
            }
        }, new com.amplifyframework.core.Consumer() { // from class: com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                observableEmitter.onError((DataStoreException) obj);
            }
        }, new StorageObserver$$ExternalSyntheticLambda9(observableEmitter));
        action.call();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startObservingStorageChanges$1(Disposable disposable) throws Throwable {
        LOG.info("Now observing local storage. Local changes will be enqueued to mutation outbox.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$startObservingStorageChanges$2(StorageItemChange storageItemChange) throws Throwable {
        return !StorageItemChange.Initiator.SYNC_ENGINE.equals(storageItemChange.initiator());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startObservingStorageChanges$3() throws Throwable {
        LOG.warn("Storage adapter subscription terminated with completion.");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$startObservingStorageChanges$4(Throwable th) throws Throwable {
        LOG.warn("Storage adapter subscription ended in error", th);
    }

    /* JADX INFO: renamed from: com.amplifyframework.datastore.syncengine.StorageObserver$1, reason: invalid class name */
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

    /* JADX INFO: Access modifiers changed from: private */
    public PendingMutation<SerializedModel> toPendingMutation(StorageItemChange<? extends Model> storageItemChange) {
        int i = AnonymousClass1.$SwitchMap$com$amplifyframework$datastore$storage$StorageItemChange$Type[storageItemChange.type().ordinal()];
        if (i == 1) {
            return PendingMutation.creation(storageItemChange.patchItem(), storageItemChange.modelSchema());
        }
        if (i == 2) {
            return PendingMutation.update(storageItemChange.patchItem(), storageItemChange.modelSchema(), storageItemChange.predicate());
        }
        if (i == 3) {
            return PendingMutation.deletion(storageItemChange.patchItem(), storageItemChange.modelSchema(), storageItemChange.predicate());
        }
        throw new IllegalStateException("Unknown mutation type = " + storageItemChange.type());
    }

    void stopObservingStorageChanges() {
        this.ongoingOperationsDisposable.clear();
    }
}

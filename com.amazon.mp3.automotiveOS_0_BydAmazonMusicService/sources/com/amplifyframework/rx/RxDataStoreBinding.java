package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.core.Action;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.NoOpAction;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.query.ObserveQueryOptions;
import com.amplifyframework.core.model.query.QueryOptions;
import com.amplifyframework.core.model.query.predicate.QueryPredicate;
import com.amplifyframework.datastore.DataStoreCategory;
import com.amplifyframework.datastore.DataStoreCategoryBehavior;
import com.amplifyframework.datastore.DataStoreException;
import com.amplifyframework.datastore.DataStoreItemChange;
import com.amplifyframework.datastore.DataStoreQuerySnapshot;
import com.amplifyframework.rx.RxAdapters;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Function;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes5.dex */
final class RxDataStoreBinding implements RxDataStoreCategoryBehavior {
    private final DataStoreCategoryBehavior dataStore;

    RxDataStoreBinding() {
        this(Amplify.DataStore);
    }

    @VisibleForTesting
    RxDataStoreBinding(DataStoreCategory dataStoreCategory) {
        this.dataStore = dataStoreCategory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$save$0(Model model, Consumer consumer, Consumer consumer2) {
        this.dataStore.save(model, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Completable save(@NonNull final T t) {
        return toCompletable(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda19
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$save$0(t, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$save$1(Model model, QueryPredicate queryPredicate, Consumer consumer, Consumer consumer2) {
        this.dataStore.save(model, queryPredicate, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Completable save(@NonNull final T t, @NonNull final QueryPredicate queryPredicate) {
        return toCompletable(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda4
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$save$1(t, queryPredicate, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$2(Model model, Consumer consumer, Consumer consumer2) {
        this.dataStore.delete(model, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Completable delete(@NonNull final T t) {
        return toCompletable(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda6
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$delete$2(t, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Completable delete(@NonNull final T t, @NonNull final QueryPredicate queryPredicate) {
        return toCompletable(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda23
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$delete$3(t, queryPredicate, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$3(Model model, QueryPredicate queryPredicate, Consumer consumer, Consumer consumer2) {
        this.dataStore.delete(model, queryPredicate, (Consumer<DataStoreItemChange<Model>>) consumer, (Consumer<DataStoreException>) consumer2);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Completable delete(@NonNull final Class<T> cls, @NonNull final QueryPredicate queryPredicate) {
        return toCompletable(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda7
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$delete$4(cls, queryPredicate, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$delete$4(Class cls, QueryPredicate queryPredicate, Consumer consumer, Consumer consumer2) {
        this.dataStore.delete(cls, queryPredicate, NoOpAction.create(), (Consumer<DataStoreException>) consumer2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$5(Class cls, Consumer consumer, Consumer consumer2) {
        this.dataStore.query(cls, consumer, consumer2);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Observable<T> query(@NonNull final Class<T> cls) {
        return toObservable(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda10
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$query$5(cls, consumer, consumer2);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Observable<T> query(@NonNull final Class<T> cls, @NonNull final QueryPredicate queryPredicate) {
        return toObservable(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda15
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$query$6(cls, queryPredicate, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$6(Class cls, QueryPredicate queryPredicate, Consumer consumer, Consumer consumer2) {
        this.dataStore.query(cls, queryPredicate, consumer, (Consumer<DataStoreException>) consumer2);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Observable<T> query(@NonNull final Class<T> cls, @NonNull final QueryOptions queryOptions) {
        return toObservable(new RxAdapters.VoidBehaviors.ResultEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda20
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ResultEmitter
            public final void emitTo(Consumer consumer, Consumer consumer2) {
                this.f$0.lambda$query$7(cls, queryOptions, consumer, consumer2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$query$7(Class cls, QueryOptions queryOptions, Consumer consumer, Consumer consumer2) {
        this.dataStore.query(cls, queryOptions, consumer, (Consumer<DataStoreException>) consumer2);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public Observable<DataStoreItemChange<? extends Model>> observe() {
        final DataStoreCategoryBehavior dataStoreCategoryBehavior = this.dataStore;
        dataStoreCategoryBehavior.getClass();
        return toObservable(new RxAdapters.VoidBehaviors.StreamEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda17
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.StreamEmitter
            public final void streamTo(Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                dataStoreCategoryBehavior.observe(consumer, consumer2, consumer3, action);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Observable<DataStoreItemChange<T>> observe(@NonNull final Class<T> cls) {
        return toObservable(new RxAdapters.VoidBehaviors.StreamEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda21
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.StreamEmitter
            public final void streamTo(Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                this.f$0.lambda$observe$8(cls, consumer, consumer2, consumer3, action);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observe$8(Class cls, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
        this.dataStore.observe(cls, consumer, consumer2, consumer3, action);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Observable<DataStoreItemChange<T>> observe(@NonNull final Class<T> cls, @NonNull final String str) {
        return toObservable(new RxAdapters.VoidBehaviors.StreamEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda2
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.StreamEmitter
            public final void streamTo(Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                this.f$0.lambda$observe$9(cls, str, consumer, consumer2, consumer3, action);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observe$9(Class cls, String str, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
        this.dataStore.observe(cls, str, (Consumer<Cancelable>) consumer, consumer2, (Consumer<DataStoreException>) consumer3, action);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Observable<DataStoreItemChange<T>> observe(@NonNull final Class<T> cls, @NonNull final QueryPredicate queryPredicate) {
        return toObservable(new RxAdapters.VoidBehaviors.StreamEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda14
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.StreamEmitter
            public final void streamTo(Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                this.f$0.lambda$observe$10(cls, queryPredicate, consumer, consumer2, consumer3, action);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$observe$10(Class cls, QueryPredicate queryPredicate, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
        this.dataStore.observe(cls, queryPredicate, (Consumer<Cancelable>) consumer, consumer2, (Consumer<DataStoreException>) consumer3, action);
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    @NonNull
    public <T extends Model> Observable<DataStoreQuerySnapshot<T>> observeQuery(@NonNull final Class<T> cls, final ObserveQueryOptions observeQueryOptions) {
        return RxAdapters.CancelableBehaviors.toObservable(new RxAdapters.CancelableBehaviors.StreamEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda12
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.StreamEmitter
            public final Cancelable streamTo(Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                return this.f$0.lambda$observeQuery$12(cls, observeQueryOptions, consumer, consumer2, consumer3, action);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ Cancelable lambda$observeQuery$12(Class cls, ObserveQueryOptions observeQueryOptions, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
        final AtomicReference atomicReference = new AtomicReference();
        this.dataStore.observeQuery(cls, observeQueryOptions, new RxDataStoreBinding$$ExternalSyntheticLambda0(atomicReference), consumer2, consumer3, action);
        return new Cancelable() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.core.async.Cancelable
            public final void cancel() {
                RxDataStoreBinding.lambda$null$11(atomicReference);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$11(AtomicReference atomicReference) {
        Cancelable cancelable = (Cancelable) atomicReference.get();
        if (cancelable != null) {
            cancelable.cancel();
        }
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    public Completable start() {
        final DataStoreCategoryBehavior dataStoreCategoryBehavior = this.dataStore;
        dataStoreCategoryBehavior.getClass();
        return RxAdapters.VoidBehaviors.toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda5
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                dataStoreCategoryBehavior.start(action, consumer);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    public Completable stop() {
        final DataStoreCategoryBehavior dataStoreCategoryBehavior = this.dataStore;
        dataStoreCategoryBehavior.getClass();
        return RxAdapters.VoidBehaviors.toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda22
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                dataStoreCategoryBehavior.stop(action, consumer);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxDataStoreCategoryBehavior
    public Completable clear() {
        final DataStoreCategoryBehavior dataStoreCategoryBehavior = this.dataStore;
        dataStoreCategoryBehavior.getClass();
        return RxAdapters.VoidBehaviors.toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda8
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                dataStoreCategoryBehavior.clear(action, consumer);
            }
        });
    }

    private static <T extends Model> Observable<T> toObservable(RxAdapters.VoidBehaviors.ResultEmitter<Iterator<T>, DataStoreException> resultEmitter) {
        return RxAdapters.VoidBehaviors.toSingle(resultEmitter).flatMapObservable(new Function() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda9
            @Override // io.reactivex.rxjava3.functions.Function
            public final Object apply(Object obj) {
                return RxDataStoreBinding.lambda$toObservable$14((Iterator) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ ObservableSource lambda$toObservable$14(final Iterator it) throws Throwable {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda11
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                RxDataStoreBinding.lambda$null$13(it, observableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$13(Iterator it, ObservableEmitter observableEmitter) throws Throwable {
        while (it.hasNext()) {
            observableEmitter.onNext(it.next());
        }
        observableEmitter.onComplete();
    }

    private static <T> Observable<T> toObservable(final RxAdapters.VoidBehaviors.StreamEmitter<Cancelable, T, DataStoreException> streamEmitter) {
        return RxAdapters.CancelableBehaviors.toObservable(new RxAdapters.CancelableBehaviors.StreamEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda18
            @Override // com.amplifyframework.rx.RxAdapters.CancelableBehaviors.StreamEmitter
            public final Cancelable streamTo(Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
                return RxDataStoreBinding.lambda$toObservable$16(streamEmitter, consumer, consumer2, consumer3, action);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ Cancelable lambda$toObservable$16(RxAdapters.VoidBehaviors.StreamEmitter streamEmitter, Consumer consumer, Consumer consumer2, Consumer consumer3, Action action) {
        final AtomicReference atomicReference = new AtomicReference();
        streamEmitter.streamTo(new RxDataStoreBinding$$ExternalSyntheticLambda0(atomicReference), consumer2, consumer3, action);
        return new Cancelable() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda16
            @Override // com.amplifyframework.core.async.Cancelable
            public final void cancel() {
                RxDataStoreBinding.lambda$null$15(atomicReference);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$null$15(AtomicReference atomicReference) {
        Cancelable cancelable = (Cancelable) atomicReference.get();
        if (cancelable != null) {
            cancelable.cancel();
        }
    }

    private static <T extends Model> Completable toCompletable(final RxAdapters.VoidBehaviors.ResultEmitter<DataStoreItemChange<T>, DataStoreException> resultEmitter) {
        return RxAdapters.VoidBehaviors.toCompletable(new RxAdapters.VoidBehaviors.ActionEmitter() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda3
            @Override // com.amplifyframework.rx.RxAdapters.VoidBehaviors.ActionEmitter
            public final void emitTo(Action action, Consumer consumer) {
                RxDataStoreBinding.lambda$toCompletable$18(resultEmitter, action, consumer);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void lambda$toCompletable$18(RxAdapters.VoidBehaviors.ResultEmitter resultEmitter, final Action action, Consumer consumer) {
        resultEmitter.emitTo(new Consumer() { // from class: com.amplifyframework.rx.RxDataStoreBinding$$ExternalSyntheticLambda13
            @Override // com.amplifyframework.core.Consumer
            public final void accept(Object obj) {
                action.call();
            }
        }, consumer);
    }
}

package com.amplifyframework.rx;

import com.amplifyframework.core.Action;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.NoOpConsumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.datastore.AWSDataStorePlugin$$ExternalSyntheticLambda10;
import com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda9;
import com.amplifyframework.rx.RxAdapters;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableEmitter;
import io.reactivex.rxjava3.core.CompletableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleEmitter;
import io.reactivex.rxjava3.core.SingleOnSubscribe;

/* JADX INFO: loaded from: classes5.dex */
final class RxAdapters {

    interface RxSingleOperation<T> extends Cancelable {
        Single<T> observeResult();
    }

    private RxAdapters() {
    }

    static final class CancelableBehaviors {

        interface ActionEmitter<E> {
            Cancelable emitTo(Action action, Consumer<E> consumer);
        }

        interface ResultEmitter<T, E extends Throwable> {
            Cancelable emitTo(Consumer<T> consumer, Consumer<E> consumer2);
        }

        interface StreamEmitter<S, T, E> {
            Cancelable streamTo(Consumer<S> consumer, Consumer<T> consumer2, Consumer<E> consumer3, Action action);
        }

        private CancelableBehaviors() {
        }

        static <E extends Throwable> Completable toCompletable(final ActionEmitter<E> actionEmitter) {
            return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.rx.RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda4
                @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
                public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                    RxAdapters.CancelableBehaviors.lambda$toCompletable$0(actionEmitter, completableEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$toCompletable$0(ActionEmitter actionEmitter, CompletableEmitter completableEmitter) throws Throwable {
            completableEmitter.getClass();
            completableEmitter.setDisposable(AmplifyDisposables.fromCancelable(actionEmitter.emitTo(new AWSDataStorePlugin$$ExternalSyntheticLambda10(completableEmitter), new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda7(completableEmitter))));
        }

        static <T, E extends Throwable> Single<T> toSingle(final ResultEmitter<T, E> resultEmitter) {
            return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.rx.RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                    RxAdapters.CancelableBehaviors.lambda$toSingle$1(resultEmitter, singleEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$toSingle$1(ResultEmitter resultEmitter, SingleEmitter singleEmitter) throws Throwable {
            singleEmitter.getClass();
            singleEmitter.setDisposable(AmplifyDisposables.fromCancelable(resultEmitter.emitTo(new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda0(singleEmitter), new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda1(singleEmitter))));
        }

        static <S, T, E extends Throwable> Observable<T> toObservable(final StreamEmitter<S, T, E> streamEmitter) {
            return Observable.create(new ObservableOnSubscribe() { // from class: com.amplifyframework.rx.RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda3
                @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                    RxAdapters.CancelableBehaviors.lambda$toObservable$2(streamEmitter, observableEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$toObservable$2(StreamEmitter streamEmitter, ObservableEmitter observableEmitter) throws Throwable {
            NoOpConsumer noOpConsumerCreate = NoOpConsumer.create();
            observableEmitter.getClass();
            observableEmitter.setDisposable(AmplifyDisposables.fromCancelable(streamEmitter.streamTo(noOpConsumerCreate, new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda5(observableEmitter), new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda6(observableEmitter), new StorageObserver$$ExternalSyntheticLambda9(observableEmitter))));
        }
    }

    static final class VoidBehaviors {

        interface ActionEmitter<E> {
            void emitTo(Action action, Consumer<E> consumer);
        }

        interface ResultEmitter<T, E extends Throwable> {
            void emitTo(Consumer<T> consumer, Consumer<E> consumer2);
        }

        interface StreamEmitter<S, T, E extends Throwable> {
            void streamTo(Consumer<S> consumer, Consumer<T> consumer2, Consumer<E> consumer3, Action action);
        }

        private VoidBehaviors() {
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$toCompletable$0(ActionEmitter actionEmitter, CompletableEmitter completableEmitter) throws Throwable {
            completableEmitter.getClass();
            actionEmitter.emitTo(new AWSDataStorePlugin$$ExternalSyntheticLambda10(completableEmitter), new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda7(completableEmitter));
        }

        static <E extends Throwable> Completable toCompletable(final ActionEmitter<E> actionEmitter) {
            return Completable.create(new CompletableOnSubscribe() { // from class: com.amplifyframework.rx.RxAdapters$VoidBehaviors$$ExternalSyntheticLambda2
                @Override // io.reactivex.rxjava3.core.CompletableOnSubscribe
                public final void subscribe(CompletableEmitter completableEmitter) throws Throwable {
                    RxAdapters.VoidBehaviors.lambda$toCompletable$0(actionEmitter, completableEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$toSingle$1(ResultEmitter resultEmitter, SingleEmitter singleEmitter) throws Throwable {
            singleEmitter.getClass();
            resultEmitter.emitTo(new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda0(singleEmitter), new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda1(singleEmitter));
        }

        static <T, E extends Throwable> Single<T> toSingle(final ResultEmitter<T, E> resultEmitter) {
            return Single.create(new SingleOnSubscribe() { // from class: com.amplifyframework.rx.RxAdapters$VoidBehaviors$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.core.SingleOnSubscribe
                public final void subscribe(SingleEmitter singleEmitter) throws Throwable {
                    RxAdapters.VoidBehaviors.lambda$toSingle$1(resultEmitter, singleEmitter);
                }
            });
        }

        static <S, T, E extends Throwable> Observable<T> toObservable(final StreamEmitter<S, T, E> streamEmitter) {
            return Observable.create(new ObservableOnSubscribe() { // from class: com.amplifyframework.rx.RxAdapters$VoidBehaviors$$ExternalSyntheticLambda0
                @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                    RxAdapters.VoidBehaviors.lambda$toObservable$2(streamEmitter, observableEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static /* synthetic */ void lambda$toObservable$2(StreamEmitter streamEmitter, ObservableEmitter observableEmitter) throws Throwable {
            NoOpConsumer noOpConsumerCreate = NoOpConsumer.create();
            observableEmitter.getClass();
            streamEmitter.streamTo(noOpConsumerCreate, new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda5(observableEmitter), new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda6(observableEmitter), new StorageObserver$$ExternalSyntheticLambda9(observableEmitter));
        }
    }
}

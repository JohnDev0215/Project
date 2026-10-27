package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.util.ObjectsCompat;
import com.amplifyframework.api.ApiException;
import com.amplifyframework.core.Consumer;
import com.amplifyframework.core.async.Cancelable;
import com.amplifyframework.datastore.syncengine.StorageObserver$$ExternalSyntheticLambda9;
import com.amplifyframework.rx.RxAdapters;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.subjects.BehaviorSubject;

/* JADX INFO: loaded from: classes5.dex */
public final class RxOperations {

    public static final class RxSubscriptionOperation<T> implements Cancelable {
        private Cancelable amplifyOperation;
        private Observable<T> subscriptionData;
        private RxSubscriptionOperation<T>.OnConnectedConsumer onConnected = new OnConnectedConsumer();
        private BehaviorSubject<ConnectionStateEvent> connectionStateSubject = BehaviorSubject.create();

        public enum ConnectionState {
            CONNECTED
        }

        public RxSubscriptionOperation(final RxAdapters.CancelableBehaviors.StreamEmitter<String, T, ApiException> streamEmitter) {
            this.subscriptionData = Observable.create(new ObservableOnSubscribe() { // from class: com.amplifyframework.rx.RxOperations$RxSubscriptionOperation$$ExternalSyntheticLambda1
                @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
                public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                    this.f$0.lambda$new$0(streamEmitter, observableEmitter);
                }
            });
        }

        /* JADX INFO: Access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0(RxAdapters.CancelableBehaviors.StreamEmitter streamEmitter, final ObservableEmitter observableEmitter) throws Throwable {
            RxSubscriptionOperation<T>.OnConnectedConsumer onConnectedConsumer = this.onConnected;
            observableEmitter.getClass();
            this.amplifyOperation = streamEmitter.streamTo(onConnectedConsumer, new RxAdapters$CancelableBehaviors$$ExternalSyntheticLambda5(observableEmitter), new Consumer() { // from class: com.amplifyframework.rx.RxOperations$RxSubscriptionOperation$$ExternalSyntheticLambda0
                @Override // com.amplifyframework.core.Consumer
                public final void accept(Object obj) {
                    observableEmitter.tryOnError((ApiException) obj);
                }
            }, new StorageObserver$$ExternalSyntheticLambda9(observableEmitter));
        }

        public Observable<T> observeSubscriptionData() {
            return this.subscriptionData;
        }

        public Observable<ConnectionStateEvent> observeConnectionState() {
            return this.connectionStateSubject;
        }

        @Override // com.amplifyframework.core.async.Cancelable
        public void cancel() {
            this.connectionStateSubject.onComplete();
            Cancelable cancelable = this.amplifyOperation;
            if (cancelable != null) {
                cancelable.cancel();
            }
        }

        public static final class ConnectionStateEvent {
            private ConnectionState connectionState;
            private String subscriptionId;

            ConnectionStateEvent(@NonNull ConnectionState connectionState, @Nullable String str) {
                this.connectionState = connectionState;
                this.subscriptionId = str;
            }

            @NonNull
            public ConnectionState getConnectionState() {
                return this.connectionState;
            }

            @Nullable
            public String getSubscriptionId() {
                return this.subscriptionId;
            }

            public int hashCode() {
                int iHashCode = this.connectionState.hashCode() * 31;
                String str = this.subscriptionId;
                return iHashCode + (str != null ? str.hashCode() : 0);
            }

            public boolean equals(@Nullable Object obj) {
                if (this == obj) {
                    return true;
                }
                if (obj == null || ConnectionStateEvent.class != obj.getClass()) {
                    return false;
                }
                ConnectionStateEvent connectionStateEvent = (ConnectionStateEvent) obj;
                return ObjectsCompat.equals(getConnectionState(), connectionStateEvent.getConnectionState()) && ObjectsCompat.equals(getSubscriptionId(), connectionStateEvent.getSubscriptionId());
            }

            @NonNull
            public String toString() {
                return "ConnectionStateEvent{connectionState=" + this.connectionState + ",subscriptionId=" + this.subscriptionId + "}";
            }
        }

        private final class OnConnectedConsumer implements Consumer<String> {
            private OnConnectedConsumer() {
            }

            @Override // com.amplifyframework.core.Consumer
            public void accept(@NonNull String str) {
                RxSubscriptionOperation.this.connectionStateSubject.onNext(new ConnectionStateEvent(ConnectionState.CONNECTED, str));
            }
        }
    }
}

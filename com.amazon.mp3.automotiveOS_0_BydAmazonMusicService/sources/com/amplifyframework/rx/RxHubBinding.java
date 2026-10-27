package com.amplifyframework.rx;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import com.amplifyframework.core.Amplify;
import com.amplifyframework.hub.HubCategory;
import com.amplifyframework.hub.HubCategoryBehavior;
import com.amplifyframework.hub.HubChannel;
import com.amplifyframework.hub.HubEvent;
import com.amplifyframework.hub.HubSubscriber;
import com.amplifyframework.hub.SubscriptionToken;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;

/* JADX INFO: loaded from: classes5.dex */
final class RxHubBinding implements RxHubCategoryBehavior {
    private final HubCategoryBehavior hub;

    RxHubBinding() {
        this(Amplify.Hub);
    }

    @VisibleForTesting
    RxHubBinding(HubCategory hubCategory) {
        this.hub = hubCategory;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$publish$0(HubChannel hubChannel, HubEvent hubEvent) throws Throwable {
        this.hub.publish(hubChannel, hubEvent);
    }

    @Override // com.amplifyframework.rx.RxHubCategoryBehavior
    @NonNull
    public <T> Completable publish(@NonNull final HubChannel hubChannel, @NonNull final HubEvent<T> hubEvent) {
        return Completable.fromAction(new Action() { // from class: com.amplifyframework.rx.RxHubBinding$$ExternalSyntheticLambda0
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                this.f$0.lambda$publish$0(hubChannel, hubEvent);
            }
        });
    }

    @Override // com.amplifyframework.rx.RxHubCategoryBehavior
    @NonNull
    public Observable<HubEvent<?>> on(@NonNull final HubChannel hubChannel) {
        return Observable.create(new ObservableOnSubscribe() { // from class: com.amplifyframework.rx.RxHubBinding$$ExternalSyntheticLambda3
            @Override // io.reactivex.rxjava3.core.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) throws Throwable {
                this.f$0.lambda$on$2(hubChannel, observableEmitter);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$on$2(HubChannel hubChannel, final ObservableEmitter observableEmitter) throws Throwable {
        HubCategoryBehavior hubCategoryBehavior = this.hub;
        observableEmitter.getClass();
        final SubscriptionToken subscriptionTokenSubscribe = hubCategoryBehavior.subscribe(hubChannel, new HubSubscriber() { // from class: com.amplifyframework.rx.RxHubBinding$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.hub.HubSubscriber
            public final void onEvent(HubEvent hubEvent) {
                observableEmitter.onNext(hubEvent);
            }
        });
        observableEmitter.setDisposable(Disposable.fromAction(new Action() { // from class: com.amplifyframework.rx.RxHubBinding$$ExternalSyntheticLambda2
            @Override // io.reactivex.rxjava3.functions.Action
            public final void run() throws Throwable {
                this.f$0.lambda$null$1(subscriptionTokenSubscribe);
            }
        }));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$null$1(SubscriptionToken subscriptionToken) throws Throwable {
        this.hub.unsubscribe(subscriptionToken);
    }
}

package com.spotify.cosmos.servicebasedrouter;

import com.google.common.base.Optional;
import com.spotify.cosmos.cosmos.Request;
import com.spotify.cosmos.cosmos.Response;
import com.spotify.cosmos.rxrouter.RxRouter;
import com.spotify.cosmos.servicebasedrouter.RxRouterClient;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.functions.p;
import io.reactivex.rxjava3.subjects.b;
import java.util.List;
import p.f60;
import p.v03;
import p.vg2;
import p.zg2;

/* JADX INFO: loaded from: classes.dex */
public final class CosmosServiceLazyRxRouter implements RxRouter {
    private final b routerSubject;
    private volatile boolean running;
    private final RxRouterClient serviceClient;
    private final zg2 subscriptionTracker;

    public CosmosServiceLazyRxRouter(RxRouterClient rxRouterClient) {
        v03.j(rxRouterClient, "serviceClient");
        this.serviceClient = rxRouterClient;
        this.routerSubject = b.a();
        this.subscriptionTracker = new zg2();
        rxRouterClient.setListener(new RxRouterClient.Listener() { // from class: com.spotify.cosmos.servicebasedrouter.CosmosServiceLazyRxRouter.1
            @Override // com.spotify.cosmos.servicebasedrouter.RxRouterClient.Listener
            public void onConnected(RxRouter rxRouter) {
                v03.j(rxRouter, "r");
                CosmosServiceLazyRxRouter.this.routerSubject.onNext(Optional.e(rxRouter));
            }

            @Override // com.spotify.cosmos.servicebasedrouter.RxRouterClient.Listener
            public void onDisconnected() {
                CosmosServiceLazyRxRouter.this.routerSubject.onNext(Optional.a());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void start() {
        f60.h();
        if (!(!this.running)) {
            throw new IllegalStateException("Check failed.".toString());
        }
        this.running = true;
        this.serviceClient.connect();
    }

    @Override // com.spotify.cosmos.rxrouter.RxRouter
    public Observable<Response> resolve(final Request request) {
        v03.j(request, "request");
        Single singleSingleOrError = this.routerSubject.filter(new p() { // from class: com.spotify.cosmos.servicebasedrouter.CosmosServiceLazyRxRouter$resolve$routerSingle$1
            @Override // io.reactivex.rxjava3.functions.p
            public final boolean test(Optional<RxRouter> optional) {
                v03.j(optional, "p0");
                return optional.d();
            }
        }).map(new o() { // from class: com.spotify.cosmos.servicebasedrouter.CosmosServiceLazyRxRouter$resolve$routerSingle$2
            @Override // io.reactivex.rxjava3.functions.o
            public final RxRouter apply(Optional<RxRouter> optional) {
                v03.j(optional, "p0");
                return (RxRouter) optional.c();
            }
        }).take(1L).singleOrError();
        v03.i(singleSingleOrError, "routerSubject.filter(Opt…).take(1).singleOrError()");
        b bVar = this.routerSubject;
        Observable observableFilter = bVar.skipUntil(bVar.filter(new p() { // from class: com.spotify.cosmos.servicebasedrouter.CosmosServiceLazyRxRouter$resolve$disconnected$1
            @Override // io.reactivex.rxjava3.functions.p
            public final boolean test(Optional<RxRouter> optional) {
                v03.j(optional, "p0");
                return optional.d();
            }
        })).filter(new p() { // from class: com.spotify.cosmos.servicebasedrouter.CosmosServiceLazyRxRouter$resolve$disconnected$2
            @Override // io.reactivex.rxjava3.functions.p
            public final boolean test(Optional<RxRouter> optional) {
                v03.j(optional, "o");
                return !optional.d();
            }
        });
        v03.i(observableFilter, "routerSubject\n          …uter> -> !o.isPresent() }");
        Observable observableTakeUntil = singleSingleOrError.flatMapObservable(new o() { // from class: com.spotify.cosmos.servicebasedrouter.CosmosServiceLazyRxRouter$resolve$response$1
            @Override // io.reactivex.rxjava3.functions.o
            public final ObservableSource<? extends Response> apply(RxRouter rxRouter) {
                return rxRouter.resolve(request);
            }
        }).takeUntil(observableFilter);
        v03.i(observableTakeUntil, "request: Request): io.re… .takeUntil(disconnected)");
        Observable<Response> observableDoOnSubscribe = this.subscriptionTracker.b(request.getAction() + ": " + request.getUri(), observableTakeUntil).doOnSubscribe(new g() { // from class: com.spotify.cosmos.servicebasedrouter.CosmosServiceLazyRxRouter.resolve.1
            @Override // io.reactivex.rxjava3.functions.g
            public final void accept(Disposable disposable) {
                if (CosmosServiceLazyRxRouter.this.running) {
                    return;
                }
                CosmosServiceLazyRxRouter.this.start();
            }
        });
        v03.i(observableDoOnSubscribe, "override fun resolve(req…\n                })\n    }");
        return observableDoOnSubscribe;
    }

    public final void stop() {
        f60.h();
        if (this.running) {
            this.running = false;
            this.serviceClient.disconnect();
        }
    }

    public final synchronized List<vg2> unsubscribeAndReturnLeaks() {
        return this.subscriptionTracker.c();
    }
}

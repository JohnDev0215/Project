package com.spotify.cosmos.servicebasedrouter;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import p.pa0;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class GlobalCoreRxRouterClient extends RxRouterClient {
    private final pa0 disposableRef;
    private final Scheduler mainScheduler;
    private final Observable<RemoteNativeRouter> nativeRouterObservable;

    public GlobalCoreRxRouterClient(Observable<RemoteNativeRouter> observable, Scheduler scheduler) {
        v03.j(observable, "nativeRouterObservable");
        v03.j(scheduler, "mainScheduler");
        this.nativeRouterObservable = observable;
        this.mainScheduler = scheduler;
        this.disposableRef = new pa0();
    }

    @Override // com.spotify.cosmos.servicebasedrouter.RxRouterClient
    public void connect() {
        super.connect();
        pa0 pa0Var = this.disposableRef;
        DisposableHelper.d(pa0Var.a.c, this.nativeRouterObservable.observeOn(this.mainScheduler).subscribe(new g() { // from class: com.spotify.cosmos.servicebasedrouter.GlobalCoreRxRouterClient.connect.1
            @Override // io.reactivex.rxjava3.functions.g
            public final void accept(RemoteNativeRouter remoteNativeRouter) {
                GlobalCoreRxRouterClient.this.notifyOnConnected(new RemoteNativeRxRouter(remoteNativeRouter));
            }
        }));
    }

    @Override // com.spotify.cosmos.servicebasedrouter.RxRouterClient
    public void disconnect() {
        super.disconnect();
        DisposableHelper.d(this.disposableRef.a.c, null);
    }
}

package com.spotify.connectivity.platformconnectiontype;

import com.spotify.base.java.logging.Logger;
import com.spotify.connectivity.connectiontype.OfflineState;
import com.spotify.connectivity.connectiontype.OfflineStateController;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.functions.g;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.operators.observable.m3;
import java.util.Iterator;
import p.ed1;
import p.hc1;
import p.lj;
import p.pa0;

/* JADX INFO: loaded from: classes.dex */
public class OfflineStateControllerCosmos implements OfflineStateController {
    private final CoreConnectionState mEndpoint;
    private final Scheduler mMainScheduler;
    private final Observable<OfflineState> mObservable;
    private final pa0 mDisposable = new pa0();
    private final g mPutOnNextHandler = new lj(6);
    private final g mPutOnErrorHandler = new lj(7);

    public OfflineStateControllerCosmos(CoreConnectionState coreConnectionState, Scheduler scheduler) {
        this.mEndpoint = coreConnectionState;
        this.mMainScheduler = scheduler;
        io.reactivex.rxjava3.observables.a aVarReplay = coreConnectionState.connection().map(new ed1(28)).observeOn(scheduler).distinctUntilChanged().replay(1);
        aVarReplay.getClass();
        this.mObservable = new m3(aVarReplay);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void lambda$new$1(Throwable th) {
        Object[] objArr = new Object[0];
        Iterator it = Logger.a.iterator();
        while (it.hasNext()) {
            ((hc1) it.next()).b().f(Logger.c.b(), Logger.d("PUT OfflineState error!", objArr), th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ OfflineState lambda$new$2(OfflineStateCosmos offlineStateCosmos) {
        return offlineStateCosmos;
    }

    @Override // com.spotify.connectivity.connectiontype.OfflineStateController
    public Observable<OfflineState> observable() {
        return this.mObservable;
    }

    @Override // com.spotify.connectivity.connectiontype.OfflineStateController
    public void setOfflineMode(boolean z) {
        OfflineState.State state = z ? OfflineState.State.FORCED_OFFLINE : OfflineState.State.ONLINE;
        DisposableHelper.d(this.mDisposable.a.c, this.mEndpoint.set(OfflineStateCosmos.create(state)).observeOn(this.mMainScheduler).subscribe(this.mPutOnNextHandler, this.mPutOnErrorHandler));
    }
}

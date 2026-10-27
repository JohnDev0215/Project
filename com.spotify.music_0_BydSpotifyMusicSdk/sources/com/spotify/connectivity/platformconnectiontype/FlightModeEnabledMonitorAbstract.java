package com.spotify.connectivity.platformconnectiontype;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.o;
import io.reactivex.rxjava3.functions.q;
import java.util.concurrent.Callable;
import p.dv0;
import p.qn;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public abstract class FlightModeEnabledMonitorAbstract implements FlightModeEnabledMonitor {
    private final Observable<Boolean> observable;

    public FlightModeEnabledMonitorAbstract(Context context) {
        v03.j(context, "context");
        final Context applicationContext = context.getApplicationContext();
        final IntentFilter intentFilter = new IntentFilter("android.intent.action.AIRPLANE_MODE");
        Observable<Boolean> observableDefer = Observable.defer(new q() { // from class: com.spotify.connectivity.platformconnectiontype.FlightModeEnabledMonitorAbstract.1
            @Override // io.reactivex.rxjava3.functions.q
            public final ObservableSource<? extends Boolean> get() {
                Context context2 = applicationContext;
                v03.i(context2, "appContext");
                IntentFilter intentFilter2 = intentFilter;
                v03.j(intentFilter2, "intentFilter");
                qn qnVar = new qn(context2, intentFilter2);
                final FlightModeEnabledMonitorAbstract flightModeEnabledMonitorAbstract = this;
                Observable map = qnVar.map(new o() { // from class: com.spotify.connectivity.platformconnectiontype.FlightModeEnabledMonitorAbstract.1.1
                    @Override // io.reactivex.rxjava3.functions.o
                    public final Boolean apply(Intent intent) {
                        return Boolean.valueOf(flightModeEnabledMonitorAbstract.isFlightModeEnabledCurrently());
                    }
                });
                final FlightModeEnabledMonitorAbstract flightModeEnabledMonitorAbstract2 = this;
                return map.startWith(Observable.fromCallable(new Callable() { // from class: com.spotify.connectivity.platformconnectiontype.FlightModeEnabledMonitorAbstract.1.2
                    @Override // java.util.concurrent.Callable
                    public final Boolean call() {
                        return Boolean.valueOf(flightModeEnabledMonitorAbstract2.isFlightModeEnabledCurrently());
                    }
                })).distinctUntilChanged();
            }
        });
        v03.i(observableDefer, "defer {\n            RxBr…tUntilChanged()\n        }");
        this.observable = observableDefer;
    }

    @Override // com.spotify.connectivity.platformconnectiontype.FlightModeEnabledMonitor
    public dv0 flightModeEnabledFlow() {
        return kotlinx.coroutines.rx3.a.a(isFlightModeEnabled());
    }

    @Override // com.spotify.connectivity.platformconnectiontype.FlightModeEnabledMonitor
    public Observable<Boolean> isFlightModeEnabled() {
        return this.observable;
    }
}

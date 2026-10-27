package com.spotify.connectivity.productstateesperanto;

import io.reactivex.rxjava3.core.Observable;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class LoggedInProductStateClient_Factory implements ul0 {
    private final xy1 accumulatedProductStateClientProvider;
    private final xy1 isLoggedInProvider;

    public LoggedInProductStateClient_Factory(xy1 xy1Var, xy1 xy1Var2) {
        this.isLoggedInProvider = xy1Var;
        this.accumulatedProductStateClientProvider = xy1Var2;
    }

    public static LoggedInProductStateClient_Factory create(xy1 xy1Var, xy1 xy1Var2) {
        return new LoggedInProductStateClient_Factory(xy1Var, xy1Var2);
    }

    public static LoggedInProductStateClient newInstance(Observable<Boolean> observable, AccumulatedProductStateClient accumulatedProductStateClient) {
        return new LoggedInProductStateClient(observable, accumulatedProductStateClient);
    }

    @Override // p.xy1
    public LoggedInProductStateClient get() {
        return newInstance((Observable) this.isLoggedInProvider.get(), (AccumulatedProductStateClient) this.accumulatedProductStateClientProvider.get());
    }
}

package com.spotify.connectivity.productstateesperanto;

import com.google.common.collect.ImmutableMap;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.o;
import java.util.Map;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class LoggedInProductStateClient {
    private final AccumulatedProductStateClient accumulatedProductStateClient;
    private final Observable<Boolean> isLoggedIn;

    public LoggedInProductStateClient(Observable<Boolean> observable, AccumulatedProductStateClient accumulatedProductStateClient) {
        v03.j(observable, "isLoggedIn");
        v03.j(accumulatedProductStateClient, "accumulatedProductStateClient");
        this.isLoggedIn = observable;
        this.accumulatedProductStateClient = accumulatedProductStateClient;
    }

    public final Observable<Map<String, String>> get() {
        Observable observableSwitchMap = this.isLoggedIn.distinctUntilChanged().switchMap(new o() { // from class: com.spotify.connectivity.productstateesperanto.LoggedInProductStateClient.get.1
            @Override // io.reactivex.rxjava3.functions.o
            public final ObservableSource<? extends Map<String, String>> apply(Boolean bool) {
                v03.i(bool, "loggedIn");
                return bool.booleanValue() ? LoggedInProductStateClient.this.accumulatedProductStateClient.get() : Observable.just(ImmutableMap.d());
            }
        });
        v03.i(observableSwitchMap, "fun get(): Observable<Ma…          }\n            }");
        return observableSwitchMap;
    }
}

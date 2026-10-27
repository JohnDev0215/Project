package com.spotify.connectivity.pubsub;

import io.reactivex.rxjava3.core.Observable;
import p.ry0;

/* JADX INFO: loaded from: classes.dex */
public interface PubSubClient {
    Observable<String> getConnectionIDObservable();

    <T> Observable<T> getObservableOf(String str, ry0 ry0Var);

    void onSessionLogin();

    void onSessionLogout();
}

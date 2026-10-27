package com.neusoft.de.nts.OneCoreTest.interfaces;

import java9.util.concurrent.CompletableFuture;
import rx.Observable;

/* JADX INFO: loaded from: classes2.dex */
public interface IBroadcast<V> {
    Class<V> getNotifyClass();

    Observable<V> getObservable();

    CompletableFuture<V> signIn();

    CompletableFuture<V> signOut();
}

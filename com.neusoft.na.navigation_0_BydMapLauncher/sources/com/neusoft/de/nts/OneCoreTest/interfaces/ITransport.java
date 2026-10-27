package com.neusoft.de.nts.OneCoreTest.interfaces;

import rx.Observable;

/* JADX INFO: loaded from: classes2.dex */
public interface ITransport<S, R> {
    Observable<R> getObservable();

    R receive();

    void send(S s);
}

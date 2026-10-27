package com.neusoft.de.nts.OneCoreTest.interfaces;

import java9.util.concurrent.CompletableFuture;

/* JADX INFO: loaded from: classes2.dex */
public interface IValueUpdater<V, P> {
    V create();

    V fromProtocol(P p);

    CompletableFuture<V> getInitialValue();

    boolean match(P p);
}

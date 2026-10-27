package com.neusoft.dlt.service;

import java.nio.channels.Selector;
import rx.Observable;

/* JADX INFO: loaded from: classes.dex */
public interface ILogSource {
    int getClientCount();

    Observable<Integer> getClientCountObservable();

    Observable<DltMessage> getMessageObservable();

    void register(Selector selector);

    boolean start();

    boolean stop();
}

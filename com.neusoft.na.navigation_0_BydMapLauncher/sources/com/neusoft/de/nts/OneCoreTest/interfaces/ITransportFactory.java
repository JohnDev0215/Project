package com.neusoft.de.nts.OneCoreTest.interfaces;

/* JADX INFO: loaded from: classes2.dex */
public interface ITransportFactory<S, R> {
    ITransport<S, R> createTransport();

    ITransport<S, R> createTransport(String str);
}

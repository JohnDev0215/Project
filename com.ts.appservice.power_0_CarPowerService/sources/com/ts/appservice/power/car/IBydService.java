package com.ts.appservice.power.car;

/* JADX INFO: loaded from: classes.dex */
public interface IBydService {
    boolean isServiceConnected();

    boolean registerConnectionListener(IServiceConnectionListener iServiceConnectionListener);

    boolean unregisterConnectionListener(IServiceConnectionListener iServiceConnectionListener);
}

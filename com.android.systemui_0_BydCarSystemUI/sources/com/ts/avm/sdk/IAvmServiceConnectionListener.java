package com.ts.avm.sdk;

/* JADX INFO: loaded from: classes2.dex */
public interface IAvmServiceConnectionListener {
    void onError(int i, String str);

    void onServiceConnected();

    void onServiceDisConnected();
}

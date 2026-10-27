package com.ts.androidauto.service.common;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes2.dex */
public class Consumer {

    public interface FdConsumer<T> {
        boolean action(T t);
    }

    public interface ReConsumer<T> {
        void accept(T t) throws RemoteException;
    }
}

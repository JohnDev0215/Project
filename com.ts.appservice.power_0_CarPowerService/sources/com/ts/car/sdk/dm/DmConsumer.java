package com.ts.car.sdk.dm;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class DmConsumer {

    public interface FdConsumer<T> {
        boolean action(T t);
    }

    public interface ReConsumer<T> {
        void accept(T t) throws RemoteException;
    }
}

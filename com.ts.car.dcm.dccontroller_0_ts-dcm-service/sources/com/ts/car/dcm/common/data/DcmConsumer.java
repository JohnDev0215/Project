package com.ts.car.dcm.common.data;

import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public class DcmConsumer {

    public interface FdConsumer<T> {
        boolean action(T t);
    }

    public interface ReConsumer<T> {
        void accept(T t) throws RemoteException;
    }
}

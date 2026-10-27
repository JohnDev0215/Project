package com.ts.car.common.listener;

import android.os.IInterface;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
@FunctionalInterface
public interface BinderConsumer<T extends IInterface> {
    void doCallback(T t) throws RemoteException;
}

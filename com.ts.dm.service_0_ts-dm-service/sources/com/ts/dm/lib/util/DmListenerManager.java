package com.ts.dm.lib.util;

import android.os.RemoteException;
import com.ts.car.sdk.dm.DmConsumer;
import java.util.HashSet;

/* JADX INFO: loaded from: classes.dex */
public class DmListenerManager<T> {
    public static final String TAG = "DmListenerManager";
    private final HashSet<T> mListener = new HashSet<>();

    public void registerListener(T listener) {
        synchronized (this.mListener) {
            this.mListener.add(listener);
        }
    }

    public void unregisterDmListener(T listener) {
        synchronized (this.mListener) {
            this.mListener.remove(listener);
        }
    }

    public void broadcastListener(DmConsumer.ReConsumer<T> consumer) {
        synchronized (this.mListener) {
            for (T listener : this.mListener) {
                try {
                    consumer.accept(listener);
                } catch (RemoteException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}

package com.ts.dm.lib.connectionmanager;

import android.os.RemoteCallbackList;
import android.os.RemoteException;
import com.ts.car.sdk.dm.DmConsumer;
import com.ts.car.sdk.dm.aidl.IDeviceManagementListener;

/* JADX INFO: loaded from: classes.dex */
public class DmsListenerManager {
    public static final String TAG = "DmsListenerManager";
    private RemoteCallbackList<IDeviceManagementListener> mListeners;

    private static class Inner {
        static final DmsListenerManager INSTANCE = new DmsListenerManager();

        private Inner() {
        }
    }

    public static DmsListenerManager getInstance() {
        return Inner.INSTANCE;
    }

    private DmsListenerManager() {
        this.mListeners = new RemoteCallbackList<>();
    }

    public synchronized void registerListener(IDeviceManagementListener listener) {
        synchronized (this.mListeners) {
            try {
                this.mListeners.register(listener);
            } finally {
                th = th;
                while (true) {
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            }
        }
    }

    public synchronized void unregisterListener(IDeviceManagementListener listener) {
        synchronized (this.mListeners) {
            try {
                this.mListeners.unregister(listener);
            } finally {
                th = th;
                while (true) {
                    try {
                    } catch (Throwable th) {
                        th = th;
                    }
                }
            }
        }
    }

    public synchronized void broadcastListener(DmConsumer.ReConsumer<IDeviceManagementListener> consumer) {
        RemoteCallbackList<IDeviceManagementListener> remoteCallbackList;
        if (this.mListeners != null && this.mListeners.getRegisteredCallbackCount() > 0) {
            int count = this.mListeners.beginBroadcast();
            for (int i = 0; i < count; i++) {
                try {
                    try {
                        try {
                            IDeviceManagementListener listener = this.mListeners.getBroadcastItem(i);
                            if (listener != null) {
                                consumer.accept(listener);
                            }
                        } catch (RemoteException e) {
                            remoteCallbackList = this.mListeners;
                            remoteCallbackList.finishBroadcast();
                        }
                    } catch (Throwable th) {
                        this.mListeners.finishBroadcast();
                        throw th;
                    }
                } catch (RemoteException e2) {
                }
            }
            remoteCallbackList = this.mListeners;
            remoteCallbackList.finishBroadcast();
        }
    }
}

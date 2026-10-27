package com.spotify.connectivity.platformconnectiontype;

import com.spotify.connectivity.connectiontype.ConnectionType;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class ConnectivityListener {
    protected final List<ConnectivityObserver> mConnectivityObservers = new CopyOnWriteArrayList();

    public abstract void destroy();

    public abstract ConnectionType getConnectionType();

    public abstract void notifyObservers();

    public void registerConnectivityObserver(ConnectivityObserver connectivityObserver) {
        synchronized (this.mConnectivityObservers) {
            try {
                if (this.mConnectivityObservers.isEmpty()) {
                    setup();
                }
                this.mConnectivityObservers.add(connectivityObserver);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public abstract void setup();

    public void unregisterConnectivityObserver(ConnectivityObserver connectivityObserver) {
        synchronized (this.mConnectivityObservers) {
            try {
                this.mConnectivityObservers.remove(connectivityObserver);
                if (this.mConnectivityObservers.isEmpty()) {
                    destroy();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

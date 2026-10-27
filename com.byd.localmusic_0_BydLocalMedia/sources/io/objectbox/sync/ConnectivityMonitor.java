package io.objectbox.sync;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public abstract class ConnectivityMonitor {

    @Nullable
    private SyncClient syncClient;

    public void onObserverRemoved() {
    }

    public void onObserverSet() {
    }

    void setObserver(SyncClient syncClient) {
        if (syncClient == null) {
            throw new IllegalArgumentException("Sync client must not be null");
        }
        this.syncClient = syncClient;
        onObserverSet();
    }

    void removeObserver() {
        this.syncClient = null;
        onObserverRemoved();
    }

    public final void notifyConnectionAvailable() {
        SyncClient syncClient = this.syncClient;
        if (syncClient != null) {
            syncClient.notifyConnectionAvailable();
        }
    }
}

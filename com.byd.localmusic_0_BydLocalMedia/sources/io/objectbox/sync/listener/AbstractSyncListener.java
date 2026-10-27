package io.objectbox.sync.listener;

import io.objectbox.sync.SyncChange;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractSyncListener implements SyncListener {
    @Override // io.objectbox.sync.listener.SyncConnectionListener
    public void onDisconnected() {
    }

    @Override // io.objectbox.sync.listener.SyncLoginListener
    public void onLoggedIn() {
    }

    @Override // io.objectbox.sync.listener.SyncLoginListener
    public void onLoginFailed(long j) {
    }

    @Override // io.objectbox.sync.listener.SyncTimeListener
    public void onServerTimeUpdate(long j) {
    }

    @Override // io.objectbox.sync.listener.SyncChangeListener
    public void onSyncChanges(SyncChange[] syncChangeArr) {
    }

    @Override // io.objectbox.sync.listener.SyncCompletedListener
    public void onUpdatesCompleted() {
    }
}

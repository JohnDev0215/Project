package io.objectbox.sync.server;

import io.objectbox.BoxStore;
import io.objectbox.sync.SyncCredentials;
import io.objectbox.sync.listener.SyncChangeListener;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class SyncServerBuilder {
    final BoxStore boxStore;

    @Nullable
    String certificatePath;
    SyncChangeListener changeListener;
    final List<SyncCredentials> credentials = new ArrayList();
    final List<PeerInfo> peers = new ArrayList();
    final String url;

    public SyncServerBuilder(BoxStore boxStore, String str, SyncCredentials syncCredentials) {
        checkNotNull(boxStore, "BoxStore is required.");
        checkNotNull(str, "Sync server URL is required.");
        checkNotNull(syncCredentials, "Authenticator credentials are required.");
        if (!BoxStore.isSyncServerAvailable()) {
            throw new IllegalStateException("This library does not include ObjectBox Sync Server. Please visit https://objectbox.io/sync/ for options.");
        }
        this.boxStore = boxStore;
        this.url = str;
        authenticatorCredentials(syncCredentials);
    }

    public SyncServerBuilder certificatePath(String str) {
        this.certificatePath = str;
        return this;
    }

    public SyncServerBuilder authenticatorCredentials(SyncCredentials syncCredentials) {
        checkNotNull(syncCredentials, "Authenticator credentials must not be null.");
        this.credentials.add(syncCredentials);
        return this;
    }

    public SyncServerBuilder changeListener(SyncChangeListener syncChangeListener) {
        this.changeListener = syncChangeListener;
        return this;
    }

    public SyncServerBuilder peer(String str) {
        return peer(str, SyncCredentials.none());
    }

    public SyncServerBuilder peer(String str, SyncCredentials syncCredentials) {
        this.peers.add(new PeerInfo(str, syncCredentials));
        return this;
    }

    public SyncServer build() {
        if (this.credentials.isEmpty()) {
            throw new IllegalStateException("At least one authenticator is required.");
        }
        return new SyncServerImpl(this);
    }

    public SyncServer buildAndStart() {
        SyncServer syncServerBuild = build();
        syncServerBuild.start();
        return syncServerBuild;
    }

    private void checkNotNull(Object obj, String str) {
        if (obj == null) {
            throw new IllegalArgumentException(str);
        }
    }
}

package io.objectbox.sync.server;

import io.objectbox.sync.SyncCredentials;

/* JADX INFO: loaded from: classes5.dex */
class PeerInfo {
    SyncCredentials credentials;
    String url;

    PeerInfo(String str, SyncCredentials syncCredentials) {
        this.url = str;
        this.credentials = syncCredentials;
    }
}

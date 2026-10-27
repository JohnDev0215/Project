package com.spotify.connectivity;

import com.spotify.connectivity.auth.SerializableCredentialsTransferManager;

/* JADX INFO: loaded from: classes.dex */
public interface ApplicationScope {
    SerializableCredentialsTransferManager getSerializableCredentialsTransferManager();

    void prepareForShutdown();
}

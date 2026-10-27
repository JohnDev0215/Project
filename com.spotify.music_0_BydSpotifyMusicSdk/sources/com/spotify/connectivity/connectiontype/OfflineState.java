package com.spotify.connectivity.connectiontype;

/* JADX INFO: loaded from: classes.dex */
public interface OfflineState {

    public enum State {
        ONLINE,
        OFFLINE,
        RECONNECTING,
        FORCED_OFFLINE
    }

    boolean offline();

    State offlineState();
}

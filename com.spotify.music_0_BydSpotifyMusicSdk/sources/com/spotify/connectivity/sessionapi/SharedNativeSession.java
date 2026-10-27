package com.spotify.connectivity.sessionapi;

import com.spotify.connectivity.auth.NativeSession;

/* JADX INFO: loaded from: classes.dex */
public interface SharedNativeSession {
    NativeSession acquire();

    boolean alive();

    void release();
}

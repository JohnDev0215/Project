package com.spotify.cosmos.cosmos;

/* JADX INFO: loaded from: classes.dex */
public interface ResolveCallback {
    void onError(Throwable th);

    void onResolved(Response response);
}

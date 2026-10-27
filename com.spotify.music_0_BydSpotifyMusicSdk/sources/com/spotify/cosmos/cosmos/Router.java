package com.spotify.cosmos.cosmos;

/* JADX INFO: loaded from: classes.dex */
public interface Router {
    void destroy();

    Lifetime resolve(Request request, ResolveCallback resolveCallback);
}

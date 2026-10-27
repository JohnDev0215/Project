package com.spotify.connectivity.connectiontypeflags;

import p.e32;
import p.he2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypeFlagsServiceDependencies {
    private final e32 remoteConfigUnauthResolverApi;
    private final he2 sharedPrefs;

    public ConnectionTypeFlagsServiceDependencies(e32 e32Var, he2 he2Var) {
        v03.j(e32Var, "remoteConfigUnauthResolverApi");
        v03.j(he2Var, "sharedPrefs");
        this.sharedPrefs = he2Var;
    }

    public final e32 getRemoteConfigUnauthResolverApi() {
        return null;
    }

    public final he2 getSharedPrefs() {
        return this.sharedPrefs;
    }
}

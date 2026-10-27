package com.spotify.connectivity.connectiontypeflags;

import p.he2;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypePropertiesWriter_Factory implements ul0 {
    private final xy1 sharedPreferencesProvider;

    public ConnectionTypePropertiesWriter_Factory(xy1 xy1Var) {
        this.sharedPreferencesProvider = xy1Var;
    }

    public static ConnectionTypePropertiesWriter_Factory create(xy1 xy1Var) {
        return new ConnectionTypePropertiesWriter_Factory(xy1Var);
    }

    public static ConnectionTypePropertiesWriter newInstance(he2 he2Var) {
        return new ConnectionTypePropertiesWriter(he2Var);
    }

    @Override // p.xy1
    public ConnectionTypePropertiesWriter get() {
        return newInstance((he2) this.sharedPreferencesProvider.get());
    }
}

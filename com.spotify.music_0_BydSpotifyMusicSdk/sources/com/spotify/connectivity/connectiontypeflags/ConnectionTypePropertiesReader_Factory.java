package com.spotify.connectivity.connectiontypeflags;

import p.he2;
import p.ul0;
import p.xy1;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypePropertiesReader_Factory implements ul0 {
    private final xy1 sharedPreferencesProvider;

    public ConnectionTypePropertiesReader_Factory(xy1 xy1Var) {
        this.sharedPreferencesProvider = xy1Var;
    }

    public static ConnectionTypePropertiesReader_Factory create(xy1 xy1Var) {
        return new ConnectionTypePropertiesReader_Factory(xy1Var);
    }

    public static ConnectionTypePropertiesReader newInstance(he2 he2Var) {
        return new ConnectionTypePropertiesReader(he2Var);
    }

    @Override // p.xy1
    public ConnectionTypePropertiesReader get() {
        return newInstance((he2) this.sharedPreferencesProvider.get());
    }
}

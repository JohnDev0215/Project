package com.spotify.connectivity.connectiontypeflags;

import kotlin.a;
import p.he2;
import p.py0;
import p.v03;
import p.v81;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypePropertiesReader {
    private final v81 netCapabilitiesValidatedDisabled$delegate;
    private final he2 sharedPreferences;
    private final v81 shouldUseSingleThread$delegate;

    public ConnectionTypePropertiesReader(he2 he2Var) {
        v03.j(he2Var, "sharedPreferences");
        this.sharedPreferences = he2Var;
        this.netCapabilitiesValidatedDisabled$delegate = a.c(new py0() { // from class: com.spotify.connectivity.connectiontypeflags.ConnectionTypePropertiesReader$netCapabilitiesValidatedDisabled$2
            {
                super(0);
            }

            @Override // p.py0
            public final Boolean invoke() {
                return Boolean.valueOf(((com.spotify.appstorage.sharedpreferencesimpl.a) this.this$0.sharedPreferences).b(ConnectionTypePropertiesKt.netCapabilitiesValidatedDisabledKey));
            }
        });
        this.shouldUseSingleThread$delegate = a.c(new py0() { // from class: com.spotify.connectivity.connectiontypeflags.ConnectionTypePropertiesReader$shouldUseSingleThread$2
            {
                super(0);
            }

            @Override // p.py0
            public final Boolean invoke() {
                return Boolean.valueOf(((com.spotify.appstorage.sharedpreferencesimpl.a) this.this$0.sharedPreferences).b(ConnectionTypePropertiesKt.shouldUseSingleThreadKey));
            }
        });
    }

    public final boolean getNetCapabilitiesValidatedDisabled() {
        return ((Boolean) this.netCapabilitiesValidatedDisabled$delegate.getValue()).booleanValue();
    }

    public final boolean getShouldUseSingleThread() {
        return ((Boolean) this.shouldUseSingleThread$delegate.getValue()).booleanValue();
    }
}

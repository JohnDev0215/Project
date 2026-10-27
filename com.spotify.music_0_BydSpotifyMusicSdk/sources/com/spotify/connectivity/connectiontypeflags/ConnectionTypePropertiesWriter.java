package com.spotify.connectivity.connectiontypeflags;

import com.spotify.appstorage.sharedpreferencesimpl.a;
import p.ge2;
import p.he2;
import p.le2;
import p.v03;

/* JADX INFO: loaded from: classes.dex */
public final class ConnectionTypePropertiesWriter {
    private final he2 sharedPreferences;

    public ConnectionTypePropertiesWriter(he2 he2Var) {
        v03.j(he2Var, "sharedPreferences");
        this.sharedPreferences = he2Var;
    }

    public final void clearNetCapabilitiesValidatedDisabled() {
        le2 le2VarA = ((a) this.sharedPreferences).a();
        ge2 ge2Var = ConnectionTypePropertiesKt.netCapabilitiesValidatedDisabledKey;
        v03.j(ge2Var, "key");
        le2VarA.a.remove(ge2Var.a);
        le2VarA.a();
    }

    public final void clearShouldUseSingleThread() {
        le2 le2VarA = ((a) this.sharedPreferences).a();
        ge2 ge2Var = ConnectionTypePropertiesKt.shouldUseSingleThreadKey;
        v03.j(ge2Var, "key");
        le2VarA.a.remove(ge2Var.a);
        le2VarA.a();
    }

    public final void storeNetCapabilitiesValidatedDisabled(boolean z) {
        le2 le2VarA = ((a) this.sharedPreferences).a();
        ge2 ge2Var = ConnectionTypePropertiesKt.netCapabilitiesValidatedDisabledKey;
        v03.j(ge2Var, "key");
        le2VarA.a.putBoolean(ge2Var.a, z);
        le2VarA.a();
    }

    public final void storeShouldUseSingleThread(boolean z) {
        le2 le2VarA = ((a) this.sharedPreferences).a();
        ge2 ge2Var = ConnectionTypePropertiesKt.shouldUseSingleThreadKey;
        v03.j(ge2Var, "key");
        le2VarA.a.putBoolean(ge2Var.a, z);
        le2VarA.a();
    }
}

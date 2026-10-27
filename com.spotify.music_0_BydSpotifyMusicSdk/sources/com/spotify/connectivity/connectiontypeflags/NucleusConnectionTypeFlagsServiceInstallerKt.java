package com.spotify.connectivity.connectiontypeflags;

import p.a51;
import p.he2;
import p.py0;
import p.sb2;
import p.u4;
import p.v03;
import p.xz;

/* JADX INFO: loaded from: classes.dex */
public final class NucleusConnectionTypeFlagsServiceInstallerKt {
    public static final a51 installConnectionTypeFlagsService(xz xzVar, final py0 py0Var, final py0 py0Var2) {
        v03.j(xzVar, "contextRuntime");
        v03.j(py0Var, "remoteConfigUnauthResolverApi");
        v03.j(py0Var2, "sharedPrefs");
        new py0() { // from class: com.spotify.connectivity.connectiontypeflags.NucleusConnectionTypeFlagsServiceInstallerKt.installConnectionTypeFlagsService.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final sb2 invoke() {
                u4.z(py0Var.invoke());
                return ConnectionTypeFlagsServiceKt.createConnectionTypeFlagsService(new ConnectionTypeFlagsServiceDependencies(null, (he2) py0Var2.invoke()));
            }
        };
        xzVar.a();
        return null;
    }
}

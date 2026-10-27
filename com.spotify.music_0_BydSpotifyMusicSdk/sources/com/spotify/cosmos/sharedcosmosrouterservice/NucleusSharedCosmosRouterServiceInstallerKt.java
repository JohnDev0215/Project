package com.spotify.cosmos.sharedcosmosrouterservice;

import p.a51;
import p.py0;
import p.r10;
import p.sb2;
import p.v03;
import p.xz;

/* JADX INFO: loaded from: classes.dex */
public final class NucleusSharedCosmosRouterServiceInstallerKt {
    public static final a51 installSharedCosmosRouterService(xz xzVar, final py0 py0Var) {
        v03.j(xzVar, "contextRuntime");
        v03.j(py0Var, "coreThreadingApi");
        new py0() { // from class: com.spotify.cosmos.sharedcosmosrouterservice.NucleusSharedCosmosRouterServiceInstallerKt.installSharedCosmosRouterService.1
            {
                super(0);
            }

            @Override // p.py0
            public final sb2 invoke() {
                return SharedCosmosRouterServiceKt.createSharedCosmosRouterService(new SharedCosmosRouterServiceDependencies((r10) py0Var.invoke()));
            }
        };
        xzVar.a();
        return null;
    }
}

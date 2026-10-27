package com.spotify.connectivity.productstateesperanto;

import com.spotify.connectivity.loginflowrolloutapi.LoginFlowRollout;
import p.a51;
import p.py0;
import p.r00;
import p.sb2;
import p.u4;
import p.v03;
import p.xz;

/* JADX INFO: loaded from: classes.dex */
public final class NucleusProductStateUpdateServiceInstallerKt {
    public static final a51 installProductStateUpdateService(xz xzVar, final py0 py0Var, final py0 py0Var2, final py0 py0Var3) {
        v03.j(xzVar, "contextRuntime");
        v03.j(py0Var, "loginFlowRollout");
        v03.j(py0Var2, "coreFullSessionApi");
        v03.j(py0Var3, "productStateProvider");
        new py0() { // from class: com.spotify.connectivity.productstateesperanto.NucleusProductStateUpdateServiceInstallerKt.installProductStateUpdateService.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            @Override // p.py0
            public final sb2 invoke() {
                LoginFlowRollout loginFlowRollout = (LoginFlowRollout) py0Var.invoke();
                r00 r00Var = (r00) py0Var2.invoke();
                u4.z(py0Var3.invoke());
                return new ProductStateUpdateService(loginFlowRollout, r00Var, null);
            }
        };
        xzVar.a();
        return null;
    }
}

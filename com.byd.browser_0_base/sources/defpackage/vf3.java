package defpackage;

import android.os.Bundle;
import defpackage.wf3;

/* JADX INFO: compiled from: TrustedWebActivityClientWrappers.java */
/* JADX INFO: loaded from: classes.dex */
public final class vf3 implements wf3.a {
    public final /* synthetic */ dg3 a;

    public vf3(dg3 dg3Var) {
        this.a = dg3Var;
    }

    public final Bundle a(String str, Bundle bundle, vd1 vd1Var) {
        dg3 dg3Var = this.a;
        dg3Var.getClass();
        cg3 cg3Var = vd1Var == null ? null : new cg3(vd1Var);
        return dg3Var.a.extraCommand(str, bundle, cg3Var != null ? cg3Var.asBinder() : null);
    }
}

package defpackage;

import android.os.Bundle;
import defpackage.sf3;

/* JADX INFO: compiled from: TrustedWebActivityClient.java */
/* JADX INFO: loaded from: classes.dex */
public final class pf3 implements sf3.a {
    public final /* synthetic */ sf3.b e;

    public pf3(y91 y91Var) {
        this.e = y91Var;
    }

    @Override // sf3.a
    public final void c() {
        this.e.c();
    }

    @Override // sf3.a
    public final void f(ft1 ft1Var, vf3 vf3Var) {
        Bundle bundleA = vf3Var.a("checkAndroidLocationPermission", Bundle.EMPTY, new of3(this, vf3Var));
        if (bundleA == null || !bundleA.getBoolean("success")) {
            this.e.a(vf3Var.a.b, 2);
        }
    }
}

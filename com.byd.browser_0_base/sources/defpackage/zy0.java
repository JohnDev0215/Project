package defpackage;

import android.content.Intent;
import android.os.Bundle;

/* JADX INFO: compiled from: IncognitoRestoreAppLaunchDrawBlocker.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zy0 {
    public final nw2<Bundle> a;
    public final zp1<y13> b;
    public final q2 c;
    public final nw2<Intent> d;
    public final nw2<Boolean> e;
    public final Runnable f;
    public boolean g;
    public final a h;
    public final b i;
    public final se3 j;
    public boolean k;

    /* JADX INFO: compiled from: IncognitoRestoreAppLaunchDrawBlocker.java */
    public class a implements hk1 {
        public a() {
        }

        @Override // defpackage.hk1
        public final void G0() {
            zy0 zy0Var = zy0.this;
            zy0Var.k = true;
            zy0.a(zy0Var);
        }
    }

    /* JADX INFO: compiled from: IncognitoRestoreAppLaunchDrawBlocker.java */
    public class b implements e23 {
        public b() {
        }

        @Override // defpackage.e23
        public final void g() {
            zy0.a(zy0.this);
        }
    }

    public zy0(nw2 nw2Var, zp1 zp1Var, ks ksVar, ls lsVar, r2 r2Var, f52 f52Var) {
        a aVar = new a();
        this.h = aVar;
        this.i = new b();
        se3 se3Var = new se3(2, this);
        this.j = se3Var;
        this.a = nw2Var;
        this.b = zp1Var;
        this.d = ksVar;
        this.e = lsVar;
        this.c = r2Var;
        this.f = f52Var;
        r2Var.f(aVar);
        zp1Var.p(se3Var);
    }

    public static void a(zy0 zy0Var) {
        zp1<y13> zp1Var = zy0Var.b;
        if (zp1Var.f() && zp1Var.get().t() && zy0Var.k && !zy0Var.g) {
            zy0Var.g = true;
            zy0Var.f.run();
        }
    }
}

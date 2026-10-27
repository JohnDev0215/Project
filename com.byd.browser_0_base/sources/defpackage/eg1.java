package defpackage;

import java.util.Map;

/* JADX INFO: compiled from: MergedWebappInfo.java */
/* JADX INFO: loaded from: classes.dex */
public final class eg1 extends ls3 {
    public final ls3 c;
    public boolean d;
    public boolean e;

    public eg1(ls3 ls3Var, ms3 ms3Var) {
        super(ms3Var);
        this.c = ls3Var;
    }

    @Override // defpackage.ls3
    public final String a() {
        return this.c.a();
    }

    @Override // defpackage.ls3
    public final ks3 g() {
        return this.e ? this.c.f().d : super.g();
    }

    @Override // defpackage.ls3
    public final Map<String, String> h() {
        return this.e ? this.c.e().j : super.h();
    }

    @Override // defpackage.ls3
    public final boolean k() {
        return this.e ? this.c.f().m : super.k();
    }

    @Override // defpackage.ls3
    public final boolean l() {
        return this.e ? this.c.f().l : super.l();
    }

    @Override // defpackage.ls3
    public final String p() {
        return this.d ? this.c.f().e : super.p();
    }

    @Override // defpackage.ls3
    public final String r() {
        return this.d ? this.c.f().f : super.r();
    }
}

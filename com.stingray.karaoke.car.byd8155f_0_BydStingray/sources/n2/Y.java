package n2;

import e2.C1192A;
import e2.C1194b;
import e2.c0;
import e2.e0;
import e2.f0;

/* JADX INFO: loaded from: classes.dex */
public final class Y extends u2.r {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final /* synthetic */ int f19609f = 0;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f19610g;

    public Y(f0 f0Var, e2.F f10) {
        super(f0Var);
        this.f19610g = f10;
    }

    @Override // u2.r, e2.f0
    public c0 f(int i7, c0 c0Var, boolean z6) {
        switch (this.f19609f) {
            case 0:
                f0 f0Var = this.f22896e;
                c0 c0VarF = f0Var.f(i7, c0Var, z6);
                if (f0Var.m(c0VarF.c, (e0) this.f19610g, 0L).a()) {
                    c0VarF.i(c0Var.f14059a, c0Var.f14060b, c0Var.c, c0Var.d, c0Var.f14061e, C1194b.f14037f, true);
                } else {
                    c0VarF.f14062f = true;
                }
                return c0VarF;
            default:
                return super.f(i7, c0Var, z6);
        }
    }

    @Override // u2.r, e2.f0
    public e0 m(int i7, e0 e0Var, long j3) {
        switch (this.f19609f) {
            case 1:
                super.m(i7, e0Var, j3);
                e2.F f10 = (e2.F) this.f19610g;
                e0Var.c = f10;
                C1192A c1192a = f10.f13879b;
                e0Var.getClass();
                return e0Var;
            default:
                return super.m(i7, e0Var, j3);
        }
    }

    public Y(f0 f0Var) {
        super(f0Var);
        this.f19610g = new e0();
    }
}

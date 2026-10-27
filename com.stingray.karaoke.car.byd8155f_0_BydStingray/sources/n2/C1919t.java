package n2;

import e2.C1207o;
import e2.C1208p;
import e2.q0;
import l5.C1778f;
import o2.C2024a;

/* JADX INFO: renamed from: n2.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class C1919t implements h2.l {
    public final /* synthetic */ int l = 0;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ q0 f19722m;

    public /* synthetic */ C1919t(q0 q0Var) {
        this.f19722m = q0Var;
    }

    @Override // h2.l
    public final void b(Object obj) {
        switch (this.l) {
            case 0:
                ((e2.U) obj).M(this.f19722m);
                break;
            default:
                o2.j jVar = (o2.j) obj;
                C1778f c1778f = jVar.o;
                q0 q0Var = this.f19722m;
                if (c1778f != null) {
                    C1208p c1208p = (C1208p) c1778f.f18638m;
                    if (c1208p.f14323v == -1) {
                        C1207o c1207oA = c1208p.a();
                        c1207oA.f14252t = q0Var.f14332a;
                        c1207oA.f14253u = q0Var.f14333b;
                        jVar.o = new C1778f(new C1208p(c1207oA), 2, (String) c1778f.f18639n);
                    }
                }
                int i7 = q0Var.f14332a;
                break;
        }
    }

    public /* synthetic */ C1919t(C2024a c2024a, q0 q0Var) {
        this.f19722m = q0Var;
    }
}

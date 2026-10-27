package androidx.compose.ui.graphics.vector;

import E0.H;
import T.C0732b0;
import T.C0735d;
import T.C0738e0;
import T.S;
import a1.k;
import e0.C1183w;
import h2.AbstractC1347d;
import h3.y;
import kotlin.Metadata;
import l3.C1764u;
import m0.f;
import n0.C1883l;
import p0.C2071b;
import r0.AbstractC2268a;
import s0.C2329C;
import s0.C2335b;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u0001¨\u0006\u0002"}, d2 = {"Landroidx/compose/ui/graphics/vector/VectorPainter;", "Lr0/a;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final class VectorPainter extends AbstractC2268a {

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0738e0 f11597q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0738e0 f11598r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C2329C f11599s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0732b0 f11600t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public float f11601u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public C1883l f11602v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f11603w;

    public VectorPainter(C2335b c2335b) {
        f fVar = new f(0L);
        S s6 = S.f8224q;
        this.f11597q = C0735d.O(fVar, s6);
        this.f11598r = C0735d.O(Boolean.FALSE, s6);
        C2329C c2329c = new C2329C(c2335b);
        c2329c.f21959f = new C1183w(this, 8);
        this.f11599s = c2329c;
        this.f11600t = C0735d.M(0);
        this.f11601u = 1.0f;
        this.f11603w = -1;
    }

    @Override // r0.AbstractC2268a
    public final boolean b(float f10) {
        this.f11601u = f10;
        return true;
    }

    @Override // r0.AbstractC2268a
    public final boolean e(C1883l c1883l) {
        this.f11602v = c1883l;
        return true;
    }

    @Override // r0.AbstractC2268a
    public final long h() {
        return ((f) this.f11597q.getValue()).f18890a;
    }

    @Override // r0.AbstractC2268a
    public final void i(H h10) {
        C1883l c1883l = this.f11602v;
        C2329C c2329c = this.f11599s;
        if (c1883l == null) {
            c1883l = (C1883l) c2329c.f21960g.getValue();
        }
        if (((Boolean) this.f11598r.getValue()).booleanValue() && h10.getLayoutDirection() == k.f11038m) {
            C2071b c2071b = h10.l;
            long jS = c2071b.S();
            y yVar = c2071b.f20638m;
            long jY = yVar.y();
            yVar.m().m();
            try {
                ((C1764u) yVar.f15499m).T(-1.0f, 1.0f, jS);
                c2329c.e(h10, this.f11601u, c1883l);
            } finally {
                AbstractC1347d.o(yVar, jY);
            }
        } else {
            c2329c.e(h10, this.f11601u, c1883l);
        }
        this.f11603w = this.f11600t.i();
    }
}

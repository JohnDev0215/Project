package androidx.compose.ui.draw;

import A7.m;
import E0.AbstractC0156f;
import E0.V;
import E0.d0;
import S0.p;
import a1.e;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import n0.C1885n;
import n0.C1891t;
import n0.InterfaceC1866P;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0080\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/draw/ShadowGraphicsLayerElement;", "LE0/V;", "Ln0/n;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
public final /* data */ class ShadowGraphicsLayerElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11558a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC1866P f11559b;
    public final boolean c;
    public final long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f11560e;

    public ShadowGraphicsLayerElement(float f10, InterfaceC1866P interfaceC1866P, boolean z6, long j3, long j10) {
        this.f11558a = f10;
        this.f11559b = interfaceC1866P;
        this.c = z6;
        this.d = j3;
        this.f11560e = j10;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ShadowGraphicsLayerElement)) {
            return false;
        }
        ShadowGraphicsLayerElement shadowGraphicsLayerElement = (ShadowGraphicsLayerElement) obj;
        return e.a(this.f11558a, shadowGraphicsLayerElement.f11558a) && m.a(this.f11559b, shadowGraphicsLayerElement.f11559b) && this.c == shadowGraphicsLayerElement.c && C1891t.c(this.d, shadowGraphicsLayerElement.d) && C1891t.c(this.f11560e, shadowGraphicsLayerElement.f11560e);
    }

    public final int hashCode() {
        int iD = AbstractC1347d.d((this.f11559b.hashCode() + (Float.hashCode(this.f11558a) * 31)) * 31, 31, this.c);
        int i7 = C1891t.l;
        return Long.hashCode(this.f11560e) + AbstractC1347d.c(iD, 31, this.d);
    }

    @Override // E0.V
    public final n i() {
        return new C1885n(new p(this, 26));
    }

    @Override // E0.V
    public final void l(n nVar) {
        C1885n c1885n = (C1885n) nVar;
        c1885n.f19442y = new p(this, 26);
        d0 d0Var = AbstractC0156f.r(c1885n, 2).f1649x;
        if (d0Var != null) {
            d0Var.l1(c1885n.f19442y, true);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("ShadowGraphicsLayerElement(elevation=");
        sb.append((Object) e.b(this.f11558a));
        sb.append(", shape=");
        sb.append(this.f11559b);
        sb.append(", clip=");
        sb.append(this.c);
        sb.append(", ambientColor=");
        AbstractC1347d.n(this.d, ", spotColor=", sb);
        sb.append((Object) C1891t.i(this.f11560e));
        sb.append(')');
        return sb.toString();
    }
}

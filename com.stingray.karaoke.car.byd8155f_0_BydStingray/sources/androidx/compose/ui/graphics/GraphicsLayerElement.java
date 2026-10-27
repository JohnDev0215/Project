package androidx.compose.ui.graphics;

import A7.m;
import E0.AbstractC0156f;
import E0.V;
import E0.d0;
import S0.p;
import g0.n;
import h2.AbstractC1347d;
import kotlin.Metadata;
import n0.AbstractC1861K;
import n0.C1867Q;
import n0.C1870U;
import n0.C1891t;
import n0.InterfaceC1866P;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b\u0082\b\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001¨\u0006\u0003"}, d2 = {"Landroidx/compose/ui/graphics/GraphicsLayerElement;", "LE0/V;", "Ln0/Q;", "ui_release"}, k = 1, mv = {1, 8, 0}, xi = 48)
final /* data */ class GraphicsLayerElement extends V {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final float f11575a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final float f11576b;
    public final float c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f11577e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f11578f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f11579g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final float f11580h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final float f11581i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f11582j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final long f11583k;
    public final InterfaceC1866P l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final boolean f11584m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f11585n;
    public final long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final int f11586p;

    public GraphicsLayerElement(float f10, float f11, float f12, float f13, float f14, float f15, float f16, float f17, float f18, float f19, long j3, InterfaceC1866P interfaceC1866P, boolean z6, long j10, long j11, int i7) {
        this.f11575a = f10;
        this.f11576b = f11;
        this.c = f12;
        this.d = f13;
        this.f11577e = f14;
        this.f11578f = f15;
        this.f11579g = f16;
        this.f11580h = f17;
        this.f11581i = f18;
        this.f11582j = f19;
        this.f11583k = j3;
        this.l = interfaceC1866P;
        this.f11584m = z6;
        this.f11585n = j10;
        this.o = j11;
        this.f11586p = i7;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof GraphicsLayerElement)) {
            return false;
        }
        GraphicsLayerElement graphicsLayerElement = (GraphicsLayerElement) obj;
        return Float.compare(this.f11575a, graphicsLayerElement.f11575a) == 0 && Float.compare(this.f11576b, graphicsLayerElement.f11576b) == 0 && Float.compare(this.c, graphicsLayerElement.c) == 0 && Float.compare(this.d, graphicsLayerElement.d) == 0 && Float.compare(this.f11577e, graphicsLayerElement.f11577e) == 0 && Float.compare(this.f11578f, graphicsLayerElement.f11578f) == 0 && Float.compare(this.f11579g, graphicsLayerElement.f11579g) == 0 && Float.compare(this.f11580h, graphicsLayerElement.f11580h) == 0 && Float.compare(this.f11581i, graphicsLayerElement.f11581i) == 0 && Float.compare(this.f11582j, graphicsLayerElement.f11582j) == 0 && C1870U.a(this.f11583k, graphicsLayerElement.f11583k) && m.a(this.l, graphicsLayerElement.l) && this.f11584m == graphicsLayerElement.f11584m && m.a(null, null) && C1891t.c(this.f11585n, graphicsLayerElement.f11585n) && C1891t.c(this.o, graphicsLayerElement.o) && AbstractC1861K.q(this.f11586p, graphicsLayerElement.f11586p);
    }

    public final int hashCode() {
        int iB = AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(AbstractC1347d.b(Float.hashCode(this.f11575a) * 31, 31, this.f11576b), 31, this.c), 31, this.d), 31, this.f11577e), 31, this.f11578f), 31, this.f11579g), 31, this.f11580h), 31, this.f11581i), 31, this.f11582j);
        int i7 = C1870U.c;
        int iD = AbstractC1347d.d((this.l.hashCode() + AbstractC1347d.c(iB, 31, this.f11583k)) * 31, 961, this.f11584m);
        int i10 = C1891t.l;
        return Integer.hashCode(this.f11586p) + AbstractC1347d.c(AbstractC1347d.c(iD, 31, this.f11585n), 31, this.o);
    }

    @Override // E0.V
    public final n i() {
        C1867Q c1867q = new C1867Q();
        c1867q.f19418y = this.f11575a;
        c1867q.f19419z = this.f11576b;
        c1867q.f19404A = this.c;
        c1867q.B = this.d;
        c1867q.f19405C = this.f11577e;
        c1867q.f19406D = this.f11578f;
        c1867q.f19407E = this.f11579g;
        c1867q.f19408F = this.f11580h;
        c1867q.f19409G = this.f11581i;
        c1867q.f19410H = this.f11582j;
        c1867q.f19411I = this.f11583k;
        c1867q.f19412J = this.l;
        c1867q.f19413K = this.f11584m;
        c1867q.f19414L = this.f11585n;
        c1867q.f19415M = this.o;
        c1867q.f19416N = this.f11586p;
        c1867q.f19417O = new p(c1867q, 27);
        return c1867q;
    }

    @Override // E0.V
    public final void l(n nVar) {
        C1867Q c1867q = (C1867Q) nVar;
        c1867q.f19418y = this.f11575a;
        c1867q.f19419z = this.f11576b;
        c1867q.f19404A = this.c;
        c1867q.B = this.d;
        c1867q.f19405C = this.f11577e;
        c1867q.f19406D = this.f11578f;
        c1867q.f19407E = this.f11579g;
        c1867q.f19408F = this.f11580h;
        c1867q.f19409G = this.f11581i;
        c1867q.f19410H = this.f11582j;
        c1867q.f19411I = this.f11583k;
        c1867q.f19412J = this.l;
        c1867q.f19413K = this.f11584m;
        c1867q.f19414L = this.f11585n;
        c1867q.f19415M = this.o;
        c1867q.f19416N = this.f11586p;
        d0 d0Var = AbstractC0156f.r(c1867q, 2).f1649x;
        if (d0Var != null) {
            d0Var.l1(c1867q.f19417O, true);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("GraphicsLayerElement(scaleX=");
        sb.append(this.f11575a);
        sb.append(", scaleY=");
        sb.append(this.f11576b);
        sb.append(", alpha=");
        sb.append(this.c);
        sb.append(", translationX=");
        sb.append(this.d);
        sb.append(", translationY=");
        sb.append(this.f11577e);
        sb.append(", shadowElevation=");
        sb.append(this.f11578f);
        sb.append(", rotationX=");
        sb.append(this.f11579g);
        sb.append(", rotationY=");
        sb.append(this.f11580h);
        sb.append(", rotationZ=");
        sb.append(this.f11581i);
        sb.append(", cameraDistance=");
        sb.append(this.f11582j);
        sb.append(", transformOrigin=");
        sb.append((Object) C1870U.d(this.f11583k));
        sb.append(", shape=");
        sb.append(this.l);
        sb.append(", clip=");
        sb.append(this.f11584m);
        sb.append(", renderEffect=null, ambientShadowColor=");
        AbstractC1347d.n(this.f11585n, ", spotShadowColor=", sb);
        sb.append((Object) C1891t.i(this.o));
        sb.append(", compositingStrategy=");
        sb.append((Object) ("CompositingStrategy(value=" + this.f11586p + ')'));
        sb.append(')');
        return sb.toString();
    }
}

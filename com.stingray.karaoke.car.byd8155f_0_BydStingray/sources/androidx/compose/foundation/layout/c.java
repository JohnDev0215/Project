package androidx.compose.foundation.layout;

import A.G0;
import A7.m;
import T5.h;
import g0.e;
import g0.f;
import g0.g;
import g0.o;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final FillElement f11475a = new FillElement(1.0f, 2);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final FillElement f11476b = new FillElement(1.0f, 1);
    public static final FillElement c = new FillElement(1.0f, 3);
    public static final WrapContentElement d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final WrapContentElement f11477e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final WrapContentElement f11478f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final WrapContentElement f11479g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final WrapContentElement f11480h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final WrapContentElement f11481i;

    static {
        e eVar = g0.b.f14673y;
        d = new WrapContentElement(2, false, new G0(eVar, 3), eVar);
        e eVar2 = g0.b.f14672x;
        f11477e = new WrapContentElement(2, false, new G0(eVar2, 3), eVar2);
        f fVar = g0.b.f14670v;
        f11478f = new WrapContentElement(1, false, new G0(fVar, 1), fVar);
        f fVar2 = g0.b.f14669u;
        f11479g = new WrapContentElement(1, false, new G0(fVar2, 1), fVar2);
        g gVar = g0.b.f14664p;
        f11480h = new WrapContentElement(3, false, new G0(gVar, 2), gVar);
        g gVar2 = g0.b.l;
        f11481i = new WrapContentElement(3, false, new G0(gVar2, 2), gVar2);
    }

    public static final o a(o oVar, float f10, float f11) {
        return oVar.k(new UnspecifiedConstraintsElement(f10, f11));
    }

    public static final o b(o oVar, float f10) {
        return oVar.k(f10 == 1.0f ? f11476b : new FillElement(f10, 1));
    }

    public static final o c(o oVar, float f10) {
        return oVar.k(f10 == 1.0f ? c : new FillElement(f10, 3));
    }

    public static final o d(o oVar, float f10) {
        return oVar.k(f10 == 1.0f ? f11475a : new FillElement(f10, 2));
    }

    public static final o e(o oVar, float f10) {
        return oVar.k(new SizeElement(0.0f, f10, 0.0f, f10, true, 5));
    }

    public static final o f(o oVar, float f10, float f11) {
        return oVar.k(new SizeElement(0.0f, f10, 0.0f, f11, true, 5));
    }

    public static /* synthetic */ o g(o oVar, float f10, float f11, int i7) {
        if ((i7 & 1) != 0) {
            f10 = Float.NaN;
        }
        if ((i7 & 2) != 0) {
            f11 = Float.NaN;
        }
        return f(oVar, f10, f11);
    }

    public static o h(o oVar, float f10) {
        return oVar.k(new SizeElement(0.0f, f10, 0.0f, Float.NaN, false, 5));
    }

    public static final o i(o oVar, float f10, float f11) {
        return oVar.k(new SizeElement(f10, f11, f10, f11, false));
    }

    public static o j(o oVar, float f10, float f11, float f12, float f13, int i7) {
        return oVar.k(new SizeElement(f10, (i7 & 2) != 0 ? Float.NaN : f11, (i7 & 4) != 0 ? Float.NaN : f12, (i7 & 8) != 0 ? Float.NaN : f13, false));
    }

    public static final o k(float f10) {
        return new SizeElement(h.f8717a, 0.0f, f10, 0.0f, false, 10);
    }

    public static final o l(o oVar, float f10) {
        return oVar.k(new SizeElement(f10, f10, f10, f10, true));
    }

    public static final o m(o oVar, float f10, float f11) {
        return oVar.k(new SizeElement(f10, f11, f10, f11, true));
    }

    public static final o n(o oVar, float f10, float f11, float f12, float f13) {
        return oVar.k(new SizeElement(f10, f11, f12, f13, true));
    }

    public static final o o(o oVar, float f10) {
        return oVar.k(new SizeElement(f10, 0.0f, f10, 0.0f, true, 10));
    }

    public static final o p(o oVar, float f10, float f11) {
        return oVar.k(new SizeElement(f10, 0.0f, f11, 0.0f, true, 10));
    }

    public static /* synthetic */ o q(o oVar, float f10, float f11, int i7) {
        if ((i7 & 1) != 0) {
            f10 = Float.NaN;
        }
        if ((i7 & 2) != 0) {
            f11 = Float.NaN;
        }
        return p(oVar, f10, f11);
    }

    public static o r(o oVar) {
        f fVar = g0.b.f14670v;
        return oVar.k(m.a(fVar, fVar) ? f11478f : m.a(fVar, g0.b.f14669u) ? f11479g : new WrapContentElement(1, false, new G0(fVar, 1), fVar));
    }

    public static o s(o oVar, g gVar, int i7) {
        int i10 = i7 & 1;
        g gVar2 = g0.b.f14664p;
        if (i10 != 0) {
            gVar = gVar2;
        }
        return oVar.k(m.a(gVar, gVar2) ? f11480h : m.a(gVar, g0.b.l) ? f11481i : new WrapContentElement(3, false, new G0(gVar, 2), gVar));
    }

    public static o t(o oVar) {
        e eVar = g0.b.f14673y;
        return oVar.k(m.a(eVar, eVar) ? d : m.a(eVar, g0.b.f14672x) ? f11477e : new WrapContentElement(2, false, new G0(eVar, 3), eVar));
    }
}

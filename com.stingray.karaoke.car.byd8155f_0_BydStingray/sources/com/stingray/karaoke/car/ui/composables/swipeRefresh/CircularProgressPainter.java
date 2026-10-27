package com.stingray.karaoke.car.ui.composables.swipeRefresh;

import A3.v;
import E0.H;
import T.C0735d;
import T.C0738e0;
import T.S;
import a1.e;
import h2.AbstractC1347d;
import h3.y;
import h6.j;
import h6.r;
import kotlin.Metadata;
import l3.C1764u;
import m0.c;
import m0.d;
import m7.n;
import n0.C1880i;
import n0.C1891t;
import n0.InterfaceC1859I;
import p0.C2071b;
import p0.h;
import r0.AbstractC2268a;

/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lcom/stingray/karaoke/car/ui/composables/swipeRefresh/CircularProgressPainter;", "Lr0/a;", "<init>", "()V", "car_byd8155fEnvProdRelease"}, k = 1, mv = {2, 1, 0}, xi = 48)
public final class CircularProgressPainter extends AbstractC2268a {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public final C0738e0 f13020A;
    public final C0738e0 B;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C0738e0 f13021q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C0738e0 f13022r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C0738e0 f13023s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0738e0 f13024t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0738e0 f13025u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final C0738e0 f13026v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C0738e0 f13027w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final C0738e0 f13028x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public final n f13029y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public final C0738e0 f13030z;

    public CircularProgressPainter() {
        C1891t c1891t = new C1891t(C1891t.f19452k);
        S s6 = S.f8224q;
        this.f13021q = C0735d.O(c1891t, s6);
        this.f13022r = C0735d.O(Float.valueOf(1.0f), s6);
        float f10 = 0;
        this.f13023s = C0735d.O(new e(f10), s6);
        this.f13024t = C0735d.O(new e(5), s6);
        this.f13025u = C0735d.O(Boolean.FALSE, s6);
        this.f13026v = C0735d.O(new e(f10), s6);
        this.f13027w = C0735d.O(new e(f10), s6);
        this.f13028x = C0735d.O(Float.valueOf(1.0f), s6);
        this.f13029y = r.o(new v(6));
        this.f13030z = C0735d.O(Float.valueOf(0.0f), s6);
        this.f13020A = C0735d.O(Float.valueOf(0.0f), s6);
        this.B = C0735d.O(Float.valueOf(0.0f), s6);
    }

    @Override // r0.AbstractC2268a
    public final boolean b(float f10) {
        this.f13022r.setValue(Float.valueOf(f10));
        return true;
    }

    @Override // r0.AbstractC2268a
    public final long h() {
        return 9205357640488583168L;
    }

    @Override // r0.AbstractC2268a
    public final void i(H h10) throws Throwable {
        long j3;
        C0738e0 c0738e0 = this.f13024t;
        C0738e0 c0738e02 = this.B;
        float fFloatValue = ((Number) c0738e02.getValue()).floatValue();
        C2071b c2071b = h10.l;
        long jS = c2071b.S();
        y yVar = c2071b.f20638m;
        long jY = yVar.y();
        yVar.m().m();
        try {
            ((C1764u) yVar.f15499m).S(fFloatValue, jS);
            float fY = (h10.y(((e) c0738e0.getValue()).l) / 2.0f) + h10.y(((e) this.f13023s.getValue()).l);
            float fD = c.d(j.k(h10.l.e())) - fY;
            float fE = c.e(j.k(h10.l.e())) - fY;
            d dVar = new d(fD, fE, c.d(j.k(h10.l.e())) + fY, c.e(j.k(h10.l.e())) + fY);
            float f10 = 360;
            float fFloatValue2 = (((Number) this.f13030z.getValue()).floatValue() + ((Number) c0738e02.getValue()).floatValue()) * f10;
            float fFloatValue3 = ((((Number) this.f13020A.getValue()).floatValue() + ((Number) c0738e02.getValue()).floatValue()) * f10) - fFloatValue2;
            try {
                p0.d.s(h10, ((C1891t) this.f13021q.getValue()).f19453a, fFloatValue2, fFloatValue3, h6.e.a(fD, fE), j.f(dVar.c(), dVar.b()), ((Number) this.f13022r.getValue()).floatValue(), new h(h10.y(((e) c0738e0.getValue()).l), 0.0f, 2, 0, 26), 768);
                if (((Boolean) this.f13025u.getValue()).booleanValue()) {
                    j(h10, fFloatValue2, fFloatValue3, dVar);
                }
                AbstractC1347d.o(yVar, jY);
            } catch (Throwable th) {
                th = th;
                j3 = jY;
                AbstractC1347d.o(yVar, j3);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            j3 = jY;
        }
    }

    public final void j(H h10, float f10, float f11, d dVar) {
        ((C1880i) k()).e();
        ((C1880i) k()).f19436a.moveTo(0.0f, 0.0f);
        InterfaceC1859I interfaceC1859IK = k();
        C0738e0 c0738e0 = this.f13026v;
        float fY = h10.y(((e) c0738e0.getValue()).l);
        C0738e0 c0738e02 = this.f13028x;
        ((C1880i) interfaceC1859IK).f19436a.lineTo(((Number) c0738e02.getValue()).floatValue() * fY, 0.0f);
        ((C1880i) k()).f19436a.lineTo((((Number) c0738e02.getValue()).floatValue() * h10.y(((e) c0738e0.getValue()).l)) / 2, ((Number) c0738e02.getValue()).floatValue() * h10.y(((e) this.f13027w.getValue()).l));
        float fMin = Math.min(dVar.c(), dVar.b()) / 2.0f;
        float fFloatValue = (((Number) c0738e02.getValue()).floatValue() * h10.y(((e) c0738e0.getValue()).l)) / 2.0f;
        ((C1880i) k()).g(h6.e.a((c.d(dVar.a()) + fMin) - fFloatValue, (h10.y(((e) this.f13024t.getValue()).l) / 2.0f) + c.e(dVar.a())));
        ((C1880i) k()).f19436a.close();
        float f12 = f10 + f11;
        C2071b c2071b = h10.l;
        long jS = c2071b.S();
        y yVar = c2071b.f20638m;
        long jY = yVar.y();
        yVar.m().m();
        try {
            ((C1764u) yVar.f15499m).S(f12, jS);
            p0.d.U(h10, k(), ((C1891t) this.f13021q.getValue()).f19453a, ((Number) this.f13022r.getValue()).floatValue(), 56);
        } finally {
            AbstractC1347d.o(yVar, jY);
        }
    }

    public final InterfaceC1859I k() {
        return (InterfaceC1859I) this.f13029y.getValue();
    }
}

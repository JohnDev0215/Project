package s0;

import android.graphics.Path;
import n0.AbstractC1861K;
import n0.AbstractC1887p;
import n0.C1880i;
import n0.C1881j;

/* JADX INFO: renamed from: s0.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2339f extends AbstractC2327A {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public AbstractC1887p f22022b;
    public float c = 1.0f;
    public Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public float f22023e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public float f22024f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public AbstractC1887p f22025g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f22026h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public int f22027i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public float f22028j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public float f22029k;
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f22030m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public boolean f22031n;
    public boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f22032p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public p0.h f22033q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C1880i f22034r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public C1880i f22035s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final Object f22036t;

    public C2339f() {
        int i7 = AbstractC2331E.f21974a;
        this.d = n7.u.l;
        this.f22023e = 1.0f;
        this.f22026h = 0;
        this.f22027i = 0;
        this.f22028j = 4.0f;
        this.l = 1.0f;
        this.f22031n = true;
        this.o = true;
        C1880i c1880iI = AbstractC1861K.i();
        this.f22034r = c1880iI;
        this.f22035s = c1880iI;
        this.f22036t = h6.r.n(m7.g.f19257m, C2338e.f22020n);
    }

    /* JADX WARN: Type inference failed for: r0v3, types: [java.lang.Object, java.util.List] */
    @Override // s0.AbstractC2327A
    public final void a(p0.d dVar) {
        if (this.f22031n) {
            AbstractC2334a.c(this.d, this.f22034r);
            e();
        } else if (this.f22032p) {
            e();
        }
        this.f22031n = false;
        this.f22032p = false;
        AbstractC1887p abstractC1887p = this.f22022b;
        if (abstractC1887p != null) {
            p0.d.m(dVar, this.f22035s, abstractC1887p, this.c, null, 56);
        }
        AbstractC1887p abstractC1887p2 = this.f22025g;
        if (abstractC1887p2 != null) {
            p0.h hVar = this.f22033q;
            if (this.o || hVar == null) {
                hVar = new p0.h(this.f22024f, this.f22028j, this.f22026h, this.f22027i, 16);
                this.f22033q = hVar;
                this.o = false;
            }
            p0.d.m(dVar, this.f22035s, abstractC1887p2, this.f22023e, hVar, 48);
        }
    }

    /* JADX WARN: Type inference failed for: r0v10, types: [java.lang.Object, m7.f] */
    public final void e() {
        Path path;
        float f10 = this.f22029k;
        C1880i c1880i = this.f22034r;
        if (f10 == 0.0f && this.l == 1.0f) {
            this.f22035s = c1880i;
            return;
        }
        if (A7.m.a(this.f22035s, c1880i)) {
            this.f22035s = AbstractC1861K.i();
        } else {
            int i7 = this.f22035s.f19436a.getFillType() == Path.FillType.EVEN_ODD ? 1 : 0;
            this.f22035s.f19436a.rewind();
            this.f22035s.f(i7);
        }
        ?? r02 = this.f22036t;
        C1881j c1881j = (C1881j) r02.getValue();
        if (c1880i != null) {
            c1881j.getClass();
            path = c1880i.f19436a;
        } else {
            path = null;
        }
        c1881j.f19438a.setPath(path, false);
        float length = ((C1881j) r02.getValue()).f19438a.getLength();
        float f11 = this.f22029k;
        float f12 = this.f22030m;
        float f13 = ((f11 + f12) % 1.0f) * length;
        float f14 = ((this.l + f12) % 1.0f) * length;
        if (f13 <= f14) {
            ((C1881j) r02.getValue()).a(f13, f14, this.f22035s);
        } else {
            ((C1881j) r02.getValue()).a(f13, length, this.f22035s);
            ((C1881j) r02.getValue()).a(0.0f, f14, this.f22035s);
        }
    }

    public final String toString() {
        return this.f22034r.toString();
    }
}

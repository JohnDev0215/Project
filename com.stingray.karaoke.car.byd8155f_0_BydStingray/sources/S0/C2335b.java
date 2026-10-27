package s0;

import h2.AbstractC1347d;
import java.util.ArrayList;
import java.util.List;
import l3.C1764u;
import n0.AbstractC1861K;
import n0.AbstractC1887p;
import n0.C1854D;
import n0.C1868S;
import n0.C1880i;
import n0.C1891t;
import q0.C2162a;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: s0.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2335b extends AbstractC2327A {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float[] f21989b;
    public final ArrayList c = new ArrayList();
    public boolean d = true;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f21990e = C1891t.f19452k;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public List f21991f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public boolean f21992g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public C1880i f21993h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public A7.o f21994i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final C2162a f21995j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public String f21996k;
    public float l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public float f21997m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public float f21998n;
    public float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public float f21999p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public float f22000q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public float f22001r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public boolean f22002s;

    public C2335b() {
        int i7 = AbstractC2331E.f21974a;
        this.f21991f = n7.u.l;
        this.f21992g = true;
        this.f21995j = new C2162a(this, 2);
        this.f21996k = "";
        this.o = 1.0f;
        this.f21999p = 1.0f;
        this.f22002s = true;
    }

    @Override // s0.AbstractC2327A
    public final void a(p0.d dVar) {
        if (this.f22002s) {
            float[] fArrA = this.f21989b;
            if (fArrA == null) {
                fArrA = C1854D.a();
                this.f21989b = fArrA;
            } else {
                C1854D.d(fArrA);
            }
            C1854D.h(this.f22000q + this.f21997m, this.f22001r + this.f21998n, 0.0f, fArrA);
            C1854D.e(fArrA, this.l);
            C1854D.f(this.o, this.f21999p, 1.0f, fArrA);
            C1854D.h(-this.f21997m, -this.f21998n, 0.0f, fArrA);
            this.f22002s = false;
        }
        if (this.f21992g) {
            if (!this.f21991f.isEmpty()) {
                C1880i c1880iI = this.f21993h;
                if (c1880iI == null) {
                    c1880iI = AbstractC1861K.i();
                    this.f21993h = c1880iI;
                }
                AbstractC2334a.c(this.f21991f, c1880iI);
            }
            this.f21992g = false;
        }
        h3.y yVarF = dVar.F();
        long jY = yVarF.y();
        yVarF.m().m();
        try {
            C1764u c1764u = (C1764u) yVarF.f15499m;
            float[] fArr = this.f21989b;
            h3.y yVar = (h3.y) c1764u.f18608m;
            if (fArr != null) {
                yVar.m().r(fArr);
            }
            C1880i c1880i = this.f21993h;
            if (!this.f21991f.isEmpty() && c1880i != null) {
                yVar.m().p(c1880i, 1);
            }
            ArrayList arrayList = this.c;
            int size = arrayList.size();
            for (int i7 = 0; i7 < size; i7++) {
                ((AbstractC2327A) arrayList.get(i7)).a(dVar);
            }
        } finally {
            AbstractC1347d.o(yVarF, jY);
        }
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [A7.o, z7.k] */
    @Override // s0.AbstractC2327A
    public final InterfaceC2782k b() {
        return this.f21994i;
    }

    @Override // s0.AbstractC2327A
    public final void d(C2162a c2162a) {
        this.f21994i = c2162a;
    }

    public final void e(int i7, AbstractC2327A abstractC2327A) {
        ArrayList arrayList = this.c;
        if (i7 < arrayList.size()) {
            arrayList.set(i7, abstractC2327A);
        } else {
            arrayList.add(abstractC2327A);
        }
        g(abstractC2327A);
        abstractC2327A.d(this.f21995j);
        c();
    }

    public final void f(long j3) {
        if (this.d && j3 != 16) {
            long j10 = this.f21990e;
            if (j10 == 16) {
                this.f21990e = j3;
                return;
            }
            int i7 = AbstractC2331E.f21974a;
            if (C1891t.h(j10) == C1891t.h(j3) && C1891t.g(j10) == C1891t.g(j3) && C1891t.e(j10) == C1891t.e(j3)) {
                return;
            }
            this.d = false;
            this.f21990e = C1891t.f19452k;
        }
    }

    public final void g(AbstractC2327A abstractC2327A) {
        if (!(abstractC2327A instanceof C2339f)) {
            if (abstractC2327A instanceof C2335b) {
                C2335b c2335b = (C2335b) abstractC2327A;
                if (c2335b.d && this.d) {
                    f(c2335b.f21990e);
                    return;
                } else {
                    this.d = false;
                    this.f21990e = C1891t.f19452k;
                    return;
                }
            }
            return;
        }
        C2339f c2339f = (C2339f) abstractC2327A;
        AbstractC1887p abstractC1887p = c2339f.f22022b;
        if (this.d && abstractC1887p != null) {
            if (abstractC1887p instanceof C1868S) {
                f(((C1868S) abstractC1887p).f19420a);
            } else {
                this.d = false;
                this.f21990e = C1891t.f19452k;
            }
        }
        AbstractC1887p abstractC1887p2 = c2339f.f22025g;
        if (this.d && abstractC1887p2 != null) {
            if (abstractC1887p2 instanceof C1868S) {
                f(((C1868S) abstractC1887p2).f19420a);
            } else {
                this.d = false;
                this.f21990e = C1891t.f19452k;
            }
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("VGroup: ");
        sb.append(this.f21996k);
        ArrayList arrayList = this.c;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            AbstractC2327A abstractC2327A = (AbstractC2327A) arrayList.get(i7);
            sb.append("\t");
            sb.append(abstractC2327A.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}

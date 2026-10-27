package P;

import E0.H;
import K7.K;
import T.C0735d;
import T.C0738e0;
import T.InterfaceC0769u0;
import T.S;
import T.X;
import android.view.ViewGroup;
import java.util.LinkedHashMap;
import n0.AbstractC1874c;
import n0.C1891t;
import n0.InterfaceC1889r;
import p0.C2071b;
import y.a0;

/* JADX INFO: renamed from: P.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0489a implements InterfaceC0769u0, s, a0 {
    public final boolean l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final C f5208m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final boolean f5209n;
    public final float o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final X f5210p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final X f5211q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final ViewGroup f5212r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public r f5213s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C0738e0 f5214t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final C0738e0 f5215u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public long f5216v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public int f5217w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final K f5218x;

    public C0489a(boolean z6, float f10, X x8, X x9, ViewGroup viewGroup) {
        this.l = z6;
        this.f5208m = new C(new E.n(x9, 5), z6);
        this.f5209n = z6;
        this.o = f10;
        this.f5210p = x8;
        this.f5211q = x9;
        this.f5212r = viewGroup;
        S s6 = S.f8224q;
        this.f5214t = C0735d.O(null, s6);
        this.f5215u = C0735d.O(Boolean.TRUE, s6);
        this.f5216v = 0L;
        this.f5217w = -1;
        this.f5218x = new K(this, 9);
    }

    @Override // T.InterfaceC0769u0
    public final void a() {
    }

    @Override // T.InterfaceC0769u0
    public final void c() {
        r rVar = this.f5213s;
        if (rVar != null) {
            h0();
            M.t tVar = rVar.o;
            t tVar2 = (t) ((LinkedHashMap) tVar.l).get(this);
            if (tVar2 != null) {
                tVar2.c();
                LinkedHashMap linkedHashMap = (LinkedHashMap) tVar.l;
                t tVar3 = (t) linkedHashMap.get(this);
                if (tVar3 != null) {
                }
                linkedHashMap.remove(this);
                rVar.f5250n.add(tVar2);
            }
        }
    }

    @Override // T.InterfaceC0769u0
    public final void d() {
        r rVar = this.f5213s;
        if (rVar != null) {
            h0();
            M.t tVar = rVar.o;
            t tVar2 = (t) ((LinkedHashMap) tVar.l).get(this);
            if (tVar2 != null) {
                tVar2.c();
                LinkedHashMap linkedHashMap = (LinkedHashMap) tVar.l;
                t tVar3 = (t) linkedHashMap.get(this);
                if (tVar3 != null) {
                }
                linkedHashMap.remove(this);
                rVar.f5250n.add(tVar2);
            }
        }
    }

    @Override // y.a0
    public final void e(H h10) {
        int iO;
        float fY;
        C2071b c2071b = h10.l;
        this.f5216v = c2071b.e();
        float f10 = this.o;
        if (Float.isNaN(f10)) {
            iO = C7.a.W(q.a(h10, this.f5209n, c2071b.e()));
        } else {
            iO = c2071b.O(f10);
        }
        this.f5217w = iO;
        long j3 = ((C1891t) this.f5210p.getValue()).f19453a;
        float f11 = ((h) this.f5211q.getValue()).d;
        h10.a();
        if (Float.isNaN(f10)) {
            fY = q.a(h10, this.l, c2071b.e());
        } else {
            fY = h10.y(f10);
        }
        this.f5208m.j(h10, fY, j3);
        InterfaceC1889r interfaceC1889rM = c2071b.f20638m.m();
        ((Boolean) this.f5215u.getValue()).booleanValue();
        t tVar = (t) this.f5214t.getValue();
        if (tVar != null) {
            tVar.e(c2071b.e(), j3, f11);
            tVar.draw(AbstractC1874c.a(interfaceC1889rM));
        }
    }

    @Override // P.s
    public final void h0() {
        this.f5214t.setValue(null);
    }
}

package Y2;

import d5.d0;
import h2.AbstractC1346c;
import h2.t;
import java.util.HashMap;
import k3.C1628b0;
import k3.C1679y0;
import k3.I0;
import k3.InterfaceC1655m;
import k3.X;
import k3.j1;
import k3.l1;
import k3.m1;
import k3.u1;
import o2.C2024a;
import u2.C2492C;

/* JADX INFO: loaded from: classes.dex */
public final /* synthetic */ class m implements h2.g, X, j1, l1, h2.l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f10650m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f10651n;

    public /* synthetic */ m(int i7, long j3, Object obj) {
        this.f10651n = obj;
        this.l = i7;
        this.f10650m = j3;
    }

    @Override // k3.l1
    public Object a(I0 i02, C1679y0 c1679y0, int i7) {
        int i10 = this.l;
        return i02.p(c1679y0, (d0) this.f10651n, i10 == -1 ? i02.f17811t.b0() : i10, i10 == -1 ? i02.f17811t.N0() : this.f10650m);
    }

    @Override // h2.g
    public void accept(Object obj) {
        a aVar = (a) obj;
        n nVar = (n) this.f10651n;
        AbstractC1346c.k(nVar.f10657h);
        byte[] bArrD = Y4.e.D(aVar.f10629a, aVar.c);
        t tVar = nVar.c;
        tVar.getClass();
        tVar.F(bArrD.length, bArrD);
        nVar.f10652a.a(tVar, bArrD.length, 0);
        long j3 = aVar.f10630b;
        long j10 = this.f10650m;
        if (j3 == -9223372036854775807L) {
            AbstractC1346c.j(nVar.f10657h.f14320s == Long.MAX_VALUE);
        } else {
            long j11 = nVar.f10657h.f14320s;
            j10 = j11 == Long.MAX_VALUE ? j10 + j3 : j3 + j11;
        }
        nVar.f10652a.b(j10, this.l, bArrD.length, 0, null);
    }

    @Override // h2.l
    public void b(Object obj) {
        o2.j jVar = (o2.j) obj;
        jVar.getClass();
        C2024a c2024a = (C2024a) this.f10651n;
        C2492C c2492c = c2024a.d;
        if (c2492c != null) {
            String strC = jVar.f20426b.c(c2024a.f20397b, c2492c);
            HashMap map = jVar.f20430h;
            Long l = (Long) map.get(strC);
            HashMap map2 = jVar.f20429g;
            Long l10 = (Long) map2.get(strC);
            map.put(strC, Long.valueOf((l == null ? 0L : l.longValue()) + this.f10650m));
            map2.put(strC, Long.valueOf((l10 != null ? l10.longValue() : 0L) + ((long) this.l)));
        }
    }

    @Override // k3.j1
    public void f(u1 u1Var, C1679y0 c1679y0) {
        u1Var.I0(this.f10650m, ((m1) this.f10651n).r1(c1679y0, u1Var, this.l));
    }

    @Override // k3.X
    public void h(InterfaceC1655m interfaceC1655m, int i7) {
        interfaceC1655m.B0(((C1628b0) this.f10651n).c, i7, this.l, this.f10650m);
    }

    public /* synthetic */ m(n nVar, long j3, int i7) {
        this.f10651n = nVar;
        this.f10650m = j3;
        this.l = i7;
    }

    public /* synthetic */ m(C2024a c2024a, int i7, long j3, long j10) {
        this.f10651n = c2024a;
        this.l = i7;
        this.f10650m = j3;
    }
}

package y;

import h2.AbstractC1347d;
import l3.C1764u;
import m0.AbstractC1792a;
import n0.AbstractC1887p;
import p0.C2071b;
import z7.InterfaceC2782k;

/* JADX INFO: renamed from: y.t, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2714t extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ boolean f23999m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ AbstractC1887p f24000n;
    public final /* synthetic */ long o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ float f24001p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ float f24002q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ long f24003r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ long f24004s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ p0.h f24005t;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C2714t(boolean z6, AbstractC1887p abstractC1887p, long j3, float f10, float f11, long j10, long j11, p0.h hVar) {
        super(1);
        this.f23999m = z6;
        this.f24000n = abstractC1887p;
        this.o = j3;
        this.f24001p = f10;
        this.f24002q = f11;
        this.f24003r = j10;
        this.f24004s = j11;
        this.f24005t = hVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        E0.H h10 = (E0.H) obj;
        h10.a();
        if (this.f23999m) {
            p0.d.T(h10, this.f24000n, 0L, 0L, this.o, null, 246);
        } else {
            long j3 = this.o;
            float fB = AbstractC1792a.b(j3);
            float f10 = this.f24001p;
            if (fB < f10) {
                float f11 = this.f24002q;
                C2071b c2071b = h10.l;
                float fD = m0.f.d(c2071b.e());
                float f12 = this.f24002q;
                float f13 = fD - f12;
                float fB2 = m0.f.b(c2071b.e()) - f12;
                AbstractC1887p abstractC1887p = this.f24000n;
                long j10 = this.o;
                h3.y yVar = c2071b.f20638m;
                long jY = yVar.y();
                yVar.m().m();
                try {
                    ((h3.y) ((C1764u) yVar.f15499m).f18608m).m().i(f11, f11, f13, fB2, 0);
                    p0.d.T(h10, abstractC1887p, 0L, 0L, j10, null, 246);
                } finally {
                    AbstractC1347d.o(yVar, jY);
                }
            } else {
                p0.d.T(h10, this.f24000n, this.f24003r, this.f24004s, h6.i.z(f10, j3), this.f24005t, 208);
            }
        }
        return m7.q.f19267a;
    }
}

package R;

import A.C0022k;
import A.EnumC0017h0;
import C0.V;
import E0.InterfaceC0174y;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class y extends g0.n implements InterfaceC0174y {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public EnumC0017h0 f7337A;
    public boolean B;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public w f7338y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public InterfaceC2785n f7339z;

    @Override // g0.n
    public final void A0() {
        this.B = false;
    }

    @Override // E0.InterfaceC0174y
    public final C0.L g(C0.M m10, C0.J j3, long j10) {
        V vB = j3.b(j10);
        if (!m10.v() || !this.B) {
            m7.i iVar = (m7.i) this.f7339z.invoke(new a1.j(H7.H.c(vB.l, vB.f871m)), new a1.a(j10));
            w wVar = this.f7338y;
            E e10 = (E) iVar.l;
            if (!A7.m.a(wVar.d(), e10)) {
                wVar.l.setValue(e10);
                b9.c cVar = wVar.d.f7238b;
                boolean zD = cVar.d(null);
                Object obj = iVar.f19259m;
                if (zD) {
                    try {
                        u uVar = wVar.f7335m;
                        float fC = wVar.d().c(obj);
                        if (!Float.isNaN(fC)) {
                            u.a(uVar, fC);
                            wVar.g(null);
                        }
                        wVar.f(obj);
                    } finally {
                        cVar.e(null);
                    }
                }
                if (!zD) {
                    wVar.g(obj);
                }
            }
        }
        this.B = m10.v() || this.B;
        return m10.m0(vB.l, vB.f871m, n7.v.l, new C0022k(m10, this, vB, 9));
    }
}

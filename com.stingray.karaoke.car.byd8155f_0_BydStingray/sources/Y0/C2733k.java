package y0;

import E0.AbstractC0156f;
import E0.InterfaceC0162l;
import E0.n0;
import E0.s0;
import F0.AbstractC0227s0;
import F0.C0232v;
import F0.Q;
import G.g0;
import j0.C1535c;

/* JADX INFO: renamed from: y0.k, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2733k extends g0.n implements s0, n0, InterfaceC0162l {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public boolean f24069A;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public C2723a f24070y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public boolean f24071z;

    @Override // g0.n
    public final void A0() {
        J0();
    }

    public final void H0() {
        A7.z zVar = new A7.z();
        AbstractC0156f.w(this, new g0(zVar, 4));
        C2733k c2733k = (C2733k) zVar.l;
        C2723a c2723a = c2733k != null ? c2733k.f24070y : this.f24070y;
        InterfaceC2736n interfaceC2736n = (InterfaceC2736n) AbstractC0156f.i(this, AbstractC0227s0.f2270s);
        if (interfaceC2736n != null) {
            Q.f2106a.a(((C0232v) interfaceC2736n).f2285a, c2723a);
        }
    }

    public final void I0() {
        A7.v vVar = new A7.v();
        vVar.l = true;
        if (!this.f24071z) {
            AbstractC0156f.x(this, new C1535c(vVar));
        }
        if (vVar.l) {
            H0();
        }
    }

    public final void J0() {
        m7.q qVar;
        InterfaceC2736n interfaceC2736n;
        if (this.f24069A) {
            this.f24069A = false;
            if (this.f14695x) {
                A7.z zVar = new A7.z();
                AbstractC0156f.w(this, new g0(zVar, 2));
                C2733k c2733k = (C2733k) zVar.l;
                if (c2733k != null) {
                    c2733k.H0();
                    qVar = m7.q.f19267a;
                } else {
                    qVar = null;
                }
                if (qVar != null || (interfaceC2736n = (InterfaceC2736n) AbstractC0156f.i(this, AbstractC0227s0.f2270s)) == null) {
                    return;
                }
                InterfaceC2735m.f24073a.getClass();
                Q.f2106a.a(((C0232v) interfaceC2736n).f2285a, AbstractC2737o.f24074a);
            }
        }
    }

    @Override // E0.n0
    public final void R(C2730h c2730h, EnumC2731i enumC2731i, long j3) {
        if (enumC2731i == EnumC2731i.f24067m) {
            if (AbstractC2737o.d(c2730h.d, 4)) {
                this.f24069A = true;
                I0();
            } else if (AbstractC2737o.d(c2730h.d, 5)) {
                J0();
            }
        }
    }

    @Override // E0.n0
    public final void e0() {
        J0();
    }

    @Override // E0.s0
    public final /* bridge */ /* synthetic */ Object r() {
        return "androidx.compose.ui.input.pointer.PointerHoverIcon";
    }
}

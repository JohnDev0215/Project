package s9;

import A7.m;
import A7.o;
import A7.z;
import C0.K;
import D.AbstractC0133o;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import R.H;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import g0.l;
import h2.AbstractC1347d;
import java.io.IOException;
import m7.q;
import r9.D;
import x.B0;
import x.D0;
import x.E0;
import x.InterfaceC2615D;
import x.u0;
import x.x0;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class h extends o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f22404m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f22405n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f22406p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f22407q;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(z zVar, D d, z zVar2, z zVar3) {
        super(2);
        this.o = zVar;
        this.f22405n = d;
        this.f22406p = zVar2;
        this.f22407q = zVar3;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws IOException {
        switch (this.f22404m) {
            case 0:
                int iIntValue = ((Number) obj).intValue();
                long jLongValue = ((Number) obj2).longValue();
                if (iIntValue == 1) {
                    z zVar = (z) this.o;
                    if (zVar.l != null) {
                        throw new IOException("bad zip: NTFS extra attribute tag 0x0001 repeated");
                    }
                    if (jLongValue != 24) {
                        throw new IOException("bad zip: NTFS extra attribute tag 0x0001 size != 24");
                    }
                    D d = (D) this.f22405n;
                    zVar.l = Long.valueOf(d.y());
                    ((z) this.f22406p).l = Long.valueOf(d.y());
                    ((z) this.f22407q).l = Long.valueOf(d.y());
                }
                return q.f19267a;
            case 1:
                int iIntValue2 = ((Number) obj).intValue();
                long jLongValue2 = ((Number) obj2).longValue();
                if (iIntValue2 == 21589) {
                    if (jLongValue2 < 1) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    D d10 = (D) this.f22405n;
                    byte bK = d10.k();
                    boolean z6 = (bK & 1) == 1;
                    boolean z10 = (bK & 2) == 2;
                    boolean z11 = (bK & 4) == 4;
                    long j3 = z6 ? 5L : 1L;
                    if (z10) {
                        j3 += 4;
                    }
                    if (z11) {
                        j3 += 4;
                    }
                    if (jLongValue2 < j3) {
                        throw new IOException("bad zip: extended timestamp extra too short");
                    }
                    if (z6) {
                        ((z) this.o).l = Integer.valueOf(d10.v());
                    }
                    if (z10) {
                        ((z) this.f22406p).l = Integer.valueOf(d10.v());
                    }
                    if (z11) {
                        ((z) this.f22407q).l = Integer.valueOf(d10.v());
                    }
                }
                return q.f19267a;
            default:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    D0 d02 = E0.f23396a;
                    x0 x0Var = (x0) this.o;
                    Object objP0 = x0Var.f23661a.P0();
                    c0761q.Q(-438678252);
                    Object obj3 = this.f22407q;
                    float f10 = m.a(objP0, obj3) ? 1.0f : 0.0f;
                    c0761q.p(false);
                    Float fValueOf = Float.valueOf(f10);
                    Object value = x0Var.d.getValue();
                    c0761q.Q(-438678252);
                    float f11 = m.a(value, obj3) ? 1.0f : 0.0f;
                    c0761q.p(false);
                    Float fValueOf2 = Float.valueOf(f11);
                    x0Var.f();
                    c0761q.Q(438406499);
                    c0761q.p(false);
                    u0 u0VarB = B0.b(x0Var, fValueOf, fValueOf2, (InterfaceC2615D) this.f22406p, d02, c0761q, 0);
                    l lVar = l.f14684a;
                    boolean zF = c0761q.f(u0VarB);
                    Object objG = c0761q.G();
                    if (zF || objG == C0753m.f8270a) {
                        objG = new H(u0VarB, 3);
                        c0761q.a0(objG);
                    }
                    g0.o oVarA = androidx.compose.ui.graphics.a.a(lVar, (InterfaceC2782k) objG);
                    K kE = AbstractC0133o.e(g0.b.l, false);
                    int i7 = c0761q.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                    g0.o oVarD = g0.a.d(c0761q, oVarA);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q.U();
                    if (c0761q.f8303O) {
                        c0761q.l(c0159i);
                    } else {
                        c0761q.d0();
                    }
                    C0735d.V(c0761q, kE, C0160j.f1679e);
                    C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                    C0158h c0158h = C0160j.f1680f;
                    if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i7))) {
                        AbstractC1347d.l(i7, c0761q, i7, c0158h);
                    }
                    C0735d.V(c0761q, oVarD, C0160j.c);
                    ((b0.b) this.f22405n).g(obj3, c0761q, 0);
                    c0761q.p(true);
                }
                return q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(D d, z zVar, z zVar2, z zVar3) {
        super(2);
        this.f22405n = d;
        this.o = zVar;
        this.f22406p = zVar2;
        this.f22407q = zVar3;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public h(x0 x0Var, InterfaceC2615D interfaceC2615D, Object obj, b0.b bVar) {
        super(2);
        this.o = x0Var;
        this.f22406p = interfaceC2615D;
        this.f22407q = obj;
        this.f22405n = bVar;
    }
}

package O;

import D.AbstractC0120b;
import D.C0119a;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import a.AbstractC0904a;
import h2.AbstractC1347d;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: O.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0471b extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ long f4945m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ boolean f4946n;
    public final /* synthetic */ g0.o o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC0480k f4947p;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0471b(long j3, boolean z6, g0.o oVar, InterfaceC0480k interfaceC0480k) {
        super(2);
        this.f4945m = j3;
        this.f4946n = z6;
        this.o = oVar;
        this.f4947p = interfaceC0480k;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        C0761q c0761q = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            long j3 = this.f4945m;
            T.S s6 = C0753m.f8270a;
            InterfaceC0480k interfaceC0480k = this.f4947p;
            boolean z6 = this.f4946n;
            if (j3 != 9205357640488583168L) {
                c0761q.Q(-837727128);
                C0119a c0119a = z6 ? AbstractC0120b.f1069b : AbstractC0120b.f1068a;
                g0.o oVarJ = androidx.compose.foundation.layout.c.j(this.o, a1.g.b(j3), a1.g.a(j3), 0.0f, 0.0f, 12);
                D.S sB = D.P.b(c0119a, g0.b.f14669u, c0761q, 0);
                int i7 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
                g0.o oVarD = g0.a.d(c0761q, oVarJ);
                InterfaceC0161k.f1681b.getClass();
                C0159i c0159i = C0160j.f1678b;
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(c0159i);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, sB, C0160j.f1679e);
                C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
                C0158h c0158h = C0160j.f1680f;
                if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i7))) {
                    AbstractC1347d.l(i7, c0761q, i7, c0158h);
                }
                C0735d.V(c0761q, oVarD, C0160j.c);
                g0.l lVar = g0.l.f14684a;
                boolean zH = c0761q.h(interfaceC0480k);
                Object objG = c0761q.G();
                if (zH || objG == s6) {
                    objG = new C0470a(interfaceC0480k, 0);
                    c0761q.a0(objG);
                }
                AbstractC0904a.o(6, c0761q, lVar, (InterfaceC2772a) objG, z6);
                c0761q.p(true);
                c0761q.p(false);
            } else {
                c0761q.Q(-836867312);
                boolean zH2 = c0761q.h(interfaceC0480k);
                Object objG2 = c0761q.G();
                if (zH2 || objG2 == s6) {
                    objG2 = new C0470a(interfaceC0480k, 1);
                    c0761q.a0(objG2);
                }
                AbstractC0904a.o(0, c0761q, this.o, (InterfaceC2772a) objG2, z6);
                c0761q.p(false);
            }
        }
        return m7.q.f19267a;
    }
}

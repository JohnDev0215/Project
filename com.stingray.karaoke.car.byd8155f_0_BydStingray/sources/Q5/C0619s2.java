package Q5;

import A.C0022k;
import C0.C0102i;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import e0.C1178r;
import g1.C1278e;
import g1.C1279f;
import g1.C1283j;
import g1.C1287n;
import n3.C1945j;
import o3.C2041n;
import o3.C2042o;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: Q5.s2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0619s2 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7059m = 0;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f7060n;
    public final /* synthetic */ Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ Object f7061p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ Object f7062q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ Object f7063r;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0619s2(T.X x8, C1283j c1283j, InterfaceC2772a interfaceC2772a, LoadingInterface$LoadingState loadingInterface$LoadingState, InterfaceC2772a interfaceC2772a2) {
        super(2);
        this.f7060n = x8;
        this.o = c1283j;
        this.f7061p = interfaceC2772a;
        this.f7063r = loadingInterface$LoadingState;
        this.f7062q = interfaceC2772a2;
    }

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) throws k1.h {
        C1283j c1283j;
        int i7;
        boolean z6;
        m7.q qVar;
        C0761q c0761q;
        switch (this.f7059m) {
            case 0:
                C0761q c0761q2 = (C0761q) obj;
                int iIntValue = ((Number) obj2).intValue() & 3;
                m7.q qVar2 = m7.q.f19267a;
                if (iIntValue == 2 && c0761q2.x()) {
                    c0761q2.K();
                    return qVar2;
                }
                ((T.X) this.f7060n).setValue(qVar2);
                C1283j c1283j2 = (C1283j) this.o;
                int i10 = c1283j2.f14726b;
                c1283j2.e();
                c0761q2.Q(-1615375032);
                C1283j c1283j3 = (C1283j) c1283j2.d().f14705a;
                C1278e c1278eC = c1283j3.c();
                C1278e c1278eC2 = c1283j3.c();
                C1278e c1278eC3 = c1283j3.c();
                int i11 = c1283j2.d;
                c1283j2.d = i11 + 1;
                Integer numValueOf = Integer.valueOf(i11);
                C1287n c1287n = new C1287n(numValueOf);
                k1.g gVarA = c1283j2.a(c1287n);
                gVarA.getClass();
                k1.i iVar = new k1.i("hGuideline".toCharArray());
                iVar.f17620m = 0L;
                iVar.r(9);
                gVarA.G("type", iVar);
                gVarA.G("percent", new k1.e(0.25f));
                c1283j2.f(8);
                c1283j2.f(Float.hashCode(0.25f));
                C1279f c1279f = new C1279f(numValueOf, 0, c1287n);
                g0.l lVar = g0.l.f14684a;
                g0.o oVarD = androidx.compose.foundation.layout.c.d(lVar, 0.5f);
                c0761q2.Q(-1021934120);
                boolean zF = c0761q2.f(c1279f);
                Object objG = c0761q2.G();
                T.S s6 = C0753m.f8270a;
                if (zF || objG == s6) {
                    objG = new N7.g(c1279f, 2);
                    c0761q2.a0(objG);
                }
                c0761q2.p(false);
                h6.q.a(l9.l.W(R.drawable.brand_logo_color, c0761q2, 0), null, C1283j.b(oVarD, c1278eC, (InterfaceC2782k) objG), null, C0102i.f903b, 0.0f, null, c0761q2, 24624, 104);
                if (((LoadingInterface$LoadingState) this.f7063r) == LoadingInterface$LoadingState.Error) {
                    c0761q2.Q(-1614843166);
                    c0761q2.Q(-1021919818);
                    boolean zF2 = c0761q2.f(c1278eC);
                    Object objG2 = c0761q2.G();
                    if (zF2 || objG2 == s6) {
                        objG2 = new C0623t2(c1278eC, 0);
                        c0761q2.a0(objG2);
                    }
                    c0761q2.p(false);
                    i7 = i10;
                    c1283j = c1283j2;
                    qVar = qVar2;
                    c0761q = c0761q2;
                    io.sentry.config.a.o(C1283j.b(lVar, c1278eC3, (InterfaceC2782k) objG2), null, false, AbstractC2673a.X(R.string.Global_Retry, c0761q2), 0L, null, false, null, null, 0L, 0.0f, (InterfaceC2772a) this.f7062q, c0761q2, 0, 0, 2038);
                    z6 = false;
                    c0761q.p(false);
                } else {
                    c1283j = c1283j2;
                    i7 = i10;
                    z6 = false;
                    qVar = qVar2;
                    c0761q = c0761q2;
                    c0761q.Q(-1614467849);
                    g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
                    c0761q.Q(-1021906602);
                    boolean zF3 = c0761q.f(c1278eC);
                    Object objG3 = c0761q.G();
                    if (zF3 || objG3 == s6) {
                        objG3 = new C0623t2(c1278eC, 1);
                        c0761q.a0(objG3);
                    }
                    c0761q.p(false);
                    io.sentry.config.a.t(C1283j.b(oVarD2, c1278eC2, (InterfaceC2782k) objG3), l6.d.c(c0761q).f18696b, c0761q, 0, 0);
                    c0761q.p(false);
                }
                c0761q.p(z6);
                if (c1283j.f14726b == i7) {
                    return qVar;
                }
                C0735d.i((InterfaceC2772a) this.f7061p, c0761q);
                return qVar;
            default:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    C1945j c1945j = (C1945j) this.f7060n;
                    boolean zH = c0761q3.h(c1945j);
                    C2042o c2042o = (C2042o) this.o;
                    boolean zF4 = zH | c0761q3.f(c2042o);
                    Object objG4 = c0761q3.G();
                    if (zF4 || objG4 == C0753m.f8270a) {
                        objG4 = new C0022k((C1178r) this.f7062q, c1945j, c2042o, 18);
                        c0761q3.a0(objG4);
                    }
                    C0735d.d(c1945j, (InterfaceC2782k) objG4, c0761q3);
                    h6.j.e(c1945j, (d0.h) this.f7061p, b0.g.b(-497631156, new A.L((C2041n) this.f7063r, 15, c1945j), c0761q3), c0761q3, 384);
                }
                return m7.q.f19267a;
        }
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0619s2(C1945j c1945j, C2042o c2042o, d0.h hVar, C1178r c1178r, C2041n c2041n) {
        super(2);
        this.f7060n = c1945j;
        this.o = c2042o;
        this.f7061p = hVar;
        this.f7062q = c1178r;
        this.f7063r = c2041n;
    }
}

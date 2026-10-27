package Q5;

import D.AbstractC0127i;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0138u;
import D.C0141x;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.kca.model.KaraokeSongResponseV2;
import g1.C1283j;
import g1.C1285l;
import h2.AbstractC1347d;
import java.io.IOException;
import java.util.List;
import n6.C1966G;
import org.xmlpull.v1.XmlPullParserException;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class Q1 implements InterfaceC2786o {
    public final /* synthetic */ KaraokeSongResponseV2 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ D.L f6507m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6508n;
    public final /* synthetic */ DriveMode o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ List f6509p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f6510q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ LoadingInterface$LoadingState f6511r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6512s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ boolean f6513t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f6514u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public final /* synthetic */ C1966G f6515v;

    public Q1(D.L l, DriveMode driveMode, LoadingInterface$LoadingState loadingInterface$LoadingState, KaraokeSongResponseV2 karaokeSongResponseV2, List list, C1966G c1966g, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2782k interfaceC2782k, InterfaceC2785n interfaceC2785n, boolean z6) {
        this.l = karaokeSongResponseV2;
        this.f6507m = l;
        this.f6508n = interfaceC2782k;
        this.o = driveMode;
        this.f6509p = list;
        this.f6510q = interfaceC2785n;
        this.f6511r = loadingInterface$LoadingState;
        this.f6512s = interfaceC2772a;
        this.f6513t = z6;
        this.f6514u = interfaceC2772a2;
        this.f6515v = c1966g;
    }

    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    @Override // z7.InterfaceC2786o
    public final Object g(Object obj, Object obj2, Object obj3) throws XmlPullParserException, IOException {
        C0138u c0138u = (C0138u) obj;
        C0761q c0761q = (C0761q) obj2;
        int iIntValue = ((Number) obj3).intValue();
        A7.m.f(c0138u, "$this$BoxWithConstraints");
        if ((iIntValue & 6) == 0) {
            iIntValue |= c0761q.f(c0138u) ? 4 : 2;
        }
        int i7 = iIntValue & 19;
        m7.q qVar = m7.q.f19267a;
        if (i7 == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            KaraokeSongResponseV2 karaokeSongResponseV2 = this.l;
            if (karaokeSongResponseV2 != null) {
                A7.m.a(karaokeSongResponseV2.f13146n, Boolean.TRUE);
            }
            int iM = h6.f.m(c0138u.b(), 0.0f, c0761q, 2);
            float fMin = Math.min(c0138u.a() * 0.45f, c0138u.b() * 0.35f);
            g0.l lVar = g0.l.f14684a;
            g0.o oVarJ = androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.c.c(h6.r.t(lVar, h6.r.q(c0761q)), 1.0f), this.f6507m);
            C0119a c0119a = AbstractC0127i.f1076a;
            C0141x c0141xA = AbstractC0139v.a(new C0124f(m6.c.f19252a.r0(c0761q)), g0.b.f14673y, c0761q, 48);
            int i10 = c0761q.f8304P;
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
            C0735d.V(c0761q, c0141xA, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i10))) {
                AbstractC1347d.l(i10, c0761q, i10, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.o oVarR = androidx.compose.foundation.layout.c.r(androidx.compose.foundation.layout.c.d(lVar, 1.0f));
            c0761q.Q(-1003410150);
            c0761q.Q(212064437);
            c0761q.p(false);
            a1.b bVar = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = new g1.p(bVar);
                c0761q.a0(objG);
            }
            g1.p pVar = (g1.p) objG;
            Object objG2 = c0761q.G();
            if (objG2 == s6) {
                objG2 = new C1283j();
                c0761q.a0(objG2);
            }
            C1283j c1283j = (C1283j) objG2;
            Object objG3 = c0761q.G();
            if (objG3 == s6) {
                objG3 = C0735d.O(Boolean.FALSE, T.S.f8224q);
                c0761q.a0(objG3);
            }
            T.X x8 = (T.X) objG3;
            Object objG4 = c0761q.G();
            if (objG4 == s6) {
                objG4 = new C1285l(c1283j);
                c0761q.a0(objG4);
            }
            C1285l c1285l = (C1285l) objG4;
            Object objG5 = c0761q.G();
            if (objG5 == s6) {
                objG5 = C0735d.O(qVar, T.S.f8222n);
                c0761q.a0(objG5);
            }
            T.X x9 = (T.X) objG5;
            boolean zD = c0761q.d(257) | c0761q.h(pVar);
            Object objG6 = c0761q.G();
            if (zD || objG6 == s6) {
                objG6 = new M1(x9, pVar, c1285l, x8, 0);
                c0761q.a0(objG6);
            }
            C0.K k10 = (C0.K) objG6;
            Object objG7 = c0761q.G();
            if (objG7 == s6) {
                objG7 = new N1(x8, c1285l, 0);
                c0761q.a0(objG7);
            }
            InterfaceC2772a interfaceC2772a = (InterfaceC2772a) objG7;
            boolean zH = c0761q.h(pVar);
            Object objG8 = c0761q.G();
            if (zH || objG8 == s6) {
                objG8 = new O1(pVar, 0);
                c0761q.a0(objG8);
            }
            g0.o oVarA = L0.k.a(oVarR, false, (InterfaceC2782k) objG8);
            boolean z6 = this.f6513t;
            KaraokeSongResponseV2 karaokeSongResponseV22 = this.l;
            C0.c0.a(oVarA, b0.g.b(1200550679, new P1(x9, c1283j, interfaceC2772a, fMin, karaokeSongResponseV22, z6, this.f6514u, this.f6515v), c0761q), k10, c0761q, 48);
            c0761q.p(false);
            g0.o oVarR2 = androidx.compose.foundation.layout.c.r(androidx.compose.foundation.layout.c.d(lVar, 1.0f));
            if (karaokeSongResponseV22 != null) {
                A7.m.a(karaokeSongResponseV22.f13146n, Boolean.TRUE);
            }
            io.sentry.config.a.z(oVarR2, this.f6508n, this.o, c0761q, 390);
            c0761q.Q(-486263512);
            InterfaceC2772a interfaceC2772a2 = this.f6512s;
            boolean zF = c0761q.f(interfaceC2772a2);
            Object objG9 = c0761q.G();
            if (zF || objG9 == s6) {
                objG9 = new C0549b(interfaceC2772a2, 8);
                c0761q.a0(objG9);
            }
            InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) objG9;
            c0761q.p(false);
            io.sentry.config.a.w(lVar, this.f6509p, iM, this.f6510q, this.f6511r, interfaceC2772a3, karaokeSongResponseV22 != null ? karaokeSongResponseV22.f13136a : null, c0761q, 6);
            c0761q.p(true);
        }
        return qVar;
    }
}

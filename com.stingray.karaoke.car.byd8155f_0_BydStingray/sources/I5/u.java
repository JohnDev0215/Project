package I5;

import D.AbstractC0127i;
import D.AbstractC0139v;
import D.C0141x;
import D.C0142y;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import Q.AbstractC0535v;
import Q.AbstractC0539x;
import Q.L;
import Q.c1;
import Q5.C0549b;
import Q5.I;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import T.InterfaceC0744h0;
import T.X;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.MainActivity;
import com.stingray.karaoke.car.utils.FontSize;
import h2.AbstractC1347d;
import n0.AbstractC1861K;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class u implements InterfaceC2785n {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f3021m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Object f3022n;

    public /* synthetic */ u(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f3021m = obj;
        this.f3022n = obj2;
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
    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        g0.l lVar = g0.l.f14684a;
        m7.q qVar = m7.q.f19267a;
        Object obj3 = this.f3022n;
        Object obj4 = this.f3021m;
        switch (this.l) {
            case 0:
                C0761q c0761q = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q.x()) {
                    c0761q.K();
                } else {
                    X x8 = (X) obj3;
                    MainActivity mainActivity = (MainActivity) obj4;
                    androidx.compose.animation.a.c(((Boolean) x8.getValue()).booleanValue(), null, mainActivity.f12989T, mainActivity.f12990U, null, b0.g.b(1055028068, new t(mainActivity, x8), c0761q), c0761q, 196608, 18);
                }
                break;
            case 1:
                C0761q c0761q2 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q2.x()) {
                    c0761q2.K();
                } else {
                    String str = (String) obj3;
                    c1.b(str, C0142y.a((C0142y) obj4, lVar, 1.0f), l6.d.c(c0761q2).f18696b, h6.f.g(FontSize.Subtitle1, c0761q2), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ((O5.d) c0761q2.k(O5.c.f5157b)).d, c0761q2, 0, 0, 65520);
                }
                break;
            case 2:
                C0761q c0761q3 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q3.x()) {
                    c0761q3.K();
                } else {
                    g0.o oVarT = h6.r.t(androidx.compose.foundation.a.b(h6.q.m((g0.o) obj4, J.e.a(m6.c.f19252a.f0(c0761q3))), l6.d.c(c0761q3).f18721w, AbstractC1861K.f19381a), h6.r.q(c0761q3));
                    C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q3, 0);
                    int i7 = c0761q3.f8304P;
                    InterfaceC0744h0 interfaceC0744h0M = c0761q3.m();
                    g0.o oVarD = g0.a.d(c0761q3, oVarT);
                    InterfaceC0161k.f1681b.getClass();
                    C0159i c0159i = C0160j.f1678b;
                    c0761q3.U();
                    if (c0761q3.f8303O) {
                        c0761q3.l(c0159i);
                    } else {
                        c0761q3.d0();
                    }
                    C0735d.V(c0761q3, c0141xA, C0160j.f1679e);
                    C0735d.V(c0761q3, interfaceC0744h0M, C0160j.d);
                    C0158h c0158h = C0160j.f1680f;
                    if (c0761q3.f8303O || !A7.m.a(c0761q3.G(), Integer.valueOf(i7))) {
                        AbstractC1347d.l(i7, c0761q3, i7, c0158h);
                    }
                    C0735d.V(c0761q3, oVarD, C0160j.c);
                    ((b0.b) obj3).g(C0142y.f1119a, c0761q3, 6);
                    c0761q3.p(true);
                }
                break;
            case 3:
                C0761q c0761q4 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q4.x()) {
                    c0761q4.K();
                } else {
                    g0.o oVarO = androidx.compose.foundation.layout.a.o(androidx.compose.foundation.layout.c.b(lVar, 1.0f), 0.0f, 0.0f, m6.c.f19252a.F(c0761q4), 0.0f, 11);
                    if (!Q8.h.M0((String) obj4)) {
                        c0761q4.Q(-932992381);
                        c0761q4.Q(-584282883);
                        InterfaceC2772a interfaceC2772a = (InterfaceC2772a) obj3;
                        boolean zF = c0761q4.f(interfaceC2772a);
                        Object objG = c0761q4.G();
                        if (zF || objG == C0753m.f8270a) {
                            objG = new C0549b(interfaceC2772a, 6);
                            c0761q4.a0(objG);
                        }
                        c0761q4.p(false);
                        b0.b bVar = I.f6386a;
                        L.c((InterfaceC2772a) objG, oVarO, false, null, null, c0761q4, 196608);
                        c0761q4.p(false);
                    } else {
                        c0761q4.Q(-933281146);
                        AbstractC0535v.a(l9.l.W(R.drawable.ic_magnifying_glass, c0761q4, 0), null, oVarO, l6.d.c(c0761q4).f18696b, c0761q4, 48, 0);
                        c0761q4.p(false);
                    }
                }
                break;
            case 4:
                C0761q c0761q5 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q5.x()) {
                    c0761q5.K();
                } else {
                    ((b0.b) obj4).g((String) obj3, c0761q5, 0);
                }
                break;
            default:
                C0761q c0761q6 = (C0761q) obj;
                if ((((Number) obj2).intValue() & 3) == 2 && c0761q6.x()) {
                    c0761q6.K();
                } else {
                    AbstractC0539x.a(((l6.a) obj4).f18694a, null, null, (b0.b) obj3, c0761q6, 0);
                }
                break;
        }
        return qVar;
    }
}

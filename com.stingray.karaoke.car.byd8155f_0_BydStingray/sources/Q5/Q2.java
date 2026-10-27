package Q5;

import T.C0732b0;
import T.C0753m;
import T.C0761q;
import com.stingray.karaoke.car.utils.FontSize;
import java.util.ArrayList;
import n0.C1886o;
import n0.C1891t;
import n7.AbstractC1999n;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class Q2 implements InterfaceC2785n {
    public final /* synthetic */ ArrayList l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6516m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C0732b0 f6517n;

    public Q2(ArrayList arrayList, InterfaceC2782k interfaceC2782k, C0732b0 c0732b0) {
        this.l = arrayList;
        this.f6516m = interfaceC2782k;
        this.f6517n = c0732b0;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r11v0 */
    /* JADX WARN: Type inference failed for: r11v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r11v3 */
    /* JADX WARN: Type inference failed for: r15v1, types: [T.q] */
    /* JADX WARN: Type inference failed for: r23v1 */
    /* JADX WARN: Type inference failed for: r24v1 */
    /* JADX WARN: Type inference failed for: r24v2 */
    /* JADX WARN: Type inference failed for: r3v1, types: [m6.b] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r6v6, types: [java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v7, types: [Q5.O2, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r6v8 */
    /* JADX WARN: Type inference failed for: r6v9, types: [m6.b] */
    /* JADX WARN: Type inference failed for: r9v0, types: [boolean] */
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
        ?? r15 = (C0761q) obj;
        if ((((Number) obj2).intValue() & 3) == 2 && r15.x()) {
            r15.K();
        } else {
            ArrayList arrayList = this.l;
            ?? r11 = 0;
            int i7 = 0;
            for (Object obj3 : arrayList) {
                int i10 = i7 + 1;
                if (i7 < 0) {
                    AbstractC1999n.I();
                    throw null;
                }
                String str = (String) obj3;
                C0732b0 c0732b0 = this.f6517n;
                ?? r52 = c0732b0.i() == i7 ? 1 : r11;
                g0.o oVarA = g0.l.f14684a;
                r15.Q(-1193787460);
                float F9 = i7 < arrayList.size() - 1 ? m6.c.f19252a.F(r15) : (float) r11;
                r15.p(r11);
                g0.o oVarM = h6.q.m(androidx.compose.foundation.layout.a.o(oVarA, 0.0f, 0.0f, F9, 0.0f, 11), J.e.f3041a);
                r15.Q(-1193781384);
                if (r52 != 0) {
                    oVarA = androidx.compose.foundation.a.a(oVarA, C1886o.m(AbstractC1999n.D(new C1891t(l6.d.c(r15).f18715q), new C1891t(l6.d.c(r15).f18716r))), null, 6);
                }
                r15.p(r11);
                g0.o oVarK = oVarM.k(oVarA);
                ?? r92 = r52 ^ 1;
                ?? r32 = m6.c.f19252a;
                float fY0 = r32.y0(r15);
                float fX0 = r32.x0(r15);
                D.L l = new D.L(fX0, fY0, fX0, fY0);
                N0.L l10 = ((O5.d) r15.k(O5.c.f5157b)).d;
                long jG = h6.f.g(FontSize.Subtitle1, r15);
                r15.Q(-579864569);
                boolean zD = r15.d(i7);
                InterfaceC2782k interfaceC2782k = this.f6516m;
                boolean zF = zD | r15.f(interfaceC2782k);
                ?? G9 = r15.G();
                if (zF || G9 == C0753m.f8270a) {
                    G9 = new O2(i7, interfaceC2782k, c0732b0, 1);
                    r15.a0(G9);
                }
                r15.p(r11);
                io.sentry.config.a.o(oVarK, null, false, str, 0L, null, r92, l, l10, jG, 0.0f, (InterfaceC2772a) G9, r15, 0, 0, 1078);
                i7 = i10;
                arrayList = arrayList;
                r11 = r11 == true ? 1 : 0;
            }
        }
        return m7.q.f19267a;
    }
}

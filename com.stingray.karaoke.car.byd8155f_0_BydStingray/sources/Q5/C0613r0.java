package Q5;

import T.C0753m;
import T.C0761q;
import java.util.List;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;
import z7.InterfaceC2787p;

/* JADX INFO: renamed from: Q5.r0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0613r0 extends A7.o implements InterfaceC2787p {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ List f7038m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ float f7039n;
    public final /* synthetic */ List o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ A7.x f7040p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f7041q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7042r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ F.y f7043s;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0613r0(List list, float f10, List list2, A7.x xVar, boolean z6, InterfaceC2785n interfaceC2785n, F.y yVar) {
        super(4);
        this.f7038m = list;
        this.f7039n = f10;
        this.o = list2;
        this.f7040p = xVar;
        this.f7041q = z6;
        this.f7042r = interfaceC2785n;
        this.f7043s = yVar;
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
    @Override // z7.InterfaceC2787p
    public final Object o(Object obj, Object obj2, Object obj3, Object obj4) {
        int i7;
        F.i iVar = (F.i) obj;
        int iIntValue = ((Number) obj2).intValue();
        C0761q c0761q = (C0761q) obj3;
        int iIntValue2 = ((Number) obj4).intValue();
        if ((iIntValue2 & 6) == 0) {
            i7 = (c0761q.f(iVar) ? 4 : 2) | iIntValue2;
        } else {
            i7 = iIntValue2;
        }
        if ((iIntValue2 & 48) == 0) {
            i7 |= c0761q.d(iIntValue) ? 32 : 16;
        }
        if ((i7 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            T2 t22 = (T2) this.f7038m.get(iIntValue);
            c0761q.Q(1801041752);
            b0.b bVar = t22.c;
            g0.o oVarE = g0.l.f14684a;
            if (t22.f6573b > 1) {
                oVarE = androidx.compose.foundation.layout.c.e(oVarE, this.f7039n);
            }
            g0.o oVar = oVarE;
            Integer numValueOf = Integer.valueOf(this.o.indexOf(t22) % this.f7040p.l);
            Boolean boolValueOf = Boolean.valueOf(this.f7041q);
            c0761q.Q(-2020101662);
            InterfaceC2785n interfaceC2785n = this.f7042r;
            boolean zF = c0761q.f(interfaceC2785n);
            F.y yVar = this.f7043s;
            boolean zF2 = zF | c0761q.f(yVar);
            Object objG = c0761q.G();
            if (zF2 || objG == C0753m.f8270a) {
                objG = new C0606p0(interfaceC2785n, yVar, 0);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            bVar.k(oVar, numValueOf, boolValueOf, (InterfaceC2772a) objG, c0761q, 0);
            c0761q.p(false);
        }
        return m7.q.f19267a;
    }
}

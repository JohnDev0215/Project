package Q5;

import D.InterfaceC0136s;
import T.C0753m;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.kca.model.KaraokeSongResponseV2;
import h8.AbstractC1386c;
import x.AbstractC2637h;
import z7.InterfaceC2782k;
import z7.InterfaceC2787p;

/* JADX INFO: renamed from: Q5.l2, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0592l2 implements InterfaceC2787p {
    public final /* synthetic */ N5.f0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f6944m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ int f6945n;
    public final /* synthetic */ InterfaceC2782k o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2782k f6946p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ P5.p f6947q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ PlayerStatus f6948r;

    public C0592l2(N5.f0 f0Var, int i7, int i10, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, P5.p pVar, PlayerStatus playerStatus) {
        this.l = f0Var;
        this.f6944m = i7;
        this.f6945n = i10;
        this.o = interfaceC2782k;
        this.f6946p = interfaceC2782k2;
        this.f6947q = pVar;
        this.f6948r = playerStatus;
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
        float fJ;
        boolean zBooleanValue = ((Boolean) obj2).booleanValue();
        C0761q c0761q = (C0761q) obj3;
        int iIntValue = ((Number) obj4).intValue();
        A7.m.f((InterfaceC0136s) obj, "$this$ReorderableItem");
        if ((iIntValue & 48) == 0) {
            iIntValue |= c0761q.g(zBooleanValue) ? 32 : 16;
        }
        if ((iIntValue & 145) == 144 && c0761q.x()) {
            c0761q.K();
        } else {
            if (zBooleanValue) {
                c0761q.Q(175452872);
                fJ = w0.c.J(R.dimen.elevation2, c0761q);
                c0761q.p(false);
            } else {
                c0761q.Q(175456328);
                fJ = w0.c.J(R.dimen.elevation0, c0761q);
                c0761q.p(false);
            }
            g0.o oVarE = androidx.compose.foundation.layout.c.e(AbstractC1386c.t(g0.l.f14684a, ((a1.e) AbstractC2637h.a(fJ, null, null, c0761q, 0, 14).getValue()).l, null, 30), m6.c.f19252a.u0(c0761q));
            N5.f0 f0Var = this.l;
            KaraokeSongResponseV2 karaokeSongResponseV2 = f0Var.f4691a;
            String str = karaokeSongResponseV2.f13137b;
            String str2 = str == null ? "" : str;
            String str3 = karaokeSongResponseV2.c;
            String str4 = str3 == null ? "" : str3;
            boolean z6 = this.f6944m == this.f6945n;
            String str5 = karaokeSongResponseV2.l;
            String str6 = str5 == null ? "" : str5;
            Boolean bool = Boolean.TRUE;
            boolean zA = A7.m.a(karaokeSongResponseV2.f13143j, bool);
            boolean zA2 = A7.m.a(karaokeSongResponseV2.f13145m, bool);
            boolean zA3 = A7.m.a(karaokeSongResponseV2.f13146n, bool);
            c0761q.Q(175483589);
            InterfaceC2782k interfaceC2782k = this.o;
            boolean zF = c0761q.f(interfaceC2782k) | c0761q.h(f0Var);
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (zF || objG == s6) {
                objG = new C0588k2(interfaceC2782k, f0Var, 0);
                c0761q.a0(objG);
            }
            InterfaceC2782k interfaceC2782k2 = (InterfaceC2782k) objG;
            c0761q.p(false);
            c0761q.Q(175486309);
            InterfaceC2782k interfaceC2782k3 = this.f6946p;
            boolean zF2 = c0761q.f(interfaceC2782k3) | c0761q.h(f0Var);
            Object objG2 = c0761q.G();
            if (zF2 || objG2 == s6) {
                objG2 = new C0588k2(interfaceC2782k3, f0Var, 1);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            l9.l.u(oVarE, f0Var.d, this.f6945n, str2, str4, str6, z6, interfaceC2782k2, (InterfaceC2782k) objG2, this.f6947q, this.f6948r, zA, zA2, zA3, c0761q, 0, 0);
        }
        return m7.q.f19267a;
    }
}

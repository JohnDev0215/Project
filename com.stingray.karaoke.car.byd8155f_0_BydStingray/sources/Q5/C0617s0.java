package Q5;

import D.AbstractC0127i;
import D.C0119a;
import D.C0124f;
import D.C0138u;
import F.C0190a;
import T.C0735d;
import T.C0753m;
import T.C0761q;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import java.util.List;
import n7.AbstractC1998m;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;
import z7.InterfaceC2786o;

/* JADX INFO: renamed from: Q5.s0, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C0617s0 implements InterfaceC2786o {
    public final /* synthetic */ D.L l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ float f7050m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ Integer f7051n;
    public final /* synthetic */ Integer o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final /* synthetic */ ArrayList f7052p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final /* synthetic */ boolean f7053q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final /* synthetic */ F.y f7054r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final /* synthetic */ b6.g f7055s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2772a f7056t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public final /* synthetic */ InterfaceC2785n f7057u;

    public C0617s0(D.L l, float f10, Integer num, Integer num2, ArrayList arrayList, boolean z6, F.y yVar, b6.g gVar, InterfaceC2772a interfaceC2772a, InterfaceC2785n interfaceC2785n) {
        this.l = l;
        this.f7050m = f10;
        this.f7051n = num;
        this.o = num2;
        this.f7052p = arrayList;
        this.f7053q = z6;
        this.f7054r = yVar;
        this.f7055s = gVar;
        this.f7056t = interfaceC2772a;
        this.f7057u = interfaceC2785n;
    }

    /* JADX WARN: Type inference failed for: r3v8, types: [Q5.n0] */
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
    public final Object g(Object obj, Object obj2, Object obj3) {
        float fM;
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
            return qVar;
        }
        m6.b bVar = m6.c.f19252a;
        float fM2 = bVar.M(c0761q);
        float fB = c0138u.b();
        a1.k kVar = a1.k.l;
        D.L l = this.l;
        float f10 = ((fB - androidx.compose.foundation.layout.a.f(l, kVar)) - androidx.compose.foundation.layout.a.e(l, kVar)) - this.f7050m;
        final A7.x xVar = new A7.x();
        c0761q.Q(304853370);
        Integer num = this.f7051n;
        int iM = num == null ? h6.f.m(f10, fM2, c0761q, 0) : num.intValue();
        c0761q.p(false);
        xVar.l = iM;
        T2 t22 = new T2(AbstractC2673a.X(R.string.Driving_LimitedScrolling, c0761q), xVar.l, E.f6319a);
        final ArrayList arrayListO0 = this.f7052p;
        Integer num2 = this.o;
        if (num2 != null) {
            arrayListO0 = AbstractC1998m.o0(AbstractC1998m.v0(num2.intValue() * xVar.l, arrayListO0), t22);
        }
        int i10 = xVar.l;
        final float f11 = (f10 - ((i10 - 1) * fM2)) / i10;
        final boolean z6 = this.f7053q && i10 <= 2;
        if (z6) {
            i10 = 1;
        }
        xVar.l = i10;
        if (i10 == 1) {
            c0761q.Q(304876489);
            fM = bVar.H(6, c0761q) * 2;
        } else {
            c0761q.Q(304877676);
            fM = bVar.M(c0761q);
        }
        c0761q.p(false);
        C0190a c0190a = new C0190a(xVar.l);
        C0119a c0119a = AbstractC0127i.f1076a;
        C0124f c0124f = new C0124f(fM);
        C0124f c0124f2 = new C0124f(fM2);
        final InterfaceC2785n interfaceC2785n = this.f7057u;
        final F.y yVar = this.f7054r;
        H7.H.d(c0190a, null, yVar, this.l, false, c0124f, c0124f2, null, false, new InterfaceC2782k() { // from class: Q5.n0
            @Override // z7.InterfaceC2782k
            public final Object b(Object obj4) {
                F.g gVar = (F.g) obj4;
                A7.m.f(gVar, "$this$LazyVerticalGrid");
                A7.x xVar2 = xVar;
                C0602o0 c0602o0 = new C0602o0(xVar2, 0);
                List list = arrayListO0;
                gVar.c.c(list.size(), new F.f(new A.L(c0602o0, 11, list), new C0558d0(1, list), new b0.b(699646206, new C0613r0(list, f11, list, xVar2, z6, interfaceC2785n, yVar), true)));
                gVar.d = true;
                return m7.q.f19267a;
            }
        }, c0761q, 0);
        c0761q.Q(304914592);
        F.y yVar2 = this.f7054r;
        boolean zF = c0761q.f(yVar2);
        b6.g gVar = this.f7055s;
        boolean zH = zF | c0761q.h(gVar);
        Object objG = c0761q.G();
        T.S s6 = C0753m.f8270a;
        if (zH || objG == s6) {
            objG = new C0610q0(yVar2, gVar, null);
            c0761q.a0(objG);
        }
        c0761q.p(false);
        C0735d.g(qVar, gVar, (InterfaceC2785n) objG, c0761q);
        int i11 = xVar.l;
        c0761q.Q(304919222);
        InterfaceC2772a interfaceC2772a = this.f7056t;
        boolean zF2 = c0761q.f(interfaceC2772a);
        Object objG2 = c0761q.G();
        if (zF2 || objG2 == s6) {
            objG2 = new C0549b(interfaceC2772a, 3);
            c0761q.a0(objG2);
        }
        c0761q.p(false);
        com.bumptech.glide.c.l(yVar2, i11, (InterfaceC2772a) objG2, c0761q, 0);
        return qVar;
    }
}

package Q7;

import java.util.List;
import n7.AbstractC1998m;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public final class C extends A7.o implements InterfaceC2782k {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f7172m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ B8.t f7173n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C(B8.t tVar, int i7) {
        super(1);
        this.f7172m = i7;
        this.f7173n = tVar;
    }

    @Override // z7.InterfaceC2782k
    public final Object b(Object obj) {
        InterfaceC0653f interfaceC0653fT;
        switch (this.f7172m) {
            case 0:
                A a10 = (A) obj;
                A7.m.f(a10, "<name for destructuring parameter 0>");
                C2057b c2057b = a10.f7167a;
                if (c2057b.c) {
                    throw new UnsupportedOperationException("Unresolved local class: " + c2057b);
                }
                C2057b c2057bF = c2057b.f();
                List list = a10.f7168b;
                B8.t tVar = this.f7173n;
                if (c2057bF != null) {
                    interfaceC0653fT = tVar.T(c2057bF, AbstractC1998m.V(list));
                } else {
                    E8.e eVar = (E8.e) tVar.f809p;
                    C2058c c2058cG = c2057b.g();
                    A7.m.e(c2058cG, "classId.packageFqName");
                    interfaceC0653fT = (InterfaceC0653f) eVar.b(c2058cG);
                }
                InterfaceC0653f interfaceC0653f = interfaceC0653fT;
                boolean z6 = !c2057b.f20565b.e().d();
                E8.o oVar = (E8.o) tVar.f808n;
                C2061f c2061fI = c2057b.i();
                A7.m.e(c2061fI, "classId.shortClassName");
                Integer num = (Integer) AbstractC1998m.a0(list);
                return new B(oVar, interfaceC0653f, c2061fI, z6, num != null ? num.intValue() : 0);
            default:
                C2058c c2058c = (C2058c) obj;
                A7.m.f(c2058c, "fqName");
                return new P7.k((InterfaceC0671y) this.f7173n.o, c2058c, 1);
        }
    }
}

package d8;

import N1.C0420a;
import Q7.AbstractC0669w;
import Q7.InterfaceC0652e;
import W7.AbstractC0839c;
import W7.C0840d;
import c8.C1036a;
import f8.InterfaceC1265a;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import w8.C2607b;
import z7.InterfaceC2772a;

/* JADX INFO: renamed from: d8.e, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1118e extends A7.o implements InterfaceC2772a {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ int f13569m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final /* synthetic */ C1119f f13570n;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public /* synthetic */ C1118e(C1119f c1119f, int i7) {
        super(0);
        this.f13569m = i7;
        this.f13570n = c1119f;
    }

    @Override // z7.InterfaceC2772a
    public final Object a() throws IllegalAccessException, InvocationTargetException {
        switch (this.f13569m) {
            case 0:
                C1119f c1119f = this.f13570n;
                ArrayList<InterfaceC1265a> arrayListB = c1119f.f13573b.b();
                ArrayList arrayList = new ArrayList();
                for (InterfaceC1265a interfaceC1265a : arrayListB) {
                    C2061f c2061f = ((W7.e) interfaceC1265a).f9881a;
                    if (c2061f == null) {
                        c2061f = Z7.x.f10964b;
                    }
                    t8.g gVarD = c1119f.d(interfaceC1265a);
                    m7.i iVar = gVarD != null ? new m7.i(c2061f, gVarD) : null;
                    if (iVar != null) {
                        arrayList.add(iVar);
                    }
                }
                return n7.z.x(arrayList);
            case 1:
                return AbstractC0839c.a(n7.z.i(n7.z.g(this.f13570n.f13573b.f9880a))).b();
            default:
                C1119f c1119f2 = this.f13570n;
                C2058c c2058cA = c1119f2.a();
                C0840d c0840d = c1119f2.f13573b;
                if (c2058cA == null) {
                    return H8.i.c(H8.h.f2880P, c0840d.toString());
                }
                N0.q qVar = c1119f2.f13572a;
                InterfaceC0652e interfaceC0652eC = P7.e.c(c2058cA, ((C1036a) qVar.f4399m).o.f8921p);
                if (interfaceC0652eC == null) {
                    W7.n nVar = new W7.n(n7.z.i(n7.z.g(c0840d.f9880a)));
                    C1036a c1036a = (C1036a) qVar.f4399m;
                    C0420a c0420a = c1036a.f12419k;
                    c0420a.getClass();
                    C2607b c2607b = (C2607b) c0420a.l;
                    if (c2607b == null) {
                        A7.m.j("resolver");
                        throw null;
                    }
                    interfaceC0652eC = c2607b.g(nVar);
                    if (interfaceC0652eC == null) {
                        interfaceC0652eC = AbstractC0669w.f(c1036a.o, C2057b.j(c2058cA), c1036a.d.c().l);
                    }
                }
                return interfaceC0652eC.j();
        }
    }
}

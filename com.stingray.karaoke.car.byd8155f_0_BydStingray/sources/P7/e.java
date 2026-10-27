package P7;

import H7.w;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import o8.C2057b;
import o8.C2058c;
import o8.C2060e;
import v8.AbstractC2564d;

/* JADX INFO: loaded from: classes.dex */
public final class e implements O8.b {
    public static final e l = new e();

    public static InterfaceC0652e a(InterfaceC0652e interfaceC0652e) {
        C2060e c2060eG = r8.d.g(interfaceC0652e);
        String str = d.f5417a;
        C2058c c2058c = (C2058c) d.f5425k.get(c2060eG);
        if (c2058c != null) {
            return AbstractC2564d.e(interfaceC0652e).i(c2058c);
        }
        throw new IllegalArgumentException("Given class " + interfaceC0652e + " is not a read-only collection");
    }

    public static InterfaceC0652e c(C2058c c2058c, N7.i iVar) {
        A7.m.f(iVar, "builtIns");
        String str = d.f5417a;
        C2057b c2057b = (C2057b) d.f5422h.get(c2058c.i());
        if (c2057b != null) {
            return iVar.i(c2057b.b());
        }
        return null;
    }

    @Override // O8.b
    public Iterable b(Object obj) {
        w[] wVarArr = n.f5447g;
        return ((InterfaceC0650c) obj).b1().q();
    }
}

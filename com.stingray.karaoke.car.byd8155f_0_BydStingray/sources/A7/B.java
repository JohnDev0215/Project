package A7;

import F0.C0226s;
import H7.InterfaceC0318c;
import H7.InterfaceC0319d;
import H7.InterfaceC0320e;
import H7.InterfaceC0321f;
import H7.InterfaceC0324i;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class B {
    public InterfaceC0321f a(i iVar) {
        return iVar;
    }

    public InterfaceC0318c b(Class cls) {
        return new C0058f(cls);
    }

    public InterfaceC0320e c(Class cls, String str) {
        return new r(cls, str);
    }

    public H7.x d(H7.x xVar) {
        H h10 = (H) xVar;
        InterfaceC0319d interfaceC0319dC = xVar.c();
        List listA = xVar.a();
        h10.getClass();
        return new H(interfaceC0319dC, listA, h10.c | 2);
    }

    public InterfaceC0324i e(C0226s c0226s) {
        return c0226s;
    }

    public H7.k f(p pVar) {
        return pVar;
    }

    public H7.r g(E.m mVar) {
        return mVar;
    }

    public H7.t h(s sVar) {
        return sVar;
    }

    public H7.v i(t tVar) {
        return tVar;
    }

    public String j(InterfaceC0060h interfaceC0060h) {
        String string = interfaceC0060h.getClass().getGenericInterfaces()[0].toString();
        return string.startsWith("kotlin.jvm.functions.") ? string.substring(21) : string;
    }

    public String k(o oVar) {
        return j(oVar);
    }

    public H7.x l(InterfaceC0318c interfaceC0318c, List list, boolean z6) {
        m.f(interfaceC0318c, "classifier");
        m.f(list, "arguments");
        return new H(interfaceC0318c, list, z6 ? 1 : 0);
    }
}

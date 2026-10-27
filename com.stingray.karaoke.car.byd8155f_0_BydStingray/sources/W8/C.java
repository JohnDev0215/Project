package W8;

import V8.InterfaceC0818g;
import q7.InterfaceC2189c;
import r7.EnumC2303a;

/* JADX INFO: loaded from: classes.dex */
public final class C implements InterfaceC0818g {
    public final U8.x l;

    public C(U8.x xVar) {
        this.l = xVar;
    }

    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) {
        Object objA = ((U8.w) this.l).o.a(obj, interfaceC2189c);
        return objA == EnumC2303a.l ? objA : m7.q.f19267a;
    }
}

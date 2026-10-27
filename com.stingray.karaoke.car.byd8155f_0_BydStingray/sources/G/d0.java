package G;

import d0.InterfaceC1054c;
import java.util.Iterator;
import java.util.Map;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends A7.o implements InterfaceC2785n {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final d0 f2553m = new d0(2);

    @Override // z7.InterfaceC2785n
    public final Object invoke(Object obj, Object obj2) {
        e0 e0Var = (e0) obj2;
        InterfaceC1054c interfaceC1054c = (InterfaceC1054c) e0Var.f2555b.getValue();
        if (interfaceC1054c != null) {
            Iterator it = e0Var.c.iterator();
            while (it.hasNext()) {
                interfaceC1054c.d(it.next());
            }
        }
        Map mapD = e0Var.f2554a.d();
        if (mapD.isEmpty()) {
            return null;
        }
        return mapD;
    }
}

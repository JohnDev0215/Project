package W7;

import f8.InterfaceC1268d;
import java.lang.reflect.Type;
import java.util.Iterator;
import o8.C2058c;

/* JADX INFO: loaded from: classes.dex */
public abstract class A implements InterfaceC1268d {
    @Override // f8.InterfaceC1266b
    public C0840d a(C2058c c2058c) {
        Object next;
        A7.m.f(c2058c, "fqName");
        Iterator it = k().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (AbstractC0839c.a(n7.z.i(n7.z.g(((C0840d) next).f9880a))).b().equals(c2058c)) {
                break;
            }
        }
        return (C0840d) next;
    }

    public abstract Type b();

    public final boolean equals(Object obj) {
        return (obj instanceof A) && A7.m.a(b(), ((A) obj).b());
    }

    public final int hashCode() {
        return b().hashCode();
    }

    public final String toString() {
        return getClass().getName() + ": " + b();
    }
}

package e.a.n.h;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: EndConsumerHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static String a(String str) {
        return "It is not allowed to subscribe with a(n) " + str + " multiple times. Please create a fresh instance of " + str + " and subscribe that to the target source instead.";
    }

    public static void b(Class<?> cls) {
        e.a.p.a.o(new e.a.l.d(a(cls.getName())));
    }

    public static boolean c(AtomicReference<e.a.k.b> atomicReference, e.a.k.b bVar, Class<?> cls) {
        e.a.n.b.b.c(bVar, "next is null");
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        bVar.d();
        if (atomicReference.get() == e.a.n.a.b.DISPOSED) {
            return false;
        }
        b(cls);
        return false;
    }
}

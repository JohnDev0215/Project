package b5;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: EndConsumerHelper.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {
    public static String a(String str) {
        return "It is not allowed to subscribe with a(n) " + str + " multiple times. Please create a fresh instance of " + str + " and subscribe that to the target source instead.";
    }

    public static void b(Class<?> cls) {
        d5.a.k(new u4.d(a(cls.getName())));
    }

    public static boolean c(AtomicReference<t4.b> atomicReference, t4.b bVar, Class<?> cls) {
        x4.b.c(bVar, "next is null");
        if (atomicReference.compareAndSet(null, bVar)) {
            return true;
        }
        bVar.dispose();
        if (atomicReference.get() == w4.b.DISPOSED) {
            return false;
        }
        b(cls);
        return false;
    }
}

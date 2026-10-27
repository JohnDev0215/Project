package y;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class n0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AtomicReference f23973a = new AtomicReference(null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final b9.c f23974b = b9.d.a();

    public static final void a(n0 n0Var, k0 k0Var) {
        while (true) {
            AtomicReference atomicReference = n0Var.f23973a;
            k0 k0Var2 = (k0) atomicReference.get();
            if (k0Var2 != null && k0Var.f23947a.compareTo(k0Var2.f23947a) < 0) {
                throw new CancellationException("Current mutation had a higher priority");
            }
            while (!atomicReference.compareAndSet(k0Var2, k0Var)) {
                if (atomicReference.get() != k0Var2) {
                    break;
                }
            }
            if (k0Var2 != null) {
                k0Var2.f23948b.g(new T.Q("Mutation interrupted", 3));
                return;
            }
            return;
        }
    }
}

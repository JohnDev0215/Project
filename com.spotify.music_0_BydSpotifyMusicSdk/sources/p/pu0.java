package p;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class pu0 implements xk {
    public static final AtomicReference a = new AtomicReference();

    public final void a(boolean z) {
        synchronized (ru0.j) {
            try {
                for (ru0 ru0Var : new ArrayList(ru0.k.values())) {
                    if (ru0Var.e.get()) {
                        Iterator it = ru0Var.i.iterator();
                        while (it.hasNext()) {
                            ru0 ru0Var2 = ((ou0) it.next()).a;
                            if (z) {
                                ru0Var2.getClass();
                            } else {
                                ((y60) ru0Var2.h.get()).b();
                            }
                        }
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

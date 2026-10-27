package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class ow {
    public static final tz2 a = new tz2("CLOSED", 3);

    public static final Object a(cb2 cb2Var, long j, fz0 fz0Var) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater;
        while (true) {
            if (cb2Var.k >= j && !cb2Var.c()) {
                return cb2Var;
            }
            Object obj = pw.c.get(cb2Var);
            tz2 tz2Var = a;
            if (obj == tz2Var) {
                return tz2Var;
            }
            cb2 cb2Var2 = (cb2) ((pw) obj);
            if (cb2Var2 == null) {
                cb2Var2 = (cb2) fz0Var.g(Long.valueOf(cb2Var.k + 1), cb2Var);
                do {
                    atomicReferenceFieldUpdater = pw.c;
                    if (atomicReferenceFieldUpdater.compareAndSet(cb2Var, null, cb2Var2)) {
                        if (cb2Var.c()) {
                            cb2Var.d();
                        }
                    }
                } while (atomicReferenceFieldUpdater.get(cb2Var) == null);
            }
            cb2Var = cb2Var2;
        }
    }
}

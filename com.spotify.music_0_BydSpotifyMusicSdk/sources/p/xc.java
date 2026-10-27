package p;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public abstract class xc extends oq1 {
    public static final AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(xc.class, Object.class, "_consensus");
    private volatile Object _consensus = wc.a;

    @Override // p.oq1
    public final Object a(Object obj) {
        AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
        Object obj2 = atomicReferenceFieldUpdater.get(this);
        tz2 tz2Var = wc.a;
        if (obj2 == tz2Var) {
            tz2 tz2VarC = c(obj);
            obj2 = atomicReferenceFieldUpdater.get(this);
            if (obj2 == tz2Var) {
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, tz2Var, tz2VarC)) {
                        obj2 = tz2VarC;
                        break;
                    }
                    if (atomicReferenceFieldUpdater.get(this) != tz2Var) {
                        obj2 = atomicReferenceFieldUpdater.get(this);
                        break;
                    }
                }
            }
        }
        b(obj, obj2);
        return obj2;
    }

    public abstract void b(Object obj, Object obj2);

    public abstract tz2 c(Object obj);
}

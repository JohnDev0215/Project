package defpackage;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/* JADX INFO: compiled from: Atomic.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class k9<T> extends ts1 {
    public static final /* synthetic */ AtomicReferenceFieldUpdater a = AtomicReferenceFieldUpdater.newUpdater(k9.class, Object.class, "_consensus");
    private volatile /* synthetic */ Object _consensus = z7.e;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // defpackage.ts1
    public final Object a(Object obj) {
        boolean z;
        Object obj2 = this._consensus;
        a41 a41Var = z7.e;
        if (obj2 == a41Var) {
            a41 a41VarC = c(obj);
            obj2 = this._consensus;
            if (obj2 == a41Var) {
                AtomicReferenceFieldUpdater atomicReferenceFieldUpdater = a;
                while (true) {
                    if (atomicReferenceFieldUpdater.compareAndSet(this, a41Var, a41VarC)) {
                        z = true;
                        break;
                    }
                    if (atomicReferenceFieldUpdater.get(this) != a41Var) {
                        z = false;
                        break;
                    }
                }
                obj2 = z ? a41VarC : this._consensus;
            }
        }
        b(obj, obj2);
        return obj2;
    }

    public abstract void b(T t, Object obj);

    public abstract a41 c(Object obj);
}

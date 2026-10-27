package c.b.e;

import java.util.concurrent.atomic.AtomicReference;

/* JADX WARN: Incorrect class signature: super class is equals to this class */
/* JADX INFO: loaded from: classes.dex */
public final class c extends AtomicReference implements b {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public c(Runnable runnable) {
        super(runnable);
        c.b.h.b.b.a(runnable, "value is null");
    }

    @Override // c.b.e.b
    public final void d() {
        Object andSet;
        if (get() == null || (andSet = getAndSet(null)) == null) {
            return;
        }
        ((Runnable) andSet).run();
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public String toString() {
        StringBuilder sbD = b.a.a.a.a.d("RunnableDisposable(disposed=");
        sbD.append(get() == null);
        sbD.append(", ");
        sbD.append(get());
        sbD.append(")");
        return sbD.toString();
    }
}

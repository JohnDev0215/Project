package p;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/* JADX INFO: loaded from: classes.dex */
public final class dq extends hv {
    public static final AtomicIntegerFieldUpdater c = AtomicIntegerFieldUpdater.newUpdater(dq.class, "_resumed");
    private volatile int _resumed;

    public dq(c00 c00Var, Throwable th, boolean z) {
        if (th == null) {
            th = new CancellationException("Continuation " + c00Var + " was cancelled normally");
        }
        super(th, z);
        this._resumed = 0;
    }
}

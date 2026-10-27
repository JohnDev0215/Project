package p;

import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
public final class fm extends a1 {
    public final Thread l;
    public final pi0 m;

    public fm(y10 y10Var, Thread thread, pi0 pi0Var) {
        super(y10Var, true, true);
        this.l = thread;
        this.m = pi0Var;
    }

    @Override // kotlinx.coroutines.g
    public final void r(Object obj) {
        Thread threadCurrentThread = Thread.currentThread();
        Thread thread = this.l;
        if (v03.b(threadCurrentThread, thread)) {
            return;
        }
        LockSupport.unpark(thread);
    }
}

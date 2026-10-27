package kotlinx.coroutines;

import java.util.concurrent.locks.LockSupport;
import kotlinx.coroutines.z0;

/* JADX INFO: compiled from: EventLoop.kt */
/* JADX INFO: loaded from: classes.dex */
public abstract class a1 extends y0 {
    protected abstract Thread m0();

    protected final void n0(long j7, z0.c cVar) {
        n0.f7710m.z0(j7, cVar);
    }

    protected final void o0() {
        Thread threadM0 = m0();
        if (Thread.currentThread() != threadM0) {
            i2 i2VarA = j2.a();
            if (i2VarA != null) {
                i2VarA.b(threadM0);
            } else {
                LockSupport.unpark(threadM0);
            }
        }
    }
}

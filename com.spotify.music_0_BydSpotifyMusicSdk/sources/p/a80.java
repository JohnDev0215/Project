package p;

import java.io.Serializable;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class a80 implements ThreadFactory {
    public final /* synthetic */ int c;
    public final Serializable e;
    public final /* synthetic */ Object k;

    public a80(c80 c80Var) {
        this.c = 0;
        this.k = c80Var;
        this.e = new AtomicInteger(0);
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        int i = this.c;
        Serializable serializable = this.e;
        switch (i) {
            case 0:
                Thread thread = new Thread(runnable);
                thread.setName("arch_disk_io_" + ((AtomicInteger) serializable).getAndIncrement());
                return thread;
            default:
                Thread threadNewThread = Executors.defaultThreadFactory().newThread(new xk0(runnable));
                threadNewThread.setName(((String) serializable) + ((AtomicLong) this.k).getAndIncrement());
                return threadNewThread;
        }
    }

    public a80(String str, AtomicLong atomicLong) {
        this.c = 1;
        this.e = str;
        this.k = atomicLong;
    }
}

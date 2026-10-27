package p;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
public final class fx implements ThreadFactory {
    public final AtomicInteger c = new AtomicInteger(0);
    public final /* synthetic */ boolean e;

    public fx(boolean z) {
        this.e = z;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        v03.j(runnable, "runnable");
        return new Thread(runnable, (this.e ? "WM.task-" : "androidx.work-") + this.c.incrementAndGet());
    }
}

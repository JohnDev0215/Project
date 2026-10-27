package p;

import android.os.StrictMode;
import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: loaded from: classes.dex */
public final class a50 implements ThreadFactory {
    public static final ThreadFactory m = Executors.defaultThreadFactory();
    public final AtomicLong c = new AtomicLong();
    public final String e;
    public final int k;
    public final StrictMode.ThreadPolicy l;

    public a50(String str, int i, StrictMode.ThreadPolicy threadPolicy) {
        this.e = str;
        this.k = i;
        this.l = threadPolicy;
    }

    @Override // java.util.concurrent.ThreadFactory
    public final Thread newThread(Runnable runnable) {
        Thread threadNewThread = m.newThread(new ua(this, 17, runnable));
        threadNewThread.setName(String.format(Locale.ROOT, "%s Thread #%d", this.e, Long.valueOf(this.c.getAndIncrement())));
        return threadNewThread;
    }
}

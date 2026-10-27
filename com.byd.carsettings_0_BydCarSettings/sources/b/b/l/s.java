package b.b.l;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: TimeStamp.java */
/* JADX INFO: loaded from: classes2.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicLong f2042a = new AtomicLong();

    public static long a() {
        return System.currentTimeMillis() + f2042a.incrementAndGet();
    }
}

package t3;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: TimeStamp.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicLong f8085a = new AtomicLong();

    public static long a() {
        return System.currentTimeMillis() + f8085a.incrementAndGet();
    }
}

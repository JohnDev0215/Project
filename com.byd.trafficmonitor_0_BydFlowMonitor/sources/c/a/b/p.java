package c.a.b;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: TimeStamp.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicLong f3513a = new AtomicLong();

    public static long a() {
        return System.currentTimeMillis() + f3513a.incrementAndGet();
    }
}

package b.a.c;

import java.util.concurrent.atomic.AtomicLong;

/* JADX INFO: compiled from: TimeStamp.java */
/* JADX INFO: loaded from: classes2.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static AtomicLong f278a = new AtomicLong();

    public static long a() {
        return System.currentTimeMillis() + f278a.incrementAndGet();
    }
}

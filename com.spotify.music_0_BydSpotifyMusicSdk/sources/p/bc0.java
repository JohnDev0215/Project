package p;

import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class bc0 {
    public final long a;
    public final long b;
    public final long c;

    public bc0(long j, long j2, long j3) {
        this.a = j;
        this.b = j2;
        this.c = j3;
    }

    public static bc0 a(long j) {
        long j2 = j + 59000;
        TimeUnit timeUnit = TimeUnit.MILLISECONDS;
        long days = timeUnit.toDays(j2);
        long hours = timeUnit.toHours(j2);
        TimeUnit timeUnit2 = TimeUnit.DAYS;
        long hours2 = hours - timeUnit2.toHours(days);
        return new bc0(days, hours2, (timeUnit.toMinutes(j2) - timeUnit2.toMinutes(days)) - TimeUnit.HOURS.toMinutes(hours2));
    }
}

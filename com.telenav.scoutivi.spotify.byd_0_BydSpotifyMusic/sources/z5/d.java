package z5;

import java.util.concurrent.TimeUnit;

/* JADX INFO: compiled from: DurationUnitJvm.kt */
/* JADX INFO: loaded from: classes.dex */
public enum d {
    NANOSECONDS(TimeUnit.NANOSECONDS),
    MICROSECONDS(TimeUnit.MICROSECONDS),
    MILLISECONDS(TimeUnit.MILLISECONDS),
    SECONDS(TimeUnit.SECONDS),
    MINUTES(TimeUnit.MINUTES),
    HOURS(TimeUnit.HOURS),
    DAYS(TimeUnit.DAYS);


    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final TimeUnit f10057e;

    d(TimeUnit timeUnit) {
        this.f10057e = timeUnit;
    }

    public final TimeUnit getTimeUnit$kotlin_stdlib() {
        return this.f10057e;
    }
}

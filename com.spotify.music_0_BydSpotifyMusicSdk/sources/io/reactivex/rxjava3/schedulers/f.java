package io.reactivex.rxjava3.schedulers;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class f {
    public final Object a;
    public final long b;
    public final TimeUnit c;

    public f(Object obj, long j, TimeUnit timeUnit) {
        Objects.requireNonNull(obj, "value is null");
        this.a = obj;
        this.b = j;
        Objects.requireNonNull(timeUnit, "unit is null");
        this.c = timeUnit;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof f)) {
            return false;
        }
        f fVar = (f) obj;
        return Objects.equals(this.a, fVar.a) && this.b == fVar.b && Objects.equals(this.c, fVar.c);
    }

    public final int hashCode() {
        int iHashCode = this.a.hashCode() * 31;
        long j = this.b;
        return this.c.hashCode() + ((iHashCode + ((int) (j ^ (j >>> 31)))) * 31);
    }

    public final String toString() {
        return "Timed[time=" + this.b + ", unit=" + this.c + ", value=" + this.a + "]";
    }
}

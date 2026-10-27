package h2;

import java.math.RoundingMode;

/* JADX INFO: loaded from: classes.dex */
public final class y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f15249a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f15250b;
    public long c;
    public final ThreadLocal d = new ThreadLocal();

    public y(long j3) {
        e(j3);
    }

    public final synchronized long a(long j3) {
        long j10;
        if (j3 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        try {
            synchronized (this) {
                if (!(this.f15250b != -9223372036854775807L)) {
                    long jLongValue = this.f15249a;
                    if (jLongValue == 9223372036854775806L) {
                        Long l = (Long) this.d.get();
                        l.getClass();
                        jLongValue = l.longValue();
                    }
                    this.f15250b = jLongValue - j3;
                    notifyAll();
                }
                this.c = j3;
                j10 = j3 + this.f15250b;
            }
            return j10;
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized long b(long j3) {
        if (j3 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        try {
            long j10 = this.c;
            if (j10 != -9223372036854775807L) {
                int i7 = AbstractC1343A.f15195a;
                long jN = AbstractC1343A.N(j10, 90000L, 1000000L, RoundingMode.DOWN);
                long j11 = (4294967296L + jN) / 8589934592L;
                long j12 = ((j11 - 1) * 8589934592L) + j3;
                long j13 = (j11 * 8589934592L) + j3;
                j3 = Math.abs(j12 - jN) < Math.abs(j13 - jN) ? j12 : j13;
            }
            long j14 = j3;
            int i10 = AbstractC1343A.f15195a;
            return a(AbstractC1343A.N(j14, 1000000L, 90000L, RoundingMode.DOWN));
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized long c(long j3) {
        if (j3 == -9223372036854775807L) {
            return -9223372036854775807L;
        }
        try {
            long j10 = this.c;
            if (j10 != -9223372036854775807L) {
                int i7 = AbstractC1343A.f15195a;
                long jN = AbstractC1343A.N(j10, 90000L, 1000000L, RoundingMode.DOWN);
                long j11 = jN / 8589934592L;
                long j12 = (j11 * 8589934592L) + j3;
                j3 = j12 >= jN ? j12 : ((j11 + 1) * 8589934592L) + j3;
            }
            long j13 = j3;
            int i10 = AbstractC1343A.f15195a;
            return a(AbstractC1343A.N(j13, 1000000L, 90000L, RoundingMode.DOWN));
        } catch (Throwable th) {
            throw th;
        }
    }

    public final synchronized long d() {
        long j3;
        j3 = this.f15249a;
        if (j3 == Long.MAX_VALUE || j3 == 9223372036854775806L) {
            j3 = -9223372036854775807L;
        }
        return j3;
    }

    public final synchronized void e(long j3) {
        this.f15249a = j3;
        this.f15250b = j3 == Long.MAX_VALUE ? 0L : -9223372036854775807L;
        this.c = -9223372036854775807L;
    }
}

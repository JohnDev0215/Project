package G7;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class e implements Iterable, B7.a {
    public final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final long f2716m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final long f2717n;

    public e(long j3, long j10, long j11) {
        if (j11 == 0) {
            throw new IllegalArgumentException("Step must be non-zero.");
        }
        if (j11 == Long.MIN_VALUE) {
            throw new IllegalArgumentException("Step must be greater than Long.MIN_VALUE to avoid overflow on negation.");
        }
        this.l = j3;
        if (j11 > 0) {
            if (j3 < j10) {
                long j12 = j10 % j11;
                long j13 = j3 % j11;
                long j14 = ((j12 < 0 ? j12 + j11 : j12) - (j13 < 0 ? j13 + j11 : j13)) % j11;
                j10 -= j14 < 0 ? j14 + j11 : j14;
            }
        } else {
            if (j11 >= 0) {
                throw new IllegalArgumentException("Step is zero.");
            }
            if (j3 > j10) {
                long j15 = -j11;
                long j16 = j3 % j15;
                long j17 = j10 % j15;
                long j18 = ((j16 < 0 ? j16 + j15 : j16) - (j17 < 0 ? j17 + j15 : j17)) % j15;
                j10 += j18 < 0 ? j18 + j15 : j18;
            }
        }
        this.f2716m = j10;
        this.f2717n = j11;
    }

    public boolean equals(Object obj) {
        if (obj instanceof e) {
            if (!isEmpty() || !((e) obj).isEmpty()) {
                e eVar = (e) obj;
                if (this.l != eVar.l || this.f2716m != eVar.f2716m || this.f2717n != eVar.f2717n) {
                }
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        if (isEmpty()) {
            return -1;
        }
        long j3 = 31;
        long j10 = this.l;
        long j11 = this.f2716m;
        long j12 = (((j10 ^ (j10 >>> 32)) * j3) + (j11 ^ (j11 >>> 32))) * j3;
        long j13 = this.f2717n;
        return (int) (j12 + (j13 ^ (j13 >>> 32)));
    }

    public boolean isEmpty() {
        long j3 = this.f2717n;
        long j10 = this.f2716m;
        long j11 = this.l;
        if (j3 > 0) {
            if (j11 <= j10) {
                return false;
            }
        } else if (j11 >= j10) {
            return false;
        }
        return true;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        return new f(this.l, this.f2716m, this.f2717n);
    }

    public String toString() {
        StringBuilder sb;
        long j3 = this.f2717n;
        long j10 = this.f2716m;
        long j11 = this.l;
        if (j3 > 0) {
            sb = new StringBuilder();
            sb.append(j11);
            sb.append("..");
            sb.append(j10);
            sb.append(" step ");
            sb.append(j3);
        } else {
            sb = new StringBuilder();
            sb.append(j11);
            sb.append(" downTo ");
            sb.append(j10);
            sb.append(" step ");
            sb.append(-j3);
        }
        return sb.toString();
    }
}

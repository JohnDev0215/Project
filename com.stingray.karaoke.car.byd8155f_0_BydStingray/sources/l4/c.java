package L4;

import android.animation.TimeInterpolator;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f4011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f4012b;
    public TimeInterpolator c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f4013e;

    public final TimeInterpolator a() {
        TimeInterpolator timeInterpolator = this.c;
        return timeInterpolator != null ? timeInterpolator : a.f4008b;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        if (this.f4011a == cVar.f4011a && this.f4012b == cVar.f4012b && this.d == cVar.d && this.f4013e == cVar.f4013e) {
            return a().getClass().equals(cVar.a().getClass());
        }
        return false;
    }

    public final int hashCode() {
        long j3 = this.f4011a;
        long j10 = this.f4012b;
        return ((((a().getClass().hashCode() + (((((int) (j3 ^ (j3 >>> 32))) * 31) + ((int) ((j10 >>> 32) ^ j10))) * 31)) * 31) + this.d) * 31) + this.f4013e;
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("\n");
        sb.append(c.class.getName());
        sb.append('{');
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append(" delay: ");
        sb.append(this.f4011a);
        sb.append(" duration: ");
        sb.append(this.f4012b);
        sb.append(" interpolator: ");
        sb.append(a().getClass());
        sb.append(" repeatCount: ");
        sb.append(this.d);
        sb.append(" repeatMode: ");
        return M5.b.h(sb, this.f4013e, "}\n");
    }
}

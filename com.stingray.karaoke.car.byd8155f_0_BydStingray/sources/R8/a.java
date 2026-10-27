package R8;

import A7.m;
import com.bumptech.glide.d;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public abstract class a implements Comparable {
    public static final long l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final long f7564m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final /* synthetic */ int f7565n = 0;

    static {
        int i7 = b.f7566a;
        l = d.z(4611686018427387903L);
        f7564m = d.z(-4611686018427387903L);
    }

    public static final long a(long j3, long j10) {
        long j11 = 1000000;
        long j12 = j10 / j11;
        long j13 = j3 + j12;
        if (-4611686018426L > j13 || j13 >= 4611686018427L) {
            return d.z(AbstractC2673a.w(j13, -4611686018427387903L, 4611686018427387903L));
        }
        long j14 = ((j13 * j11) + (j10 - (j12 * j11))) << 1;
        int i7 = b.f7566a;
        return j14;
    }

    public static final long b(long j3) {
        int i7 = ((int) j3) & 1;
        if (i7 == 1 && !c(j3)) {
            return j3 >> 1;
        }
        c cVar = c.f7568n;
        m.f(cVar, "unit");
        if (j3 == l) {
            return Long.MAX_VALUE;
        }
        if (j3 == f7564m) {
            return Long.MIN_VALUE;
        }
        long j10 = j3 >> 1;
        c cVar2 = i7 == 0 ? c.f7567m : cVar;
        m.f(cVar2, "sourceUnit");
        return cVar.l.convert(j10, cVar2.l);
    }

    public static final boolean c(long j3) {
        return j3 == l || j3 == f7564m;
    }
}

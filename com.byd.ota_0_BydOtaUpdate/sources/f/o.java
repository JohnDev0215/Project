package f;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: SegmentPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public static n f3728a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f3729b;

    public static void a(n nVar) {
        if (nVar.f3726f != null || nVar.f3727g != null) {
            throw new IllegalArgumentException();
        }
        if (nVar.f3724d) {
            return;
        }
        synchronized (o.class) {
            long j = f3729b;
            if (j + 8192 > 65536) {
                return;
            }
            f3729b = j + 8192;
            nVar.f3726f = f3728a;
            nVar.f3723c = 0;
            nVar.f3722b = 0;
            f3728a = nVar;
        }
    }

    public static n b() {
        synchronized (o.class) {
            n nVar = f3728a;
            if (nVar == null) {
                return new n();
            }
            f3728a = nVar.f3726f;
            nVar.f3726f = null;
            f3729b -= 8192;
            return nVar;
        }
    }
}

package i7;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: SegmentPool.java */
/* JADX INFO: loaded from: classes2.dex */
public final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public static h f5529a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f5530b;

    public static void a(h hVar) {
        if (hVar.f5527f != null || hVar.f5528g != null) {
            throw new IllegalArgumentException();
        }
        if (hVar.f5525d) {
            return;
        }
        synchronized (i.class) {
            long j8 = f5530b;
            if (j8 + 8192 > 65536) {
                return;
            }
            f5530b = j8 + 8192;
            hVar.f5527f = f5529a;
            hVar.f5524c = 0;
            hVar.f5523b = 0;
            f5529a = hVar;
        }
    }

    public static h b() {
        synchronized (i.class) {
            h hVar = f5529a;
            if (hVar == null) {
                return new h();
            }
            f5529a = hVar.f5527f;
            hVar.f5527f = null;
            f5530b -= 8192;
            return hVar;
        }
    }
}

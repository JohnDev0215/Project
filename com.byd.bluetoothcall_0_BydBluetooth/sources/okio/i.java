package okio;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: SegmentPool.java */
/* JADX INFO: loaded from: classes.dex */
final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    static h f4386a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static long f4387b;

    private i() {
    }

    static void a(h hVar) {
        if (hVar.f != null || hVar.g != null) {
            throw new IllegalArgumentException();
        }
        if (hVar.f4384d) {
            return;
        }
        synchronized (i.class) {
            long j = f4387b;
            if (j + 8192 > 65536) {
                return;
            }
            f4387b = j + 8192;
            hVar.f = f4386a;
            hVar.f4383c = 0;
            hVar.f4382b = 0;
            f4386a = hVar;
        }
    }

    static h b() {
        synchronized (i.class) {
            h hVar = f4386a;
            if (hVar == null) {
                return new h();
            }
            f4386a = hVar.f;
            hVar.f = null;
            f4387b -= 8192;
            return hVar;
        }
    }
}

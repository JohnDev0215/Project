package okio;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: SegmentPool.java */
/* JADX INFO: loaded from: classes.dex */
final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    static h f4664a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static long f4665b;

    private i() {
    }

    static void a(h hVar) {
        if (hVar.f != null || hVar.g != null) {
            throw new IllegalArgumentException();
        }
        if (hVar.f4662d) {
            return;
        }
        synchronized (i.class) {
            long j = f4665b;
            if (j + 8192 > 65536) {
                return;
            }
            f4665b = j + 8192;
            hVar.f = f4664a;
            hVar.f4661c = 0;
            hVar.f4660b = 0;
            f4664a = hVar;
        }
    }

    static h b() {
        synchronized (i.class) {
            h hVar = f4664a;
            if (hVar == null) {
                return new h();
            }
            f4664a = hVar.f;
            hVar.f = null;
            f4665b -= 8192;
            return hVar;
        }
    }
}

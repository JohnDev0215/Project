package c;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
final class i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    static h f642a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static long f643b;

    private i() {
    }

    static void a(h hVar) {
        if (hVar.f != null || hVar.g != null) {
            throw new IllegalArgumentException();
        }
        if (hVar.d) {
            return;
        }
        synchronized (i.class) {
            long j = f643b;
            if (j + 8192 > 65536) {
                return;
            }
            f643b = j + 8192;
            hVar.f = f642a;
            hVar.f641c = 0;
            hVar.f640b = 0;
            f642a = hVar;
        }
    }

    static h b() {
        synchronized (i.class) {
            h hVar = f642a;
            if (hVar == null) {
                return new h();
            }
            f642a = hVar.f;
            hVar.f = null;
            f643b -= 8192;
            return hVar;
        }
    }
}

package g;

import javax.annotation.Nullable;

/* JADX INFO: compiled from: SegmentPool.java */
/* JADX INFO: loaded from: classes.dex */
public final class n {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    public static m f5503a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static long f5504b;

    public static void a(m mVar) {
        if (mVar.f5501f != null || mVar.f5502g != null) {
            throw new IllegalArgumentException();
        }
        if (mVar.f5499d) {
            return;
        }
        synchronized (n.class) {
            long j = f5504b;
            if (j + 8192 > 65536) {
                return;
            }
            f5504b = j + 8192;
            mVar.f5501f = f5503a;
            mVar.f5498c = 0;
            mVar.f5497b = 0;
            f5503a = mVar;
        }
    }

    public static m b() {
        synchronized (n.class) {
            m mVar = f5503a;
            if (mVar == null) {
                return new m();
            }
            f5503a = mVar.f5501f;
            mVar.f5501f = null;
            f5504b -= 8192;
            return mVar;
        }
    }
}

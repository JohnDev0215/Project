package okio;

import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
final class SegmentPool {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    @Nullable
    static Segment f5849a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static long f5850b;

    private SegmentPool() {
    }

    static void a(Segment segment) {
        if (segment.f5847f != null || segment.f5848g != null) {
            throw new IllegalArgumentException();
        }
        if (segment.f5845d) {
            return;
        }
        synchronized (SegmentPool.class) {
            long j = f5850b;
            if (j + 8192 > 65536) {
                return;
            }
            f5850b = j + 8192;
            segment.f5847f = f5849a;
            segment.f5844c = 0;
            segment.f5843b = 0;
            f5849a = segment;
        }
    }

    static Segment b() {
        synchronized (SegmentPool.class) {
            Segment segment = f5849a;
            if (segment == null) {
                return new Segment();
            }
            f5849a = segment.f5847f;
            segment.f5847f = null;
            f5850b -= 8192;
            return segment;
        }
    }
}

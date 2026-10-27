package okio;

import kotlin.j;
import kotlin.jvm.internal.i;

/* JADX INFO: compiled from: SegmentPool.kt */
/* JADX INFO: loaded from: classes.dex */
public final class SegmentPool {
    public static final SegmentPool INSTANCE = new SegmentPool();
    public static final long MAX_SIZE = 65536;
    private static long byteCount;
    private static Segment next;

    private SegmentPool() {
    }

    public final long getByteCount() {
        return byteCount;
    }

    public final Segment getNext() {
        return next;
    }

    public final void recycle(Segment segment) {
        i.c(segment, "segment");
        if (!(segment.next == null && segment.prev == null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (segment.shared) {
            return;
        }
        synchronized (this) {
            long j = byteCount;
            long j2 = Segment.SIZE;
            if (j + j2 > MAX_SIZE) {
                return;
            }
            byteCount = j + j2;
            segment.next = next;
            segment.limit = 0;
            segment.pos = 0;
            next = segment;
            j jVar = j.f5109a;
        }
    }

    public final void setByteCount(long j) {
        byteCount = j;
    }

    public final void setNext(Segment segment) {
        next = segment;
    }

    public final Segment take() {
        synchronized (this) {
            Segment segment = next;
            if (segment == null) {
                return new Segment();
            }
            next = segment.next;
            segment.next = null;
            byteCount -= (long) Segment.SIZE;
            return segment;
        }
    }
}

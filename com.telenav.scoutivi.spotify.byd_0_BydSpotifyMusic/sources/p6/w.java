package p6;

import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: compiled from: SegmentPool.kt */
/* JADX INFO: loaded from: classes.dex */
public final class w {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final int f8911c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final AtomicReference<v>[] f8912d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final w f8913e = new w();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f8909a = 65536;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final v f8910b = new v(new byte[0], 0, 0, false, false);

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        f8911c = iHighestOneBit;
        AtomicReference<v>[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i7 = 0; i7 < iHighestOneBit; i7++) {
            atomicReferenceArr[i7] = new AtomicReference<>();
        }
        f8912d = atomicReferenceArr;
    }

    private w() {
    }

    private final AtomicReference<v> a() {
        Thread threadCurrentThread = Thread.currentThread();
        kotlin.jvm.internal.l.d(threadCurrentThread, "Thread.currentThread()");
        return f8912d[(int) (threadCurrentThread.getId() & (((long) f8911c) - 1))];
    }

    public static final void b(v segment) {
        AtomicReference<v> atomicReferenceA;
        v vVar;
        kotlin.jvm.internal.l.e(segment, "segment");
        if (!(segment.f8907f == null && segment.f8908g == null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (segment.f8905d || (vVar = (atomicReferenceA = f8913e.a()).get()) == f8910b) {
            return;
        }
        int i7 = vVar != null ? vVar.f8904c : 0;
        if (i7 >= f8909a) {
            return;
        }
        segment.f8907f = vVar;
        segment.f8903b = 0;
        segment.f8904c = i7 + 8192;
        if (atomicReferenceA.compareAndSet(vVar, segment)) {
            return;
        }
        segment.f8907f = null;
    }

    public static final v c() {
        AtomicReference<v> atomicReferenceA = f8913e.a();
        v vVar = f8910b;
        v andSet = atomicReferenceA.getAndSet(vVar);
        if (andSet == vVar) {
            return new v();
        }
        if (andSet == null) {
            atomicReferenceA.set(null);
            return new v();
        }
        atomicReferenceA.set(andSet.f8907f);
        andSet.f8907f = null;
        andSet.f8904c = 0;
        return andSet;
    }
}

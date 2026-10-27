package spi;

import java.util.concurrent.atomic.AtomicReference;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX INFO: loaded from: classes2.dex */
public final class wo {
    public static final int c;
    public static final AtomicReference<vo>[] d;
    public static final wo e = new wo();
    public static final int a = 65536;
    public static final vo b = new vo(new byte[0], 0, 0, false, false);

    static {
        int iHighestOneBit = Integer.highestOneBit((Runtime.getRuntime().availableProcessors() * 2) - 1);
        c = iHighestOneBit;
        AtomicReference<vo>[] atomicReferenceArr = new AtomicReference[iHighestOneBit];
        for (int i = 0; i < iHighestOneBit; i++) {
            atomicReferenceArr[i] = new AtomicReference<>();
        }
        d = atomicReferenceArr;
    }

    private wo() {
    }

    @JvmStatic
    public static final void b(@NotNull vo segment) {
        AtomicReference<vo> atomicReferenceA;
        vo voVar;
        Intrinsics.checkNotNullParameter(segment, "segment");
        if (!(segment.f == null && segment.g == null)) {
            throw new IllegalArgumentException("Failed requirement.".toString());
        }
        if (segment.d || (voVar = (atomicReferenceA = e.a()).get()) == b) {
            return;
        }
        int i = voVar != null ? voVar.c : 0;
        if (i >= a) {
            return;
        }
        segment.f = voVar;
        segment.b = 0;
        segment.c = i + 8192;
        if (atomicReferenceA.compareAndSet(voVar, segment)) {
            return;
        }
        segment.f = null;
    }

    @JvmStatic
    @NotNull
    public static final vo c() {
        AtomicReference<vo> atomicReferenceA = e.a();
        vo voVar = b;
        vo andSet = atomicReferenceA.getAndSet(voVar);
        if (andSet == voVar) {
            return new vo();
        }
        if (andSet == null) {
            atomicReferenceA.set(null);
            return new vo();
        }
        atomicReferenceA.set(andSet.f);
        andSet.f = null;
        andSet.c = 0;
        return andSet;
    }

    public final AtomicReference<vo> a() {
        Thread threadCurrentThread = Thread.currentThread();
        Intrinsics.checkNotNullExpressionValue(threadCurrentThread, "Thread.currentThread()");
        return d[(int) (threadCurrentThread.getId() & (((long) c) - 1))];
    }
}

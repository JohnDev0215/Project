package kotlinx.coroutines.sync;

import kotlinx.coroutines.internal.c0;
import kotlinx.coroutines.internal.f0;

/* JADX INFO: compiled from: Semaphore.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final int f7819a = f0.d("kotlinx.coroutines.semaphore.maxSpinCycles", 100, 0, 0, 12, null);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final c0 f7820b = new c0("PERMIT");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final c0 f7821c = new c0("TAKEN");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static final c0 f7822d = new c0("BROKEN");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static final c0 f7823e = new c0("CANCELLED");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private static final int f7824f = f0.d("kotlinx.coroutines.semaphore.segmentSize", 16, 0, 0, 12, null);

    public static final b a(int i7, int i8) {
        return new c(i7, i8);
    }

    public static /* synthetic */ b b(int i7, int i8, int i9, Object obj) {
        if ((i9 & 2) != 0) {
            i8 = 0;
        }
        return a(i7, i8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final e j(long j7, e eVar) {
        return new e(j7, eVar, 0);
    }
}

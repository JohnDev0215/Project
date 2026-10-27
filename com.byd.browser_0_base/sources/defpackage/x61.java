package defpackage;

import J.N;
import defpackage.xz;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: LibraryPrefetcher.java */
/* JADX INFO: loaded from: classes.dex */
public final class x61 {
    public static final AtomicBoolean a = new AtomicBoolean();

    public static void a() {
        N.MOXOasS5();
        int i = 1;
        final boolean zCompareAndSet = a.compareAndSet(false, true);
        if (zCompareAndSet && iv.e().g("log-native-library-residency")) {
            new Thread(new wz(i)).start();
        } else {
            PostTask.c(d73.j, new Runnable() { // from class: w61
                @Override // java.lang.Runnable
                public final void run() {
                    int iMdFgVRJJ = N.MdFgVRJJ();
                    TraceEvent traceEventH0 = TraceEvent.H0("LibraryPrefetcher.asyncPrefetchLibrariesToMemory", Integer.toString(iMdFgVRJJ));
                    boolean z = zCompareAndSet;
                    if (z && iMdFgVRJJ < 90) {
                        try {
                            N.MUjpxN8d();
                        } catch (Throwable th) {
                            if (traceEventH0 != null) {
                                try {
                                    traceEventH0.close();
                                } catch (Throwable unused) {
                                }
                            }
                            throw th;
                        }
                    }
                    if (iMdFgVRJJ != -1) {
                        oj2.Z(iMdFgVRJJ, "LibraryLoader.PercentageOfResidentCodeBeforePrefetch".concat(z ? ".ColdStartup" : ".WarmStartup"));
                    }
                    if (traceEventH0 != null) {
                        traceEventH0.close();
                    }
                    xz.a.a.edit().remove("dont_prefetch_libraries").apply();
                }
            });
        }
    }
}

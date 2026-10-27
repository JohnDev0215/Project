package defpackage;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlinx.coroutines.scheduling.a;

/* JADX INFO: compiled from: Dispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public class le2 extends hi0 {
    public final a f;

    public le2(int i, int i2, long j) {
        this.f = new a("DefaultDispatcher", i, i2, j);
    }

    @Override // defpackage.p00
    public final void h1(m00 m00Var, Runnable runnable) {
        AtomicLongFieldUpdater atomicLongFieldUpdater = a.l;
        this.f.t(runnable, e73.f, false);
    }
}

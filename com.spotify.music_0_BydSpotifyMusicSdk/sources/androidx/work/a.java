package androidx.work;

import androidx.work.impl.utils.futures.b;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import p.d1;
import p.lb1;
import p.lq2;
import p.r61;
import p.ry0;

/* JADX INFO: loaded from: classes.dex */
public final class a implements lb1 {
    public final b c = new b();

    public a(r61 r61Var) {
        r61Var.M(false, true, new ry0() { // from class: androidx.work.JobListenableFuture$1
            {
                super(1);
            }

            @Override // p.ry0
            public final Object invoke(Object obj) {
                Throwable th = (Throwable) obj;
                if (th == null) {
                    if (!this.this$0.c.isDone()) {
                        throw new IllegalArgumentException("Failed requirement.".toString());
                    }
                } else if (th instanceof CancellationException) {
                    this.this$0.c.cancel(true);
                } else {
                    b bVar = this.this$0.c;
                    Throwable cause = th.getCause();
                    if (cause != null) {
                        th = cause;
                    }
                    bVar.k(th);
                }
                return lq2.a;
            }
        });
    }

    @Override // p.lb1
    public final void a(Runnable runnable, Executor executor) {
        this.c.a(runnable, executor);
    }

    @Override // java.util.concurrent.Future
    public final boolean cancel(boolean z) {
        return this.c.cancel(z);
    }

    @Override // java.util.concurrent.Future
    public final Object get() {
        return this.c.get();
    }

    @Override // java.util.concurrent.Future
    public final boolean isCancelled() {
        return this.c.c instanceof d1;
    }

    @Override // java.util.concurrent.Future
    public final boolean isDone() {
        return this.c.isDone();
    }

    @Override // java.util.concurrent.Future
    public final Object get(long j, TimeUnit timeUnit) {
        return this.c.get(j, timeUnit);
    }
}

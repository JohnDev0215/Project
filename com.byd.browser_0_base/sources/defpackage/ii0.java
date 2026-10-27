package defpackage;

import defpackage.m31;
import java.lang.reflect.Method;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/* JADX INFO: compiled from: Executors.kt */
/* JADX INFO: loaded from: classes.dex */
public final class ii0 extends hi0 implements h80 {
    public final Executor f;

    public ii0(Executor executor) {
        Method method;
        this.f = executor;
        Method method2 = ax.a;
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = executor instanceof ScheduledThreadPoolExecutor ? (ScheduledThreadPoolExecutor) executor : null;
            if (scheduledThreadPoolExecutor != null && (method = ax.a) != null) {
                method.invoke(scheduledThreadPoolExecutor, Boolean.TRUE);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Executor executor = this.f;
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService == null) {
            return;
        }
        executorService.shutdown();
    }

    public final boolean equals(Object obj) {
        return (obj instanceof ii0) && ((ii0) obj).f == this.f;
    }

    @Override // defpackage.p00
    public final void h1(m00 m00Var, Runnable runnable) {
        try {
            this.f.execute(runnable);
        } catch (RejectedExecutionException e) {
            CancellationException cancellationException = new CancellationException("The task was rejected");
            cancellationException.initCause(e);
            m31 m31Var = (m31) m00Var.get(m31.a.e);
            if (m31Var != null) {
                m31Var.Q0(cancellationException);
            }
            xa0.b.h1(m00Var, runnable);
        }
    }

    public final int hashCode() {
        return System.identityHashCode(this.f);
    }

    @Override // defpackage.p00
    public final String toString() {
        return this.f.toString();
    }
}

package p;

import java.lang.reflect.Method;
import java.util.concurrent.CancellationException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* JADX INFO: loaded from: classes.dex */
public final class wk0 extends kotlinx.coroutines.f implements o80 {
    public final Executor k;

    public wk0(Executor executor) {
        Method method;
        this.k = executor;
        Method method2 = nw.a;
        try {
            ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = executor instanceof ScheduledThreadPoolExecutor ? (ScheduledThreadPoolExecutor) executor : null;
            if (scheduledThreadPoolExecutor != null && (method = nw.a) != null) {
                method.invoke(scheduledThreadPoolExecutor, Boolean.TRUE);
            }
        } catch (Throwable unused) {
        }
    }

    @Override // kotlinx.coroutines.b
    public final void G0(y10 y10Var, Runnable runnable) {
        try {
            this.k.execute(runnable);
        } catch (RejectedExecutionException e) {
            CancellationException cancellationException = new CancellationException("The task was rejected");
            cancellationException.initCause(e);
            p61 p61Var = (p61) y10Var.E0(qo.o);
            if (p61Var != null) {
                p61Var.i(cancellationException);
            }
            ha0.c.G0(y10Var, runnable);
        }
    }

    @Override // p.o80
    public final oa0 J(long j, ul2 ul2Var, y10 y10Var) {
        Executor executor = this.k;
        ScheduledFuture<?> scheduledFutureSchedule = null;
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        if (scheduledExecutorService != null) {
            try {
                scheduledFutureSchedule = scheduledExecutorService.schedule(ul2Var, j, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e) {
                CancellationException cancellationException = new CancellationException("The task was rejected");
                cancellationException.initCause(e);
                p61 p61Var = (p61) y10Var.E0(qo.o);
                if (p61Var != null) {
                    p61Var.i(cancellationException);
                }
            }
        }
        return scheduledFutureSchedule != null ? new na0(scheduledFutureSchedule) : kotlinx.coroutines.c.r.J(j, ul2Var, y10Var);
    }

    @Override // p.o80
    public final void X(long j, zp zpVar) {
        Executor executor = this.k;
        ScheduledFuture<?> scheduledFutureSchedule = null;
        ScheduledExecutorService scheduledExecutorService = executor instanceof ScheduledExecutorService ? (ScheduledExecutorService) executor : null;
        if (scheduledExecutorService != null) {
            try {
                scheduledFutureSchedule = scheduledExecutorService.schedule(new z13(this, zpVar, 5), j, TimeUnit.MILLISECONDS);
            } catch (RejectedExecutionException e) {
                CancellationException cancellationException = new CancellationException("The task was rejected");
                cancellationException.initCause(e);
                p61 p61Var = (p61) zpVar.m.E0(qo.o);
                if (p61Var != null) {
                    p61Var.i(cancellationException);
                }
            }
        }
        if (scheduledFutureSchedule != null) {
            zpVar.t(new rp(0, scheduledFutureSchedule));
        } else {
            kotlinx.coroutines.c.r.X(j, zpVar);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public final void close() {
        Executor executor = this.k;
        ExecutorService executorService = executor instanceof ExecutorService ? (ExecutorService) executor : null;
        if (executorService != null) {
            executorService.shutdown();
        }
    }

    public final boolean equals(Object obj) {
        return (obj instanceof wk0) && ((wk0) obj).k == this.k;
    }

    public final int hashCode() {
        return System.identityHashCode(this.k);
    }

    @Override // kotlinx.coroutines.b
    public final String toString() {
        return this.k.toString();
    }
}

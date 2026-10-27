package defpackage;

import android.os.Binder;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;
import org.chromium.build.annotations.DoNotInline;

/* JADX INFO: compiled from: AsyncTask.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class i9<Result> {
    public static final f8 e = new f8();
    public static final si2 f = new si2();
    public static final c g = new c();
    public final i9<Result>.b a;
    public volatile int b = 0;
    public final AtomicBoolean c = new AtomicBoolean();
    public final AtomicBoolean d = new AtomicBoolean();

    /* JADX INFO: compiled from: AsyncTask.java */
    public class a implements Callable<Result> {
        public a() {
        }

        /* JADX WARN: Type inference fix 'apply assigned field type' failed
        java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
        	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
        	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
        	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
         */
        @Override // java.util.concurrent.Callable
        public final Result call() {
            i9 i9Var = i9.this;
            i9Var.d.set(true);
            Result result = null;
            try {
                result = (Result) i9Var.b();
                Binder.flushPendingCommands();
                return result;
            } finally {
            }
        }
    }

    /* JADX INFO: compiled from: AsyncTask.java */
    public class b extends FutureTask<Result> {
        public b(a aVar) {
            super(aVar);
        }

        @Override // java.util.concurrent.FutureTask
        public final void done() {
            i9 i9Var = i9.this;
            try {
                Result result = get();
                if (i9Var.d.get()) {
                    return;
                }
                i9Var.n(result);
            } catch (InterruptedException e) {
                Log.w("cr_AsyncTask", e.toString());
            } catch (CancellationException unused) {
                if (i9Var.d.get()) {
                    return;
                }
                i9Var.n(null);
            } catch (ExecutionException e2) {
                throw new uv("An error occurred while executing doInBackground()", e2.getCause());
            }
        }

        @Override // java.util.concurrent.FutureTask, java.util.concurrent.RunnableFuture, java.lang.Runnable
        public final void run() {
            TraceEvent traceEventH0 = TraceEvent.H0("AsyncTask.run: ".concat(i9.this.getClass().getName()), null);
            try {
                super.run();
                if (traceEventH0 != null) {
                    traceEventH0.close();
                }
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
    }

    /* JADX INFO: compiled from: AsyncTask.java */
    public static class c implements RejectedExecutionHandler {
        @Override // java.util.concurrent.RejectedExecutionHandler
        public final void rejectedExecution(Runnable runnable, ThreadPoolExecutor threadPoolExecutor) {
            i9.e.execute(runnable);
        }
    }

    public i9() {
        Object obj = PostTask.a;
        this.a = new b(new a());
    }

    public final boolean a(boolean z) {
        this.c.set(true);
        return this.a.cancel(z);
    }

    public abstract Result b();

    public final void c(Executor executor) {
        f();
        executor.execute(this.a);
    }

    public final void d(b73 b73Var) {
        f();
        b73Var.b(this.a);
    }

    public final void e(d73 d73Var) {
        f();
        PostTask.b(d73Var, this.a, 0L);
    }

    public final void f() {
        if (this.b != 0) {
            int i = this.b;
            if (i == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.b = 1;
        m();
    }

    @DoNotInline
    public final Result g() {
        String str;
        int i = (this.b != 1 || this.d.get()) ? this.b : 0;
        i9<Result>.b bVar = this.a;
        if (i == 2 || !ThreadUtils.g()) {
            return bVar.get();
        }
        oj2.U(i, 3, "Android.Jank.AsyncTaskGetOnUiThreadStatus");
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        if (stackTrace.length > 1) {
            str = stackTrace[1].getClassName() + '.' + stackTrace[1].getMethodName() + '.';
        } else {
            str = "";
        }
        TraceEvent traceEventH0 = TraceEvent.H0(str + "AsyncTask.get", null);
        try {
            Result result = bVar.get();
            if (traceEventH0 == null) {
                return result;
            }
            traceEventH0.close();
            return result;
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

    @DoNotInline
    public final Result h(long j, TimeUnit timeUnit) {
        String str;
        int i = (this.b != 1 || this.d.get()) ? this.b : 0;
        i9<Result>.b bVar = this.a;
        if (i == 2 || !ThreadUtils.g()) {
            return bVar.get(j, timeUnit);
        }
        oj2.U(i, 3, "Android.Jank.AsyncTaskGetOnUiThreadStatus");
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        if (stackTrace.length > 1) {
            str = stackTrace[1].getClassName() + '.' + stackTrace[1].getMethodName() + '.';
        } else {
            str = "";
        }
        TraceEvent traceEventH0 = TraceEvent.H0(str + "AsyncTask.get", null);
        try {
            Result result = bVar.get(j, timeUnit);
            if (traceEventH0 == null) {
                return result;
            }
            traceEventH0.close();
            return result;
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

    public final boolean i() {
        return this.c.get();
    }

    public void k(Result result) {
        j();
    }

    public abstract void l(Result result);

    public final void n(Result result) {
        if (this instanceof ac) {
            this.b = 2;
        } else {
            Object obj = PostTask.a;
            ThreadUtils.e(new z8(this, 4, result));
        }
    }

    public void j() {
    }

    public void m() {
    }
}

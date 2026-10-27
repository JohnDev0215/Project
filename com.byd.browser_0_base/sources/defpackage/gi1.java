package defpackage;

import android.os.Binder;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: compiled from: ModernAsyncTask.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class gi1<Result> {
    public static Handler i;
    public volatile int f = 1;
    public final AtomicBoolean g = new AtomicBoolean();
    public final AtomicBoolean h = new AtomicBoolean();
    public final b e = new b(new a());

    /* JADX INFO: compiled from: ModernAsyncTask.java */
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
            gi1 gi1Var = gi1.this;
            gi1Var.h.set(true);
            Result result = null;
            try {
                Process.setThreadPriority(10);
                result = (Result) gi1Var.a();
                Binder.flushPendingCommands();
                return result;
            } finally {
            }
        }
    }

    /* JADX INFO: compiled from: ModernAsyncTask.java */
    public class b extends FutureTask<Result> {
        public b(a aVar) {
            super(aVar);
        }

        @Override // java.util.concurrent.FutureTask
        public final void done() {
            gi1 gi1Var = gi1.this;
            try {
                Result result = get();
                if (gi1Var.h.get()) {
                    return;
                }
                gi1Var.d(result);
            } catch (InterruptedException e) {
                Log.w("AsyncTask", e);
            } catch (CancellationException unused) {
                if (gi1Var.h.get()) {
                    return;
                }
                gi1Var.d(null);
            } catch (ExecutionException e2) {
                throw new RuntimeException("An error occurred while executing doInBackground()", e2.getCause());
            } catch (Throwable th) {
                throw new RuntimeException("An error occurred while executing doInBackground()", th);
            }
        }
    }

    /* JADX INFO: compiled from: ModernAsyncTask.java */
    public class c implements Runnable {
        public final /* synthetic */ Object e;

        public c(Object obj) {
            this.e = obj;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // java.lang.Runnable
        public final void run() {
            gi1 gi1Var = gi1.this;
            Object obj = this.e;
            if (gi1Var.g.get()) {
                gi1Var.b(obj);
            } else {
                gi1Var.c(obj);
            }
            gi1Var.f = 3;
        }
    }

    public abstract Result a();

    public final void d(Result result) {
        Handler handler;
        synchronized (gi1.class) {
            if (i == null) {
                i = new Handler(Looper.getMainLooper());
            }
            handler = i;
        }
        handler.post(new c(result));
    }

    public void b(Result result) {
    }

    public void c(Result result) {
    }
}

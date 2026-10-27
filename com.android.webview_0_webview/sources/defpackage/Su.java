package defpackage;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.base.ThreadUtils;
import org.chromium.base.TraceEvent;
import org.chromium.base.task.PostTask;

/* JADX INFO: compiled from: chromium-SystemWebView.apk-default-410412053 */
/* JADX INFO: loaded from: classes.dex */
public abstract class Su {
    public static final Executor f = new Pu();
    public static final Executor g = new ExecutorC0797fv();
    public volatile int c = 0;
    public final AtomicBoolean d = new AtomicBoolean();
    public final AtomicBoolean e = new AtomicBoolean();
    public final Callable a = new Qu(this);
    public final FutureTask b = new Ru(this, this.a);

    public static void a(Su su, Object obj) {
        if (su.e.get()) {
            return;
        }
        if (su instanceof Tu) {
            su.c = 2;
        } else {
            ThreadUtils.e(new Ou(su, obj));
        }
    }

    public final boolean b(boolean z) {
        this.d.set(true);
        return this.b.cancel(z);
    }

    public abstract Object c();

    public final Su d(Executor executor) {
        g();
        executor.execute(this.b);
        return this;
    }

    public final Su e(InterfaceC1020jv interfaceC1020jv) {
        g();
        interfaceC1020jv.a(this.b);
        return this;
    }

    public final Su f(C1242nv c1242nv) {
        g();
        PostTask.a(c1242nv, this.b, 0L);
        return this;
    }

    public final void g() {
        if (this.c != 0) {
            int i = this.c;
            if (i == 1) {
                throw new IllegalStateException("Cannot execute task: the task is already running.");
            }
            if (i == 2) {
                throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
            }
        }
        this.c = 1;
        o();
    }

    public final Object h() {
        String str;
        if (i() == 2 || !ThreadUtils.j()) {
            return this.b.get();
        }
        StackTraceElement[] stackTrace = new Exception().getStackTrace();
        if (stackTrace.length > 1) {
            str = stackTrace[1].getClassName() + '.' + stackTrace[1].getMethodName() + '.';
        } else {
            str = "";
        }
        TraceEvent traceEventM = TraceEvent.M(str + "AsyncTask.get");
        try {
            Object obj = this.b.get();
            if (traceEventM == null) {
                return obj;
            }
            traceEventM.close();
            return obj;
        } catch (Throwable th) {
            if (traceEventM != null) {
                try {
                    traceEventM.close();
                } catch (Throwable th2) {
                    AbstractC0279Pk.a.a(th, th2);
                }
            }
            throw th;
        }
    }

    public final int i() {
        return this.c;
    }

    public final boolean j() {
        return this.d.get();
    }

    public final void k(Object obj) {
        if (j()) {
            m(obj);
        } else {
            n(obj);
        }
        this.c = 2;
    }

    public void l() {
    }

    public void m(Object obj) {
        l();
    }

    public abstract void n(Object obj);

    public void o() {
    }
}

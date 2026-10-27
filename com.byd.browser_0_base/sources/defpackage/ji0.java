package defpackage;

import J.N;
import defpackage.ap3;
import defpackage.ch1;
import defpackage.k00;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import org.chromium.mojo.system.impl.CoreImpl;
import org.chromium.mojo.system.impl.WatcherImpl;

/* JADX INFO: compiled from: ExecutorFactory.java */
/* JADX INFO: loaded from: classes3.dex */
public final class ji0 {
    public static final ThreadLocal<Executor> a = new ThreadLocal<>();

    /* JADX INFO: compiled from: ExecutorFactory.java */
    public static class a implements Executor, ap3.a {
        public final ch1 e;
        public final ch1 f;
        public final ArrayList g;
        public final Object h;
        public final WatcherImpl i;

        public a(k00 k00Var) {
            CoreImpl coreImpl = (CoreImpl) k00Var;
            coreImpl.getClass();
            WatcherImpl watcherImpl = new WatcherImpl();
            this.i = watcherImpl;
            this.h = new Object();
            pv1<ch1, ch1> pv1VarB = coreImpl.b(new ch1.b());
            ch1 ch1Var = pv1VarB.a;
            this.f = ch1Var;
            this.e = pv1VarB.b;
            this.g = new ArrayList();
            watcherImpl.a(ch1Var, k00.a.c, this);
        }

        @Override // ap3.a
        public final void a(int i) {
            Runnable runnable;
            ch1 ch1Var;
            if (i == 0) {
                try {
                    ch1Var = this.f;
                    ch1.c cVar = ch1.c.c;
                } catch (ki1 unused) {
                }
                boolean z = ch1Var.u().a == 0;
                if (z) {
                    synchronized (this.h) {
                        runnable = (Runnable) this.g.remove(0);
                    }
                    runnable.run();
                    return;
                }
            }
            synchronized (this.h) {
                this.e.close();
                this.g.clear();
            }
            WatcherImpl watcherImpl = this.i;
            long j = watcherImpl.a;
            if (j != 0) {
                watcherImpl.b = null;
                N.MPTT407x(watcherImpl, j);
            }
            WatcherImpl watcherImpl2 = this.i;
            long j2 = watcherImpl2.a;
            if (j2 != 0) {
                N.Mi32vqDA(watcherImpl2, j2);
                watcherImpl2.a = 0L;
            }
            this.f.close();
        }

        @Override // java.util.concurrent.Executor
        public final void execute(Runnable runnable) {
            synchronized (this.h) {
                if (!this.e.isValid()) {
                    throw new IllegalStateException("Trying to execute an action on a closed executor.");
                }
                this.g.add(runnable);
                ch1 ch1Var = this.e;
                ThreadLocal<Executor> threadLocal = ji0.a;
                ch1.e eVar = ch1.e.c;
                ch1Var.l(null, null);
            }
        }
    }

    public static Executor a(k00 k00Var) {
        ThreadLocal<Executor> threadLocal = a;
        Executor executor = threadLocal.get();
        if (executor != null) {
            return executor;
        }
        a aVar = new a(k00Var);
        threadLocal.set(aVar);
        return aVar;
    }
}

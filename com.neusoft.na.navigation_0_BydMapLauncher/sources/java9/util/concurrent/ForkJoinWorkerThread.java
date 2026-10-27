package java9.util.concurrent;

import java.lang.Thread;
import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.security.ProtectionDomain;
import java9.util.concurrent.ForkJoinPool;

/* JADX INFO: loaded from: classes3.dex */
public class ForkJoinWorkerThread extends Thread {
    private static final String NAME_PLACEHOLDER = "aForkJoinWorkerThread";
    final ForkJoinPool pool;
    final ForkJoinPool.WorkQueue workQueue;

    void afterTopLevelExec() {
    }

    protected void onStart() {
    }

    protected void onTermination(Throwable th) {
    }

    protected ForkJoinWorkerThread(ForkJoinPool forkJoinPool) {
        super(NAME_PLACEHOLDER);
        this.pool = forkJoinPool;
        this.workQueue = forkJoinPool.registerWorker(this);
    }

    ForkJoinWorkerThread(ForkJoinPool forkJoinPool, ClassLoader classLoader) {
        super(NAME_PLACEHOLDER);
        TLRandom.setContextClassLoader(this, classLoader);
        this.pool = forkJoinPool;
        this.workQueue = forkJoinPool.registerWorker(this);
    }

    ForkJoinWorkerThread(ForkJoinPool forkJoinPool, ClassLoader classLoader, ThreadGroup threadGroup, AccessControlContext accessControlContext) {
        super(threadGroup, NAME_PLACEHOLDER);
        super.setContextClassLoader(classLoader);
        TLRandom.setInheritedAccessControlContext(this, accessControlContext);
        TLRandom.eraseThreadLocals(this);
        this.pool = forkJoinPool;
        this.workQueue = forkJoinPool.registerWorker(this);
    }

    public ForkJoinPool getPool() {
        return this.pool;
    }

    public int getPoolIndex() {
        return this.workQueue.getPoolIndex();
    }

    @Override // java.lang.Thread, java.lang.Runnable
    public void run() throws Throwable {
        if (this.workQueue.array == null) {
            try {
                onStart();
                this.pool.runWorker(this.workQueue);
                th = null;
                try {
                    onTermination(null);
                } catch (Throwable th) {
                    th = th;
                }
            } catch (Throwable th2) {
                th = th2;
                try {
                    onTermination(th);
                } catch (Throwable unused) {
                }
            }
            this.pool.deregisterWorker(this, th);
        }
    }

    static final class InnocuousForkJoinWorkerThread extends ForkJoinWorkerThread {
        private static final ThreadGroup innocuousThreadGroup = (ThreadGroup) AccessController.doPrivileged(new PrivilegedAction<ThreadGroup>() { // from class: java9.util.concurrent.ForkJoinWorkerThread.InnocuousForkJoinWorkerThread.1
            @Override // java.security.PrivilegedAction
            public ThreadGroup run() {
                ThreadGroup threadGroup = Thread.currentThread().getThreadGroup();
                while (true) {
                    ThreadGroup parent = threadGroup.getParent();
                    if (parent == null) {
                        return new ThreadGroup(threadGroup, "InnocuousForkJoinWorkerThreadGroup");
                    }
                    threadGroup = parent;
                }
            }
        });
        private static final AccessControlContext INNOCUOUS_ACC = new AccessControlContext(new ProtectionDomain[]{new ProtectionDomain(null, null)});

        @Override // java.lang.Thread
        public void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler uncaughtExceptionHandler) {
        }

        InnocuousForkJoinWorkerThread(ForkJoinPool forkJoinPool) {
            super(forkJoinPool, ClassLoader.getSystemClassLoader(), innocuousThreadGroup, INNOCUOUS_ACC);
        }

        @Override // java9.util.concurrent.ForkJoinWorkerThread
        void afterTopLevelExec() {
            TLRandom.eraseThreadLocals(this);
        }

        @Override // java.lang.Thread
        public void setContextClassLoader(ClassLoader classLoader) {
            if (classLoader != null && ClassLoader.getSystemClassLoader() != classLoader) {
                throw new SecurityException("setContextClassLoader");
            }
        }
    }
}

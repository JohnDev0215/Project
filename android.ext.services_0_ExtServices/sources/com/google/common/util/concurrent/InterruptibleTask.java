package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE;
    private static final Runnable INTERRUPTING;
    private static final Runnable PARKED;

    abstract void afterRanInterruptibly(T t, Throwable th);

    abstract boolean isDone();

    abstract T runInterruptibly() throws Exception;

    abstract String toPendingString();

    InterruptibleTask() {
    }

    static {
        DONE = new DoNothingRunnable();
        INTERRUPTING = new DoNothingRunnable();
        PARKED = new DoNothingRunnable();
    }

    private static final class DoNothingRunnable implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        private DoNothingRunnable() {
        }
    }

    @Override // java.lang.Runnable
    public final void run() {
        T tRunInterruptibly;
        Runnable runnable = DONE;
        Runnable runnable2 = PARKED;
        Runnable runnable3 = INTERRUPTING;
        Thread threadCurrentThread = Thread.currentThread();
        if (compareAndSet(null, threadCurrentThread)) {
            boolean z = !isDone();
            if (z) {
                try {
                    tRunInterruptibly = runInterruptibly();
                } catch (Throwable th) {
                    if (!compareAndSet(threadCurrentThread, runnable)) {
                        Runnable runnable4 = get();
                        boolean z2 = false;
                        int i = 0;
                        while (true) {
                            if (runnable4 != runnable3 && runnable4 != runnable2) {
                                break;
                            }
                            i++;
                            if (i > 1000) {
                                if (runnable4 == runnable2 || compareAndSet(runnable3, runnable2)) {
                                    z2 = Thread.interrupted() || z2;
                                    LockSupport.park(this);
                                }
                            } else {
                                Thread.yield();
                            }
                            runnable4 = get();
                        }
                        if (z2) {
                            threadCurrentThread.interrupt();
                        }
                    }
                    if (z) {
                        afterRanInterruptibly(null, th);
                        return;
                    }
                    return;
                }
            } else {
                tRunInterruptibly = null;
            }
            if (!compareAndSet(threadCurrentThread, runnable)) {
                Runnable runnable5 = get();
                boolean z3 = false;
                int i2 = 0;
                while (true) {
                    if (runnable5 != runnable3 && runnable5 != runnable2) {
                        break;
                    }
                    i2++;
                    if (i2 > 1000) {
                        if (runnable5 == runnable2 || compareAndSet(runnable3, runnable2)) {
                            z3 = Thread.interrupted() || z3;
                            LockSupport.park(this);
                        }
                    } else {
                        Thread.yield();
                    }
                    runnable5 = get();
                }
                if (z3) {
                    threadCurrentThread.interrupt();
                }
            }
            if (z) {
                afterRanInterruptibly(tRunInterruptibly, null);
            }
        }
    }

    final void interruptTask() {
        Runnable runnable = PARKED;
        Runnable runnable2 = DONE;
        Runnable runnable3 = get();
        if ((runnable3 instanceof Thread) && compareAndSet(runnable3, INTERRUPTING)) {
            try {
                ((Thread) runnable3).interrupt();
            } finally {
                if (getAndSet(runnable2) == runnable) {
                    LockSupport.unpark((Thread) runnable3);
                }
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String str;
        Runnable runnable = get();
        if (runnable == DONE) {
            str = "running=[DONE]";
        } else if (runnable == INTERRUPTING) {
            str = "running=[INTERRUPTED]";
        } else if (runnable instanceof Thread) {
            str = "running=[RUNNING ON " + ((Thread) runnable).getName() + "]";
        } else {
            str = "running=[NOT STARTED YET]";
        }
        return str + ", " + toPendingString();
    }
}

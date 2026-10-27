package com.google.common.util.concurrent;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/* JADX INFO: loaded from: classes.dex */
abstract class InterruptibleTask<T> extends AtomicReference<Runnable> implements Runnable {
    private static final Runnable DONE;
    private static final Runnable INTERRUPTING;
    private static final int MAX_BUSY_WAIT_SPINS = 1000;
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
        private DoNothingRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x007a  */
    /* JADX WARN: Removed duplicated region for block: B:76:? A[RETURN, SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() throws java.lang.Exception {
        /*
            r12 = this;
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r1 = 0
            boolean r1 = r12.compareAndSet(r1, r0)
            if (r1 != 0) goto Lc
            return
        Lc:
            boolean r1 = r12.isDone()
            r2 = 1
            r1 = r1 ^ r2
            r3 = 0
            r4 = 0
            r5 = 0
            r6 = 1000(0x3e8, float:1.401E-42)
            if (r1 == 0) goto L72
            java.lang.Object r7 = r12.runInterruptibly()     // Catch: java.lang.Throwable -> L1f
            r3 = r7
            goto L72
        L1f:
            r7 = move-exception
            r4 = r7
            java.lang.Runnable r7 = com.google.common.util.concurrent.InterruptibleTask.DONE
            boolean r7 = r12.compareAndSet(r0, r7)
            if (r7 != 0) goto L6c
            r7 = 0
            r8 = 0
            java.lang.Object r9 = r12.get()
            java.lang.Runnable r9 = (java.lang.Runnable) r9
        L31:
            java.lang.Runnable r10 = com.google.common.util.concurrent.InterruptibleTask.INTERRUPTING
            if (r9 == r10) goto L40
            java.lang.Runnable r10 = com.google.common.util.concurrent.InterruptibleTask.PARKED
            if (r9 != r10) goto L3a
            goto L40
        L3a:
            if (r7 == 0) goto L6c
            r0.interrupt()
            goto L6c
        L40:
            int r8 = r8 + 1
            if (r8 <= r6) goto L61
            java.lang.Runnable r10 = com.google.common.util.concurrent.InterruptibleTask.PARKED
            if (r9 == r10) goto L50
            java.lang.Runnable r11 = com.google.common.util.concurrent.InterruptibleTask.INTERRUPTING
            boolean r10 = r12.compareAndSet(r11, r10)
            if (r10 == 0) goto L64
        L50:
            boolean r10 = java.lang.Thread.interrupted()
            if (r10 != 0) goto L5b
            if (r7 == 0) goto L59
            goto L5b
        L59:
            r10 = r5
            goto L5c
        L5b:
            r10 = r2
        L5c:
            r7 = r10
            java.util.concurrent.locks.LockSupport.park(r12)
            goto L64
        L61:
            java.lang.Thread.yield()
        L64:
            java.lang.Object r10 = r12.get()
            r9 = r10
            java.lang.Runnable r9 = (java.lang.Runnable) r9
            goto L31
        L6c:
            if (r1 == 0) goto Lc0
        L6e:
            r12.afterRanInterruptibly(r3, r4)
            goto Lc0
        L72:
            java.lang.Runnable r7 = com.google.common.util.concurrent.InterruptibleTask.DONE
            boolean r7 = r12.compareAndSet(r0, r7)
            if (r7 != 0) goto Lbd
            r7 = 0
            r8 = 0
            java.lang.Object r9 = r12.get()
            java.lang.Runnable r9 = (java.lang.Runnable) r9
        L82:
            java.lang.Runnable r10 = com.google.common.util.concurrent.InterruptibleTask.INTERRUPTING
            if (r9 == r10) goto L91
            java.lang.Runnable r10 = com.google.common.util.concurrent.InterruptibleTask.PARKED
            if (r9 != r10) goto L8b
            goto L91
        L8b:
            if (r7 == 0) goto Lbd
            r0.interrupt()
            goto Lbd
        L91:
            int r8 = r8 + 1
            if (r8 <= r6) goto Lb2
            java.lang.Runnable r10 = com.google.common.util.concurrent.InterruptibleTask.PARKED
            if (r9 == r10) goto La1
            java.lang.Runnable r11 = com.google.common.util.concurrent.InterruptibleTask.INTERRUPTING
            boolean r10 = r12.compareAndSet(r11, r10)
            if (r10 == 0) goto Lb5
        La1:
            boolean r10 = java.lang.Thread.interrupted()
            if (r10 != 0) goto Lac
            if (r7 == 0) goto Laa
            goto Lac
        Laa:
            r10 = r5
            goto Lad
        Lac:
            r10 = r2
        Lad:
            r7 = r10
            java.util.concurrent.locks.LockSupport.park(r12)
            goto Lb5
        Lb2:
            java.lang.Thread.yield()
        Lb5:
            java.lang.Object r10 = r12.get()
            r9 = r10
            java.lang.Runnable r9 = (java.lang.Runnable) r9
            goto L82
        Lbd:
            if (r1 == 0) goto Lc0
            goto L6e
        Lc0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.InterruptibleTask.run():void");
    }

    final void interruptTask() {
        Runnable currentRunner = get();
        if ((currentRunner instanceof Thread) && compareAndSet(currentRunner, INTERRUPTING)) {
            try {
                ((Thread) currentRunner).interrupt();
            } finally {
                Runnable prev = getAndSet(DONE);
                if (prev == PARKED) {
                    LockSupport.unpark((Thread) currentRunner);
                }
            }
        }
    }

    @Override // java.util.concurrent.atomic.AtomicReference
    public final String toString() {
        String result;
        Runnable state = get();
        if (state == DONE) {
            result = "running=[DONE]";
        } else if (state == INTERRUPTING) {
            result = "running=[INTERRUPTED]";
        } else if (state instanceof Thread) {
            result = "running=[RUNNING ON " + ((Thread) state).getName() + "]";
        } else {
            result = "running=[NOT STARTED YET]";
        }
        return result + ", " + toPendingString();
    }
}

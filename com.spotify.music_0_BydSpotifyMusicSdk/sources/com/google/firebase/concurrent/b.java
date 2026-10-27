package com.google.firebase.concurrent;

import java.util.ArrayDeque;
import java.util.concurrent.Executor;
import java.util.logging.Logger;

/* JADX INFO: loaded from: classes.dex */
public final class b implements Executor {
    public static final Logger n = Logger.getLogger(b.class.getName());
    public final Executor c;
    public final ArrayDeque e = new ArrayDeque();
    public SequentialExecutor$WorkerRunningState k = SequentialExecutor$WorkerRunningState.c;
    public long l = 0;
    public final a m = new a(this, 0);

    public b(Executor executor) {
        if (executor == null) {
            throw new NullPointerException("null reference");
        }
        this.c = executor;
    }

    /* JADX WARN: Removed duplicated region for block: B:43:0x0061  */
    @Override // java.util.concurrent.Executor
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void execute(java.lang.Runnable r8) {
        /*
            r7 = this;
            p.he1.g(r8)
            java.util.ArrayDeque r0 = r7.e
            monitor-enter(r0)
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r1 = r7.k     // Catch: java.lang.Throwable -> L6d
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState.l     // Catch: java.lang.Throwable -> L6d
            if (r1 == r2) goto L6f
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r2 = com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState.k     // Catch: java.lang.Throwable -> L6d
            if (r1 != r2) goto L11
            goto L6f
        L11:
            long r3 = r7.l     // Catch: java.lang.Throwable -> L6d
            com.google.firebase.concurrent.a r1 = new com.google.firebase.concurrent.a     // Catch: java.lang.Throwable -> L6d
            r1.<init>(r7, r8)     // Catch: java.lang.Throwable -> L6d
            java.util.ArrayDeque r8 = r7.e     // Catch: java.lang.Throwable -> L6d
            r8.add(r1)     // Catch: java.lang.Throwable -> L6d
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r8 = com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState.e     // Catch: java.lang.Throwable -> L6d
            r7.k = r8     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6d
            java.util.concurrent.Executor r0 = r7.c     // Catch: java.lang.Error -> L44 java.lang.RuntimeException -> L46
            com.google.firebase.concurrent.a r5 = r7.m     // Catch: java.lang.Error -> L44 java.lang.RuntimeException -> L46
            r0.execute(r5)     // Catch: java.lang.Error -> L44 java.lang.RuntimeException -> L46
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r0 = r7.k
            if (r0 == r8) goto L2e
            return
        L2e:
            java.util.ArrayDeque r0 = r7.e
            monitor-enter(r0)
            long r5 = r7.l     // Catch: java.lang.Throwable -> L3e
            int r1 = (r5 > r3 ? 1 : (r5 == r3 ? 0 : -1))
            if (r1 != 0) goto L40
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r1 = r7.k     // Catch: java.lang.Throwable -> L3e
            if (r1 != r8) goto L40
            r7.k = r2     // Catch: java.lang.Throwable -> L3e
            goto L40
        L3e:
            r8 = move-exception
            goto L42
        L40:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
            return
        L42:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L3e
            throw r8
        L44:
            r8 = move-exception
            goto L47
        L46:
            r8 = move-exception
        L47:
            java.util.ArrayDeque r2 = r7.e
            monitor-enter(r2)
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r0 = r7.k     // Catch: java.lang.Throwable -> L55
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState.c     // Catch: java.lang.Throwable -> L55
            if (r0 == r3) goto L57
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState.e     // Catch: java.lang.Throwable -> L55
            if (r0 != r3) goto L61
            goto L57
        L55:
            r8 = move-exception
            goto L6b
        L57:
            java.util.ArrayDeque r0 = r7.e     // Catch: java.lang.Throwable -> L55
            boolean r0 = r0.removeLastOccurrence(r1)     // Catch: java.lang.Throwable -> L55
            if (r0 == 0) goto L61
            r0 = 1
            goto L62
        L61:
            r0 = 0
        L62:
            boolean r1 = r8 instanceof java.util.concurrent.RejectedExecutionException     // Catch: java.lang.Throwable -> L55
            if (r1 == 0) goto L6a
            if (r0 != 0) goto L6a
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L55
            return
        L6a:
            throw r8     // Catch: java.lang.Throwable -> L55
        L6b:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L55
            throw r8
        L6d:
            r8 = move-exception
            goto L76
        L6f:
            java.util.ArrayDeque r1 = r7.e     // Catch: java.lang.Throwable -> L6d
            r1.add(r8)     // Catch: java.lang.Throwable -> L6d
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6d
            return
        L76:
            monitor-exit(r0)     // Catch: java.lang.Throwable -> L6d
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.concurrent.b.execute(java.lang.Runnable):void");
    }

    public final String toString() {
        return "SequentialExecutor@" + System.identityHashCode(this) + "{" + this.c + "}";
    }
}

package com.google.firebase.concurrent;

/* JADX INFO: loaded from: classes.dex */
public final class a implements Runnable {
    public final /* synthetic */ int c = 0;
    public Runnable e;
    public final /* synthetic */ b k;

    public a(b bVar, Runnable runnable) {
        this.k = bVar;
        this.e = runnable;
    }

    /* JADX WARN: Code restructure failed: missing block: B:21:0x0046, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:24:0x004c, code lost:
    
        r1 = r1 | java.lang.Thread.interrupted();
        r2 = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x004e, code lost:
    
        r9.e.run();
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x0058, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x005a, code lost:
    
        r3 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x005b, code lost:
    
        com.google.firebase.concurrent.b.n.log(java.util.logging.Level.SEVERE, "Exception while executing runnable " + r9.e, (java.lang.Throwable) r3);
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x0076, code lost:
    
        r9.e = null;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x0078, code lost:
    
        throw r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:?, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r9 = this;
            r0 = 0
            r1 = r0
        L2:
            com.google.firebase.concurrent.b r2 = r9.k     // Catch: java.lang.Throwable -> L56
            java.util.ArrayDeque r2 = r2.e     // Catch: java.lang.Throwable -> L56
            monitor-enter(r2)     // Catch: java.lang.Throwable -> L56
            if (r0 != 0) goto L28
            com.google.firebase.concurrent.b r0 = r9.k     // Catch: java.lang.Throwable -> L1c
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r3 = r0.k     // Catch: java.lang.Throwable -> L1c
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r4 = com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState.l     // Catch: java.lang.Throwable -> L1c
            if (r3 != r4) goto L1e
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1c
            if (r1 == 0) goto L1b
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L1b:
            return
        L1c:
            r0 = move-exception
            goto L79
        L1e:
            long r5 = r0.l     // Catch: java.lang.Throwable -> L1c
            r7 = 1
            long r5 = r5 + r7
            r0.l = r5     // Catch: java.lang.Throwable -> L1c
            r0.k = r4     // Catch: java.lang.Throwable -> L1c
            r0 = 1
        L28:
            com.google.firebase.concurrent.b r3 = r9.k     // Catch: java.lang.Throwable -> L1c
            java.util.ArrayDeque r3 = r3.e     // Catch: java.lang.Throwable -> L1c
            java.lang.Object r3 = r3.poll()     // Catch: java.lang.Throwable -> L1c
            java.lang.Runnable r3 = (java.lang.Runnable) r3     // Catch: java.lang.Throwable -> L1c
            r9.e = r3     // Catch: java.lang.Throwable -> L1c
            if (r3 != 0) goto L47
            com.google.firebase.concurrent.b r0 = r9.k     // Catch: java.lang.Throwable -> L1c
            com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState r3 = com.google.firebase.concurrent.SequentialExecutor$WorkerRunningState.c     // Catch: java.lang.Throwable -> L1c
            r0.k = r3     // Catch: java.lang.Throwable -> L1c
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1c
            if (r1 == 0) goto L46
            java.lang.Thread r0 = java.lang.Thread.currentThread()
            r0.interrupt()
        L46:
            return
        L47:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1c
            boolean r2 = java.lang.Thread.interrupted()     // Catch: java.lang.Throwable -> L56
            r1 = r1 | r2
            r2 = 0
            java.lang.Runnable r3 = r9.e     // Catch: java.lang.Throwable -> L58 java.lang.RuntimeException -> L5a
            r3.run()     // Catch: java.lang.Throwable -> L58 java.lang.RuntimeException -> L5a
        L53:
            r9.e = r2     // Catch: java.lang.Throwable -> L56
            goto L2
        L56:
            r0 = move-exception
            goto L7b
        L58:
            r0 = move-exception
            goto L76
        L5a:
            r3 = move-exception
            java.util.logging.Logger r4 = com.google.firebase.concurrent.b.n     // Catch: java.lang.Throwable -> L58
            java.util.logging.Level r5 = java.util.logging.Level.SEVERE     // Catch: java.lang.Throwable -> L58
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L58
            r6.<init>()     // Catch: java.lang.Throwable -> L58
            java.lang.String r7 = "Exception while executing runnable "
            r6.append(r7)     // Catch: java.lang.Throwable -> L58
            java.lang.Runnable r7 = r9.e     // Catch: java.lang.Throwable -> L58
            r6.append(r7)     // Catch: java.lang.Throwable -> L58
            java.lang.String r6 = r6.toString()     // Catch: java.lang.Throwable -> L58
            r4.log(r5, r6, r3)     // Catch: java.lang.Throwable -> L58
            goto L53
        L76:
            r9.e = r2     // Catch: java.lang.Throwable -> L56
            throw r0     // Catch: java.lang.Throwable -> L56
        L79:
            monitor-exit(r2)     // Catch: java.lang.Throwable -> L1c
            throw r0     // Catch: java.lang.Throwable -> L56
        L7b:
            if (r1 == 0) goto L84
            java.lang.Thread r1 = java.lang.Thread.currentThread()
            r1.interrupt()
        L84:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.concurrent.a.a():void");
    }

    @Override // java.lang.Runnable
    public final void run() {
        switch (this.c) {
            case 0:
                try {
                    a();
                    return;
                } catch (Error e) {
                    synchronized (this.k.e) {
                        this.k.k = SequentialExecutor$WorkerRunningState.c;
                        throw e;
                    }
                }
            default:
                this.e.run();
                return;
        }
    }

    public final String toString() {
        switch (this.c) {
            case 0:
                Runnable runnable = this.e;
                if (runnable != null) {
                    return "SequentialExecutorWorker{running=" + runnable + "}";
                }
                return "SequentialExecutorWorker{state=" + this.k.k + "}";
            default:
                return this.e.toString();
        }
    }

    public a(b bVar) {
        this.k = bVar;
    }

    public /* synthetic */ a(b bVar, int i) {
        this(bVar);
    }
}

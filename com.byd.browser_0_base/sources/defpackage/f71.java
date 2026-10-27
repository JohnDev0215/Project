package defpackage;

/* JADX INFO: compiled from: LimitedDispatcher.kt */
/* JADX INFO: loaded from: classes.dex */
public final class f71 extends p00 implements Runnable, h80 {
    public final p00 f;
    public final int g;
    public final /* synthetic */ h80 h;
    public final da1<Runnable> i;
    public final Object j;
    private volatile int runningWorkers;

    /* JADX WARN: Multi-variable type inference failed */
    public f71(wi3 wi3Var, int i) {
        this.f = wi3Var;
        this.g = i;
        h80 h80Var = wi3Var instanceof h80 ? (h80) wi3Var : null;
        this.h = h80Var == null ? r70.a : h80Var;
        this.i = new da1<>();
        this.j = new Object();
    }

    @Override // defpackage.p00
    public final void h1(m00 m00Var, Runnable runnable) {
        this.i.a(runnable);
        boolean z = true;
        if (this.runningWorkers >= this.g) {
            return;
        }
        synchronized (this.j) {
            if (this.runningWorkers >= this.g) {
                z = false;
            } else {
                this.runningWorkers++;
            }
        }
        if (z) {
            this.f.h1(this, this);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0029, code lost:
    
        r0 = r3.j;
     */
    /* JADX WARN: Code restructure failed: missing block: B:16:0x002b, code lost:
    
        monitor-enter(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x002c, code lost:
    
        r3.runningWorkers--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x0038, code lost:
    
        if (r3.i.c() != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:19:0x003a, code lost:
    
        monitor-exit(r0);
     */
    /* JADX WARN: Code restructure failed: missing block: B:20:0x003b, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:21:0x003c, code lost:
    
        r3.runningWorkers++;
        r1 = defpackage.vi3.a;
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void run() throws java.lang.IllegalAccessException, java.lang.reflect.InvocationTargetException {
        /*
            r3 = this;
        L0:
            r0 = 0
        L1:
            da1<java.lang.Runnable> r1 = r3.i
            java.lang.Object r1 = r1.d()
            java.lang.Runnable r1 = (java.lang.Runnable) r1
            if (r1 == 0) goto L29
            r1.run()     // Catch: java.lang.Throwable -> Lf
            goto L15
        Lf:
            r1 = move-exception
            dh0 r2 = defpackage.dh0.e
            defpackage.a8.H(r2, r1)
        L15:
            int r0 = r0 + 1
            r1 = 16
            if (r0 < r1) goto L1
            p00 r1 = r3.f
            boolean r1 = r1.i1()
            if (r1 == 0) goto L1
            p00 r0 = r3.f
            r0.h1(r3, r3)
            return
        L29:
            java.lang.Object r0 = r3.j
            monitor-enter(r0)
            int r1 = r3.runningWorkers     // Catch: java.lang.Throwable -> L46
            int r1 = r1 + (-1)
            r3.runningWorkers = r1     // Catch: java.lang.Throwable -> L46
            da1<java.lang.Runnable> r1 = r3.i     // Catch: java.lang.Throwable -> L46
            int r1 = r1.c()     // Catch: java.lang.Throwable -> L46
            if (r1 != 0) goto L3c
            monitor-exit(r0)
            return
        L3c:
            int r1 = r3.runningWorkers     // Catch: java.lang.Throwable -> L46
            int r1 = r1 + 1
            r3.runningWorkers = r1     // Catch: java.lang.Throwable -> L46
            vi3 r1 = defpackage.vi3.a     // Catch: java.lang.Throwable -> L46
            monitor-exit(r0)
            goto L0
        L46:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.f71.run():void");
    }
}

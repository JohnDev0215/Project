package c.b.i;

import c.b.b;
import c.b.h.f.c;

/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements b<T>, c.b.e.b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b<? super T> f2331a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f2332b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public c.b.e.b f2333c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f2334d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public c.b.h.f.a<Object> f2335e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public volatile boolean f2336f;

    public a(b<? super T> bVar) {
        this.f2331a = bVar;
    }

    @Override // c.b.b
    public void a() {
        if (this.f2336f) {
            return;
        }
        synchronized (this) {
            if (this.f2336f) {
                return;
            }
            if (!this.f2334d) {
                this.f2336f = true;
                this.f2334d = true;
                this.f2331a.a();
            } else {
                c.b.h.f.a<Object> aVar = this.f2335e;
                if (aVar == null) {
                    aVar = new c.b.h.f.a<>(4);
                    this.f2335e = aVar;
                }
                aVar.a(c.COMPLETE);
            }
        }
    }

    @Override // c.b.b
    public void b(Throwable th) {
        if (this.f2336f) {
            c.b.j.a.c(th);
            return;
        }
        synchronized (this) {
            boolean z = false;
            if (this.f2336f) {
                z = true;
            } else {
                if (this.f2334d) {
                    this.f2336f = true;
                    c.b.h.f.a<Object> aVar = this.f2335e;
                    if (aVar == null) {
                        aVar = new c.b.h.f.a<>(4);
                        this.f2335e = aVar;
                    }
                    c.b bVar = new c.b(th);
                    if (this.f2332b) {
                        aVar.a(bVar);
                    } else {
                        aVar.f2319b[0] = bVar;
                    }
                    return;
                }
                this.f2336f = true;
                this.f2334d = true;
            }
            if (z) {
                c.b.j.a.c(th);
            } else {
                this.f2331a.b(th);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:73:0x0086, code lost:
    
        continue;
     */
    /* JADX WARN: Removed duplicated region for block: B:53:0x0083 A[LOOP:2: B:35:0x0053->B:53:0x0083, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0081 A[SYNTHETIC] */
    @Override // c.b.b
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void c(T r8) {
        /*
            r7 = this;
            boolean r0 = r7.f2336f
            if (r0 == 0) goto L5
            return
        L5:
            if (r8 != 0) goto L17
            c.b.e.b r8 = r7.f2333c
            r8.d()
            java.lang.NullPointerException r8 = new java.lang.NullPointerException
            java.lang.String r0 = "onNext called with null. Null values are generally not allowed in 2.x operators and sources."
            r8.<init>(r0)
            r7.b(r8)
            return
        L17:
            monitor-enter(r7)
            boolean r0 = r7.f2336f     // Catch: java.lang.Throwable -> L91
            if (r0 == 0) goto L1e
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L91
            return
        L1e:
            boolean r0 = r7.f2334d     // Catch: java.lang.Throwable -> L91
            if (r0 == 0) goto L33
            c.b.h.f.a<java.lang.Object> r0 = r7.f2335e     // Catch: java.lang.Throwable -> L91
            if (r0 != 0) goto L2e
            c.b.h.f.a r0 = new c.b.h.f.a     // Catch: java.lang.Throwable -> L91
            r1 = 4
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L91
            r7.f2335e = r0     // Catch: java.lang.Throwable -> L91
        L2e:
            r0.a(r8)     // Catch: java.lang.Throwable -> L91
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L91
            return
        L33:
            r0 = 1
            r7.f2334d = r0     // Catch: java.lang.Throwable -> L91
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L91
            c.b.b<? super T> r1 = r7.f2331a
            r1.c(r8)
        L3c:
            monitor-enter(r7)
            c.b.h.f.a<java.lang.Object> r8 = r7.f2335e     // Catch: java.lang.Throwable -> L8e
            r1 = 0
            if (r8 != 0) goto L46
            r7.f2334d = r1     // Catch: java.lang.Throwable -> L8e
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L8e
            goto L8d
        L46:
            r2 = 0
            r7.f2335e = r2     // Catch: java.lang.Throwable -> L8e
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L8e
            c.b.b<? super T> r2 = r7.f2331a
            java.lang.Object[] r3 = r8.f2319b
            int r8 = r8.f2318a
        L50:
            if (r3 == 0) goto L8b
            r4 = r1
        L53:
            if (r4 >= r8) goto L86
            r5 = r3[r4]
            if (r5 != 0) goto L5a
            goto L86
        L5a:
            c.b.h.f.c r6 = c.b.h.f.c.COMPLETE
            if (r5 != r6) goto L62
            r2.a()
            goto L6d
        L62:
            boolean r6 = r5 instanceof c.b.h.f.c.b
            if (r6 == 0) goto L6f
            c.b.h.f.c$b r5 = (c.b.h.f.c.b) r5
            java.lang.Throwable r5 = r5.f2325a
            r2.b(r5)
        L6d:
            r5 = r0
            goto L7f
        L6f:
            boolean r6 = r5 instanceof c.b.h.f.c.a
            if (r6 == 0) goto L7b
            c.b.h.f.c$a r5 = (c.b.h.f.c.a) r5
            c.b.e.b r5 = r5.f2324a
            r2.e(r5)
            goto L7e
        L7b:
            r2.c(r5)
        L7e:
            r5 = r1
        L7f:
            if (r5 == 0) goto L83
            r1 = r0
            goto L8b
        L83:
            int r4 = r4 + 1
            goto L53
        L86:
            r3 = r3[r8]
            java.lang.Object[] r3 = (java.lang.Object[]) r3
            goto L50
        L8b:
            if (r1 == 0) goto L3c
        L8d:
            return
        L8e:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L8e
            throw r8
        L91:
            r8 = move-exception
            monitor-exit(r7)     // Catch: java.lang.Throwable -> L91
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: c.b.i.a.c(java.lang.Object):void");
    }

    @Override // c.b.e.b
    public void d() {
        this.f2333c.d();
    }

    @Override // c.b.b
    public void e(c.b.e.b bVar) {
        if (c.b.h.a.b.a(this.f2333c, bVar)) {
            this.f2333c = bVar;
            this.f2331a.e(this);
        }
    }
}

package c.a.m.e.a;

import c.a.f;
import c.a.g;
import c.a.h;
import c.a.m.g.k;

/* JADX INFO: loaded from: classes.dex */
public final class d<T> extends c.a.m.e.a.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final h f1839b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f1840c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f1841d;

    static final class a<T> extends c.a.m.d.a<T> implements g<T>, Runnable {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final g<? super T> f1842b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        final h.b f1843c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        final boolean f1844d;
        final int e;
        c.a.m.c.c<T> f;
        c.a.j.b g;
        Throwable h;
        volatile boolean i;
        volatile boolean j;
        int k;
        boolean l;

        a(g<? super T> gVar, h.b bVar, boolean z, int i) {
            this.f1842b = gVar;
            this.f1843c = bVar;
            this.f1844d = z;
            this.e = i;
        }

        @Override // c.a.g
        public void a() {
            if (this.i) {
                return;
            }
            this.i = true;
            m();
        }

        @Override // c.a.g
        public void b(Throwable th) {
            if (this.i) {
                c.a.n.a.l(th);
                return;
            }
            this.h = th;
            this.i = true;
            m();
        }

        @Override // c.a.g
        public void c(T t) {
            if (this.i) {
                return;
            }
            if (this.k != 2) {
                this.f.f(t);
            }
            m();
        }

        @Override // c.a.m.c.c
        public void clear() {
            this.f.clear();
        }

        @Override // c.a.m.c.c
        public T d() {
            return this.f.d();
        }

        @Override // c.a.j.b
        public void e() {
            if (this.j) {
                return;
            }
            this.j = true;
            this.g.e();
            this.f1843c.e();
            if (getAndIncrement() == 0) {
                this.f.clear();
            }
        }

        @Override // c.a.j.b
        public boolean g() {
            return this.j;
        }

        @Override // c.a.g
        public void h(c.a.j.b bVar) {
            if (c.a.m.a.b.f(this.g, bVar)) {
                this.g = bVar;
                if (bVar instanceof c.a.m.c.a) {
                    c.a.m.c.a aVar = (c.a.m.c.a) bVar;
                    int i = aVar.i(7);
                    if (i == 1) {
                        this.k = i;
                        this.f = aVar;
                        this.i = true;
                        this.f1842b.h(this);
                        m();
                        return;
                    }
                    if (i == 2) {
                        this.k = i;
                        this.f = aVar;
                        this.f1842b.h(this);
                        return;
                    }
                }
                this.f = new c.a.m.f.a(this.e);
                this.f1842b.h(this);
            }
        }

        @Override // c.a.m.c.b
        public int i(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.l = true;
            return 2;
        }

        @Override // c.a.m.c.c
        public boolean isEmpty() {
            return this.f.isEmpty();
        }

        boolean j(boolean z, boolean z2, g<? super T> gVar) {
            if (this.j) {
                this.f.clear();
                return true;
            }
            if (!z) {
                return false;
            }
            Throwable th = this.h;
            if (this.f1844d) {
                if (!z2) {
                    return false;
                }
                if (th != null) {
                    gVar.b(th);
                } else {
                    gVar.a();
                }
                this.f1843c.e();
                return true;
            }
            if (th != null) {
                this.f.clear();
                gVar.b(th);
                this.f1843c.e();
                return true;
            }
            if (!z2) {
                return false;
            }
            gVar.a();
            this.f1843c.e();
            return true;
        }

        void k() {
            int iAddAndGet = 1;
            while (!this.j) {
                boolean z = this.i;
                Throwable th = this.h;
                if (this.f1844d || !z || th == null) {
                    this.f1842b.c(null);
                    if (z) {
                        Throwable th2 = this.h;
                        if (th2 != null) {
                            this.f1842b.b(th2);
                        } else {
                            this.f1842b.a();
                        }
                    } else {
                        iAddAndGet = addAndGet(-iAddAndGet);
                        if (iAddAndGet == 0) {
                            return;
                        }
                    }
                } else {
                    this.f1842b.b(th);
                }
                this.f1843c.e();
                return;
            }
        }

        /* JADX WARN: Code restructure failed: missing block: B:15:0x0027, code lost:
        
            r3 = addAndGet(-r3);
         */
        /* JADX WARN: Code restructure failed: missing block: B:16:0x002c, code lost:
        
            if (r3 != 0) goto L27;
         */
        /* JADX WARN: Code restructure failed: missing block: B:17:0x002e, code lost:
        
            return;
         */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void l() {
            /*
                r7 = this;
                c.a.m.c.c<T> r0 = r7.f
                c.a.g<? super T> r1 = r7.f1842b
                r2 = 1
                r3 = r2
            L6:
                boolean r4 = r7.i
                boolean r5 = r0.isEmpty()
                boolean r4 = r7.j(r4, r5, r1)
                if (r4 == 0) goto L13
                return
            L13:
                boolean r4 = r7.i
                java.lang.Object r5 = r0.d()     // Catch: java.lang.Throwable -> L33
                if (r5 != 0) goto L1d
                r6 = r2
                goto L1e
            L1d:
                r6 = 0
            L1e:
                boolean r4 = r7.j(r4, r6, r1)
                if (r4 == 0) goto L25
                return
            L25:
                if (r6 == 0) goto L2f
                int r3 = -r3
                int r3 = r7.addAndGet(r3)
                if (r3 != 0) goto L6
                return
            L2f:
                r1.c(r5)
                goto L13
            L33:
                r2 = move-exception
                c.a.k.b.b(r2)
                c.a.j.b r3 = r7.g
                r3.e()
                r0.clear()
                r1.b(r2)
                c.a.h$b r0 = r7.f1843c
                r0.e()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: c.a.m.e.a.d.a.l():void");
        }

        void m() {
            if (getAndIncrement() == 0) {
                this.f1843c.b(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.l) {
                k();
            } else {
                l();
            }
        }
    }

    public d(f<T> fVar, h hVar, boolean z, int i) {
        super(fVar);
        this.f1839b = hVar;
        this.f1840c = z;
        this.f1841d = i;
    }

    @Override // c.a.c
    protected void j(g<? super T> gVar) {
        h hVar = this.f1839b;
        if (hVar instanceof k) {
            this.f1834a.a(gVar);
        } else {
            this.f1834a.a(new a(gVar, hVar.a(), this.f1840c, this.f1841d));
        }
    }
}

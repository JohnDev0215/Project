package e.a.n.e.b;

import e.a.g;
import e.a.h;
import e.a.n.g.k;

/* JADX INFO: compiled from: ObservableObserveOn.java */
/* JADX INFO: loaded from: classes.dex */
public final class c<T> extends e.a.n.e.b.a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final h f4888b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f4889c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final int f4890d;

    /* JADX INFO: compiled from: ObservableObserveOn.java */
    static final class a<T> extends e.a.n.d.a<T> implements g<T>, Runnable {
        private static final long serialVersionUID = 6576896619930983584L;
        final g<? super T> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        boolean outputFused;
        e.a.n.c.c<T> queue;
        e.a.k.b s;
        int sourceMode;
        final h.b worker;

        a(g<? super T> gVar, h.b bVar, boolean z, int i2) {
            this.actual = gVar;
            this.worker = bVar;
            this.delayError = z;
            this.bufferSize = i2;
        }

        @Override // e.a.g
        public void a() {
            if (this.done) {
                return;
            }
            this.done = true;
            o();
        }

        @Override // e.a.n.c.c
        public T b() {
            return this.queue.b();
        }

        @Override // e.a.n.c.c
        public void clear() {
            this.queue.clear();
        }

        @Override // e.a.k.b
        public void d() {
            if (this.cancelled) {
                return;
            }
            this.cancelled = true;
            this.s.d();
            this.worker.d();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        @Override // e.a.g
        public void g(Throwable th) {
            if (this.done) {
                e.a.p.a.o(th);
                return;
            }
            this.error = th;
            this.done = true;
            o();
        }

        @Override // e.a.g
        public void h(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 2) {
                this.queue.e(t);
            }
            o();
        }

        @Override // e.a.g
        public void i(e.a.k.b bVar) {
            if (e.a.n.a.b.g(this.s, bVar)) {
                this.s = bVar;
                if (bVar instanceof e.a.n.c.a) {
                    e.a.n.c.a aVar = (e.a.n.c.a) bVar;
                    int iJ = aVar.j(7);
                    if (iJ == 1) {
                        this.sourceMode = iJ;
                        this.queue = aVar;
                        this.done = true;
                        this.actual.i(this);
                        o();
                        return;
                    }
                    if (iJ == 2) {
                        this.sourceMode = iJ;
                        this.queue = aVar;
                        this.actual.i(this);
                        return;
                    }
                }
                this.queue = new e.a.n.f.a(this.bufferSize);
                this.actual.i(this);
            }
        }

        @Override // e.a.n.c.c
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        @Override // e.a.n.c.b
        public int j(int i2) {
            if ((i2 & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        boolean k(boolean z, boolean z2, g<? super T> gVar) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            if (!z) {
                return false;
            }
            Throwable th = this.error;
            if (this.delayError) {
                if (!z2) {
                    return false;
                }
                if (th != null) {
                    gVar.g(th);
                } else {
                    gVar.a();
                }
                this.worker.d();
                return true;
            }
            if (th != null) {
                this.queue.clear();
                gVar.g(th);
                this.worker.d();
                return true;
            }
            if (!z2) {
                return false;
            }
            gVar.a();
            this.worker.d();
            return true;
        }

        void m() {
            int iAddAndGet = 1;
            while (!this.cancelled) {
                boolean z = this.done;
                Throwable th = this.error;
                if (!this.delayError && z && th != null) {
                    this.actual.g(th);
                    this.worker.d();
                    return;
                }
                this.actual.h(null);
                if (z) {
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        this.actual.g(th2);
                    } else {
                        this.actual.a();
                    }
                    this.worker.d();
                    return;
                }
                iAddAndGet = addAndGet(-iAddAndGet);
                if (iAddAndGet == 0) {
                    return;
                }
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
        void n() {
            /*
                r7 = this;
                e.a.n.c.c<T> r0 = r7.queue
                e.a.g<? super T> r1 = r7.actual
                r2 = 1
                r3 = r2
            L6:
                boolean r4 = r7.done
                boolean r5 = r0.isEmpty()
                boolean r4 = r7.k(r4, r5, r1)
                if (r4 == 0) goto L13
                return
            L13:
                boolean r4 = r7.done
                java.lang.Object r5 = r0.b()     // Catch: java.lang.Throwable -> L33
                if (r5 != 0) goto L1d
                r6 = r2
                goto L1e
            L1d:
                r6 = 0
            L1e:
                boolean r4 = r7.k(r4, r6, r1)
                if (r4 == 0) goto L25
                return
            L25:
                if (r6 == 0) goto L2f
                int r3 = -r3
                int r3 = r7.addAndGet(r3)
                if (r3 != 0) goto L6
                return
            L2f:
                r1.h(r5)
                goto L13
            L33:
                r2 = move-exception
                e.a.l.b.b(r2)
                e.a.k.b r3 = r7.s
                r3.d()
                r0.clear()
                r1.g(r2)
                e.a.h$b r0 = r7.worker
                r0.d()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: e.a.n.e.b.c.a.n():void");
        }

        void o() {
            if (getAndIncrement() == 0) {
                this.worker.b(this);
            }
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.outputFused) {
                m();
            } else {
                n();
            }
        }
    }

    public c(e.a.f<T> fVar, h hVar, boolean z, int i2) {
        super(fVar);
        this.f4888b = hVar;
        this.f4889c = z;
        this.f4890d = i2;
    }

    @Override // e.a.e
    protected void h(g<? super T> gVar) {
        h hVar = this.f4888b;
        if (hVar instanceof k) {
            this.f4886a.a(gVar);
        } else {
            this.f4886a.a(new a(gVar, hVar.a(), this.f4889c, this.f4890d));
        }
    }
}

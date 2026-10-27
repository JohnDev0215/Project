package io.reactivex.internal.operators.observable;

import d.a.h;
import d.a.i;
import d.a.j;
import d.a.n.b.e;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.BasicIntQueueDisposable;
import io.reactivex.internal.schedulers.g;

/* JADX INFO: loaded from: classes.dex */
public final class ObservableObserveOn<T> extends a<T, T> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final j f4282c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    final boolean f4283d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    final int f4284e;

    static final class ObserveOnObserver<T> extends BasicIntQueueDisposable<T> implements i<T>, Runnable {
        private static final long serialVersionUID = 6576896619930983584L;
        final int bufferSize;
        final boolean delayError;
        volatile boolean disposed;
        volatile boolean done;
        final i<? super T> downstream;
        Throwable error;
        boolean outputFused;
        e<T> queue;
        int sourceMode;
        io.reactivex.disposables.b upstream;
        final j.b worker;

        ObserveOnObserver(i<? super T> iVar, j.b bVar, boolean z, int i) {
            this.downstream = iVar;
            this.worker = bVar;
            this.delayError = z;
            this.bufferSize = i;
        }

        boolean checkTerminated(boolean z, boolean z2, i<? super T> iVar) {
            if (this.disposed) {
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
                this.disposed = true;
                if (th != null) {
                    iVar.onError(th);
                } else {
                    iVar.onComplete();
                }
                this.worker.dispose();
                return true;
            }
            if (th != null) {
                this.disposed = true;
                this.queue.clear();
                iVar.onError(th);
                this.worker.dispose();
                return true;
            }
            if (!z2) {
                return false;
            }
            this.disposed = true;
            iVar.onComplete();
            this.worker.dispose();
            return true;
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, d.a.n.b.e
        public void clear() {
            this.queue.clear();
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, io.reactivex.disposables.b
        public void dispose() {
            if (this.disposed) {
                return;
            }
            this.disposed = true;
            this.upstream.dispose();
            this.worker.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void drainFused() {
            int iAddAndGet = 1;
            while (!this.disposed) {
                boolean z = this.done;
                Throwable th = this.error;
                if (!this.delayError && z && th != null) {
                    this.disposed = true;
                    this.downstream.onError(this.error);
                    this.worker.dispose();
                    return;
                }
                this.downstream.onNext(null);
                if (z) {
                    this.disposed = true;
                    Throwable th2 = this.error;
                    if (th2 != null) {
                        this.downstream.onError(th2);
                    } else {
                        this.downstream.onComplete();
                    }
                    this.worker.dispose();
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
        void drainNormal() {
            /*
                r7 = this;
                d.a.n.b.e<T> r0 = r7.queue
                d.a.i<? super T> r1 = r7.downstream
                r2 = 1
                r3 = r2
            L6:
                boolean r4 = r7.done
                boolean r5 = r0.isEmpty()
                boolean r4 = r7.checkTerminated(r4, r5, r1)
                if (r4 == 0) goto L13
                return
            L13:
                boolean r4 = r7.done
                java.lang.Object r5 = r0.poll()     // Catch: java.lang.Throwable -> L33
                if (r5 != 0) goto L1d
                r6 = r2
                goto L1e
            L1d:
                r6 = 0
            L1e:
                boolean r4 = r7.checkTerminated(r4, r6, r1)
                if (r4 == 0) goto L25
                return
            L25:
                if (r6 == 0) goto L2f
                int r3 = -r3
                int r3 = r7.addAndGet(r3)
                if (r3 != 0) goto L6
                return
            L2f:
                r1.onNext(r5)
                goto L13
            L33:
                r3 = move-exception
                io.reactivex.exceptions.a.b(r3)
                r7.disposed = r2
                io.reactivex.disposables.b r2 = r7.upstream
                r2.dispose()
                r0.clear()
                r1.onError(r3)
                d.a.j$b r0 = r7.worker
                r0.dispose()
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableObserveOn.ObserveOnObserver.drainNormal():void");
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, io.reactivex.disposables.b
        public boolean isDisposed() {
            return this.disposed;
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, d.a.n.b.e
        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        @Override // d.a.i
        public void onComplete() {
            if (this.done) {
                return;
            }
            this.done = true;
            schedule();
        }

        @Override // d.a.i
        public void onError(Throwable th) {
            if (this.done) {
                d.a.p.a.l(th);
                return;
            }
            this.error = th;
            this.done = true;
            schedule();
        }

        @Override // d.a.i
        public void onNext(T t) {
            if (this.done) {
                return;
            }
            if (this.sourceMode != 2) {
                this.queue.offer(t);
            }
            schedule();
        }

        @Override // d.a.i
        public void onSubscribe(io.reactivex.disposables.b bVar) {
            if (DisposableHelper.validate(this.upstream, bVar)) {
                this.upstream = bVar;
                if (bVar instanceof d.a.n.b.a) {
                    d.a.n.b.a aVar = (d.a.n.b.a) bVar;
                    int iRequestFusion = aVar.requestFusion(7);
                    if (iRequestFusion == 1) {
                        this.sourceMode = iRequestFusion;
                        this.queue = aVar;
                        this.done = true;
                        this.downstream.onSubscribe(this);
                        schedule();
                        return;
                    }
                    if (iRequestFusion == 2) {
                        this.sourceMode = iRequestFusion;
                        this.queue = aVar;
                        this.downstream.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new io.reactivex.internal.queue.a(this.bufferSize);
                this.downstream.onSubscribe(this);
            }
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, d.a.n.b.e
        public T poll() throws Exception {
            return this.queue.poll();
        }

        @Override // io.reactivex.internal.observers.BasicIntQueueDisposable, d.a.n.b.b
        public int requestFusion(int i) {
            if ((i & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (this.outputFused) {
                drainFused();
            } else {
                drainNormal();
            }
        }

        void schedule() {
            if (getAndIncrement() == 0) {
                this.worker.b(this);
            }
        }
    }

    public ObservableObserveOn(h<T> hVar, j jVar, boolean z, int i) {
        super(hVar);
        this.f4282c = jVar;
        this.f4283d = z;
        this.f4284e = i;
    }

    @Override // d.a.e
    protected void o(i<? super T> iVar) {
        j jVar = this.f4282c;
        if (jVar instanceof g) {
            this.f4292b.a(iVar);
        } else {
            this.f4292b.a(new ObserveOnObserver(iVar, jVar.a(), this.f4283d, this.f4284e));
        }
    }
}

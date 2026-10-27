package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import io.reactivex.rxjava3.internal.observers.InnerQueuedObserver;
import io.reactivex.rxjava3.internal.util.AtomicThrowable;
import io.reactivex.rxjava3.internal.util.ErrorMode;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes.dex */
final class ObservableConcatMapEager$ConcatMapEagerMainObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable, io.reactivex.rxjava3.internal.observers.j {
    private static final long serialVersionUID = 8080567949447303262L;
    int activeCount;
    volatile boolean cancelled;
    InnerQueuedObserver<R> current;
    volatile boolean done;
    final Observer<? super R> downstream;
    final ErrorMode errorMode;
    final io.reactivex.rxjava3.functions.o mapper;
    final int maxConcurrency;
    final int prefetch;
    io.reactivex.rxjava3.operators.g queue;
    int sourceMode;
    Disposable upstream;
    final AtomicThrowable errors = new AtomicThrowable();
    final ArrayDeque<InnerQueuedObserver<R>> observers = new ArrayDeque<>();

    public ObservableConcatMapEager$ConcatMapEagerMainObserver(Observer observer, io.reactivex.rxjava3.functions.o oVar, int i, int i2, ErrorMode errorMode) {
        this.downstream = observer;
        this.mapper = oVar;
        this.maxConcurrency = i;
        this.prefetch = i2;
        this.errorMode = errorMode;
    }

    public final void a() {
        InnerQueuedObserver<R> innerQueuedObserver = this.current;
        if (innerQueuedObserver != null) {
            innerQueuedObserver.dispose();
        }
        while (true) {
            InnerQueuedObserver<R> innerQueuedObserverPoll = this.observers.poll();
            if (innerQueuedObserverPoll == null) {
                return;
            } else {
                innerQueuedObserverPoll.dispose();
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:107:?, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x007e, code lost:
    
        r14.activeCount = r6;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0082, code lost:
    
        if (r14.cancelled == false) goto L30;
     */
    /* JADX WARN: Code restructure failed: missing block: B:28:0x0084, code lost:
    
        r0.clear();
        a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:29:0x008a, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:30:0x008b, code lost:
    
        if (r3 != r8) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:32:0x0095, code lost:
    
        if (r14.errors.get() == null) goto L35;
     */
    /* JADX WARN: Code restructure failed: missing block: B:33:0x0097, code lost:
    
        r0.clear();
        a();
        r14.errors.d(r14.downstream);
     */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x00a4, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:35:0x00a5, code lost:
    
        r6 = r14.current;
     */
    /* JADX WARN: Code restructure failed: missing block: B:36:0x00a8, code lost:
    
        if (r6 != null) goto L57;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x00ac, code lost:
    
        if (r3 != io.reactivex.rxjava3.internal.util.ErrorMode.e) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:40:0x00b6, code lost:
    
        if (r14.errors.get() == null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00b8, code lost:
    
        r0.clear();
        a();
        r14.errors.d(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:42:0x00c3, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00c4, code lost:
    
        r6 = r14.done;
        r9 = r1.poll();
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x00cc, code lost:
    
        if (r9 != null) goto L46;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00ce, code lost:
    
        r10 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00d0, code lost:
    
        r10 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d1, code lost:
    
        if (r6 == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00d3, code lost:
    
        if (r10 == false) goto L54;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00dd, code lost:
    
        if (r14.errors.get() == null) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00df, code lost:
    
        r0.clear();
        a();
        r14.errors.d(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:52:0x00eb, code lost:
    
        r2.onComplete();
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ee, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x00ef, code lost:
    
        if (r10 != false) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:55:0x00f1, code lost:
    
        r14.current = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00f3, code lost:
    
        r6 = r9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:57:0x00f4, code lost:
    
        if (r6 == null) goto L95;
     */
    /* JADX WARN: Code restructure failed: missing block: B:58:0x00f6, code lost:
    
        r9 = r6.b();
     */
    /* JADX WARN: Code restructure failed: missing block: B:60:0x00fc, code lost:
    
        if (r14.cancelled == false) goto L63;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00fe, code lost:
    
        r0.clear();
        a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:62:0x0104, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:63:0x0105, code lost:
    
        r10 = r6.a();
     */
    /* JADX WARN: Code restructure failed: missing block: B:64:0x0109, code lost:
    
        if (r3 != r8) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:0x0113, code lost:
    
        if (r14.errors.get() == null) goto L69;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:0x0115, code lost:
    
        r0.clear();
        a();
        r14.errors.d(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:0x0120, code lost:
    
        return;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:0x0122, code lost:
    
        r12 = r9.poll();
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x0126, code lost:
    
        if (r12 != null) goto L73;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:0x0128, code lost:
    
        r13 = true;
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x012a, code lost:
    
        r13 = false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:74:0x012b, code lost:
    
        if (r10 == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:75:0x012d, code lost:
    
        if (r13 == false) goto L77;
     */
    /* JADX WARN: Code restructure failed: missing block: B:76:0x012f, code lost:
    
        r14.current = null;
        r14.activeCount--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:77:0x0138, code lost:
    
        if (r13 == false) goto L79;
     */
    /* JADX WARN: Code restructure failed: missing block: B:79:0x013b, code lost:
    
        r2.onNext(r12);
     */
    /* JADX WARN: Code restructure failed: missing block: B:80:0x013f, code lost:
    
        r6 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:81:0x0140, code lost:
    
        p.he1.R(r6);
        r14.errors.a(r6);
        r14.current = null;
        r14.activeCount--;
     */
    /* JADX WARN: Code restructure failed: missing block: B:82:0x0151, code lost:
    
        r5 = addAndGet(-r5);
     */
    /* JADX WARN: Code restructure failed: missing block: B:83:0x0156, code lost:
    
        if (r5 != 0) goto L103;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x0158, code lost:
    
        return;
     */
    @Override // io.reactivex.rxjava3.internal.observers.j
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void b() {
        /*
            Method dump skipped, instruction units count: 345
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: io.reactivex.rxjava3.internal.operators.observable.ObservableConcatMapEager$ConcatMapEagerMainObserver.b():void");
    }

    @Override // io.reactivex.rxjava3.internal.observers.j
    public final void c(InnerQueuedObserver innerQueuedObserver, Throwable th) {
        if (this.errors.a(th)) {
            if (this.errorMode == ErrorMode.c) {
                this.upstream.dispose();
            }
            innerQueuedObserver.c();
            b();
        }
    }

    @Override // io.reactivex.rxjava3.internal.observers.j
    public final void d(InnerQueuedObserver innerQueuedObserver, Object obj) {
        innerQueuedObserver.b().offer(obj);
        b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.cancelled) {
            return;
        }
        this.cancelled = true;
        this.upstream.dispose();
        this.errors.b();
        if (getAndIncrement() == 0) {
            do {
                this.queue.clear();
                a();
            } while (decrementAndGet() != 0);
        }
    }

    @Override // io.reactivex.rxjava3.internal.observers.j
    public final void f(InnerQueuedObserver innerQueuedObserver) {
        innerQueuedObserver.c();
        b();
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.cancelled;
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        this.done = true;
        b();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.errors.a(th)) {
            this.done = true;
            b();
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.sourceMode == 0) {
            this.queue.offer(obj);
        }
        b();
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (DisposableHelper.g(this.upstream, disposable)) {
            this.upstream = disposable;
            if (disposable instanceof io.reactivex.rxjava3.operators.b) {
                io.reactivex.rxjava3.operators.b bVar = (io.reactivex.rxjava3.operators.b) disposable;
                int iB = bVar.b(3);
                if (iB == 1) {
                    this.sourceMode = iB;
                    this.queue = bVar;
                    this.done = true;
                    this.downstream.onSubscribe(this);
                    b();
                    return;
                }
                if (iB == 2) {
                    this.sourceMode = iB;
                    this.queue = bVar;
                    this.downstream.onSubscribe(this);
                    return;
                }
            }
            this.queue = new io.reactivex.rxjava3.operators.h(this.prefetch);
            this.downstream.onSubscribe(this);
        }
    }
}

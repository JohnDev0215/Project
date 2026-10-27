package spi;

import io.reactivex.rxjava3.internal.operators.observable.ObservableGroupBy$GroupByObserver;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes2.dex */
public final class ak<T, K> extends AtomicInteger implements q6 {
    private static final long serialVersionUID = -3852313036005250360L;
    public final K a;
    public final zr<T> b;
    public final ObservableGroupBy$GroupByObserver<?, K, T> c;
    public final boolean d;
    public volatile boolean e;
    public Throwable f;
    public final AtomicBoolean g = new AtomicBoolean();
    public final AtomicReference<bk<? super T>> h = new AtomicReference<>();
    public final AtomicInteger i = new AtomicInteger();

    public ak(int i, ObservableGroupBy$GroupByObserver<?, K, T> observableGroupBy$GroupByObserver, K k, boolean z) {
        this.b = new zr<>(i);
        this.c = observableGroupBy$GroupByObserver;
        this.a = k;
        this.d = z;
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0079  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0078 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void a() {
        /*
            r11 = this;
            int r0 = r11.getAndIncrement()
            if (r0 == 0) goto L7
            return
        L7:
            spi.zr<T> r0 = r11.b
            boolean r1 = r11.d
            java.util.concurrent.atomic.AtomicReference<spi.bk<? super T>> r2 = r11.h
            java.lang.Object r2 = r2.get()
            spi.bk r2 = (spi.bk) r2
            r3 = 1
            r4 = r3
        L15:
            if (r2 == 0) goto L80
        L17:
            boolean r5 = r11.e
            java.lang.Object r6 = r0.b()
            r7 = 0
            if (r6 != 0) goto L22
            r8 = r3
            goto L23
        L22:
            r8 = r7
        L23:
            java.util.concurrent.atomic.AtomicBoolean r9 = r11.g
            boolean r9 = r9.get()
            r10 = 0
            if (r9 == 0) goto L49
            spi.zr<T> r5 = r11.b
            r5.clear()
            java.util.concurrent.atomic.AtomicReference<spi.bk<? super T>> r5 = r11.h
            r5.lazySet(r10)
            java.util.concurrent.atomic.AtomicInteger r5 = r11.i
            int r5 = r5.get()
            r5 = r5 & 2
            if (r5 != 0) goto L47
            io.reactivex.rxjava3.internal.operators.observable.ObservableGroupBy$GroupByObserver<?, K, T> r5 = r11.c
            K r7 = r11.a
            r5.cancel(r7)
        L47:
            r7 = r3
            goto L76
        L49:
            if (r5 == 0) goto L76
            if (r1 == 0) goto L59
            if (r8 == 0) goto L76
            java.lang.Throwable r5 = r11.f
            java.util.concurrent.atomic.AtomicReference<spi.bk<? super T>> r7 = r11.h
            r7.lazySet(r10)
            if (r5 == 0) goto L72
            goto L67
        L59:
            java.lang.Throwable r5 = r11.f
            if (r5 == 0) goto L6b
            spi.zr<T> r7 = r11.b
            r7.clear()
            java.util.concurrent.atomic.AtomicReference<spi.bk<? super T>> r7 = r11.h
            r7.lazySet(r10)
        L67:
            r2.onError(r5)
            goto L47
        L6b:
            if (r8 == 0) goto L76
            java.util.concurrent.atomic.AtomicReference<spi.bk<? super T>> r5 = r11.h
            r5.lazySet(r10)
        L72:
            r2.onComplete()
            goto L47
        L76:
            if (r7 == 0) goto L79
            return
        L79:
            if (r8 == 0) goto L7c
            goto L80
        L7c:
            r2.onNext(r6)
            goto L17
        L80:
            int r4 = -r4
            int r4 = r11.addAndGet(r4)
            if (r4 != 0) goto L88
            return
        L88:
            if (r2 != 0) goto L15
            java.util.concurrent.atomic.AtomicReference<spi.bk<? super T>> r2 = r11.h
            java.lang.Object r2 = r2.get()
            spi.bk r2 = (spi.bk) r2
            goto L15
        */
        throw new UnsupportedOperationException("Method not decompiled: spi.ak.a():void");
    }

    @Override // spi.q6
    public final void dispose() {
        if (this.g.compareAndSet(false, true) && getAndIncrement() == 0) {
            this.h.lazySet(null);
            if ((this.i.get() & 2) == 0) {
                this.c.cancel(this.a);
            }
        }
    }
}

package io.reactivex.rxjava3.internal.operators.observable;

import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import spi.ak;
import spi.be;
import spi.bk;
import spi.q6;
import spi.uc;
import spi.x8;
import spi.zj;

/* JADX INFO: loaded from: classes2.dex */
public final class ObservableGroupBy$GroupByObserver<T, K, V> extends AtomicInteger implements bk<T>, q6 {
    public static final Object NULL_KEY = new Object();
    private static final long serialVersionUID = -3688291656102519502L;
    public final int bufferSize;
    public final boolean delayError;
    public final bk<? super be<K, V>> downstream;
    public final uc<? super T, ? extends K> keySelector;
    public q6 upstream;
    public final uc<? super T, ? extends V> valueSelector;
    public final AtomicBoolean cancelled = new AtomicBoolean();
    public final Map<Object, zj<K, V>> groups = new ConcurrentHashMap();

    public ObservableGroupBy$GroupByObserver(bk<? super be<K, V>> bkVar, uc<? super T, ? extends K> ucVar, uc<? super T, ? extends V> ucVar2, int i, boolean z) {
        this.downstream = bkVar;
        this.keySelector = ucVar;
        this.valueSelector = ucVar2;
        this.bufferSize = i;
        this.delayError = z;
        lazySet(1);
    }

    public void cancel(K k) {
        if (k == null) {
            k = (K) NULL_KEY;
        }
        this.groups.remove(k);
        if (decrementAndGet() == 0) {
            this.upstream.dispose();
        }
    }

    @Override // spi.q6
    public void dispose() {
        if (this.cancelled.compareAndSet(false, true) && decrementAndGet() == 0) {
            this.upstream.dispose();
        }
    }

    public boolean isDisposed() {
        return this.cancelled.get();
    }

    @Override // spi.bk
    public void onComplete() {
        ArrayList arrayList = new ArrayList(this.groups.values());
        this.groups.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ak<T, K> akVar = ((zj) it.next()).b;
            akVar.e = true;
            akVar.a();
        }
        this.downstream.onComplete();
    }

    @Override // spi.bk
    public void onError(Throwable th) {
        ArrayList arrayList = new ArrayList(this.groups.values());
        this.groups.clear();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ak<T, K> akVar = ((zj) it.next()).b;
            akVar.f = th;
            akVar.e = true;
            akVar.a();
        }
        this.downstream.onError(th);
    }

    @Override // spi.bk
    public void onNext(T t) {
        boolean z;
        try {
            K kApply = this.keySelector.apply(t);
            Object obj = kApply != null ? kApply : NULL_KEY;
            zj<K, V> zjVar = this.groups.get(obj);
            boolean z2 = false;
            if (zjVar != null) {
                z = false;
            } else {
                if (this.cancelled.get()) {
                    return;
                }
                zjVar = new zj<>(kApply, new ak(this.bufferSize, this, kApply, this.delayError));
                this.groups.put(obj, zjVar);
                getAndIncrement();
                z = true;
            }
            try {
                V vApply = this.valueSelector.apply(t);
                Objects.requireNonNull(vApply, "The value supplied is null");
                ak<V, K> akVar = zjVar.b;
                akVar.b.offer(vApply);
                akVar.a();
                if (z) {
                    this.downstream.onNext(zjVar);
                    ak<V, K> akVar2 = zjVar.b;
                    if (akVar2.i.get() == 0 && akVar2.i.compareAndSet(0, 2)) {
                        z2 = true;
                    }
                    if (z2) {
                        cancel(kApply);
                        ak<V, K> akVar3 = zjVar.b;
                        akVar3.e = true;
                        akVar3.a();
                    }
                }
            } catch (Throwable th) {
                x8.a(th);
                this.upstream.dispose();
                if (z) {
                    this.downstream.onNext(zjVar);
                }
                onError(th);
            }
        } catch (Throwable th2) {
            x8.a(th2);
            this.upstream.dispose();
            onError(th2);
        }
    }

    @Override // spi.bk
    public void onSubscribe(q6 q6Var) {
        if (DisposableHelper.validate(this.upstream, q6Var)) {
            this.upstream = q6Var;
            this.downstream.onSubscribe(this);
        }
    }
}

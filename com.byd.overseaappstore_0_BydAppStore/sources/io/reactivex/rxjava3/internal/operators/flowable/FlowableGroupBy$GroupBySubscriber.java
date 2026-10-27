package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import spi.ec;
import spi.fc;
import spi.os;
import spi.ps;
import spi.uc;
import spi.v8;
import spi.vn;
import spi.x8;
import spi.z0;
import spi.zd;

/* JADX INFO: loaded from: classes2.dex */
public final class FlowableGroupBy$GroupBySubscriber<T, K, V> extends AtomicLong implements os, ps {
    public static final Object NULL_KEY = new Object();
    private static final long serialVersionUID = -3688291656102519502L;
    public final int bufferSize;
    public final boolean delayError;
    public boolean done;
    public final os<? super zd<K, V>> downstream;
    public long emittedGroups;
    public final Queue<ec<K, V>> evictedGroups;
    public final Map<Object, ec<K, V>> groups;
    public final uc<? super T, ? extends K> keySelector;
    public final int limit;
    public ps upstream;
    public final uc<? super T, ? extends V> valueSelector;
    public final AtomicBoolean cancelled = new AtomicBoolean();
    public final AtomicInteger groupCount = new AtomicInteger(1);
    public final AtomicLong groupConsumed = new AtomicLong();

    public FlowableGroupBy$GroupBySubscriber(os<? super zd<K, V>> osVar, uc<? super T, ? extends K> ucVar, uc<? super T, ? extends V> ucVar2, int i, boolean z, Map<Object, ec<K, V>> map, Queue<ec<K, V>> queue) {
        this.downstream = osVar;
        this.keySelector = ucVar;
        this.valueSelector = ucVar2;
        this.bufferSize = i;
        this.limit = i - (i >> 2);
        this.delayError = z;
        this.groups = map;
        this.evictedGroups = queue;
    }

    private void completeEvictions() {
        if (this.evictedGroups != null) {
            int i = 0;
            while (true) {
                ec<K, V> ecVarPoll = this.evictedGroups.poll();
                if (ecVarPoll == null) {
                    break;
                }
                fc<V, K> fcVar = ecVarPoll.b;
                boolean zCompareAndSet = fcVar.l.compareAndSet(false, true);
                fcVar.f = true;
                fcVar.b();
                if (zCompareAndSet) {
                    i++;
                }
            }
            if (i != 0) {
                this.groupCount.addAndGet(-i);
            }
        }
    }

    public static MissingBackpressureException groupHangWarning(long j) {
        return new MissingBackpressureException("Unable to emit a new group (#" + j + ") due to lack of requests. Please make sure the downstream can always accept a new group as well as each group is consumed in order for the whole operator to be able to proceed.");
    }

    @Override // spi.ps
    public void cancel() {
        if (this.cancelled.compareAndSet(false, true)) {
            completeEvictions();
            if (this.groupCount.decrementAndGet() == 0) {
                this.upstream.cancel();
            }
        }
    }

    public void cancel(K k) {
        if (k == null) {
            k = (K) NULL_KEY;
        }
        if (this.groups.remove(k) == null || this.groupCount.decrementAndGet() != 0) {
            return;
        }
        this.upstream.cancel();
    }

    @Override // spi.os
    public void onComplete() {
        if (this.done) {
            return;
        }
        Iterator<ec<K, V>> it = this.groups.values().iterator();
        while (it.hasNext()) {
            fc<V, K> fcVar = it.next().b;
            fcVar.f = true;
            fcVar.b();
        }
        this.groups.clear();
        completeEvictions();
        this.done = true;
        this.downstream.onComplete();
    }

    @Override // spi.os
    public void onError(Throwable th) {
        if (this.done) {
            vn.a(th);
            return;
        }
        this.done = true;
        Iterator<ec<K, V>> it = this.groups.values().iterator();
        while (it.hasNext()) {
            fc<V, K> fcVar = it.next().b;
            fcVar.g = th;
            fcVar.f = true;
            fcVar.b();
        }
        this.groups.clear();
        completeEvictions();
        this.downstream.onError(th);
    }

    @Override // spi.os
    public void onNext(T t) {
        boolean z;
        if (this.done) {
            return;
        }
        try {
            K kApply = this.keySelector.apply(t);
            Object obj = kApply != null ? kApply : NULL_KEY;
            ec<K, V> ecVar = this.groups.get(obj);
            boolean z2 = false;
            if (ecVar != null) {
                z = false;
            } else {
                if (this.cancelled.get()) {
                    return;
                }
                int i = this.bufferSize;
                boolean z3 = this.delayError;
                int i2 = ec.c;
                ecVar = new ec<>(kApply, new fc(i, this, kApply, z3));
                this.groups.put(obj, ecVar);
                this.groupCount.getAndIncrement();
                z = true;
            }
            try {
                V vApply = this.valueSelector.apply(t);
                v8.a aVar = v8.a;
                if (vApply == null) {
                    throw v8.a("The valueSelector returned a null value.");
                }
                fc<V, K> fcVar = ecVar.b;
                fcVar.b.offer(vApply);
                fcVar.b();
                completeEvictions();
                if (z) {
                    if (this.emittedGroups == get()) {
                        this.upstream.cancel();
                        onError(groupHangWarning(this.emittedGroups));
                        return;
                    }
                    this.emittedGroups++;
                    this.downstream.onNext(ecVar);
                    fc<V, K> fcVar2 = ecVar.b;
                    if (fcVar2.k.get() == 0 && fcVar2.k.compareAndSet(0, 2)) {
                        z2 = true;
                    }
                    if (z2) {
                        cancel(kApply);
                        fc<V, K> fcVar3 = ecVar.b;
                        fcVar3.f = true;
                        fcVar3.b();
                        requestGroup(1L);
                    }
                }
            } catch (Throwable th) {
                x8.a(th);
                this.upstream.cancel();
                if (z) {
                    if (this.emittedGroups == get()) {
                        MissingBackpressureException missingBackpressureExceptionGroupHangWarning = groupHangWarning(this.emittedGroups);
                        missingBackpressureExceptionGroupHangWarning.initCause(th);
                        onError(missingBackpressureExceptionGroupHangWarning);
                        return;
                    }
                    this.downstream.onNext(ecVar);
                }
                onError(th);
            }
        } catch (Throwable th2) {
            x8.a(th2);
            this.upstream.cancel();
            onError(th2);
        }
    }

    @Override // spi.os
    public void onSubscribe(ps psVar) {
        if (SubscriptionHelper.validate(this.upstream, psVar)) {
            this.upstream = psVar;
            this.downstream.onSubscribe(this);
            psVar.request(this.bufferSize);
        }
    }

    @Override // spi.ps
    public void request(long j) {
        if (SubscriptionHelper.validate(j)) {
            z0.a(this, j);
        }
    }

    public void requestGroup(long j) {
        long j2;
        long jB;
        AtomicLong atomicLong = this.groupConsumed;
        int i = this.limit;
        do {
            j2 = atomicLong.get();
            jB = z0.b(j2, j);
        } while (!atomicLong.compareAndSet(j2, jB));
        while (true) {
            long j3 = i;
            if (jB < j3) {
                return;
            }
            if (atomicLong.compareAndSet(jB, jB - j3)) {
                this.upstream.request(j3);
            }
            jB = atomicLong.get();
        }
    }
}

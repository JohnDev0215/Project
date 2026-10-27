package io.reactivex.rxjava3.internal.operators.flowable;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import p.he1;
import p.rg2;

/* JADX INFO: loaded from: classes.dex */
public final class h0 extends Flowable {
    public final io.reactivex.rxjava3.flowables.a e;
    public final int k;
    public FlowableRefCount$RefConnection l;

    public h0(n0 n0Var) {
        TimeUnit timeUnit = TimeUnit.NANOSECONDS;
        this.e = n0Var;
        this.k = 1;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        FlowableRefCount$RefConnection flowableRefCount$RefConnection;
        boolean z;
        FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber;
        Disposable disposable;
        synchronized (this) {
            try {
                flowableRefCount$RefConnection = this.l;
                if (flowableRefCount$RefConnection == null) {
                    flowableRefCount$RefConnection = new FlowableRefCount$RefConnection(this);
                    this.l = flowableRefCount$RefConnection;
                }
                long j = flowableRefCount$RefConnection.subscriberCount;
                if (j == 0 && (disposable = flowableRefCount$RefConnection.timer) != null) {
                    disposable.dispose();
                }
                long j2 = j + 1;
                flowableRefCount$RefConnection.subscriberCount = j2;
                if (flowableRefCount$RefConnection.connected || j2 != this.k) {
                    z = false;
                } else {
                    flowableRefCount$RefConnection.connected = true;
                    z = true;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.e.subscribe((FlowableSubscriber) new FlowableRefCount$RefCountSubscriber(rg2Var, this, flowableRefCount$RefConnection));
        if (z) {
            n0 n0Var = (n0) this.e;
            loop0: while (true) {
                AtomicReference atomicReference = n0Var.k;
                flowableReplay$ReplaySubscriber = (FlowableReplay$ReplaySubscriber) atomicReference.get();
                if (flowableReplay$ReplaySubscriber != null && !flowableReplay$ReplaySubscriber.isDisposed()) {
                    break;
                }
                try {
                    FlowableReplay$ReplaySubscriber flowableReplay$ReplaySubscriber2 = new FlowableReplay$ReplaySubscriber((k0) n0Var.l.get(), atomicReference);
                    while (!atomicReference.compareAndSet(flowableReplay$ReplaySubscriber, flowableReplay$ReplaySubscriber2)) {
                        if (atomicReference.get() != flowableReplay$ReplaySubscriber) {
                            break;
                        }
                    }
                    flowableReplay$ReplaySubscriber = flowableReplay$ReplaySubscriber2;
                    break loop0;
                } finally {
                    he1.R(th);
                    RuntimeException runtimeExceptionF = io.reactivex.rxjava3.internal.util.c.f(th);
                }
            }
            boolean z2 = !flowableReplay$ReplaySubscriber.shouldConnect.get() && flowableReplay$ReplaySubscriber.shouldConnect.compareAndSet(false, true);
            try {
                flowableRefCount$RefConnection.accept(flowableReplay$ReplaySubscriber);
                if (z2) {
                    n0Var.e.subscribe((FlowableSubscriber) flowableReplay$ReplaySubscriber);
                }
            } catch (Throwable th2) {
                he1.R(th2);
                if (z2) {
                    flowableReplay$ReplaySubscriber.shouldConnect.compareAndSet(true, false);
                }
                throw io.reactivex.rxjava3.internal.util.c.f(th2);
            }
        }
    }

    public final void o(FlowableRefCount$RefConnection flowableRefCount$RefConnection) {
        synchronized (this) {
            try {
                if (this.l == flowableRefCount$RefConnection) {
                    Disposable disposable = flowableRefCount$RefConnection.timer;
                    if (disposable != null) {
                        disposable.dispose();
                        flowableRefCount$RefConnection.timer = null;
                    }
                    long j = flowableRefCount$RefConnection.subscriberCount - 1;
                    flowableRefCount$RefConnection.subscriberCount = j;
                    if (j == 0) {
                        this.l = null;
                        this.e.o();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void p(FlowableRefCount$RefConnection flowableRefCount$RefConnection) {
        synchronized (this) {
            try {
                if (flowableRefCount$RefConnection.subscriberCount == 0 && flowableRefCount$RefConnection == this.l) {
                    this.l = null;
                    Disposable disposable = flowableRefCount$RefConnection.get();
                    DisposableHelper.a(flowableRefCount$RefConnection);
                    if (disposable == null) {
                        flowableRefCount$RefConnection.disconnectedEarly = true;
                    } else {
                        this.e.o();
                    }
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

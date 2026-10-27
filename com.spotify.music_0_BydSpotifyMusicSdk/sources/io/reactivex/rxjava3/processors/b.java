package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.exceptions.MissingBackpressureException;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicReference;
import p.ci1;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class b extends a {
    public static final PublishProcessor$PublishSubscription[] l = new PublishProcessor$PublishSubscription[0];
    public static final PublishProcessor$PublishSubscription[] m = new PublishProcessor$PublishSubscription[0];
    public final AtomicReference e = new AtomicReference(m);
    public Throwable k;

    /* JADX WARN: Multi-variable type inference failed */
    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        PublishProcessor$PublishSubscription publishProcessor$PublishSubscription = new PublishProcessor$PublishSubscription(rg2Var, this);
        rg2Var.onSubscribe(publishProcessor$PublishSubscription);
        while (true) {
            AtomicReference atomicReference = this.e;
            PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr = (PublishProcessor$PublishSubscription[]) atomicReference.get();
            if (publishProcessor$PublishSubscriptionArr == l) {
                Throwable th = this.k;
                if (th != null) {
                    rg2Var.onError(th);
                    return;
                } else {
                    rg2Var.onComplete();
                    return;
                }
            }
            int length = publishProcessor$PublishSubscriptionArr.length;
            PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr2 = new PublishProcessor$PublishSubscription[length + 1];
            System.arraycopy(publishProcessor$PublishSubscriptionArr, 0, publishProcessor$PublishSubscriptionArr2, 0, length);
            publishProcessor$PublishSubscriptionArr2[length] = publishProcessor$PublishSubscription;
            while (!atomicReference.compareAndSet(publishProcessor$PublishSubscriptionArr, publishProcessor$PublishSubscriptionArr2)) {
                if (atomicReference.get() != publishProcessor$PublishSubscriptionArr) {
                    break;
                }
            }
            if (publishProcessor$PublishSubscription.get() == Long.MIN_VALUE) {
                o(publishProcessor$PublishSubscription);
                return;
            }
            return;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void o(PublishProcessor$PublishSubscription publishProcessor$PublishSubscription) {
        PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr;
        while (true) {
            AtomicReference atomicReference = this.e;
            PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr2 = (PublishProcessor$PublishSubscription[]) atomicReference.get();
            if (publishProcessor$PublishSubscriptionArr2 == l || publishProcessor$PublishSubscriptionArr2 == (publishProcessor$PublishSubscriptionArr = m)) {
                return;
            }
            int length = publishProcessor$PublishSubscriptionArr2.length;
            int i = 0;
            while (true) {
                if (i >= length) {
                    i = -1;
                    break;
                } else if (publishProcessor$PublishSubscriptionArr2[i] == publishProcessor$PublishSubscription) {
                    break;
                } else {
                    i++;
                }
            }
            if (i < 0) {
                return;
            }
            if (length != 1) {
                publishProcessor$PublishSubscriptionArr = new PublishProcessor$PublishSubscription[length - 1];
                System.arraycopy(publishProcessor$PublishSubscriptionArr2, 0, publishProcessor$PublishSubscriptionArr, 0, i);
                System.arraycopy(publishProcessor$PublishSubscriptionArr2, i + 1, publishProcessor$PublishSubscriptionArr, i, (length - i) - 1);
            }
            while (!atomicReference.compareAndSet(publishProcessor$PublishSubscriptionArr2, publishProcessor$PublishSubscriptionArr)) {
                if (atomicReference.get() != publishProcessor$PublishSubscriptionArr2) {
                    break;
                }
            }
            return;
        }
    }

    @Override // p.rg2
    public final void onComplete() {
        AtomicReference atomicReference = this.e;
        Object obj = atomicReference.get();
        Object obj2 = l;
        if (obj == obj2) {
            return;
        }
        PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr = (PublishProcessor$PublishSubscription[]) atomicReference.getAndSet(obj2);
        for (PublishProcessor$PublishSubscription publishProcessor$PublishSubscription : publishProcessor$PublishSubscriptionArr) {
            if (publishProcessor$PublishSubscription.get() != Long.MIN_VALUE) {
                publishProcessor$PublishSubscription.downstream.onComplete();
            }
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        io.reactivex.rxjava3.internal.util.c.c(th, "onError called with a null Throwable.");
        AtomicReference atomicReference = this.e;
        Object obj = atomicReference.get();
        Object obj2 = l;
        if (obj == obj2) {
            RxJavaPlugins.b(th);
            return;
        }
        this.k = th;
        PublishProcessor$PublishSubscription[] publishProcessor$PublishSubscriptionArr = (PublishProcessor$PublishSubscription[]) atomicReference.getAndSet(obj2);
        for (PublishProcessor$PublishSubscription publishProcessor$PublishSubscription : publishProcessor$PublishSubscriptionArr) {
            if (publishProcessor$PublishSubscription.get() != Long.MIN_VALUE) {
                publishProcessor$PublishSubscription.downstream.onError(th);
            } else {
                RxJavaPlugins.b(th);
            }
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        io.reactivex.rxjava3.internal.util.c.c(obj, "onNext called with a null value.");
        for (PublishProcessor$PublishSubscription publishProcessor$PublishSubscription : (PublishProcessor$PublishSubscription[]) this.e.get()) {
            long j = publishProcessor$PublishSubscription.get();
            if (j != Long.MIN_VALUE) {
                if (j != 0) {
                    publishProcessor$PublishSubscription.downstream.onNext(obj);
                    ci1.B(publishProcessor$PublishSubscription, 1L);
                } else {
                    publishProcessor$PublishSubscription.cancel();
                    publishProcessor$PublishSubscription.downstream.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
                }
            }
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (this.e.get() == l) {
            tg2Var.cancel();
        } else {
            tg2Var.a(Long.MAX_VALUE);
        }
    }
}

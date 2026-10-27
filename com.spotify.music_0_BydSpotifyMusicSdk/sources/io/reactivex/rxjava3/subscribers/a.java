package io.reactivex.rxjava3.subscribers;

import io.reactivex.rxjava3.core.FlowableSubscriber;
import io.reactivex.rxjava3.internal.subscriptions.SubscriptionHelper;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.internal.util.c;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.ab;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class a implements FlowableSubscriber, tg2 {
    public final rg2 c;
    public tg2 e;
    public boolean k;
    public ab l;
    public volatile boolean m;

    public a(rg2 rg2Var) {
        this.c = rg2Var;
    }

    @Override // p.tg2
    public final void a(long j) {
        this.e.a(j);
    }

    @Override // p.tg2
    public final void cancel() {
        this.e.cancel();
    }

    @Override // p.rg2
    public final void onComplete() {
        if (this.m) {
            return;
        }
        synchronized (this) {
            try {
                if (this.m) {
                    return;
                }
                if (!this.k) {
                    this.m = true;
                    this.k = true;
                    this.c.onComplete();
                } else {
                    ab abVar = this.l;
                    if (abVar == null) {
                        abVar = new ab(1);
                        this.l = abVar;
                    }
                    abVar.b(NotificationLite.c);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p.rg2
    public final void onError(Throwable th) {
        if (this.m) {
            RxJavaPlugins.b(th);
            return;
        }
        synchronized (this) {
            try {
                boolean z = true;
                if (!this.m) {
                    if (this.k) {
                        this.m = true;
                        ab abVar = this.l;
                        if (abVar == null) {
                            abVar = new ab(1);
                            this.l = abVar;
                        }
                        abVar.c[0] = NotificationLite.f(th);
                        return;
                    }
                    this.m = true;
                    this.k = true;
                    z = false;
                }
                if (z) {
                    RxJavaPlugins.b(th);
                } else {
                    this.c.onError(th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        ab abVar;
        if (this.m) {
            return;
        }
        if (obj == null) {
            this.e.cancel();
            onError(c.b("onNext called with a null value."));
            return;
        }
        synchronized (this) {
            try {
                if (this.m) {
                    return;
                }
                if (this.k) {
                    ab abVar2 = this.l;
                    if (abVar2 == null) {
                        abVar2 = new ab(1);
                        this.l = abVar2;
                    }
                    abVar2.b(obj);
                    return;
                }
                this.k = true;
                this.c.onNext(obj);
                do {
                    synchronized (this) {
                        try {
                            abVar = this.l;
                            if (abVar == null) {
                                this.k = false;
                                return;
                            }
                            this.l = null;
                        } finally {
                        }
                    }
                } while (!abVar.a(this.c));
            } finally {
            }
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (SubscriptionHelper.g(this.e, tg2Var)) {
            this.e = tg2Var;
            this.c.onSubscribe(this);
        }
    }
}

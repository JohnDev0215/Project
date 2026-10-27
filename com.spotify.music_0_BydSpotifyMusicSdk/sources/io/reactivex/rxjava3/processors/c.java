package io.reactivex.rxjava3.processors;

import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.ab;
import p.rg2;
import p.tg2;

/* JADX INFO: loaded from: classes.dex */
public final class c extends a {
    public final a e;
    public boolean k;
    public ab l;
    public volatile boolean m;

    public c(d dVar) {
        this.e = dVar;
    }

    @Override // io.reactivex.rxjava3.core.Flowable
    public final void m(rg2 rg2Var) {
        this.e.subscribe(rg2Var);
    }

    public final void o() {
        ab abVar;
        while (true) {
            synchronized (this) {
                try {
                    abVar = this.l;
                    if (abVar == null) {
                        this.k = false;
                        return;
                    }
                    this.l = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            abVar.a(this.e);
        }
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
                this.m = true;
                if (!this.k) {
                    this.k = true;
                    this.e.onComplete();
                    return;
                }
                ab abVar = this.l;
                if (abVar == null) {
                    abVar = new ab(1);
                    this.l = abVar;
                }
                abVar.b(NotificationLite.c);
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
                    this.m = true;
                    if (this.k) {
                        ab abVar = this.l;
                        if (abVar == null) {
                            abVar = new ab(1);
                            this.l = abVar;
                        }
                        abVar.c[0] = NotificationLite.f(th);
                        return;
                    }
                    this.k = true;
                    z = false;
                }
                if (z) {
                    RxJavaPlugins.b(th);
                } else {
                    this.e.onError(th);
                }
            } catch (Throwable th2) {
                throw th2;
            }
        }
    }

    @Override // p.rg2
    public final void onNext(Object obj) {
        if (this.m) {
            return;
        }
        synchronized (this) {
            try {
                if (this.m) {
                    return;
                }
                if (!this.k) {
                    this.k = true;
                    this.e.onNext(obj);
                    o();
                } else {
                    ab abVar = this.l;
                    if (abVar == null) {
                        abVar = new ab(1);
                        this.l = abVar;
                    }
                    abVar.b(obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // p.rg2
    public final void onSubscribe(tg2 tg2Var) {
        if (!this.m) {
            synchronized (this) {
                try {
                    boolean z = true;
                    if (!this.m) {
                        if (this.k) {
                            ab abVar = this.l;
                            if (abVar == null) {
                                abVar = new ab(1);
                                this.l = abVar;
                            }
                            abVar.b(NotificationLite.j(tg2Var));
                            return;
                        }
                        this.k = true;
                        z = false;
                    }
                    if (!z) {
                        this.e.onSubscribe(tg2Var);
                        o();
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        tg2Var.cancel();
    }
}

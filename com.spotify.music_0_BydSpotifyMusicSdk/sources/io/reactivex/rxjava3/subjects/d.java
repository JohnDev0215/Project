package io.reactivex.rxjava3.subjects;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.internal.util.NotificationLite;
import io.reactivex.rxjava3.plugins.RxJavaPlugins;
import p.ab;

/* JADX INFO: loaded from: classes.dex */
public final class d extends e implements io.reactivex.rxjava3.internal.util.a {
    public final e c;
    public boolean e;
    public ab k;
    public volatile boolean l;

    public d(c cVar) {
        this.c = cVar;
    }

    public final void a() {
        ab abVar;
        while (true) {
            synchronized (this) {
                try {
                    abVar = this.k;
                    if (abVar == null) {
                        this.e = false;
                        return;
                    }
                    this.k = null;
                } catch (Throwable th) {
                    throw th;
                }
            }
            abVar.c(this);
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onComplete() {
        if (this.l) {
            return;
        }
        synchronized (this) {
            try {
                if (this.l) {
                    return;
                }
                this.l = true;
                if (!this.e) {
                    this.e = true;
                    this.c.onComplete();
                    return;
                }
                ab abVar = this.k;
                if (abVar == null) {
                    abVar = new ab(1);
                    this.k = abVar;
                }
                abVar.b(NotificationLite.c);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onError(Throwable th) {
        if (this.l) {
            RxJavaPlugins.b(th);
            return;
        }
        synchronized (this) {
            try {
                boolean z = true;
                if (!this.l) {
                    this.l = true;
                    if (this.e) {
                        ab abVar = this.k;
                        if (abVar == null) {
                            abVar = new ab(1);
                            this.k = abVar;
                        }
                        abVar.c[0] = NotificationLite.f(th);
                        return;
                    }
                    this.e = true;
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

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onNext(Object obj) {
        if (this.l) {
            return;
        }
        synchronized (this) {
            try {
                if (this.l) {
                    return;
                }
                if (!this.e) {
                    this.e = true;
                    this.c.onNext(obj);
                    a();
                } else {
                    ab abVar = this.k;
                    if (abVar == null) {
                        abVar = new ab(1);
                        this.k = abVar;
                    }
                    abVar.b(obj);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // io.reactivex.rxjava3.core.Observer
    public final void onSubscribe(Disposable disposable) {
        if (!this.l) {
            synchronized (this) {
                try {
                    boolean z = true;
                    if (!this.l) {
                        if (this.e) {
                            ab abVar = this.k;
                            if (abVar == null) {
                                abVar = new ab(1);
                                this.k = abVar;
                            }
                            abVar.b(NotificationLite.e(disposable));
                            return;
                        }
                        this.e = true;
                        z = false;
                    }
                    if (!z) {
                        this.c.onSubscribe(disposable);
                        a();
                        return;
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
        disposable.dispose();
    }

    @Override // io.reactivex.rxjava3.core.Observable
    public final void subscribeActual(Observer observer) {
        this.c.subscribe(observer);
    }

    @Override // io.reactivex.rxjava3.functions.p
    public final boolean test(Object obj) {
        return NotificationLite.c(this.c, obj);
    }
}

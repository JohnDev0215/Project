package d.a.q;

import d.a.i;
import d.a.m.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.NotificationLite;

/* JADX INFO: compiled from: SerializedObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements i<T>, b {
    public volatile boolean E3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i<? super T> f3263c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final boolean f3264d;
    public b s;
    public boolean x;
    public d.a.p.h.a<Object> y;

    public a(i<? super T> iVar) {
        this(iVar, false);
    }

    public void a() {
        d.a.p.h.a<Object> aVar;
        do {
            synchronized (this) {
                aVar = this.y;
                if (aVar == null) {
                    this.x = false;
                    return;
                }
                this.y = null;
            }
        } while (!aVar.a(this.f3263c));
    }

    @Override // d.a.m.b
    public void dispose() {
        this.s.dispose();
    }

    @Override // d.a.m.b
    public boolean isDisposed() {
        return this.s.isDisposed();
    }

    @Override // d.a.i
    public void onComplete() {
        if (this.E3) {
            return;
        }
        synchronized (this) {
            if (this.E3) {
                return;
            }
            if (!this.x) {
                this.E3 = true;
                this.x = true;
                this.f3263c.onComplete();
            } else {
                d.a.p.h.a<Object> aVar = this.y;
                if (aVar == null) {
                    aVar = new d.a.p.h.a<>(4);
                    this.y = aVar;
                }
                aVar.b(NotificationLite.complete());
            }
        }
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        if (this.E3) {
            d.a.r.a.o(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.E3) {
                if (this.x) {
                    this.E3 = true;
                    d.a.p.h.a<Object> aVar = this.y;
                    if (aVar == null) {
                        aVar = new d.a.p.h.a<>(4);
                        this.y = aVar;
                    }
                    Object objError = NotificationLite.error(th);
                    if (this.f3264d) {
                        aVar.b(objError);
                    } else {
                        aVar.d(objError);
                    }
                    return;
                }
                this.E3 = true;
                this.x = true;
                z = false;
            }
            if (z) {
                d.a.r.a.o(th);
            } else {
                this.f3263c.onError(th);
            }
        }
    }

    @Override // d.a.i
    public void onNext(T t) {
        if (this.E3) {
            return;
        }
        if (t == null) {
            this.s.dispose();
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return;
        }
        synchronized (this) {
            if (this.E3) {
                return;
            }
            if (!this.x) {
                this.x = true;
                this.f3263c.onNext(t);
                a();
            } else {
                d.a.p.h.a<Object> aVar = this.y;
                if (aVar == null) {
                    aVar = new d.a.p.h.a<>(4);
                    this.y = aVar;
                }
                aVar.b(NotificationLite.next(t));
            }
        }
    }

    @Override // d.a.i
    public void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.s, bVar)) {
            this.s = bVar;
            this.f3263c.onSubscribe(this);
        }
    }

    public a(i<? super T> iVar, boolean z) {
        this.f3263c = iVar;
        this.f3264d = z;
    }
}

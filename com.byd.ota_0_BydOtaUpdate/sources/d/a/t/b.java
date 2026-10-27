package d.a.t;

import d.a.i;
import d.a.p.h.a;
import io.reactivex.internal.util.NotificationLite;

/* JADX INFO: compiled from: SerializedSubject.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> extends c<T> implements a.InterfaceC0077a<Object> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final c<T> f3287c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f3288d;
    public d.a.p.h.a<Object> s;
    public volatile boolean x;

    public b(c<T> cVar) {
        this.f3287c = cVar;
    }

    @Override // d.a.i
    public void onComplete() {
        if (this.x) {
            return;
        }
        synchronized (this) {
            if (this.x) {
                return;
            }
            this.x = true;
            if (!this.f3288d) {
                this.f3288d = true;
                this.f3287c.onComplete();
                return;
            }
            d.a.p.h.a<Object> aVar = this.s;
            if (aVar == null) {
                aVar = new d.a.p.h.a<>(4);
                this.s = aVar;
            }
            aVar.b(NotificationLite.complete());
        }
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        if (this.x) {
            d.a.r.a.o(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.x) {
                this.x = true;
                if (this.f3288d) {
                    d.a.p.h.a<Object> aVar = this.s;
                    if (aVar == null) {
                        aVar = new d.a.p.h.a<>(4);
                        this.s = aVar;
                    }
                    aVar.d(NotificationLite.error(th));
                    return;
                }
                this.f3288d = true;
                z = false;
            }
            if (z) {
                d.a.r.a.o(th);
            } else {
                this.f3287c.onError(th);
            }
        }
    }

    @Override // d.a.i
    public void onNext(T t) {
        if (this.x) {
            return;
        }
        synchronized (this) {
            if (this.x) {
                return;
            }
            if (!this.f3288d) {
                this.f3288d = true;
                this.f3287c.onNext(t);
                y();
            } else {
                d.a.p.h.a<Object> aVar = this.s;
                if (aVar == null) {
                    aVar = new d.a.p.h.a<>(4);
                    this.s = aVar;
                }
                aVar.b(NotificationLite.next(t));
            }
        }
    }

    @Override // d.a.i
    public void onSubscribe(d.a.m.b bVar) {
        boolean z = true;
        if (!this.x) {
            synchronized (this) {
                if (!this.x) {
                    if (this.f3288d) {
                        d.a.p.h.a<Object> aVar = this.s;
                        if (aVar == null) {
                            aVar = new d.a.p.h.a<>(4);
                            this.s = aVar;
                        }
                        aVar.b(NotificationLite.disposable(bVar));
                        return;
                    }
                    this.f3288d = true;
                    z = false;
                }
            }
        }
        if (z) {
            bVar.dispose();
        } else {
            this.f3287c.onSubscribe(bVar);
            y();
        }
    }

    @Override // d.a.g
    public void t(i<? super T> iVar) {
        this.f3287c.a(iVar);
    }

    @Override // d.a.p.h.a.InterfaceC0077a, d.a.o.g
    public boolean test(Object obj) {
        return NotificationLite.acceptFull(obj, this.f3287c);
    }

    public void y() {
        d.a.p.h.a<Object> aVar;
        while (true) {
            synchronized (this) {
                aVar = this.s;
                if (aVar == null) {
                    this.f3288d = false;
                    return;
                }
                this.s = null;
            }
            aVar.c(this);
        }
    }
}

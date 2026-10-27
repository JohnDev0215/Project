package d.a.o;

import d.a.i;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.NotificationLite;

/* JADX INFO: compiled from: SerializedObserver.java */
/* JADX INFO: loaded from: classes.dex */
public final class a<T> implements i<T>, b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    final i<? super T> f4241b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    final boolean f4242c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    b f4243d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f4244e;
    io.reactivex.internal.util.a<Object> f;
    volatile boolean g;

    public a(i<? super T> iVar) {
        this(iVar, false);
    }

    void a() {
        io.reactivex.internal.util.a<Object> aVar;
        do {
            synchronized (this) {
                aVar = this.f;
                if (aVar == null) {
                    this.f4244e = false;
                    return;
                }
                this.f = null;
            }
        } while (!aVar.a(this.f4241b));
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        this.f4243d.dispose();
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.f4243d.isDisposed();
    }

    @Override // d.a.i
    public void onComplete() {
        if (this.g) {
            return;
        }
        synchronized (this) {
            if (this.g) {
                return;
            }
            if (!this.f4244e) {
                this.g = true;
                this.f4244e = true;
                this.f4241b.onComplete();
            } else {
                io.reactivex.internal.util.a<Object> aVar = this.f;
                if (aVar == null) {
                    aVar = new io.reactivex.internal.util.a<>(4);
                    this.f = aVar;
                }
                aVar.b(NotificationLite.complete());
            }
        }
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        if (this.g) {
            d.a.p.a.l(th);
            return;
        }
        synchronized (this) {
            boolean z = true;
            if (!this.g) {
                if (this.f4244e) {
                    this.g = true;
                    io.reactivex.internal.util.a<Object> aVar = this.f;
                    if (aVar == null) {
                        aVar = new io.reactivex.internal.util.a<>(4);
                        this.f = aVar;
                    }
                    Object objError = NotificationLite.error(th);
                    if (this.f4242c) {
                        aVar.b(objError);
                    } else {
                        aVar.c(objError);
                    }
                    return;
                }
                this.g = true;
                this.f4244e = true;
                z = false;
            }
            if (z) {
                d.a.p.a.l(th);
            } else {
                this.f4241b.onError(th);
            }
        }
    }

    @Override // d.a.i
    public void onNext(T t) {
        if (this.g) {
            return;
        }
        if (t == null) {
            this.f4243d.dispose();
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
            return;
        }
        synchronized (this) {
            if (this.g) {
                return;
            }
            if (!this.f4244e) {
                this.f4244e = true;
                this.f4241b.onNext(t);
                a();
            } else {
                io.reactivex.internal.util.a<Object> aVar = this.f;
                if (aVar == null) {
                    aVar = new io.reactivex.internal.util.a<>(4);
                    this.f = aVar;
                }
                aVar.b(NotificationLite.next(t));
            }
        }
    }

    @Override // d.a.i
    public void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.f4243d, bVar)) {
            this.f4243d = bVar;
            this.f4241b.onSubscribe(this);
        }
    }

    public a(i<? super T> iVar, boolean z) {
        this.f4241b = iVar;
        this.f4242c = z;
    }
}

package d.a.p.d;

import d.a.i;
import d.a.m.b;
import io.reactivex.internal.disposables.DisposableHelper;

/* JADX INFO: compiled from: BasicFuseableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T, R> implements i<T>, d.a.p.c.a<R> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final i<? super R> f3181c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b f3182d;
    public d.a.p.c.a<T> s;
    public boolean x;
    public int y;

    public a(i<? super R> iVar) {
        this.f3181c = iVar;
    }

    public void a() {
    }

    public boolean b() {
        return true;
    }

    public final void c(Throwable th) {
        d.a.n.a.a(th);
        this.f3182d.dispose();
        onError(th);
    }

    @Override // d.a.p.c.e
    public void clear() {
        this.s.clear();
    }

    public final int d(int i2) {
        d.a.p.c.a<T> aVar = this.s;
        if (aVar == null || (i2 & 4) != 0) {
            return 0;
        }
        int iRequestFusion = aVar.requestFusion(i2);
        if (iRequestFusion != 0) {
            this.y = iRequestFusion;
        }
        return iRequestFusion;
    }

    @Override // d.a.m.b
    public void dispose() {
        this.f3182d.dispose();
    }

    @Override // d.a.m.b
    public boolean isDisposed() {
        return this.f3182d.isDisposed();
    }

    @Override // d.a.p.c.e
    public boolean isEmpty() {
        return this.s.isEmpty();
    }

    @Override // d.a.p.c.e
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // d.a.i
    public void onComplete() {
        if (this.x) {
            return;
        }
        this.x = true;
        this.f3181c.onComplete();
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        if (this.x) {
            d.a.r.a.o(th);
        } else {
            this.x = true;
            this.f3181c.onError(th);
        }
    }

    @Override // d.a.i
    public final void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.f3182d, bVar)) {
            this.f3182d = bVar;
            if (bVar instanceof d.a.p.c.a) {
                this.s = (d.a.p.c.a) bVar;
            }
            if (b()) {
                this.f3181c.onSubscribe(this);
                a();
            }
        }
    }
}

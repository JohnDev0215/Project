package io.reactivex.internal.observers;

import d.a.i;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;

/* JADX INFO: compiled from: BasicFuseableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T, R> implements i<T>, d.a.n.b.a<R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final i<? super R> f4267b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected b f4268c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected d.a.n.b.a<T> f4269d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected boolean f4270e;
    protected int f;

    public a(i<? super R> iVar) {
        this.f4267b = iVar;
    }

    protected void a() {
    }

    protected boolean b() {
        return true;
    }

    protected final void c(Throwable th) {
        io.reactivex.exceptions.a.b(th);
        this.f4268c.dispose();
        onError(th);
    }

    @Override // d.a.n.b.e
    public void clear() {
        this.f4269d.clear();
    }

    protected final int d(int i) {
        d.a.n.b.a<T> aVar = this.f4269d;
        if (aVar == null || (i & 4) != 0) {
            return 0;
        }
        int iRequestFusion = aVar.requestFusion(i);
        if (iRequestFusion != 0) {
            this.f = iRequestFusion;
        }
        return iRequestFusion;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        this.f4268c.dispose();
    }

    @Override // io.reactivex.disposables.b
    public boolean isDisposed() {
        return this.f4268c.isDisposed();
    }

    @Override // d.a.n.b.e
    public boolean isEmpty() {
        return this.f4269d.isEmpty();
    }

    @Override // d.a.n.b.e
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // d.a.i
    public void onComplete() {
        if (this.f4270e) {
            return;
        }
        this.f4270e = true;
        this.f4267b.onComplete();
    }

    @Override // d.a.i
    public void onError(Throwable th) {
        if (this.f4270e) {
            d.a.p.a.l(th);
        } else {
            this.f4270e = true;
            this.f4267b.onError(th);
        }
    }

    @Override // d.a.i
    public final void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.f4268c, bVar)) {
            this.f4268c = bVar;
            if (bVar instanceof d.a.n.b.a) {
                this.f4269d = (d.a.n.b.a) bVar;
            }
            if (b()) {
                this.f4267b.onSubscribe(this);
                a();
            }
        }
    }
}

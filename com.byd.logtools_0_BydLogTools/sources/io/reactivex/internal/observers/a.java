package io.reactivex.internal.observers;

import e.a.i;
import io.reactivex.disposables.b;
import io.reactivex.internal.disposables.DisposableHelper;

/* JADX INFO: compiled from: BasicFuseableObserver.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a<T, R> implements i<T>, e.a.n.b.a<R> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final i<? super R> f4991b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected b f4992c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected e.a.n.b.a<T> f4993d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected boolean f4994e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f4995f;

    public a(i<? super R> iVar) {
        this.f4991b = iVar;
    }

    protected void a() {
    }

    protected boolean b() {
        return true;
    }

    protected final void c(Throwable th) {
        io.reactivex.exceptions.a.b(th);
        this.f4992c.dispose();
        onError(th);
    }

    @Override // e.a.n.b.e
    public void clear() {
        this.f4993d.clear();
    }

    protected final int d(int i) {
        e.a.n.b.a<T> aVar = this.f4993d;
        if (aVar == null || (i & 4) != 0) {
            return 0;
        }
        int iRequestFusion = aVar.requestFusion(i);
        if (iRequestFusion != 0) {
            this.f4995f = iRequestFusion;
        }
        return iRequestFusion;
    }

    @Override // io.reactivex.disposables.b
    public void dispose() {
        this.f4992c.dispose();
    }

    @Override // e.a.n.b.e
    public boolean isEmpty() {
        return this.f4993d.isEmpty();
    }

    @Override // e.a.n.b.e
    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    @Override // e.a.i
    public void onComplete() {
        if (this.f4994e) {
            return;
        }
        this.f4994e = true;
        this.f4991b.onComplete();
    }

    @Override // e.a.i
    public void onError(Throwable th) {
        if (this.f4994e) {
            e.a.o.a.k(th);
        } else {
            this.f4994e = true;
            this.f4991b.onError(th);
        }
    }

    @Override // e.a.i
    public final void onSubscribe(b bVar) {
        if (DisposableHelper.validate(this.f4992c, bVar)) {
            this.f4992c = bVar;
            if (bVar instanceof e.a.n.b.a) {
                this.f4993d = (e.a.n.b.a) bVar;
            }
            if (b()) {
                this.f4991b.onSubscribe(this);
                a();
            }
        }
    }
}

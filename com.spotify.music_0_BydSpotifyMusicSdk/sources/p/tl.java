package p;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/* JADX INFO: loaded from: classes.dex */
public final class tl implements Disposable, io.reactivex.rxjava3.functions.p {
    public final Observer c;
    public final ul e;
    public boolean k;
    public boolean l;
    public ab m;
    public boolean n;
    public volatile boolean o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public long f190p;

    public tl(Observer observer, ul ulVar) {
        this.c = observer;
        this.e = ulVar;
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public void dispose() {
        if (this.o) {
            return;
        }
        this.o = true;
        this.e.a(this);
    }

    @Override // io.reactivex.rxjava3.disposables.Disposable
    public boolean isDisposed() {
        return this.o;
    }

    @Override // io.reactivex.rxjava3.functions.p
    public final boolean test(Object obj) {
        if (this.o) {
            return false;
        }
        this.c.onNext(obj);
        return false;
    }
}

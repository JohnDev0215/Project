package d.a.p.e.b;

import io.reactivex.internal.disposables.EmptyDisposable;

/* JADX INFO: compiled from: ObservableEmpty.java */
/* JADX INFO: loaded from: classes.dex */
public final class c extends d.a.g<Object> implements d.a.p.c.c<Object> {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d.a.g<Object> f3199c = new c();

    @Override // d.a.p.c.c, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    @Override // d.a.g
    public void t(d.a.i<? super Object> iVar) {
        EmptyDisposable.complete(iVar);
    }
}

package io.reactivex.internal.operators.observable;

import d.a.e;
import d.a.i;
import io.reactivex.internal.disposables.EmptyDisposable;

/* JADX INFO: compiled from: ObservableEmpty.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends e<Object> implements d.a.n.b.c<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e<Object> f4293b = new b();

    private b() {
    }

    @Override // d.a.n.b.c, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    @Override // d.a.e
    protected void o(i<? super Object> iVar) {
        EmptyDisposable.complete(iVar);
    }
}

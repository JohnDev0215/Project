package io.reactivex.internal.operators.observable;

import e.a.e;
import e.a.i;
import io.reactivex.internal.disposables.EmptyDisposable;

/* JADX INFO: compiled from: ObservableEmpty.java */
/* JADX INFO: loaded from: classes.dex */
public final class b extends e<Object> implements e.a.n.b.c<Object> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final e<Object> f5010b = new b();

    private b() {
    }

    @Override // e.a.n.b.c, java.util.concurrent.Callable
    public Object call() {
        return null;
    }

    @Override // e.a.e
    protected void m(i<? super Object> iVar) {
        EmptyDisposable.complete(iVar);
    }
}

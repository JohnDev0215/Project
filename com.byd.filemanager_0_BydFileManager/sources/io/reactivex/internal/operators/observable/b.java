package io.reactivex.internal.operators.observable;

import d.a.i;
import d.a.m.e;

/* JADX INFO: compiled from: ObservableDoOnLifecycle.java */
/* JADX INFO: loaded from: classes.dex */
public final class b<T> extends a<T, T> {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final e<? super io.reactivex.disposables.b> f4567b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final d.a.m.a f4568c;

    public b(d.a.e<T> eVar, e<? super io.reactivex.disposables.b> eVar2, d.a.m.a aVar) {
        super(eVar);
        this.f4567b = eVar2;
        this.f4568c = aVar;
    }

    @Override // d.a.e
    protected void j(i<? super T> iVar) {
        this.f4566a.a(new io.reactivex.internal.observers.a(iVar, this.f4567b, this.f4568c));
    }
}

package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
class SingleGeneratedAdapterObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f1491a;

    SingleGeneratedAdapterObserver(d dVar) {
        this.f1491a = dVar;
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, Lifecycle.Event event) {
        this.f1491a.a(gVar, event, false, null);
        this.f1491a.a(gVar, event, true, null);
    }
}

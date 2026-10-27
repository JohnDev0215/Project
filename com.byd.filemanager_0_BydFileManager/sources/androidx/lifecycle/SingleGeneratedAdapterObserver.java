package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
class SingleGeneratedAdapterObserver implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final d f1241a;

    SingleGeneratedAdapterObserver(d dVar) {
        this.f1241a = dVar;
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, Lifecycle.Event event) {
        this.f1241a.a(gVar, event, false, null);
        this.f1241a.a(gVar, event, true, null);
    }
}

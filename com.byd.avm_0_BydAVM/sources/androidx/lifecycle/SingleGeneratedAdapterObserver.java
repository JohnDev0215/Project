package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: loaded from: classes.dex */
class SingleGeneratedAdapterObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f1101a;

    SingleGeneratedAdapterObserver(c cVar) {
        this.f1101a = cVar;
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f1101a.a(fVar, event, false, null);
        this.f1101a.a(fVar, event, true, null);
    }
}

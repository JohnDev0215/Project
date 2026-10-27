package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class SingleGeneratedAdapterObserver implements d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c f762c;

    SingleGeneratedAdapterObserver(c cVar) {
        this.f762c = cVar;
    }

    @Override // androidx.lifecycle.d
    public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
        this.f762c.a(fVar, event, false, null);
        this.f762c.a(fVar, event, true, null);
    }
}

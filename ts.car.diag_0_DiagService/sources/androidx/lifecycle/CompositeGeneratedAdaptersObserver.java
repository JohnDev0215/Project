package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class CompositeGeneratedAdaptersObserver implements d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final c[] f748c;

    CompositeGeneratedAdaptersObserver(c[] cVarArr) {
        this.f748c = cVarArr;
    }

    @Override // androidx.lifecycle.d
    public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
        j jVar = new j();
        for (c cVar : this.f748c) {
            cVar.a(fVar, event, false, jVar);
        }
        for (c cVar2 : this.f748c) {
            cVar2.a(fVar, event, true, jVar);
        }
    }
}

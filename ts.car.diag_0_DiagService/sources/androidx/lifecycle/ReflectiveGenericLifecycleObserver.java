package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.a;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final Object f758c;
    private final a.C0024a d;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f758c = obj;
        this.d = a.f763c.c(obj.getClass());
    }

    @Override // androidx.lifecycle.d
    public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
        this.d.a(fVar, event, this.f758c);
    }
}

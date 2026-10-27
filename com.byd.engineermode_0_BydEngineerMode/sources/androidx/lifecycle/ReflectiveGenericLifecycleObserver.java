package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.a;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1080a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.C0019a f1081b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1080a = obj;
        this.f1081b = a.f1084a.c(obj.getClass());
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f1081b.a(fVar, event, this.f1080a);
    }
}

package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.a;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1647a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.C0021a f1648b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1647a = obj;
        this.f1648b = a.f1651a.c(obj.getClass());
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f1648b.a(fVar, event, this.f1647a);
    }
}

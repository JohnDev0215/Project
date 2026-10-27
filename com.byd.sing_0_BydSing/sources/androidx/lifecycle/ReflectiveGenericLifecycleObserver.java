package androidx.lifecycle;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.a;

/* JADX INFO: loaded from: classes.dex */
class ReflectiveGenericLifecycleObserver implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final Object f1624a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final a.C0047a f1625b;

    ReflectiveGenericLifecycleObserver(Object obj) {
        this.f1624a = obj;
        this.f1625b = a.f1628a.c(obj.getClass());
    }

    @Override // androidx.lifecycle.d
    public void d(f fVar, Lifecycle.Event event) {
        this.f1625b.a(fVar, event, this.f1624a);
    }
}

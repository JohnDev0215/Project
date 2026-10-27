package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
class o implements androidx.lifecycle.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.lifecycle.h f1466b = null;

    o() {
    }

    void a(Lifecycle.Event event) {
        this.f1466b.i(event);
    }

    void b() {
        if (this.f1466b == null) {
            this.f1466b = new androidx.lifecycle.h(this);
        }
    }

    boolean c() {
        return this.f1466b != null;
    }

    @Override // androidx.lifecycle.g
    public Lifecycle getLifecycle() {
        b();
        return this.f1466b;
    }
}

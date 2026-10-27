package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
class n implements androidx.lifecycle.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.lifecycle.g f1624b = null;

    n() {
    }

    void a(Lifecycle.Event event) {
        this.f1624b.i(event);
    }

    void b() {
        if (this.f1624b == null) {
            this.f1624b = new androidx.lifecycle.g(this);
        }
    }

    boolean c() {
        return this.f1624b != null;
    }

    @Override // androidx.lifecycle.f
    public Lifecycle getLifecycle() {
        b();
        return this.f1624b;
    }
}

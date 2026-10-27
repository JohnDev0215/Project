package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
class n implements androidx.lifecycle.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.lifecycle.g f1607b = null;

    n() {
    }

    @Override // androidx.lifecycle.f
    public Lifecycle a() {
        e();
        return this.f1607b;
    }

    void b(Lifecycle.Event event) {
        this.f1607b.i(event);
    }

    void e() {
        if (this.f1607b == null) {
            this.f1607b = new androidx.lifecycle.g(this);
        }
    }

    boolean f() {
        return this.f1607b != null;
    }
}

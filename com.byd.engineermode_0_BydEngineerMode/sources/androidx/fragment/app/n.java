package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
class n implements androidx.lifecycle.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.lifecycle.g f1063b = null;

    n() {
    }

    @Override // androidx.lifecycle.f
    public Lifecycle a() {
        e();
        return this.f1063b;
    }

    void b(Lifecycle.Event event) {
        this.f1063b.i(event);
    }

    void e() {
        if (this.f1063b == null) {
            this.f1063b = new androidx.lifecycle.g(this);
        }
    }

    boolean f() {
        return this.f1063b != null;
    }
}

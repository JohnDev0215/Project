package androidx.fragment.app;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
class n implements androidx.lifecycle.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.lifecycle.h f1219b = null;

    n() {
    }

    @Override // androidx.lifecycle.g
    public Lifecycle a() {
        e();
        return this.f1219b;
    }

    void b(Lifecycle.Event event) {
        this.f1219b.i(event);
    }

    void e() {
        if (this.f1219b == null) {
            this.f1219b = new androidx.lifecycle.h(this);
        }
    }

    boolean f() {
        return this.f1219b != null;
    }
}

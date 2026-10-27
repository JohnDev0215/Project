package androidx.fragment.app;

import androidx.lifecycle.d;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
class s implements androidx.lifecycle.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.lifecycle.h f1566b = null;

    s() {
    }

    @Override // androidx.lifecycle.g
    public androidx.lifecycle.d a() {
        c();
        return this.f1566b;
    }

    void b(d.a aVar) {
        this.f1566b.i(aVar);
    }

    void c() {
        if (this.f1566b == null) {
            this.f1566b = new androidx.lifecycle.h(this);
        }
    }

    boolean f() {
        return this.f1566b != null;
    }
}

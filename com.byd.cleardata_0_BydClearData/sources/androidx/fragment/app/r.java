package androidx.fragment.app;

import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
class r implements androidx.lifecycle.g {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private androidx.lifecycle.h f1376b = null;

    r() {
    }

    @Override // androidx.lifecycle.g
    public androidx.lifecycle.d a() {
        e();
        return this.f1376b;
    }

    void b(d.a aVar) {
        this.f1376b.i(aVar);
    }

    void e() {
        if (this.f1376b == null) {
            this.f1376b = new androidx.lifecycle.h(this);
        }
    }

    boolean f() {
        return this.f1376b != null;
    }
}

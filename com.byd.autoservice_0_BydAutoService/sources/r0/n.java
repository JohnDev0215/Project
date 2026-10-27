package r0;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements t0.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public t0.g f4833b = null;

    public void a(Lifecycle.Event event) {
        this.f4833b.i(event);
    }

    public void b() {
        if (this.f4833b == null) {
            this.f4833b = new t0.g(this);
        }
    }

    public boolean c() {
        return this.f4833b != null;
    }

    @Override // t0.f
    public Lifecycle getLifecycle() {
        b();
        return this.f4833b;
    }
}

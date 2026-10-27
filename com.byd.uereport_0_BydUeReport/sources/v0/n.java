package v0;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements x0.f {

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public x0.g f8511d = null;

    public void a(Lifecycle.Event event) {
        this.f8511d.i(event);
    }

    public void b() {
        if (this.f8511d == null) {
            this.f8511d = new x0.g(this);
        }
    }

    public boolean c() {
        return this.f8511d != null;
    }

    @Override // x0.f
    public Lifecycle getLifecycle() {
        b();
        return this.f8511d;
    }
}

package b.k.a;

import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public class n implements b.m.f {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public b.m.g f1297b = null;

    public void a(Lifecycle.Event event) {
        this.f1297b.i(event);
    }

    public void b() {
        if (this.f1297b == null) {
            this.f1297b = new b.m.g(this);
        }
    }

    public boolean c() {
        return this.f1297b != null;
    }

    @Override // b.m.f
    public Lifecycle getLifecycle() {
        b();
        return this.f1297b;
    }
}

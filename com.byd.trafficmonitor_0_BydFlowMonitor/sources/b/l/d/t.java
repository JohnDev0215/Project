package b.l.d;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: FragmentViewLifecycleOwner.java */
/* JADX INFO: loaded from: classes.dex */
public class t implements b.t.c, b.n.t {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Fragment f2926b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final b.n.s f2927c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public b.n.h f2928d = null;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public b.t.b f2929e = null;

    public t(Fragment fragment, b.n.s sVar) {
        this.f2926b = fragment;
        this.f2927c = sVar;
    }

    public void a(Lifecycle.Event event) {
        this.f2928d.h(event);
    }

    public void b() {
        if (this.f2928d == null) {
            this.f2928d = new b.n.h(this);
            this.f2929e = b.t.b.a(this);
        }
    }

    public boolean c() {
        return this.f2928d != null;
    }

    public void d(Bundle bundle) {
        this.f2929e.c(bundle);
    }

    public void e(Bundle bundle) {
        this.f2929e.d(bundle);
    }

    public void f(Lifecycle.State state) {
        this.f2928d.o(state);
    }

    @Override // b.n.g
    public Lifecycle getLifecycle() {
        b();
        return this.f2928d;
    }

    @Override // b.t.c
    public SavedStateRegistry getSavedStateRegistry() {
        b();
        return this.f2929e.b();
    }

    @Override // b.n.t
    public b.n.s getViewModelStore() {
        b();
        return this.f2927c;
    }
}

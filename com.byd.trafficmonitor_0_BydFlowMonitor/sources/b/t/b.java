package b.t;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f3170a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SavedStateRegistry f3171b = new SavedStateRegistry();

    public b(c cVar) {
        this.f3170a = cVar;
    }

    public static b a(c cVar) {
        return new b(cVar);
    }

    public SavedStateRegistry b() {
        return this.f3171b;
    }

    public void c(Bundle bundle) {
        Lifecycle lifecycle = this.f3170a.getLifecycle();
        if (lifecycle.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.a(new Recreator(this.f3170a));
        this.f3171b.b(lifecycle, bundle);
    }

    public void d(Bundle bundle) {
        this.f3171b.c(bundle);
    }
}

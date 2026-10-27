package b.p;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f1426a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SavedStateRegistry f1427b = new SavedStateRegistry();

    public a(b bVar) {
        this.f1426a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.f1427b;
    }

    public void c(Bundle bundle) {
        Lifecycle lifecycle = this.f1426a.getLifecycle();
        if (lifecycle.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.a(new Recreator(this.f1426a));
        this.f1427b.b(lifecycle, bundle);
    }

    public void d(Bundle bundle) {
        this.f1427b.c(bundle);
    }
}

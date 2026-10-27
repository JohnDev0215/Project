package g1;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f5011a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SavedStateRegistry f5012b = new SavedStateRegistry();

    public a(b bVar) {
        this.f5011a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.f5012b;
    }

    public void c(Bundle bundle) {
        Lifecycle lifecycle = this.f5011a.getLifecycle();
        if (lifecycle.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.a(new Recreator(this.f5011a));
        this.f5012b.b(lifecycle, bundle);
    }

    public void d(Bundle bundle) {
        this.f5012b.c(bundle);
    }
}

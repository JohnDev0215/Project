package b.o;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final b f2122a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final SavedStateRegistry f2123b = new SavedStateRegistry();

    public a(b bVar) {
        this.f2122a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.f2123b;
    }

    public void c(Bundle bundle) {
        Lifecycle lifecycleA = this.f2122a.a();
        if (lifecycleA.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycleA.a(new Recreator(this.f2122a));
        this.f2123b.b(lifecycleA, bundle);
    }

    public void d(Bundle bundle) {
        this.f2123b.c(bundle);
    }
}

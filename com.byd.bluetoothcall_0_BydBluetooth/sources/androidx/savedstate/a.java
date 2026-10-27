package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1835a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f1836b = new SavedStateRegistry();

    private a(b bVar) {
        this.f1835a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.f1836b;
    }

    public void c(Bundle bundle) {
        Lifecycle lifecycle = this.f1835a.getLifecycle();
        if (lifecycle.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.a(new Recreator(this.f1835a));
        this.f1836b.b(lifecycle, bundle);
    }

    public void d(Bundle bundle) {
        this.f1836b.c(bundle);
    }
}

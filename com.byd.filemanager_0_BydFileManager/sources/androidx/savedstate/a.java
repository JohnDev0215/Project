package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1500a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f1501b = new SavedStateRegistry();

    private a(b bVar) {
        this.f1500a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.f1501b;
    }

    public void c(Bundle bundle) {
        Lifecycle lifecycleA = this.f1500a.a();
        if (lifecycleA.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycleA.a(new Recreator(this.f1500a));
        this.f1501b.b(lifecycleA, bundle);
    }

    public void d(Bundle bundle) {
        this.f1501b.c(bundle);
    }
}

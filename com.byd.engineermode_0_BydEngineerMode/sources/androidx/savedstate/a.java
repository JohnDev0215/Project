package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1406a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f1407b = new SavedStateRegistry();

    private a(b bVar) {
        this.f1406a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.f1407b;
    }

    public void c(Bundle bundle) {
        Lifecycle lifecycleA = this.f1406a.a();
        if (lifecycleA.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycleA.a(new Recreator(this.f1406a));
        this.f1407b.b(lifecycleA, bundle);
    }

    public void d(Bundle bundle) {
        this.f1407b.c(bundle);
    }
}

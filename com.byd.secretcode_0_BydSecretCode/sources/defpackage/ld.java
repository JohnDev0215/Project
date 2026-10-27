package defpackage;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.Recreator;
import androidx.savedstate.SavedStateRegistry;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class ld {
    public final md a;
    public final SavedStateRegistry b = new SavedStateRegistry();

    public ld(md mdVar) {
        this.a = mdVar;
    }

    public static ld a(md mdVar) {
        return new ld(mdVar);
    }

    public SavedStateRegistry b() {
        return this.b;
    }

    public void c(Bundle bundle) {
        Lifecycle lifecycle = this.a.getLifecycle();
        if (lifecycle.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycle.a(new Recreator(this.a));
        this.b.b(lifecycle, bundle);
    }

    public void d(Bundle bundle) {
        this.b.c(bundle);
    }
}

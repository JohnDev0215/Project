package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.i;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final c f3201a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f3202b = new SavedStateRegistry();

    private b(c cVar) {
        this.f3201a = cVar;
    }

    public static b a(c cVar) {
        return new b(cVar);
    }

    public SavedStateRegistry b() {
        return this.f3202b;
    }

    public void c(Bundle bundle) {
        i iVarA = this.f3201a.a();
        if (iVarA.b() != i.c.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        iVarA.a(new Recreator(this.f3201a));
        this.f3202b.b(iVarA, bundle);
    }

    public void d(Bundle bundle) {
        this.f3202b.c(bundle);
    }
}

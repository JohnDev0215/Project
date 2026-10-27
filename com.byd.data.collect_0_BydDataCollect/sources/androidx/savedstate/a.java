package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.d;

/* JADX INFO: compiled from: SavedStateRegistryController.java */
/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f2039a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f2040b = new SavedStateRegistry();

    private a(b bVar) {
        this.f2039a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.f2040b;
    }

    public void c(Bundle bundle) {
        d dVarA = this.f2039a.a();
        if (dVarA.b() != d.b.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        dVarA.a(new Recreator(this.f2039a));
        this.f2040b.b(dVarA, bundle);
    }

    public void d(Bundle bundle) {
        this.f2040b.c(bundle);
    }
}

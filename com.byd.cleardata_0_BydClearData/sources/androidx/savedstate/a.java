package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.d;

/* JADX INFO: loaded from: classes.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1625a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f1626b = new SavedStateRegistry();

    private a(b bVar) {
        this.f1625a = bVar;
    }

    public static a a(b bVar) {
        return new a(bVar);
    }

    public SavedStateRegistry b() {
        return this.f1626b;
    }

    public void c(Bundle bundle) {
        d dVarA = this.f1625a.a();
        if (dVarA.b() != d.b.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        dVarA.a(new Recreator(this.f1625a));
        this.f1626b.b(dVarA, bundle);
    }

    public void d(Bundle bundle) {
        this.f1626b.c(bundle);
    }
}

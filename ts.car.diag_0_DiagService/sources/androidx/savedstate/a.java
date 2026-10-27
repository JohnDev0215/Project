package androidx.savedstate;

import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
public final class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final b f1230a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final SavedStateRegistry f1231b = new SavedStateRegistry();

    private a(b bVar) {
        this.f1230a = bVar;
    }

    @NonNull
    public static a a(@NonNull b bVar) {
        return new a(bVar);
    }

    @NonNull
    public SavedStateRegistry b() {
        return this.f1231b;
    }

    @MainThread
    public void c(@Nullable Bundle bundle) {
        Lifecycle lifecycleA = this.f1230a.a();
        if (lifecycleA.b() != Lifecycle.State.INITIALIZED) {
            throw new IllegalStateException("Restarter must be created only during owner's initialization stage");
        }
        lifecycleA.a(new Recreator(this.f1230a));
        this.f1231b.b(lifecycleA, bundle);
    }

    @MainThread
    public void d(@NonNull Bundle bundle) {
        this.f1231b.c(bundle);
    }
}

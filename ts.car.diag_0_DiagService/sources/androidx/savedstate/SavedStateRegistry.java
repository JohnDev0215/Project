package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.savedstate.Recreator;
import java.util.Map;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @Nullable
    private Bundle f1227b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1228c;
    private Recreator.a d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private androidx.arch.core.b.b<String, b> f1226a = new androidx.arch.core.b.b<>();
    boolean e = true;

    /* JADX INFO: compiled from: caradapter-bug */
    public interface a {
        void a(@NonNull androidx.savedstate.b bVar);
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface b {
        @NonNull
        Bundle a();
    }

    SavedStateRegistry() {
    }

    @Nullable
    @MainThread
    public Bundle a(@NonNull String str) {
        if (!this.f1228c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f1227b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f1227b.remove(str);
        if (this.f1227b.isEmpty()) {
            this.f1227b = null;
        }
        return bundle2;
    }

    @MainThread
    void b(@NonNull Lifecycle lifecycle, @Nullable Bundle bundle) {
        if (this.f1228c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.f1227b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        lifecycle.a(new d() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // androidx.lifecycle.d
            public void f(f fVar, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_START) {
                    SavedStateRegistry.this.e = true;
                } else if (event == Lifecycle.Event.ON_STOP) {
                    SavedStateRegistry.this.e = false;
                }
            }
        });
        this.f1228c = true;
    }

    @MainThread
    void c(@NonNull Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f1227b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        androidx.arch.core.b.b<String, b>.d dVarD = this.f1226a.d();
        while (dVarD.hasNext()) {
            Map.Entry next = dVarD.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    @MainThread
    public void d(@NonNull String str, @NonNull b bVar) {
        if (this.f1226a.g(str, bVar) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    @MainThread
    public void e(@NonNull Class<? extends a> cls) {
        if (!this.e) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.d == null) {
            this.d = new Recreator.a(this);
        }
        try {
            cls.getDeclaredConstructor(new Class[0]);
            this.d.b(cls.getName());
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Class" + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e);
        }
    }
}

package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import java.util.Map;
import t0.d;
import t0.f;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bundle f1626b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1627c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public q.b<String, b> f1625a = new q.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1628d = true;

    public interface a {
        void a(x0.b bVar);
    }

    public interface b {
        Bundle a();
    }

    public Bundle a(String str) {
        if (!this.f1627c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f1626b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f1626b.remove(str);
        if (this.f1626b.isEmpty()) {
            this.f1626b = null;
        }
        return bundle2;
    }

    public void b(Lifecycle lifecycle, Bundle bundle) {
        if (this.f1627c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.f1626b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        lifecycle.a(new d() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // t0.d
            public void d(f fVar, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_START) {
                    SavedStateRegistry.this.f1628d = true;
                } else if (event == Lifecycle.Event.ON_STOP) {
                    SavedStateRegistry.this.f1628d = false;
                }
            }
        });
        this.f1627c = true;
    }

    public void c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f1626b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        q.b<String, b>.d dVarD = this.f1625a.d();
        while (dVarD.hasNext()) {
            Map.Entry next = dVarD.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}

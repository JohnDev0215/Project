package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import java.util.Map;
import x0.d;
import x0.f;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bundle f1766b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1767c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public t.b<String, b> f1765a = new t.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f1768d = true;

    public interface a {
        void a(g1.b bVar);
    }

    public interface b {
        Bundle saveState();
    }

    public Bundle a(String str) {
        if (!this.f1767c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f1766b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f1766b.remove(str);
        if (this.f1766b.isEmpty()) {
            this.f1766b = null;
        }
        return bundle2;
    }

    public void b(Lifecycle lifecycle, Bundle bundle) {
        if (this.f1767c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.f1766b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        lifecycle.a(new d() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // x0.d
            public void onStateChanged(f fVar, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_START) {
                    SavedStateRegistry.this.f1768d = true;
                } else if (event == Lifecycle.Event.ON_STOP) {
                    SavedStateRegistry.this.f1768d = false;
                }
            }
        });
        this.f1767c = true;
    }

    public void c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f1766b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        t.b<String, b>.d dVarD = this.f1765a.d();
        while (dVarD.hasNext()) {
            Map.Entry next = dVarD.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).saveState());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}

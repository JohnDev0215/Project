package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import b.l.d;
import b.l.f;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bundle f672b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f673c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.c.a.b.b<String, b> f671a = new b.c.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f674d = true;

    public interface a {
        void a(b.o.b bVar);
    }

    public interface b {
        Bundle a();
    }

    public Bundle a(String str) {
        if (!this.f673c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f672b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f672b.remove(str);
        if (this.f672b.isEmpty()) {
            this.f672b = null;
        }
        return bundle2;
    }

    public void b(Lifecycle lifecycle, Bundle bundle) {
        if (this.f673c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.f672b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        lifecycle.a(new d() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // b.l.d
            public void d(f fVar, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_START) {
                    SavedStateRegistry.this.f674d = true;
                } else if (event == Lifecycle.Event.ON_STOP) {
                    SavedStateRegistry.this.f674d = false;
                }
            }
        });
        this.f673c = true;
    }

    public void c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f672b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        b.c.a.b.b<String, b>.d dVarD = this.f671a.d();
        while (dVarD.hasNext()) {
            Map.Entry next = dVarD.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}

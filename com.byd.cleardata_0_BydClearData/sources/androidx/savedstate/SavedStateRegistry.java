package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.d;
import androidx.lifecycle.e;
import androidx.lifecycle.g;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bundle f1621b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f1622c;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private a.b.a.b.b<String, b> f1620a = new a.b.a.b.b<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    boolean f1623d = true;

    public interface a {
        void a(androidx.savedstate.b bVar);
    }

    public interface b {
        Bundle a();
    }

    SavedStateRegistry() {
    }

    public Bundle a(String str) {
        if (!this.f1622c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f1621b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f1621b.remove(str);
        if (this.f1621b.isEmpty()) {
            this.f1621b = null;
        }
        return bundle2;
    }

    void b(d dVar, Bundle bundle) {
        if (this.f1622c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.f1621b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        dVar.a(new e() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // androidx.lifecycle.e
            public void d(g gVar, d.a aVar) {
                SavedStateRegistry savedStateRegistry;
                boolean z;
                if (aVar == d.a.ON_START) {
                    savedStateRegistry = SavedStateRegistry.this;
                    z = true;
                } else {
                    if (aVar != d.a.ON_STOP) {
                        return;
                    }
                    savedStateRegistry = SavedStateRegistry.this;
                    z = false;
                }
                savedStateRegistry.f1623d = z;
            }
        });
        this.f1622c = true;
    }

    void c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f1621b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        a.b.a.b.b<String, b>.d dVarD = this.f1620a.d();
        while (dVarD.hasNext()) {
            Map.Entry next = dVarD.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }
}

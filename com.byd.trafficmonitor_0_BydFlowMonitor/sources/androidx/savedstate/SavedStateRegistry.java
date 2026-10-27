package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.Recreator;
import b.n.e;
import b.n.g;
import b.t.c;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Bundle f1307b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public boolean f1308c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public Recreator.a f1309d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public b.c.a.b.b<String, b> f1306a = new b.c.a.b.b<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public boolean f1310e = true;

    public interface a {
        void a(c cVar);
    }

    public interface b {
        Bundle a();
    }

    public Bundle a(String str) {
        if (!this.f1308c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f1307b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f1307b.remove(str);
        if (this.f1307b.isEmpty()) {
            this.f1307b = null;
        }
        return bundle2;
    }

    public void b(Lifecycle lifecycle, Bundle bundle) {
        if (this.f1308c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.f1307b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        lifecycle.a(new e() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // b.n.e
            public void d(g gVar, Lifecycle.Event event) {
                if (event == Lifecycle.Event.ON_START) {
                    SavedStateRegistry.this.f1310e = true;
                } else if (event == Lifecycle.Event.ON_STOP) {
                    SavedStateRegistry.this.f1310e = false;
                }
            }
        });
        this.f1308c = true;
    }

    public void c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f1307b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        b.c.a.b.b<String, b>.d dVarD = this.f1306a.d();
        while (dVarD.hasNext()) {
            Map.Entry next = dVarD.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public void d(String str, b bVar) {
        if (this.f1306a.g(str, bVar) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public void e(Class<? extends a> cls) {
        if (!this.f1310e) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.f1309d == null) {
            this.f1309d = new Recreator.a(this);
        }
        try {
            cls.getDeclaredConstructor(new Class[0]);
            this.f1309d.b(cls.getName());
        } catch (NoSuchMethodException e2) {
            throw new IllegalArgumentException("Class" + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
        }
    }
}

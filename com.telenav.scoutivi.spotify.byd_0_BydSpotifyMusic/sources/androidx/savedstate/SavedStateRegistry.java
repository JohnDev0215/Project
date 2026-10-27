package androidx.savedstate;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.lifecycle.i;
import androidx.lifecycle.m;
import androidx.lifecycle.o;
import androidx.savedstate.Recreator;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class SavedStateRegistry {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private Bundle f3196b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private boolean f3197c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Recreator.a f3198d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private l.b<String, b> f3195a = new l.b<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    boolean f3199e = true;

    public interface a {
        void a(c cVar);
    }

    public interface b {
        Bundle a();
    }

    SavedStateRegistry() {
    }

    public Bundle a(String str) {
        if (!this.f3197c) {
            throw new IllegalStateException("You can consumeRestoredStateForKey only after super.onCreate of corresponding component");
        }
        Bundle bundle = this.f3196b;
        if (bundle == null) {
            return null;
        }
        Bundle bundle2 = bundle.getBundle(str);
        this.f3196b.remove(str);
        if (this.f3196b.isEmpty()) {
            this.f3196b = null;
        }
        return bundle2;
    }

    void b(i iVar, Bundle bundle) {
        if (this.f3197c) {
            throw new IllegalStateException("SavedStateRegistry was already restored.");
        }
        if (bundle != null) {
            this.f3196b = bundle.getBundle("androidx.lifecycle.BundlableSavedStateRegistry.key");
        }
        iVar.a(new m() { // from class: androidx.savedstate.SavedStateRegistry.1
            @Override // androidx.lifecycle.m
            public void d(o oVar, i.b bVar) {
                if (bVar == i.b.ON_START) {
                    SavedStateRegistry.this.f3199e = true;
                } else if (bVar == i.b.ON_STOP) {
                    SavedStateRegistry.this.f3199e = false;
                }
            }
        });
        this.f3197c = true;
    }

    void c(Bundle bundle) {
        Bundle bundle2 = new Bundle();
        Bundle bundle3 = this.f3196b;
        if (bundle3 != null) {
            bundle2.putAll(bundle3);
        }
        l.b<String, b>.d dVarF = this.f3195a.f();
        while (dVarF.hasNext()) {
            Map.Entry next = dVarF.next();
            bundle2.putBundle((String) next.getKey(), ((b) next.getValue()).a());
        }
        bundle.putBundle("androidx.lifecycle.BundlableSavedStateRegistry.key", bundle2);
    }

    public void d(String str, b bVar) {
        if (this.f3195a.i(str, bVar) != null) {
            throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
        }
    }

    public void e(Class<? extends a> cls) {
        if (!this.f3199e) {
            throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
        }
        if (this.f3198d == null) {
            this.f3198d = new Recreator.a(this);
        }
        try {
            cls.getDeclaredConstructor(new Class[0]);
            this.f3198d.b(cls.getName());
        } catch (NoSuchMethodException e7) {
            throw new IllegalArgumentException("Class" + cls.getSimpleName() + " must have default constructor in order to be automatically recreated", e7);
        }
    }
}

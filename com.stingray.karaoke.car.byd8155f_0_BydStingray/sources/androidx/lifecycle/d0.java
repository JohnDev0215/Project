package androidx.lifecycle;

import android.app.Application;
import android.os.Bundle;
import b2.C0993c;
import d2.C1066d;
import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import r.C2257p;

/* JADX INFO: loaded from: classes.dex */
public final class d0 extends j0 implements h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final Application f11977a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final g0 f11978b;
    public final Bundle c;
    public final A9.b d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C2257p f11979e;

    public d0(Application application, t3.e eVar, Bundle bundle) {
        g0 g0Var;
        A7.m.f(eVar, "owner");
        this.f11979e = eVar.b();
        this.d = eVar.g();
        this.c = bundle;
        this.f11977a = application;
        if (application != null) {
            if (g0.c == null) {
                g0.c = new g0(application);
            }
            g0Var = g0.c;
            A7.m.c(g0Var);
        } else {
            g0Var = new g0(null);
        }
        this.f11978b = g0Var;
    }

    @Override // androidx.lifecycle.h0
    public final f0 a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName != null) {
            return e(cls, canonicalName);
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    @Override // androidx.lifecycle.h0
    public final f0 c(Class cls, C0993c c0993c) {
        C1066d c1066d = C1066d.f13337a;
        LinkedHashMap linkedHashMap = c0993c.f12203a;
        String str = (String) linkedHashMap.get(c1066d);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        if (linkedHashMap.get(a0.f11964a) == null || linkedHashMap.get(a0.f11965b) == null) {
            if (this.d != null) {
                return e(cls, str);
            }
            throw new IllegalStateException("SAVED_STATE_REGISTRY_OWNER_KEY andVIEW_MODEL_STORE_OWNER_KEY must be provided in the creation extras tosuccessfully create a ViewModel.");
        }
        Application application = (Application) linkedHashMap.get(g0.d);
        boolean zIsAssignableFrom = AbstractC0963a.class.isAssignableFrom(cls);
        Constructor constructorA = (!zIsAssignableFrom || application == null) ? e0.a(cls, e0.f11983b) : e0.a(cls, e0.f11982a);
        return constructorA == null ? this.f11978b.c(cls, c0993c) : (!zIsAssignableFrom || application == null) ? e0.b(cls, constructorA, a0.d(c0993c)) : e0.b(cls, constructorA, application, a0.d(c0993c));
    }

    @Override // androidx.lifecycle.j0
    public final void d(f0 f0Var) {
        A9.b bVar = this.d;
        if (bVar != null) {
            C2257p c2257p = this.f11979e;
            A7.m.c(c2257p);
            a0.a(f0Var, c2257p, bVar);
        }
    }

    public final f0 e(Class cls, String str) {
        A9.b bVar = this.d;
        if (bVar == null) {
            throw new UnsupportedOperationException("SavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        boolean zIsAssignableFrom = AbstractC0963a.class.isAssignableFrom(cls);
        Application application = this.f11977a;
        Constructor constructorA = (!zIsAssignableFrom || application == null) ? e0.a(cls, e0.f11983b) : e0.a(cls, e0.f11982a);
        if (constructorA == null) {
            if (application != null) {
                return this.f11978b.a(cls);
            }
            if (i0.f11993a == null) {
                i0.f11993a = new i0();
            }
            A7.m.c(i0.f11993a);
            return C7.a.D(cls);
        }
        C2257p c2257p = this.f11979e;
        A7.m.c(c2257p);
        Y yB = a0.b(c2257p, bVar, str, this.c);
        X x8 = yB.f11962m;
        f0 f0VarB = (!zIsAssignableFrom || application == null) ? e0.b(cls, constructorA, x8) : e0.b(cls, constructorA, application, x8);
        f0VarB.a("androidx.lifecycle.savedstate.vm.tag", yB);
        return f0VarB;
    }
}

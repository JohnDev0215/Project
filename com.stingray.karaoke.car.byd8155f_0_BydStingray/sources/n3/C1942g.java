package n3;

import androidx.lifecycle.Y;
import androidx.lifecycle.a0;
import androidx.lifecycle.f0;
import androidx.lifecycle.h0;
import androidx.lifecycle.j0;
import b2.C0993c;
import d2.C1066d;
import r.C2257p;

/* JADX INFO: renamed from: n3.g, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1942g extends j0 implements h0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public C2257p f19860a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public A9.b f19861b;

    @Override // androidx.lifecycle.h0
    public final f0 a(Class cls) {
        String canonicalName = cls.getCanonicalName();
        if (canonicalName == null) {
            throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
        }
        if (this.f19861b == null) {
            throw new UnsupportedOperationException("AbstractSavedStateViewModelFactory constructed with empty constructor supports only calls to create(modelClass: Class<T>, extras: CreationExtras).");
        }
        C2257p c2257p = this.f19860a;
        A7.m.c(c2257p);
        A9.b bVar = this.f19861b;
        A7.m.c(bVar);
        Y yB = a0.b(c2257p, bVar, canonicalName, null);
        C1943h c1943h = new C1943h(yB.f11962m);
        c1943h.a("androidx.lifecycle.savedstate.vm.tag", yB);
        return c1943h;
    }

    @Override // androidx.lifecycle.h0
    public final f0 c(Class cls, C0993c c0993c) {
        String str = (String) c0993c.f12203a.get(C1066d.f13337a);
        if (str == null) {
            throw new IllegalStateException("VIEW_MODEL_KEY must always be provided by ViewModelProvider");
        }
        C2257p c2257p = this.f19860a;
        if (c2257p == null) {
            return new C1943h(a0.d(c0993c));
        }
        A7.m.c(c2257p);
        A9.b bVar = this.f19861b;
        A7.m.c(bVar);
        Y yB = a0.b(c2257p, bVar, str, null);
        C1943h c1943h = new C1943h(yB.f11962m);
        c1943h.a("androidx.lifecycle.savedstate.vm.tag", yB);
        return c1943h;
    }

    @Override // androidx.lifecycle.j0
    public final void d(f0 f0Var) {
        C2257p c2257p = this.f19860a;
        if (c2257p != null) {
            A9.b bVar = this.f19861b;
            A7.m.c(bVar);
            a0.a(f0Var, c2257p, bVar);
        }
    }
}

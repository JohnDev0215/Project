package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.i;
import androidx.savedstate.SavedStateRegistry;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class SavedStateHandleController implements m {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f2522e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2523g = false;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final y f2524h;

    static final class a implements SavedStateRegistry.a {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.a
        public void a(androidx.savedstate.c cVar) {
            if (!(cVar instanceof e0)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
            }
            d0 d0VarK = ((e0) cVar).k();
            SavedStateRegistry savedStateRegistryD = cVar.d();
            Iterator<String> it = d0VarK.c().iterator();
            while (it.hasNext()) {
                SavedStateHandleController.h(d0VarK.b(it.next()), savedStateRegistryD, cVar.a());
            }
            if (d0VarK.c().isEmpty()) {
                return;
            }
            savedStateRegistryD.e(a.class);
        }
    }

    SavedStateHandleController(String str, y yVar) {
        this.f2522e = str;
        this.f2524h = yVar;
    }

    static void h(a0 a0Var, SavedStateRegistry savedStateRegistry, i iVar) {
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) a0Var.getTag("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.l()) {
            return;
        }
        savedStateHandleController.i(savedStateRegistry, iVar);
        m(savedStateRegistry, iVar);
    }

    static SavedStateHandleController j(SavedStateRegistry savedStateRegistry, i iVar, String str, Bundle bundle) {
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, y.a(savedStateRegistry.a(str), bundle));
        savedStateHandleController.i(savedStateRegistry, iVar);
        m(savedStateRegistry, iVar);
        return savedStateHandleController;
    }

    private static void m(final SavedStateRegistry savedStateRegistry, final i iVar) {
        i.c cVarB = iVar.b();
        if (cVarB == i.c.INITIALIZED || cVarB.isAtLeast(i.c.STARTED)) {
            savedStateRegistry.e(a.class);
        } else {
            iVar.a(new m() { // from class: androidx.lifecycle.SavedStateHandleController.1
                @Override // androidx.lifecycle.m
                public void d(o oVar, i.b bVar) {
                    if (bVar == i.b.ON_START) {
                        iVar.c(this);
                        savedStateRegistry.e(a.class);
                    }
                }
            });
        }
    }

    @Override // androidx.lifecycle.m
    public void d(o oVar, i.b bVar) {
        if (bVar == i.b.ON_DESTROY) {
            this.f2523g = false;
            oVar.a().c(this);
        }
    }

    void i(SavedStateRegistry savedStateRegistry, i iVar) {
        if (this.f2523g) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.f2523g = true;
        iVar.a(this);
        savedStateRegistry.d(this.f2522e, this.f2524h.e());
    }

    y k() {
        return this.f2524h;
    }

    boolean l() {
        return this.f2523g;
    }
}

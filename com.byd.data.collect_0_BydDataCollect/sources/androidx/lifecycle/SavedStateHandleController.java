package androidx.lifecycle;

import androidx.lifecycle.d;
import androidx.savedstate.SavedStateRegistry;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
final class SavedStateHandleController implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private boolean f1590a;

    static final class a implements SavedStateRegistry.a {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.a
        public void a(androidx.savedstate.b bVar) {
            if (!(bVar instanceof s)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
            }
            r rVarJ = ((s) bVar).j();
            SavedStateRegistry savedStateRegistryE = bVar.e();
            Iterator<String> it = rVarJ.c().iterator();
            while (it.hasNext()) {
                SavedStateHandleController.h(rVarJ.b(it.next()), savedStateRegistryE, bVar.a());
            }
            if (rVarJ.c().isEmpty()) {
                return;
            }
            savedStateRegistryE.e(a.class);
        }
    }

    static void h(p pVar, SavedStateRegistry savedStateRegistry, d dVar) {
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) pVar.c("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.j()) {
            return;
        }
        savedStateHandleController.i(savedStateRegistry, dVar);
        k(savedStateRegistry, dVar);
    }

    private static void k(final SavedStateRegistry savedStateRegistry, final d dVar) {
        d.b bVarB = dVar.b();
        if (bVarB == d.b.INITIALIZED || bVarB.a(d.b.STARTED)) {
            savedStateRegistry.e(a.class);
        } else {
            dVar.a(new e() { // from class: androidx.lifecycle.SavedStateHandleController.1
                @Override // androidx.lifecycle.e
                public void d(g gVar, d.a aVar) {
                    if (aVar == d.a.ON_START) {
                        dVar.c(this);
                        savedStateRegistry.e(a.class);
                    }
                }
            });
        }
    }

    @Override // androidx.lifecycle.e
    public void d(g gVar, d.a aVar) {
        if (aVar == d.a.ON_DESTROY) {
            this.f1590a = false;
            gVar.a().c(this);
        }
    }

    void i(SavedStateRegistry savedStateRegistry, d dVar) {
        if (this.f1590a) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.f1590a = true;
        dVar.a(this);
        throw null;
    }

    boolean j() {
        return this.f1590a;
    }
}

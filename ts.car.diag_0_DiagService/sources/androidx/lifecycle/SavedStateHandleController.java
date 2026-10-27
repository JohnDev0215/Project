package androidx.lifecycle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import java.util.Iterator;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
final class SavedStateHandleController implements d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f760c;
    private boolean d;
    private final m e;

    /* JADX INFO: compiled from: caradapter-bug */
    static final class a implements SavedStateRegistry.a {
        a() {
        }

        @Override // androidx.savedstate.SavedStateRegistry.a
        public void a(@NonNull androidx.savedstate.b bVar) {
            if (!(bVar instanceof q)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
            }
            p pVarH = ((q) bVar).h();
            SavedStateRegistry savedStateRegistryC = bVar.c();
            Iterator<String> it = pVarH.c().iterator();
            while (it.hasNext()) {
                SavedStateHandleController.a(pVarH.b(it.next()), savedStateRegistryC, bVar.a());
            }
            if (pVarH.c().isEmpty()) {
                return;
            }
            savedStateRegistryC.e(a.class);
        }
    }

    static void a(n nVar, SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) nVar.c("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.g()) {
            return;
        }
        savedStateHandleController.c(savedStateRegistry, lifecycle);
        k(savedStateRegistry, lifecycle);
    }

    private static void k(final SavedStateRegistry savedStateRegistry, final Lifecycle lifecycle) {
        Lifecycle.State stateB = lifecycle.b();
        if (stateB == Lifecycle.State.INITIALIZED || stateB.isAtLeast(Lifecycle.State.STARTED)) {
            savedStateRegistry.e(a.class);
        } else {
            lifecycle.a(new d() { // from class: androidx.lifecycle.SavedStateHandleController.1
                @Override // androidx.lifecycle.d
                public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_START) {
                        lifecycle.c(this);
                        savedStateRegistry.e(a.class);
                    }
                }
            });
        }
    }

    void c(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        if (this.d) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.d = true;
        lifecycle.a(this);
        savedStateRegistry.d(this.f760c, this.e.a());
    }

    @Override // androidx.lifecycle.d
    public void f(@NonNull f fVar, @NonNull Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.d = false;
            fVar.a().c(this);
        }
    }

    boolean g() {
        return this.d;
    }
}

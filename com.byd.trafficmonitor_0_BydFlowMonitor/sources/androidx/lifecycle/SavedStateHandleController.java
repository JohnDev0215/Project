package androidx.lifecycle;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle;
import androidx.savedstate.SavedStateRegistry;
import b.n.e;
import b.n.g;
import b.n.o;
import b.n.q;
import b.n.s;
import b.n.t;
import b.t.c;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class SavedStateHandleController implements e {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f1038a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f1039b = false;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final o f1040c;

    public static final class a implements SavedStateRegistry.a {
        @Override // androidx.savedstate.SavedStateRegistry.a
        public void a(c cVar) {
            if (!(cVar instanceof t)) {
                throw new IllegalStateException("Internal error: OnRecreation should be registered only on componentsthat implement ViewModelStoreOwner");
            }
            s viewModelStore = ((t) cVar).getViewModelStore();
            SavedStateRegistry savedStateRegistry = cVar.getSavedStateRegistry();
            Iterator<String> it = viewModelStore.c().iterator();
            while (it.hasNext()) {
                SavedStateHandleController.h(viewModelStore.b(it.next()), savedStateRegistry, cVar.getLifecycle());
            }
            if (viewModelStore.c().isEmpty()) {
                return;
            }
            savedStateRegistry.e(a.class);
        }
    }

    public SavedStateHandleController(String str, o oVar) {
        this.f1038a = str;
        this.f1040c = oVar;
    }

    public static void h(q qVar, SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        SavedStateHandleController savedStateHandleController = (SavedStateHandleController) qVar.c("androidx.lifecycle.savedstate.vm.tag");
        if (savedStateHandleController == null || savedStateHandleController.l()) {
            return;
        }
        savedStateHandleController.i(savedStateRegistry, lifecycle);
        m(savedStateRegistry, lifecycle);
    }

    public static SavedStateHandleController j(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle, String str, Bundle bundle) {
        SavedStateHandleController savedStateHandleController = new SavedStateHandleController(str, o.a(savedStateRegistry.a(str), bundle));
        savedStateHandleController.i(savedStateRegistry, lifecycle);
        m(savedStateRegistry, lifecycle);
        return savedStateHandleController;
    }

    public static void m(final SavedStateRegistry savedStateRegistry, final Lifecycle lifecycle) {
        Lifecycle.State stateB = lifecycle.b();
        if (stateB == Lifecycle.State.INITIALIZED || stateB.isAtLeast(Lifecycle.State.STARTED)) {
            savedStateRegistry.e(a.class);
        } else {
            lifecycle.a(new e() { // from class: androidx.lifecycle.SavedStateHandleController.1
                @Override // b.n.e
                public void d(g gVar, Lifecycle.Event event) {
                    if (event == Lifecycle.Event.ON_START) {
                        lifecycle.c(this);
                        savedStateRegistry.e(a.class);
                    }
                }
            });
        }
    }

    @Override // b.n.e
    public void d(g gVar, Lifecycle.Event event) {
        if (event == Lifecycle.Event.ON_DESTROY) {
            this.f1039b = false;
            gVar.getLifecycle().c(this);
        }
    }

    public void i(SavedStateRegistry savedStateRegistry, Lifecycle lifecycle) {
        if (this.f1039b) {
            throw new IllegalStateException("Already attached to lifecycleOwner");
        }
        this.f1039b = true;
        lifecycle.a(this);
        savedStateRegistry.d(this.f1038a, this.f1040c.b());
    }

    public o k() {
        return this.f1040c;
    }

    public boolean l() {
        return this.f1039b;
    }
}

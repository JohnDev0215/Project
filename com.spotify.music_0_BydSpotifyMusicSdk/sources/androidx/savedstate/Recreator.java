package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.Lifecycle$Event;
import androidx.lifecycle.SavedStateHandleController;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import p.iu2;
import p.l91;
import p.lu2;
import p.mu2;
import p.p91;
import p.s91;
import p.t62;
import p.u4;
import p.v03;
import p.v62;
import p.x62;

/* JADX INFO: loaded from: classes.dex */
public final class Recreator implements p91 {
    public final x62 c;

    public Recreator(x62 x62Var) {
        v03.j(x62Var, "owner");
        this.c = x62Var;
    }

    @Override // p.p91
    public final void d(s91 s91Var, Lifecycle$Event lifecycle$Event) {
        Object obj;
        boolean z;
        if (lifecycle$Event != Lifecycle$Event.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        s91Var.l().c(this);
        Bundle bundleA = this.c.a().a("androidx.savedstate.Restarter");
        if (bundleA == null) {
            return;
        }
        ArrayList<String> stringArrayList = bundleA.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            try {
                Class<? extends U> clsAsSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(t62.class);
                v03.i(clsAsSubclass, "{\n                Class.…class.java)\n            }");
                try {
                    Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    try {
                        Object objNewInstance = declaredConstructor.newInstance(new Object[0]);
                        v03.i(objNewInstance, "{\n                constr…wInstance()\n            }");
                        x62 x62Var = this.c;
                        v03.j(x62Var, "owner");
                        if (!(x62Var instanceof mu2)) {
                            throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner".toString());
                        }
                        lu2 lu2VarJ = ((mu2) x62Var).j();
                        v62 v62VarA = x62Var.a();
                        lu2VarJ.getClass();
                        for (String str2 : new HashSet(lu2VarJ.a.keySet())) {
                            v03.j(str2, "key");
                            iu2 iu2Var = (iu2) lu2VarJ.a.get(str2);
                            v03.h(iu2Var);
                            l91 l91VarL = x62Var.l();
                            v03.j(v62VarA, "registry");
                            v03.j(l91VarL, "lifecycle");
                            HashMap map = iu2Var.a;
                            if (map == null) {
                                obj = null;
                            } else {
                                synchronized (map) {
                                    obj = iu2Var.a.get("androidx.lifecycle.savedstate.vm.tag");
                                }
                            }
                            SavedStateHandleController savedStateHandleController = (SavedStateHandleController) obj;
                            if (savedStateHandleController != null && !(z = savedStateHandleController.c)) {
                                if (!(!z)) {
                                    throw new IllegalStateException("Already attached to lifecycleOwner".toString());
                                }
                                savedStateHandleController.c = true;
                                l91VarL.a(savedStateHandleController);
                                throw null;
                            }
                        }
                        if (!new HashSet(lu2VarJ.a.keySet()).isEmpty()) {
                            v62VarA.c();
                        }
                    } catch (Exception e) {
                        throw new RuntimeException(u4.x("Failed to instantiate ", str), e);
                    }
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
                }
            } catch (ClassNotFoundException e3) {
                throw new RuntimeException(u4.j("Class ", str, " wasn't found"), e3);
            }
        }
    }
}

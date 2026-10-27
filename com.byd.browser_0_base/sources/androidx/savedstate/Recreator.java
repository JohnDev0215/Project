package androidx.savedstate;

import android.os.Bundle;
import androidx.lifecycle.d;
import androidx.lifecycle.f;
import androidx.savedstate.a;
import defpackage.C0175b;
import defpackage.b71;
import defpackage.ge2;
import defpackage.gq2;
import defpackage.i21;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/* JADX INFO: compiled from: Recreator.kt */
/* JADX INFO: loaded from: classes.dex */
public final class Recreator implements f {
    public final ge2 e;

    public Recreator(ge2 ge2Var) {
        i21.e(ge2Var, "owner");
        this.e = ge2Var;
    }

    @Override // androidx.lifecycle.f
    public final void a(b71 b71Var, d.a aVar) {
        if (aVar != d.a.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        b71Var.j1().b(this);
        ge2 ge2Var = this.e;
        Bundle bundleA = ge2Var.s0().a("androidx.savedstate.Restarter");
        if (bundleA == null) {
            return;
        }
        ArrayList<String> stringArrayList = bundleA.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            try {
                Class<? extends U> clsAsSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(a.InterfaceC0027a.class);
                i21.d(clsAsSubclass, "{\n                Class.…class.java)\n            }");
                try {
                    Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    try {
                        Object objNewInstance = declaredConstructor.newInstance(new Object[0]);
                        i21.d(objNewInstance, "{\n                constr…wInstance()\n            }");
                        ((a.InterfaceC0027a) objNewInstance).a(ge2Var);
                    } catch (Exception e) {
                        throw new RuntimeException(C0175b.j("Failed to instantiate ", str), e);
                    }
                } catch (NoSuchMethodException e2) {
                    throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e2);
                }
            } catch (ClassNotFoundException e3) {
                throw new RuntimeException(gq2.g("Class ", str, " wasn't found"), e3);
            }
        }
    }
}

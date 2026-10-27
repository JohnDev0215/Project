package t3;

import A7.m;
import android.os.Bundle;
import androidx.lifecycle.A;
import androidx.lifecycle.EnumC0980s;
import androidx.lifecycle.InterfaceC0976n;
import androidx.lifecycle.InterfaceC0986y;
import androidx.lifecycle.a0;
import androidx.lifecycle.b0;
import androidx.lifecycle.f0;
import androidx.lifecycle.k0;
import androidx.lifecycle.l0;
import h.C1337i;
import h.n;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import r.C2257p;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC0986y {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f22456m;

    public /* synthetic */ b(Object obj, int i7) {
        this.l = i7;
        this.f22456m = obj;
    }

    @Override // androidx.lifecycle.InterfaceC0986y
    public final void b(A a10, EnumC0980s enumC0980s) {
        switch (this.l) {
            case 0:
                if (enumC0980s != EnumC0980s.ON_CREATE) {
                    throw new AssertionError("Next event must be ON_CREATE");
                }
                a10.g().V0(this);
                e eVar = (e) this.f22456m;
                Bundle bundleB = eVar.b().b("androidx.savedstate.Restarter");
                if (bundleB == null) {
                    return;
                }
                ArrayList<String> stringArrayList = bundleB.getStringArrayList("classes_to_restore");
                if (stringArrayList == null) {
                    throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
                }
                for (String str : stringArrayList) {
                    try {
                        Class<? extends U> clsAsSubclass = Class.forName(str, false, b.class.getClassLoader()).asSubclass(c.class);
                        m.e(clsAsSubclass, "{\n                Class.…class.java)\n            }");
                        try {
                            Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(null);
                            declaredConstructor.setAccessible(true);
                            try {
                                Object objNewInstance = declaredConstructor.newInstance(null);
                                m.e(objNewInstance, "{\n                constr…wInstance()\n            }");
                                if (!(eVar instanceof l0)) {
                                    throw new IllegalStateException("Internal error: OnRecreation should be registered only on components that implement ViewModelStoreOwner");
                                }
                                k0 k0VarF = ((l0) eVar).f();
                                C2257p c2257pB = eVar.b();
                                k0VarF.getClass();
                                LinkedHashMap linkedHashMap = k0VarF.f11997a;
                                for (String str2 : new HashSet(linkedHashMap.keySet())) {
                                    m.f(str2, "key");
                                    f0 f0Var = (f0) linkedHashMap.get(str2);
                                    m.c(f0Var);
                                    a0.a(f0Var, c2257pB, eVar.g());
                                }
                                if (!new HashSet(linkedHashMap.keySet()).isEmpty()) {
                                    c2257pB.e();
                                }
                            } catch (Exception e10) {
                                throw new RuntimeException(M5.b.g("Failed to instantiate ", str), e10);
                            }
                        } catch (NoSuchMethodException e11) {
                            throw new IllegalStateException("Class " + clsAsSubclass.getSimpleName() + " must have default constructor in order to be automatically recreated", e11);
                        }
                    } catch (ClassNotFoundException e12) {
                        throw new RuntimeException(android.hardware.bydauto.ac.tsimpl.a.i("Class ", str, " wasn't found"), e12);
                    }
                }
                return;
            case 1:
                new HashMap();
                InterfaceC0976n[] interfaceC0976nArr = (InterfaceC0976n[]) this.f22456m;
                if (interfaceC0976nArr.length > 0) {
                    InterfaceC0976n interfaceC0976n = interfaceC0976nArr[0];
                    throw null;
                }
                if (interfaceC0976nArr.length <= 0) {
                    return;
                }
                InterfaceC0976n interfaceC0976n2 = interfaceC0976nArr[0];
                throw null;
            case 2:
                if (enumC0980s != EnumC0980s.ON_CREATE) {
                    throw new IllegalStateException(("Next event must be ON_CREATE, it was " + enumC0980s).toString());
                }
                a10.g().V0(this);
                ((b0) this.f22456m).b();
                return;
            default:
                n nVar = (n) this.f22456m;
                if (nVar.f15118p == null) {
                    C1337i c1337i = (C1337i) nVar.getLastNonConfigurationInstance();
                    if (c1337i != null) {
                        nVar.f15118p = c1337i.f15099a;
                    }
                    if (nVar.f15118p == null) {
                        nVar.f15118p = new k0();
                    }
                }
                nVar.l.V0(this);
                return;
        }
    }
}

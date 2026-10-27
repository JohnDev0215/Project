package androidx.savedstate;

import a.n.g;
import a.n.i;
import a.n.k;
import a.n.l;
import a.q.a;
import a.q.c;
import android.annotation.SuppressLint;
import android.os.Bundle;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
@SuppressLint({"RestrictedApi"})
public final class Recreator implements i {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final c f1722a;

    public static final class a implements a.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Set<String> f1723a = new HashSet();

        public a(a.q.a aVar) {
            if (aVar.f1270a.f("androidx.savedstate.Restarter", this) != null) {
                throw new IllegalArgumentException("SavedStateProvider with the given key is already registered");
            }
        }

        @Override // a.q.a.b
        public Bundle a() {
            Bundle bundle = new Bundle();
            bundle.putStringArrayList("classes_to_restore", new ArrayList<>(this.f1723a));
            return bundle;
        }
    }

    public Recreator(c cVar) {
        this.f1722a = cVar;
    }

    @Override // a.n.i
    public void g(k kVar, g.a aVar) {
        if (aVar != g.a.ON_CREATE) {
            throw new AssertionError("Next event must be ON_CREATE");
        }
        ((l) kVar.a()).f1093a.g(this);
        Bundle bundleA = this.f1722a.d().a("androidx.savedstate.Restarter");
        if (bundleA == null) {
            return;
        }
        ArrayList<String> stringArrayList = bundleA.getStringArrayList("classes_to_restore");
        if (stringArrayList == null) {
            throw new IllegalStateException("Bundle with restored state for the component \"androidx.savedstate.Restarter\" must contain list of strings by the key \"classes_to_restore\"");
        }
        for (String str : stringArrayList) {
            try {
                Class<? extends U> clsAsSubclass = Class.forName(str, false, Recreator.class.getClassLoader()).asSubclass(a.InterfaceC0026a.class);
                try {
                    Constructor declaredConstructor = clsAsSubclass.getDeclaredConstructor(new Class[0]);
                    declaredConstructor.setAccessible(true);
                    try {
                        ((a.InterfaceC0026a) declaredConstructor.newInstance(new Object[0])).a(this.f1722a);
                    } catch (Exception e2) {
                        throw new RuntimeException(b.a.a.a.a.c("Failed to instantiate ", str), e2);
                    }
                } catch (NoSuchMethodException e3) {
                    StringBuilder sbD = b.a.a.a.a.d("Class");
                    sbD.append(clsAsSubclass.getSimpleName());
                    sbD.append(" must have default constructor in order to be automatically recreated");
                    throw new IllegalStateException(sbD.toString(), e3);
                }
            } catch (ClassNotFoundException e4) {
                throw new RuntimeException("Class " + str + " wasn't found", e4);
            }
        }
    }
}

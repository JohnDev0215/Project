package androidx.lifecycle;

import S8.AbstractC0727y;
import android.os.Bundle;
import android.view.View;
import b2.AbstractC0992b;
import b2.C0991a;
import b2.C0993c;
import com.stingray.drivemodedetector.R;
import d2.C1063a;
import d2.C1066d;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import q7.C2195i;
import q7.InterfaceC2194h;
import r.C2257p;

/* JADX INFO: loaded from: classes.dex */
public abstract class a0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Y4.e f11964a = new Y4.e(16);

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final io.sentry.hints.i f11965b = new io.sentry.hints.i(16);
    public static final C8.d c = new C8.d(16);
    public static final C1066d d = new C1066d();

    public static final void a(f0 f0Var, C2257p c2257p, A9.b bVar) {
        A7.m.f(c2257p, "registry");
        A7.m.f(bVar, "lifecycle");
        Y y10 = (Y) f0Var.c("androidx.lifecycle.savedstate.vm.tag");
        if (y10 == null || y10.f11963n) {
            return;
        }
        y10.g(bVar, c2257p);
        l(bVar, c2257p);
    }

    public static final Y b(C2257p c2257p, A9.b bVar, String str, Bundle bundle) {
        A7.m.f(c2257p, "registry");
        A7.m.f(bVar, "lifecycle");
        Bundle bundleB = c2257p.b(str);
        Class[] clsArr = X.f11958f;
        Y y10 = new Y(str, c(bundleB, bundle));
        y10.g(bVar, c2257p);
        l(bVar, c2257p);
        return y10;
    }

    public static X c(Bundle bundle, Bundle bundle2) {
        if (bundle == null) {
            if (bundle2 == null) {
                return new X();
            }
            HashMap map = new HashMap();
            for (String str : bundle2.keySet()) {
                A7.m.e(str, "key");
                map.put(str, bundle2.get(str));
            }
            return new X(map);
        }
        ClassLoader classLoader = X.class.getClassLoader();
        A7.m.c(classLoader);
        bundle.setClassLoader(classLoader);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList("keys");
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList("values");
        if (parcelableArrayList == null || parcelableArrayList2 == null || parcelableArrayList.size() != parcelableArrayList2.size()) {
            throw new IllegalStateException("Invalid bundle passed as restored state");
        }
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int size = parcelableArrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            Object obj = parcelableArrayList.get(i7);
            A7.m.d(obj, "null cannot be cast to non-null type kotlin.String");
            linkedHashMap.put((String) obj, parcelableArrayList2.get(i7));
        }
        return new X(linkedHashMap);
    }

    public static final X d(C0993c c0993c) {
        Y4.e eVar = f11964a;
        LinkedHashMap linkedHashMap = c0993c.f12203a;
        t3.e eVar2 = (t3.e) linkedHashMap.get(eVar);
        if (eVar2 == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `SAVED_STATE_REGISTRY_OWNER_KEY`");
        }
        l0 l0Var = (l0) linkedHashMap.get(f11965b);
        if (l0Var == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_STORE_OWNER_KEY`");
        }
        Bundle bundle = (Bundle) linkedHashMap.get(c);
        String str = (String) linkedHashMap.get(C1066d.f13337a);
        if (str == null) {
            throw new IllegalArgumentException("CreationExtras must have a value by `VIEW_MODEL_KEY`");
        }
        t3.d dVarC = eVar2.b().c();
        b0 b0Var = dVarC instanceof b0 ? (b0) dVarC : null;
        if (b0Var == null) {
            throw new IllegalStateException("enableSavedStateHandles() wasn't called prior to createSavedStateHandle() call");
        }
        LinkedHashMap linkedHashMap2 = h(l0Var).f11971b;
        X x8 = (X) linkedHashMap2.get(str);
        if (x8 != null) {
            return x8;
        }
        Class[] clsArr = X.f11958f;
        b0Var.b();
        Bundle bundle2 = b0Var.c;
        Bundle bundle3 = bundle2 != null ? bundle2.getBundle(str) : null;
        Bundle bundle4 = b0Var.c;
        if (bundle4 != null) {
            bundle4.remove(str);
        }
        Bundle bundle5 = b0Var.c;
        if (bundle5 != null && bundle5.isEmpty()) {
            b0Var.c = null;
        }
        X xC = c(bundle3, bundle);
        linkedHashMap2.put(str, xC);
        return xC;
    }

    public static final void e(t3.e eVar) {
        EnumC0981t enumC0981tO0 = eVar.g().O0();
        if (enumC0981tO0 != EnumC0981t.f12005m && enumC0981tO0 != EnumC0981t.f12006n) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        if (eVar.b().c() == null) {
            b0 b0Var = new b0(eVar.b(), (l0) eVar);
            eVar.b().d("androidx.lifecycle.internal.SavedStateHandlesProvider", b0Var);
            eVar.g().M0(new t3.b(b0Var, 2));
        }
    }

    public static final A f(View view) {
        A7.m.f(view, "<this>");
        return (A) P8.m.b0(P8.m.h0(P8.m.d0(view, m0.f12000n), m0.o));
    }

    public static final l0 g(View view) {
        A7.m.f(view, "<this>");
        return (l0) P8.m.b0(P8.m.h0(P8.m.d0(view, m0.f12001p), m0.f12002q));
    }

    public static final c0 h(l0 l0Var) {
        Z z6 = new Z();
        k0 k0VarF = l0Var.f();
        AbstractC0992b abstractC0992bE = l0Var instanceof InterfaceC0977o ? ((InterfaceC0977o) l0Var).e() : C0991a.f12202b;
        A7.m.f(k0VarF, "store");
        A7.m.f(abstractC0992bE, "defaultCreationExtras");
        return (c0) new l3.P(k0VarF, z6, abstractC0992bE).s(n7.z.l(c0.class), "androidx.lifecycle.internal.SavedStateHandlesVM");
    }

    public static final C1063a i(f0 f0Var) {
        C1063a c1063a;
        A7.m.f(f0Var, "<this>");
        synchronized (d) {
            c1063a = (C1063a) f0Var.c("androidx.lifecycle.viewmodel.internal.ViewModelCoroutineScope.JOB_KEY");
            if (c1063a == null) {
                InterfaceC2194h interfaceC2194h = C2195i.l;
                try {
                    Z8.e eVar = S8.E.f8084a;
                    interfaceC2194h = X8.m.f10573a.f8932q;
                } catch (IllegalStateException | m7.h unused) {
                }
                C1063a c1063a2 = new C1063a(interfaceC2194h.v(AbstractC0727y.c()));
                f0Var.a("androidx.lifecycle.viewmodel.internal.ViewModelCoroutineScope.JOB_KEY", c1063a2);
                c1063a = c1063a2;
            }
        }
        return c1063a;
    }

    public static final void j(View view, A a10) {
        A7.m.f(view, "<this>");
        view.setTag(R.id.view_tree_lifecycle_owner, a10);
    }

    public static final void k(View view, l0 l0Var) {
        A7.m.f(view, "<this>");
        view.setTag(R.id.view_tree_view_model_store_owner, l0Var);
    }

    public static void l(A9.b bVar, C2257p c2257p) {
        EnumC0981t enumC0981tO0 = bVar.O0();
        if (enumC0981tO0 == EnumC0981t.f12005m || enumC0981tO0.compareTo(EnumC0981t.o) >= 0) {
            c2257p.e();
        } else {
            bVar.M0(new C0973k(bVar, c2257p));
        }
    }
}

package n3;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import androidx.lifecycle.EnumC0981t;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.a0;
import androidx.lifecycle.d0;
import androidx.lifecycle.g0;
import androidx.lifecycle.h0;
import androidx.lifecycle.k0;
import androidx.lifecycle.l0;
import b2.AbstractC0992b;
import b2.C0993c;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Set;
import r.C2257p;

/* JADX INFO: renamed from: n3.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1945j implements androidx.lifecycle.A, l0, InterfaceC0977o, t3.e {
    public final Context l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public y f19865m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final Bundle f19866n;
    public EnumC0981t o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public final r f19867p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final String f19868q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final Bundle f19869r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final androidx.lifecycle.C f19870s = new androidx.lifecycle.C(this);

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final t4.e f19871t = new t4.e(this);

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public boolean f19872u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public EnumC0981t f19873v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final d0 f19874w;

    public C1945j(Context context, y yVar, Bundle bundle, EnumC0981t enumC0981t, r rVar, String str, Bundle bundle2) {
        this.l = context;
        this.f19865m = yVar;
        this.f19866n = bundle;
        this.o = enumC0981t;
        this.f19867p = rVar;
        this.f19868q = str;
        this.f19869r = bundle2;
        m7.n nVarO = h6.r.o(new C1944i(this, 0));
        h6.r.o(new C1944i(this, 1));
        this.f19873v = EnumC0981t.f12005m;
        this.f19874w = (d0) nVarO.getValue();
    }

    @Override // t3.e
    public final C2257p b() {
        return (C2257p) this.f19871t.o;
    }

    public final Bundle c() {
        Bundle bundle = this.f19866n;
        if (bundle == null) {
            return null;
        }
        return new Bundle(bundle);
    }

    @Override // androidx.lifecycle.InterfaceC0977o
    public final h0 d() {
        return this.f19874w;
    }

    @Override // androidx.lifecycle.InterfaceC0977o
    public final AbstractC0992b e() {
        C0993c c0993c = new C0993c();
        Context context = this.l;
        Context applicationContext = context != null ? context.getApplicationContext() : null;
        Application application = applicationContext instanceof Application ? (Application) applicationContext : null;
        LinkedHashMap linkedHashMap = c0993c.f12203a;
        if (application != null) {
            linkedHashMap.put(g0.d, application);
        }
        linkedHashMap.put(a0.f11964a, this);
        linkedHashMap.put(a0.f11965b, this);
        Bundle bundleC = c();
        if (bundleC != null) {
            linkedHashMap.put(a0.c, bundleC);
        }
        return c0993c;
    }

    public final boolean equals(Object obj) {
        Set<String> setKeySet;
        if (obj == null || !(obj instanceof C1945j)) {
            return false;
        }
        C1945j c1945j = (C1945j) obj;
        if (!A7.m.a(this.f19868q, c1945j.f19868q) || !A7.m.a(this.f19865m, c1945j.f19865m) || !A7.m.a(this.f19870s, c1945j.f19870s) || !A7.m.a((C2257p) this.f19871t.o, (C2257p) c1945j.f19871t.o)) {
            return false;
        }
        Bundle bundle = this.f19866n;
        Bundle bundle2 = c1945j.f19866n;
        if (!A7.m.a(bundle, bundle2)) {
            if (bundle == null || (setKeySet = bundle.keySet()) == null) {
                return false;
            }
            Set<String> set = setKeySet;
            if (!(set instanceof Collection) || !set.isEmpty()) {
                for (String str : set) {
                    if (!A7.m.a(bundle.get(str), bundle2 != null ? bundle2.get(str) : null)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    @Override // androidx.lifecycle.l0
    public final k0 f() {
        if (!this.f19872u) {
            throw new IllegalStateException("You cannot access the NavBackStackEntry's ViewModels until it is added to the NavController's back stack (i.e., the Lifecycle of the NavBackStackEntry reaches the CREATED state).");
        }
        if (this.f19870s.f11908p == EnumC0981t.l) {
            throw new IllegalStateException("You cannot access the NavBackStackEntry's ViewModels after the NavBackStackEntry is destroyed.");
        }
        r rVar = this.f19867p;
        if (rVar == null) {
            throw new IllegalStateException("You must call setViewModelStore() on your NavHostController before accessing the ViewModelStore of a navigation graph.");
        }
        String str = this.f19868q;
        A7.m.f(str, "backStackEntryId");
        LinkedHashMap linkedHashMap = rVar.f19890b;
        k0 k0Var = (k0) linkedHashMap.get(str);
        if (k0Var != null) {
            return k0Var;
        }
        k0 k0Var2 = new k0();
        linkedHashMap.put(str, k0Var2);
        return k0Var2;
    }

    @Override // androidx.lifecycle.A
    public final A9.b g() {
        return this.f19870s;
    }

    public final void h(EnumC0981t enumC0981t) {
        A7.m.f(enumC0981t, "maxState");
        this.f19873v = enumC0981t;
        i();
    }

    public final int hashCode() {
        Set<String> setKeySet;
        int iHashCode = this.f19865m.hashCode() + (this.f19868q.hashCode() * 31);
        Bundle bundle = this.f19866n;
        if (bundle != null && (setKeySet = bundle.keySet()) != null) {
            Iterator<T> it = setKeySet.iterator();
            while (it.hasNext()) {
                int i7 = iHashCode * 31;
                Object obj = bundle.get((String) it.next());
                iHashCode = i7 + (obj != null ? obj.hashCode() : 0);
            }
        }
        return ((C2257p) this.f19871t.o).hashCode() + ((this.f19870s.hashCode() + (iHashCode * 31)) * 31);
    }

    public final void i() {
        if (!this.f19872u) {
            t4.e eVar = this.f19871t;
            eVar.k();
            this.f19872u = true;
            if (this.f19867p != null) {
                a0.e(this);
            }
            eVar.l(this.f19869r);
        }
        int iOrdinal = this.o.ordinal();
        int iOrdinal2 = this.f19873v.ordinal();
        androidx.lifecycle.C c = this.f19870s;
        if (iOrdinal < iOrdinal2) {
            c.e1(this.o);
        } else {
            c.e1(this.f19873v);
        }
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(C1945j.class.getSimpleName());
        sb.append("(" + this.f19868q + ')');
        sb.append(" destination=");
        sb.append(this.f19865m);
        String string = sb.toString();
        A7.m.e(string, "sb.toString()");
        return string;
    }
}

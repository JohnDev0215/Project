package b.k.a;

import android.util.Log;
import androidx.fragment.app.Fragment;
import b.m.o;
import b.m.p;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends b.m.n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o.a f1250c = new a();
    public final boolean g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet<Fragment> f1251d = new HashSet<>();
    public final HashMap<String, i> e = new HashMap<>();
    public final HashMap<String, p> f = new HashMap<>();
    public boolean h = false;
    public boolean i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    public static class a implements o.a {
        @Override // b.m.o.a
        public <T extends b.m.n> T a(Class<T> cls) {
            return new i(true);
        }
    }

    public i(boolean z) {
        this.g = z;
    }

    public static i g(p pVar) {
        return (i) new o(pVar, f1250c).a(i.class);
    }

    @Override // b.m.n
    public void c() {
        if (h.f1223d) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.h = true;
    }

    public boolean d(Fragment fragment) {
        return this.f1251d.add(fragment);
    }

    public void e(Fragment fragment) {
        if (h.f1223d) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        i iVar = this.e.get(fragment.mWho);
        if (iVar != null) {
            iVar.c();
            this.e.remove(fragment.mWho);
        }
        p pVar = this.f.get(fragment.mWho);
        if (pVar != null) {
            pVar.a();
            this.f.remove(fragment.mWho);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || i.class != obj.getClass()) {
            return false;
        }
        i iVar = (i) obj;
        return this.f1251d.equals(iVar.f1251d) && this.e.equals(iVar.e) && this.f.equals(iVar.f);
    }

    public i f(Fragment fragment) {
        i iVar = this.e.get(fragment.mWho);
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this.g);
        this.e.put(fragment.mWho, iVar2);
        return iVar2;
    }

    public Collection<Fragment> h() {
        return this.f1251d;
    }

    public int hashCode() {
        return (((this.f1251d.hashCode() * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    public p i(Fragment fragment) {
        p pVar = this.f.get(fragment.mWho);
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p();
        this.f.put(fragment.mWho, pVar2);
        return pVar2;
    }

    public boolean j() {
        return this.h;
    }

    public boolean k(Fragment fragment) {
        return this.f1251d.remove(fragment);
    }

    public boolean l(Fragment fragment) {
        if (this.f1251d.contains(fragment)) {
            return this.g ? this.h : !this.i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1251d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f.keySet().iterator();
        while (it3.hasNext()) {
            sb.append(it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}

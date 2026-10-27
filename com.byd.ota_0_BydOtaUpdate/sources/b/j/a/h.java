package b.j.a;

import android.util.Log;
import androidx.fragment.app.Fragment;
import b.l.n;
import b.l.o;
import b.l.p;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class h extends n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o.a f1897c = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f1901g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet<Fragment> f1898d = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<String, h> f1899e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap<String, p> f1900f = new HashMap<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1902h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1903i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    public static class a implements o.a {
        @Override // b.l.o.a
        public <T extends n> T a(Class<T> cls) {
            return new h(true);
        }
    }

    public h(boolean z) {
        this.f1901g = z;
    }

    public static h g(p pVar) {
        return (h) new o(pVar, f1897c).a(h.class);
    }

    @Override // b.l.n
    public void c() {
        if (g.s) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f1902h = true;
    }

    public boolean d(Fragment fragment) {
        return this.f1898d.add(fragment);
    }

    public void e(Fragment fragment) {
        if (g.s) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        h hVar = this.f1899e.get(fragment.E3);
        if (hVar != null) {
            hVar.c();
            this.f1899e.remove(fragment.E3);
        }
        p pVar = this.f1900f.get(fragment.E3);
        if (pVar != null) {
            pVar.a();
            this.f1900f.remove(fragment.E3);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || h.class != obj.getClass()) {
            return false;
        }
        h hVar = (h) obj;
        return this.f1898d.equals(hVar.f1898d) && this.f1899e.equals(hVar.f1899e) && this.f1900f.equals(hVar.f1900f);
    }

    public h f(Fragment fragment) {
        h hVar = this.f1899e.get(fragment.E3);
        if (hVar != null) {
            return hVar;
        }
        h hVar2 = new h(this.f1901g);
        this.f1899e.put(fragment.E3, hVar2);
        return hVar2;
    }

    public Collection<Fragment> h() {
        return this.f1898d;
    }

    public int hashCode() {
        return (((this.f1898d.hashCode() * 31) + this.f1899e.hashCode()) * 31) + this.f1900f.hashCode();
    }

    public p i(Fragment fragment) {
        p pVar = this.f1900f.get(fragment.E3);
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p();
        this.f1900f.put(fragment.E3, pVar2);
        return pVar2;
    }

    public boolean j() {
        return this.f1902h;
    }

    public boolean k(Fragment fragment) {
        return this.f1898d.remove(fragment);
    }

    public boolean l(Fragment fragment) {
        if (this.f1898d.contains(fragment)) {
            return this.f1901g ? this.f1902h : !this.f1903i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1898d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f1899e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f1900f.keySet().iterator();
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

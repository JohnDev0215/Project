package r0;

import android.util.Log;
import androidx.fragment.app.Fragment;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import t0.o;
import t0.p;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends t0.n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o.a f4737c = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f4741g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet<Fragment> f4738d = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<String, i> f4739e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap<String, p> f4740f = new HashMap<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4742h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4743i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    public static class a implements o.a {
        @Override // t0.o.a
        public <T extends t0.n> T a(Class<T> cls) {
            return new i(true);
        }
    }

    public i(boolean z3) {
        this.f4741g = z3;
    }

    public static i g(p pVar) {
        return (i) new o(pVar, f4737c).a(i.class);
    }

    @Override // t0.n
    public void c() {
        if (h.f4686d) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f4742h = true;
    }

    public boolean d(Fragment fragment) {
        return this.f4738d.add(fragment);
    }

    public void e(Fragment fragment) {
        if (h.f4686d) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        i iVar = this.f4739e.get(fragment.mWho);
        if (iVar != null) {
            iVar.c();
            this.f4739e.remove(fragment.mWho);
        }
        p pVar = this.f4740f.get(fragment.mWho);
        if (pVar != null) {
            pVar.a();
            this.f4740f.remove(fragment.mWho);
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
        return this.f4738d.equals(iVar.f4738d) && this.f4739e.equals(iVar.f4739e) && this.f4740f.equals(iVar.f4740f);
    }

    public i f(Fragment fragment) {
        i iVar = this.f4739e.get(fragment.mWho);
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this.f4741g);
        this.f4739e.put(fragment.mWho, iVar2);
        return iVar2;
    }

    public Collection<Fragment> h() {
        return this.f4738d;
    }

    public int hashCode() {
        return (((this.f4738d.hashCode() * 31) + this.f4739e.hashCode()) * 31) + this.f4740f.hashCode();
    }

    public p i(Fragment fragment) {
        p pVar = this.f4740f.get(fragment.mWho);
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p();
        this.f4740f.put(fragment.mWho, pVar2);
        return pVar2;
    }

    public boolean j() {
        return this.f4742h;
    }

    public boolean k(Fragment fragment) {
        return this.f4738d.remove(fragment);
    }

    public boolean l(Fragment fragment) {
        if (this.f4738d.contains(fragment)) {
            return this.f4741g ? this.f4742h : !this.f4743i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f4738d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f4739e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f4740f.keySet().iterator();
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

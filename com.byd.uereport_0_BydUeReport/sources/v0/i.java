package v0;

import android.util.Log;
import androidx.fragment.app.Fragment;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import x0.o;
import x0.p;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class i extends x0.n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final o.a f8415c = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f8419g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashSet<Fragment> f8416d = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<String, i> f8417e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap<String, p> f8418f = new HashMap<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f8420h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f8421i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    public static class a implements o.a {
        @Override // x0.o.a
        public <T extends x0.n> T a(Class<T> cls) {
            return new i(true);
        }
    }

    public i(boolean z7) {
        this.f8419g = z7;
    }

    public static i g(p pVar) {
        return (i) new o(pVar, f8415c).a(i.class);
    }

    @Override // x0.n
    public void c() {
        if (h.f8366f) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f8420h = true;
    }

    public boolean d(Fragment fragment) {
        return this.f8416d.add(fragment);
    }

    public void e(Fragment fragment) {
        if (h.f8366f) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        i iVar = this.f8417e.get(fragment.mWho);
        if (iVar != null) {
            iVar.c();
            this.f8417e.remove(fragment.mWho);
        }
        p pVar = this.f8418f.get(fragment.mWho);
        if (pVar != null) {
            pVar.a();
            this.f8418f.remove(fragment.mWho);
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
        return this.f8416d.equals(iVar.f8416d) && this.f8417e.equals(iVar.f8417e) && this.f8418f.equals(iVar.f8418f);
    }

    public i f(Fragment fragment) {
        i iVar = this.f8417e.get(fragment.mWho);
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this.f8419g);
        this.f8417e.put(fragment.mWho, iVar2);
        return iVar2;
    }

    public Collection<Fragment> h() {
        return this.f8416d;
    }

    public int hashCode() {
        return (((this.f8416d.hashCode() * 31) + this.f8417e.hashCode()) * 31) + this.f8418f.hashCode();
    }

    public p i(Fragment fragment) {
        p pVar = this.f8418f.get(fragment.mWho);
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p();
        this.f8418f.put(fragment.mWho, pVar2);
        return pVar2;
    }

    public boolean j() {
        return this.f8420h;
    }

    public boolean k(Fragment fragment) {
        return this.f8416d.remove(fragment);
    }

    public boolean l(Fragment fragment) {
        if (this.f8416d.contains(fragment)) {
            return this.f8419g ? this.f8420h : !this.f8421i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f8416d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f8417e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f8418f.keySet().iterator();
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

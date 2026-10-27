package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.o;
import androidx.lifecycle.p;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
class i extends androidx.lifecycle.n {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final o.a f1545c = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final boolean f1549g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<Fragment> f1546d = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final HashMap<String, i> f1547e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final HashMap<String, p> f1548f = new HashMap<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1550h = false;
    private boolean i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    static class a implements o.a {
        a() {
        }

        @Override // androidx.lifecycle.o.a
        public <T extends androidx.lifecycle.n> T a(Class<T> cls) {
            return new i(true);
        }
    }

    i(boolean z) {
        this.f1549g = z;
    }

    static i g(p pVar) {
        return (i) new o(pVar, f1545c).a(i.class);
    }

    @Override // androidx.lifecycle.n
    protected void c() {
        if (h.f1512d) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f1550h = true;
    }

    boolean d(Fragment fragment) {
        return this.f1546d.add(fragment);
    }

    void e(Fragment fragment) {
        if (h.f1512d) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        i iVar = this.f1547e.get(fragment.mWho);
        if (iVar != null) {
            iVar.c();
            this.f1547e.remove(fragment.mWho);
        }
        p pVar = this.f1548f.get(fragment.mWho);
        if (pVar != null) {
            pVar.a();
            this.f1548f.remove(fragment.mWho);
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
        return this.f1546d.equals(iVar.f1546d) && this.f1547e.equals(iVar.f1547e) && this.f1548f.equals(iVar.f1548f);
    }

    i f(Fragment fragment) {
        i iVar = this.f1547e.get(fragment.mWho);
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this.f1549g);
        this.f1547e.put(fragment.mWho, iVar2);
        return iVar2;
    }

    Collection<Fragment> h() {
        return this.f1546d;
    }

    public int hashCode() {
        return (((this.f1546d.hashCode() * 31) + this.f1547e.hashCode()) * 31) + this.f1548f.hashCode();
    }

    p i(Fragment fragment) {
        p pVar = this.f1548f.get(fragment.mWho);
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p();
        this.f1548f.put(fragment.mWho, pVar2);
        return pVar2;
    }

    boolean j() {
        return this.f1550h;
    }

    boolean k(Fragment fragment) {
        return this.f1546d.remove(fragment);
    }

    boolean l(Fragment fragment) {
        if (this.f1546d.contains(fragment)) {
            return this.f1549g ? this.f1550h : !this.i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1546d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f1547e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f1548f.keySet().iterator();
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

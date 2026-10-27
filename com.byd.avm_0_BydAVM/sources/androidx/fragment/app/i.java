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
    private static final o.a f1034c = new a();
    private final boolean g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<Fragment> f1035d = new HashSet<>();
    private final HashMap<String, i> e = new HashMap<>();
    private final HashMap<String, p> f = new HashMap<>();
    private boolean h = false;
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
        this.g = z;
    }

    static i g(p pVar) {
        return (i) new o(pVar, f1034c).a(i.class);
    }

    @Override // androidx.lifecycle.n
    protected void c() {
        if (h.f1007d) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.h = true;
    }

    boolean d(Fragment fragment) {
        return this.f1035d.add(fragment);
    }

    void e(Fragment fragment) {
        if (h.f1007d) {
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
        return this.f1035d.equals(iVar.f1035d) && this.e.equals(iVar.e) && this.f.equals(iVar.f);
    }

    i f(Fragment fragment) {
        i iVar = this.e.get(fragment.mWho);
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this.g);
        this.e.put(fragment.mWho, iVar2);
        return iVar2;
    }

    Collection<Fragment> h() {
        return this.f1035d;
    }

    public int hashCode() {
        return (((this.f1035d.hashCode() * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    p i(Fragment fragment) {
        p pVar = this.f.get(fragment.mWho);
        if (pVar != null) {
            return pVar;
        }
        p pVar2 = new p();
        this.f.put(fragment.mWho, pVar2);
        return pVar2;
    }

    boolean j() {
        return this.h;
    }

    boolean k(Fragment fragment) {
        return this.f1035d.remove(fragment);
    }

    boolean l(Fragment fragment) {
        if (this.f1035d.contains(fragment)) {
            return this.g ? this.h : !this.i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1035d.iterator();
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

package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.q;
import androidx.lifecycle.r;
import androidx.lifecycle.s;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
class i extends q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final r.b f1407c = new a();
    private final boolean g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<Fragment> f1408d = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final HashMap<String, i> f1409e = new HashMap<>();
    private final HashMap<String, s> f = new HashMap<>();
    private boolean h = false;
    private boolean i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    static class a implements r.b {
        a() {
        }

        @Override // androidx.lifecycle.r.b
        public <T extends q> T a(Class<T> cls) {
            return new i(true);
        }
    }

    i(boolean z) {
        this.g = z;
    }

    static i g(s sVar) {
        return (i) new r(sVar, f1407c).a(i.class);
    }

    @Override // androidx.lifecycle.q
    protected void c() {
        if (h.f1378d) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.h = true;
    }

    boolean d(Fragment fragment) {
        return this.f1408d.add(fragment);
    }

    void e(Fragment fragment) {
        if (h.f1378d) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        i iVar = this.f1409e.get(fragment.mWho);
        if (iVar != null) {
            iVar.c();
            this.f1409e.remove(fragment.mWho);
        }
        s sVar = this.f.get(fragment.mWho);
        if (sVar != null) {
            sVar.a();
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
        return this.f1408d.equals(iVar.f1408d) && this.f1409e.equals(iVar.f1409e) && this.f.equals(iVar.f);
    }

    i f(Fragment fragment) {
        i iVar = this.f1409e.get(fragment.mWho);
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this.g);
        this.f1409e.put(fragment.mWho, iVar2);
        return iVar2;
    }

    Collection<Fragment> h() {
        return this.f1408d;
    }

    public int hashCode() {
        return (((this.f1408d.hashCode() * 31) + this.f1409e.hashCode()) * 31) + this.f.hashCode();
    }

    s i(Fragment fragment) {
        s sVar = this.f.get(fragment.mWho);
        if (sVar != null) {
            return sVar;
        }
        s sVar2 = new s();
        this.f.put(fragment.mWho, sVar2);
        return sVar2;
    }

    boolean j() {
        return this.h;
    }

    boolean k(Fragment fragment) {
        return this.f1408d.remove(fragment);
    }

    boolean l(Fragment fragment) {
        if (this.f1408d.contains(fragment)) {
            return this.g ? this.h : !this.i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1408d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f1409e.keySet().iterator();
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

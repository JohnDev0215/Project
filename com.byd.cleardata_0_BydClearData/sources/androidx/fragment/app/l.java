package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.q;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
class l extends androidx.lifecycle.p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final q.a f1326c = new a();
    private final boolean g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<Fragment> f1327d = new HashSet<>();
    private final HashMap<String, l> e = new HashMap<>();
    private final HashMap<String, androidx.lifecycle.r> f = new HashMap<>();
    private boolean h = false;
    private boolean i = false;

    static class a implements q.a {
        a() {
        }

        @Override // androidx.lifecycle.q.a
        public <T extends androidx.lifecycle.p> T a(Class<T> cls) {
            return new l(true);
        }
    }

    l(boolean z) {
        this.g = z;
    }

    static l g(androidx.lifecycle.r rVar) {
        return (l) new androidx.lifecycle.q(rVar, f1326c).a(l.class);
    }

    @Override // androidx.lifecycle.p
    protected void c() {
        if (j.f1292d) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.h = true;
    }

    boolean d(Fragment fragment) {
        return this.f1327d.add(fragment);
    }

    void e(Fragment fragment) {
        if (j.f1292d) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        l lVar = this.e.get(fragment.g);
        if (lVar != null) {
            lVar.c();
            this.e.remove(fragment.g);
        }
        androidx.lifecycle.r rVar = this.f.get(fragment.g);
        if (rVar != null) {
            rVar.a();
            this.f.remove(fragment.g);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l.class != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        return this.f1327d.equals(lVar.f1327d) && this.e.equals(lVar.e) && this.f.equals(lVar.f);
    }

    l f(Fragment fragment) {
        l lVar = this.e.get(fragment.g);
        if (lVar != null) {
            return lVar;
        }
        l lVar2 = new l(this.g);
        this.e.put(fragment.g, lVar2);
        return lVar2;
    }

    Collection<Fragment> h() {
        return this.f1327d;
    }

    public int hashCode() {
        return (((this.f1327d.hashCode() * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    androidx.lifecycle.r i(Fragment fragment) {
        androidx.lifecycle.r rVar = this.f.get(fragment.g);
        if (rVar != null) {
            return rVar;
        }
        androidx.lifecycle.r rVar2 = new androidx.lifecycle.r();
        this.f.put(fragment.g, rVar2);
        return rVar2;
    }

    boolean j() {
        return this.h;
    }

    boolean k(Fragment fragment) {
        return this.f1327d.remove(fragment);
    }

    boolean l(Fragment fragment) {
        if (this.f1327d.contains(fragment)) {
            return this.g ? this.h : !this.i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1327d.iterator();
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

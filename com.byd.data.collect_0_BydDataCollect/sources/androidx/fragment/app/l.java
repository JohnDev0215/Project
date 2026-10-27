package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.q;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
final class l extends androidx.lifecycle.p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final q.a f1469c = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final boolean f1473g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashMap<String, Fragment> f1470d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final HashMap<String, l> f1471e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private final HashMap<String, androidx.lifecycle.r> f1472f = new HashMap<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f1474h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f1475i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    static class a implements q.a {
        a() {
        }

        @Override // androidx.lifecycle.q.a
        public <T extends androidx.lifecycle.p> T a(Class<T> cls) {
            return new l(true);
        }
    }

    l(boolean z) {
        this.f1473g = z;
    }

    static l i(androidx.lifecycle.r rVar) {
        return (l) new androidx.lifecycle.q(rVar, f1469c).a(l.class);
    }

    @Override // androidx.lifecycle.p
    protected void d() {
        if (j.q0(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f1474h = true;
    }

    boolean e(Fragment fragment) {
        if (this.f1470d.containsKey(fragment.f1366g)) {
            return false;
        }
        this.f1470d.put(fragment.f1366g, fragment);
        return true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || l.class != obj.getClass()) {
            return false;
        }
        l lVar = (l) obj;
        return this.f1470d.equals(lVar.f1470d) && this.f1471e.equals(lVar.f1471e) && this.f1472f.equals(lVar.f1472f);
    }

    void f(Fragment fragment) {
        if (j.q0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        l lVar = this.f1471e.get(fragment.f1366g);
        if (lVar != null) {
            lVar.d();
            this.f1471e.remove(fragment.f1366g);
        }
        androidx.lifecycle.r rVar = this.f1472f.get(fragment.f1366g);
        if (rVar != null) {
            rVar.a();
            this.f1472f.remove(fragment.f1366g);
        }
    }

    Fragment g(String str) {
        return this.f1470d.get(str);
    }

    l h(Fragment fragment) {
        l lVar = this.f1471e.get(fragment.f1366g);
        if (lVar != null) {
            return lVar;
        }
        l lVar2 = new l(this.f1473g);
        this.f1471e.put(fragment.f1366g, lVar2);
        return lVar2;
    }

    public int hashCode() {
        return (((this.f1470d.hashCode() * 31) + this.f1471e.hashCode()) * 31) + this.f1472f.hashCode();
    }

    Collection<Fragment> j() {
        return this.f1470d.values();
    }

    androidx.lifecycle.r k(Fragment fragment) {
        androidx.lifecycle.r rVar = this.f1472f.get(fragment.f1366g);
        if (rVar != null) {
            return rVar;
        }
        androidx.lifecycle.r rVar2 = new androidx.lifecycle.r();
        this.f1472f.put(fragment.f1366g, rVar2);
        return rVar2;
    }

    boolean l() {
        return this.f1474h;
    }

    boolean m(Fragment fragment) {
        return this.f1470d.remove(fragment.f1366g) != null;
    }

    boolean n(Fragment fragment) {
        if (this.f1470d.containsKey(fragment.f1366g)) {
            return this.f1473g ? this.f1474h : !this.f1475i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1470d.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f1471e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f1472f.keySet().iterator();
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

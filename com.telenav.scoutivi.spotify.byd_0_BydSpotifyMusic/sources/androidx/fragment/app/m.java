package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.a0;
import androidx.lifecycle.b0;
import androidx.lifecycle.d0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
final class m extends a0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private static final b0.b f2347h = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final boolean f2351d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, Fragment> f2348a = new HashMap<>();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashMap<String, m> f2349b = new HashMap<>();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final HashMap<String, d0> f2350c = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private boolean f2352e = false;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private boolean f2353f = false;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private boolean f2354g = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    class a implements b0.b {
        a() {
        }

        @Override // androidx.lifecycle.b0.b
        public <T extends a0> T a(Class<T> cls) {
            return new m(true);
        }
    }

    m(boolean z6) {
        this.f2351d = z6;
    }

    static m l(d0 d0Var) {
        return (m) new b0(d0Var, f2347h).a(m.class);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || m.class != obj.getClass()) {
            return false;
        }
        m mVar = (m) obj;
        return this.f2348a.equals(mVar.f2348a) && this.f2349b.equals(mVar.f2349b) && this.f2350c.equals(mVar.f2350c);
    }

    void h(Fragment fragment) {
        if (this.f2354g) {
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else {
            if (this.f2348a.containsKey(fragment.f2115k)) {
                return;
            }
            this.f2348a.put(fragment.f2115k, fragment);
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
            }
        }
    }

    public int hashCode() {
        return (((this.f2348a.hashCode() * 31) + this.f2349b.hashCode()) * 31) + this.f2350c.hashCode();
    }

    void i(Fragment fragment) {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        m mVar = this.f2349b.get(fragment.f2115k);
        if (mVar != null) {
            mVar.onCleared();
            this.f2349b.remove(fragment.f2115k);
        }
        d0 d0Var = this.f2350c.get(fragment.f2115k);
        if (d0Var != null) {
            d0Var.a();
            this.f2350c.remove(fragment.f2115k);
        }
    }

    Fragment j(String str) {
        return this.f2348a.get(str);
    }

    m k(Fragment fragment) {
        m mVar = this.f2349b.get(fragment.f2115k);
        if (mVar != null) {
            return mVar;
        }
        m mVar2 = new m(this.f2351d);
        this.f2349b.put(fragment.f2115k, mVar2);
        return mVar2;
    }

    Collection<Fragment> m() {
        return new ArrayList(this.f2348a.values());
    }

    d0 n(Fragment fragment) {
        d0 d0Var = this.f2350c.get(fragment.f2115k);
        if (d0Var != null) {
            return d0Var;
        }
        d0 d0Var2 = new d0();
        this.f2350c.put(fragment.f2115k, d0Var2);
        return d0Var2;
    }

    boolean o() {
        return this.f2352e;
    }

    @Override // androidx.lifecycle.a0
    protected void onCleared() {
        if (FragmentManager.F0(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f2352e = true;
    }

    void p(Fragment fragment) {
        if (this.f2354g) {
            if (FragmentManager.F0(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
                return;
            }
            return;
        }
        if ((this.f2348a.remove(fragment.f2115k) != null) && FragmentManager.F0(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    void q(boolean z6) {
        this.f2354g = z6;
    }

    boolean r(Fragment fragment) {
        if (this.f2348a.containsKey(fragment.f2115k)) {
            return this.f2351d ? this.f2352e : !this.f2353f;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f2348a.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f2349b.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f2350c.keySet().iterator();
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

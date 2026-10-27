package b.l.d;

import android.util.Log;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import b.n.r;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public final class k extends b.n.q {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final r.b f2824c = new a();

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean f2828g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap<String, Fragment> f2825d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<String, k> f2826e = new HashMap<>();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final HashMap<String, b.n.s> f2827f = new HashMap<>();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f2829h = false;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f2830i = false;
    public boolean j = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    public class a implements r.b {
        @Override // b.n.r.b
        public <T extends b.n.q> T a(Class<T> cls) {
            return new k(true);
        }
    }

    public k(boolean z) {
        this.f2828g = z;
    }

    public static k j(b.n.s sVar) {
        return (k) new b.n.r(sVar, f2824c).a(k.class);
    }

    @Override // b.n.q
    public void d() {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.f2829h = true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || k.class != obj.getClass()) {
            return false;
        }
        k kVar = (k) obj;
        return this.f2825d.equals(kVar.f2825d) && this.f2826e.equals(kVar.f2826e) && this.f2827f.equals(kVar.f2827f);
    }

    public void f(Fragment fragment) {
        if (this.j) {
            if (FragmentManager.G0(2)) {
                Log.v("FragmentManager", "Ignoring addRetainedFragment as the state is already saved");
            }
        } else {
            if (this.f2825d.containsKey(fragment.mWho)) {
                return;
            }
            this.f2825d.put(fragment.mWho, fragment);
            if (FragmentManager.G0(2)) {
                Log.v("FragmentManager", "Updating retained Fragments: Added " + fragment);
            }
        }
    }

    public void g(Fragment fragment) {
        if (FragmentManager.G0(3)) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        k kVar = this.f2826e.get(fragment.mWho);
        if (kVar != null) {
            kVar.d();
            this.f2826e.remove(fragment.mWho);
        }
        b.n.s sVar = this.f2827f.get(fragment.mWho);
        if (sVar != null) {
            sVar.a();
            this.f2827f.remove(fragment.mWho);
        }
    }

    public Fragment h(String str) {
        return this.f2825d.get(str);
    }

    public int hashCode() {
        return (((this.f2825d.hashCode() * 31) + this.f2826e.hashCode()) * 31) + this.f2827f.hashCode();
    }

    public k i(Fragment fragment) {
        k kVar = this.f2826e.get(fragment.mWho);
        if (kVar != null) {
            return kVar;
        }
        k kVar2 = new k(this.f2828g);
        this.f2826e.put(fragment.mWho, kVar2);
        return kVar2;
    }

    public Collection<Fragment> k() {
        return new ArrayList(this.f2825d.values());
    }

    public b.n.s l(Fragment fragment) {
        b.n.s sVar = this.f2827f.get(fragment.mWho);
        if (sVar != null) {
            return sVar;
        }
        b.n.s sVar2 = new b.n.s();
        this.f2827f.put(fragment.mWho, sVar2);
        return sVar2;
    }

    public boolean m() {
        return this.f2829h;
    }

    public void n(Fragment fragment) {
        if (this.j) {
            if (FragmentManager.G0(2)) {
                Log.v("FragmentManager", "Ignoring removeRetainedFragment as the state is already saved");
                return;
            }
            return;
        }
        if ((this.f2825d.remove(fragment.mWho) != null) && FragmentManager.G0(2)) {
            Log.v("FragmentManager", "Updating retained Fragments: Removed " + fragment);
        }
    }

    public void o(boolean z) {
        this.j = z;
    }

    public boolean p(Fragment fragment) {
        if (this.f2825d.containsKey(fragment.mWho)) {
            return this.f2828g ? this.f2829h : !this.f2830i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f2825d.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f2826e.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f2827f.keySet().iterator();
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

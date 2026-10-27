package defpackage;

import android.util.Log;
import androidx.fragment.app.Fragment;
import defpackage.nc;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public class pb extends mc {
    public static final nc.a c = new a();
    public final boolean g;
    public final HashSet<Fragment> d = new HashSet<>();
    public final HashMap<String, pb> e = new HashMap<>();
    public final HashMap<String, oc> f = new HashMap<>();
    public boolean h = false;
    public boolean i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    public static class a implements nc.a {
        @Override // nc.a
        public <T extends mc> T a(Class<T> cls) {
            return new pb(true);
        }
    }

    public pb(boolean z) {
        this.g = z;
    }

    public static pb g(oc ocVar) {
        return (pb) new nc(ocVar, c).a(pb.class);
    }

    @Override // defpackage.mc
    public void c() {
        if (ob.d) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.h = true;
    }

    public boolean d(Fragment fragment) {
        return this.d.add(fragment);
    }

    public void e(Fragment fragment) {
        if (ob.d) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        pb pbVar = this.e.get(fragment.mWho);
        if (pbVar != null) {
            pbVar.c();
            this.e.remove(fragment.mWho);
        }
        oc ocVar = this.f.get(fragment.mWho);
        if (ocVar != null) {
            ocVar.a();
            this.f.remove(fragment.mWho);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || pb.class != obj.getClass()) {
            return false;
        }
        pb pbVar = (pb) obj;
        return this.d.equals(pbVar.d) && this.e.equals(pbVar.e) && this.f.equals(pbVar.f);
    }

    public pb f(Fragment fragment) {
        pb pbVar = this.e.get(fragment.mWho);
        if (pbVar != null) {
            return pbVar;
        }
        pb pbVar2 = new pb(this.g);
        this.e.put(fragment.mWho, pbVar2);
        return pbVar2;
    }

    public Collection<Fragment> h() {
        return this.d;
    }

    public int hashCode() {
        return (((this.d.hashCode() * 31) + this.e.hashCode()) * 31) + this.f.hashCode();
    }

    public oc i(Fragment fragment) {
        oc ocVar = this.f.get(fragment.mWho);
        if (ocVar != null) {
            return ocVar;
        }
        oc ocVar2 = new oc();
        this.f.put(fragment.mWho, ocVar2);
        return ocVar2;
    }

    public boolean j() {
        return this.h;
    }

    public boolean k(Fragment fragment) {
        return this.d.remove(fragment);
    }

    public boolean l(Fragment fragment) {
        if (this.d.contains(fragment)) {
            return this.g ? this.h : !this.i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.d.iterator();
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

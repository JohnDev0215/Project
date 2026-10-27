package a.l.d;

import android.util.Log;
import androidx.fragment.app.Fragment;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class v extends a.n.x {
    public static final a.n.z i = new a();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final boolean f1049f;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final HashMap<String, Fragment> f1046c = new HashMap<>();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final HashMap<String, v> f1047d = new HashMap<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final HashMap<String, a.n.d0> f1048e = new HashMap<>();
    public boolean g = false;
    public boolean h = false;

    public static class a implements a.n.z {
        @Override // a.n.z
        public <T extends a.n.x> T a(Class<T> cls) {
            return new v(true);
        }
    }

    public v(boolean z) {
        this.f1049f = z;
    }

    @Override // a.n.x
    public void d() {
        if (r.J(3)) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.g = true;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || v.class != obj.getClass()) {
            return false;
        }
        v vVar = (v) obj;
        return this.f1046c.equals(vVar.f1046c) && this.f1047d.equals(vVar.f1047d) && this.f1048e.equals(vVar.f1048e);
    }

    public boolean f(Fragment fragment) {
        if (this.f1046c.containsKey(fragment.f1557e)) {
            return this.f1049f ? this.g : !this.h;
        }
        return true;
    }

    public int hashCode() {
        return this.f1048e.hashCode() + ((this.f1047d.hashCode() + (this.f1046c.hashCode() * 31)) * 31);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1046c.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f1047d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.f1048e.keySet().iterator();
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

package defpackage;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.l;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
public final class mp0 extends ym3 {
    public static final a i = new a();
    public final boolean f;
    public final HashMap<String, Fragment> c = new HashMap<>();
    public final HashMap<String, mp0> d = new HashMap<>();
    public final HashMap<String, an3> e = new HashMap<>();
    public boolean g = false;
    public boolean h = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    public class a implements l.a {
        @Override // androidx.lifecycle.l.a
        public final <T extends ym3> T a(Class<T> cls) {
            return new mp0(true);
        }
    }

    public mp0(boolean z) {
        this.f = z;
    }

    @Override // defpackage.ym3
    public final void a() {
        this.g = true;
    }

    public final void b(String str) {
        HashMap<String, mp0> map = this.d;
        mp0 mp0Var = map.get(str);
        if (mp0Var != null) {
            mp0Var.g = true;
            map.remove(str);
        }
        HashMap<String, an3> map2 = this.e;
        an3 an3Var = map2.get(str);
        if (an3Var != null) {
            an3Var.a();
            map2.remove(str);
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || mp0.class != obj.getClass()) {
            return false;
        }
        mp0 mp0Var = (mp0) obj;
        return this.c.equals(mp0Var.c) && this.d.equals(mp0Var.d) && this.e.equals(mp0Var.e);
    }

    public final int hashCode() {
        return this.e.hashCode() + ((this.d.hashCode() + (this.c.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.c.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append(it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator<String> it3 = this.e.keySet().iterator();
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

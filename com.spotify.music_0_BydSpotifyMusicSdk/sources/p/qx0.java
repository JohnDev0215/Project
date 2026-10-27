package p;

import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class qx0 extends iu2 {
    public static final qo i = new qo();
    public final boolean f;
    public final HashMap c = new HashMap();
    public final HashMap d = new HashMap();
    public final HashMap e = new HashMap();
    public boolean g = false;
    public boolean h = false;

    public qx0(boolean z) {
        this.f = z;
    }

    @Override // p.iu2
    public final void a() {
        if (Log.isLoggable("FragmentManager", 3)) {
            toString();
        }
        this.g = true;
    }

    public final void b(String str, boolean z) {
        Log.isLoggable("FragmentManager", 3);
        c(str, z);
    }

    public final void c(String str, boolean z) {
        HashMap map = this.d;
        qx0 qx0Var = (qx0) map.get(str);
        if (qx0Var != null) {
            if (z) {
                ArrayList arrayList = new ArrayList();
                arrayList.addAll(qx0Var.d.keySet());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    qx0Var.b((String) it.next(), true);
                }
            }
            qx0Var.a();
            map.remove(str);
        }
        HashMap map2 = this.e;
        lu2 lu2Var = (lu2) map2.get(str);
        if (lu2Var != null) {
            lu2Var.a();
            map2.remove(str);
        }
    }

    public final void d(ww0 ww0Var) {
        if (this.h) {
            Log.isLoggable("FragmentManager", 2);
        } else {
            if (this.c.remove(ww0Var.m) == null || !Log.isLoggable("FragmentManager", 2)) {
                return;
            }
            ww0Var.toString();
        }
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || qx0.class != obj.getClass()) {
            return false;
        }
        qx0 qx0Var = (qx0) obj;
        return this.c.equals(qx0Var.c) && this.d.equals(qx0Var.d) && this.e.equals(qx0Var.e);
    }

    public final int hashCode() {
        return this.e.hashCode() + ((this.d.hashCode() + (this.c.hashCode() * 31)) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator it = this.c.values().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator it2 = this.d.keySet().iterator();
        while (it2.hasNext()) {
            sb.append((String) it2.next());
            if (it2.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") ViewModelStores (");
        Iterator it3 = this.e.keySet().iterator();
        while (it3.hasNext()) {
            sb.append((String) it3.next());
            if (it3.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}

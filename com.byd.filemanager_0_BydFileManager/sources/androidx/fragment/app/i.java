package androidx.fragment.app;

import android.util.Log;
import androidx.lifecycle.o;
import androidx.lifecycle.p;
import androidx.lifecycle.q;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

/* JADX INFO: compiled from: FragmentManagerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
class i extends o {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final p.b f1163c = new a();
    private final boolean g;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashSet<Fragment> f1164d = new HashSet<>();

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final HashMap<String, i> f1165e = new HashMap<>();
    private final HashMap<String, q> f = new HashMap<>();
    private boolean h = false;
    private boolean i = false;

    /* JADX INFO: compiled from: FragmentManagerViewModel.java */
    static class a implements p.b {
        a() {
        }

        @Override // androidx.lifecycle.p.b
        public <T extends o> T a(Class<T> cls) {
            return new i(true);
        }
    }

    i(boolean z) {
        this.g = z;
    }

    static i g(q qVar) {
        return (i) new p(qVar, f1163c).a(i.class);
    }

    @Override // androidx.lifecycle.o
    protected void c() {
        if (h.f1130d) {
            Log.d("FragmentManager", "onCleared called for " + this);
        }
        this.h = true;
    }

    boolean d(Fragment fragment) {
        return this.f1164d.add(fragment);
    }

    void e(Fragment fragment) {
        if (h.f1130d) {
            Log.d("FragmentManager", "Clearing non-config state for " + fragment);
        }
        i iVar = this.f1165e.get(fragment.g);
        if (iVar != null) {
            iVar.c();
            this.f1165e.remove(fragment.g);
        }
        q qVar = this.f.get(fragment.g);
        if (qVar != null) {
            qVar.a();
            this.f.remove(fragment.g);
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
        return this.f1164d.equals(iVar.f1164d) && this.f1165e.equals(iVar.f1165e) && this.f.equals(iVar.f);
    }

    i f(Fragment fragment) {
        i iVar = this.f1165e.get(fragment.g);
        if (iVar != null) {
            return iVar;
        }
        i iVar2 = new i(this.g);
        this.f1165e.put(fragment.g, iVar2);
        return iVar2;
    }

    Collection<Fragment> h() {
        return this.f1164d;
    }

    public int hashCode() {
        return (((this.f1164d.hashCode() * 31) + this.f1165e.hashCode()) * 31) + this.f.hashCode();
    }

    q i(Fragment fragment) {
        q qVar = this.f.get(fragment.g);
        if (qVar != null) {
            return qVar;
        }
        q qVar2 = new q();
        this.f.put(fragment.g, qVar2);
        return qVar2;
    }

    boolean j() {
        return this.h;
    }

    boolean k(Fragment fragment) {
        return this.f1164d.remove(fragment);
    }

    boolean l(Fragment fragment) {
        if (this.f1164d.contains(fragment)) {
            return this.g ? this.h : !this.i;
        }
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("FragmentManagerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} Fragments (");
        Iterator<Fragment> it = this.f1164d.iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(") Child Non Config (");
        Iterator<String> it2 = this.f1165e.keySet().iterator();
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

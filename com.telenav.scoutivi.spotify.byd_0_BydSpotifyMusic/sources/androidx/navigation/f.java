package androidx.navigation;

import androidx.lifecycle.a0;
import androidx.lifecycle.b0;
import androidx.lifecycle.d0;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

/* JADX INFO: compiled from: NavControllerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
class f extends a0 {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final b0.b f2658b = new a();

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<UUID, d0> f2659a = new HashMap<>();

    /* JADX INFO: compiled from: NavControllerViewModel.java */
    class a implements b0.b {
        a() {
        }

        @Override // androidx.lifecycle.b0.b
        public <T extends a0> T a(Class<T> cls) {
            return new f();
        }
    }

    f() {
    }

    static f i(d0 d0Var) {
        return (f) new b0(d0Var, f2658b).a(f.class);
    }

    void h(UUID uuid) {
        d0 d0VarRemove = this.f2659a.remove(uuid);
        if (d0VarRemove != null) {
            d0VarRemove.a();
        }
    }

    d0 j(UUID uuid) {
        d0 d0Var = this.f2659a.get(uuid);
        if (d0Var != null) {
            return d0Var;
        }
        d0 d0Var2 = new d0();
        this.f2659a.put(uuid, d0Var2);
        return d0Var2;
    }

    @Override // androidx.lifecycle.a0
    protected void onCleared() {
        Iterator<d0> it = this.f2659a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f2659a.clear();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NavControllerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} ViewModelStores (");
        Iterator<UUID> it = this.f2659a.keySet().iterator();
        while (it.hasNext()) {
            sb.append(it.next());
            if (it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append(')');
        return sb.toString();
    }
}

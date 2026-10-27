package androidx.navigation;

import androidx.lifecycle.q;
import java.util.HashMap;
import java.util.Iterator;
import java.util.UUID;

/* JADX INFO: compiled from: NavControllerViewModel.java */
/* JADX INFO: loaded from: classes.dex */
class f extends androidx.lifecycle.p {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static final q.a f1667c = new a();

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final HashMap<UUID, androidx.lifecycle.r> f1668d = new HashMap<>();

    /* JADX INFO: compiled from: NavControllerViewModel.java */
    class a implements q.a {
        a() {
        }

        @Override // androidx.lifecycle.q.a
        public <T extends androidx.lifecycle.p> T a(Class<T> cls) {
            return new f();
        }
    }

    f() {
    }

    static f f(androidx.lifecycle.r rVar) {
        return (f) new androidx.lifecycle.q(rVar, f1667c).a(f.class);
    }

    @Override // androidx.lifecycle.p
    protected void d() {
        Iterator<androidx.lifecycle.r> it = this.f1668d.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1668d.clear();
    }

    void e(UUID uuid) {
        androidx.lifecycle.r rVarRemove = this.f1668d.remove(uuid);
        if (rVarRemove != null) {
            rVarRemove.a();
        }
    }

    androidx.lifecycle.r g(UUID uuid) {
        androidx.lifecycle.r rVar = this.f1668d.get(uuid);
        if (rVar != null) {
            return rVar;
        }
        androidx.lifecycle.r rVar2 = new androidx.lifecycle.r();
        this.f1668d.put(uuid, rVar2);
        return rVar2;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("NavControllerViewModel{");
        sb.append(Integer.toHexString(System.identityHashCode(this)));
        sb.append("} ViewModelStores (");
        Iterator<UUID> it = this.f1668d.keySet().iterator();
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

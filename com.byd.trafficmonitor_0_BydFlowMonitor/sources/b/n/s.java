package b.n;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap<String, q> f2977a = new HashMap<>();

    public final void a() {
        Iterator<q> it = this.f2977a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f2977a.clear();
    }

    public final q b(String str) {
        return this.f2977a.get(str);
    }

    public Set<String> c() {
        return new HashSet(this.f2977a.keySet());
    }

    public final void d(String str, q qVar) {
        q qVarPut = this.f2977a.put(str, qVar);
        if (qVarPut != null) {
            qVarPut.d();
        }
    }
}

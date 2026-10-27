package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, n> f780a = new HashMap<>();

    public final void a() {
        Iterator<n> it = this.f780a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f780a.clear();
    }

    final n b(String str) {
        return this.f780a.get(str);
    }

    Set<String> c() {
        return new HashSet(this.f780a.keySet());
    }

    final void d(String str, n nVar) {
        n nVarPut = this.f780a.put(str, nVar);
        if (nVarPut != null) {
            nVarPut.d();
        }
    }
}

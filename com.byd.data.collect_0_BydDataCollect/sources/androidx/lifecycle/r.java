package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, p> f1627a = new HashMap<>();

    public final void a() {
        Iterator<p> it = this.f1627a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1627a.clear();
    }

    final p b(String str) {
        return this.f1627a.get(str);
    }

    Set<String> c() {
        return new HashSet(this.f1627a.keySet());
    }

    final void d(String str, p pVar) {
        p pVarPut = this.f1627a.put(str, pVar);
        if (pVarPut != null) {
            pVarPut.d();
        }
    }
}

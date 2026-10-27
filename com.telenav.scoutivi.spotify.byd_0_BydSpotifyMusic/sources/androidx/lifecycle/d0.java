package androidx.lifecycle;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class d0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, a0> f2543a = new HashMap<>();

    public final void a() {
        Iterator<a0> it = this.f2543a.values().iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
        this.f2543a.clear();
    }

    final a0 b(String str) {
        return this.f2543a.get(str);
    }

    Set<String> c() {
        return new HashSet(this.f2543a.keySet());
    }

    final void d(String str, a0 a0Var) {
        a0 a0VarPut = this.f2543a.put(str, a0Var);
        if (a0VarPut != null) {
            a0VarPut.onCleared();
        }
    }
}

package androidx.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, n> f1649a = new HashMap<>();

    public final void a() {
        Iterator<n> it = this.f1649a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1649a.clear();
    }

    final n b(String str) {
        return this.f1649a.get(str);
    }

    final void c(String str, n nVar) {
        n nVarPut = this.f1649a.put(str, nVar);
        if (nVarPut != null) {
            nVarPut.c();
        }
    }
}

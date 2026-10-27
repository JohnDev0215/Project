package androidx.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, n> f1105a = new HashMap<>();

    public final void a() {
        Iterator<n> it = this.f1105a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1105a.clear();
    }

    final n b(String str) {
        return this.f1105a.get(str);
    }

    final void c(String str, n nVar) {
        n nVarPut = this.f1105a.put(str, nVar);
        if (nVarPut != null) {
            nVarPut.c();
        }
    }
}

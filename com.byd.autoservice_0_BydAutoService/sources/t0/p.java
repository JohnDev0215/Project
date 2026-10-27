package t0;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap<String, n> f5015a = new HashMap<>();

    public final void a() {
        Iterator<n> it = this.f5015a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f5015a.clear();
    }

    public final n b(String str) {
        return this.f5015a.get(str);
    }

    public final void c(String str, n nVar) {
        n nVarPut = this.f5015a.put(str, nVar);
        if (nVarPut != null) {
            nVarPut.c();
        }
    }
}

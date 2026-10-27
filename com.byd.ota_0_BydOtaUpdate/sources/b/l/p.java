package b.l;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class p {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final HashMap<String, n> f1988a = new HashMap<>();

    public final void a() {
        Iterator<n> it = this.f1988a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1988a.clear();
    }

    public final n b(String str) {
        return this.f1988a.get(str);
    }

    public final void c(String str, n nVar) {
        n nVarPut = this.f1988a.put(str, nVar);
        if (nVarPut != null) {
            nVarPut.c();
        }
    }
}

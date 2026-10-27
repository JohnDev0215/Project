package androidx.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class q {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, o> f1266a = new HashMap<>();

    public final void a() {
        Iterator<o> it = this.f1266a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1266a.clear();
    }

    final o b(String str) {
        return this.f1266a.get(str);
    }

    final void c(String str, o oVar) {
        o oVarPut = this.f1266a.put(str, oVar);
        if (oVarPut != null) {
            oVarPut.c();
        }
    }
}

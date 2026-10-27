package androidx.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class s {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, q> f1521a = new HashMap<>();

    public final void a() {
        Iterator<q> it = this.f1521a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1521a.clear();
    }

    final q b(String str) {
        return this.f1521a.get(str);
    }

    final void c(String str, q qVar) {
        q qVarPut = this.f1521a.put(str, qVar);
        if (qVarPut != null) {
            qVarPut.c();
        }
    }
}

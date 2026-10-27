package androidx.lifecycle;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final HashMap<String, p> f1420a = new HashMap<>();

    public final void a() {
        Iterator<p> it = this.f1420a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.f1420a.clear();
    }

    final p b(String str) {
        return this.f1420a.get(str);
    }

    final void c(String str, p pVar) {
        p pVarPut = this.f1420a.put(str, pVar);
        if (pVarPut != null) {
            pVarPut.c();
        }
    }
}

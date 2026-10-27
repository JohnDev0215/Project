package defpackage;

import java.util.HashMap;
import java.util.Iterator;

/* JADX INFO: compiled from: ViewModelStore.java */
/* JADX INFO: loaded from: classes.dex */
public class oc {
    public final HashMap<String, mc> a = new HashMap<>();

    public final void a() {
        Iterator<mc> it = this.a.values().iterator();
        while (it.hasNext()) {
            it.next().a();
        }
        this.a.clear();
    }

    public final mc b(String str) {
        return this.a.get(str);
    }

    public final void c(String str, mc mcVar) {
        mc mcVarPut = this.a.put(str, mcVar);
        if (mcVarPut != null) {
            mcVarPut.c();
        }
    }
}

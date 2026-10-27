package defpackage;

import java.util.Collections;
import java.util.Iterator;
import java.util.Set;
import java.util.WeakHashMap;

/* JADX INFO: compiled from: DelegatingConnectionErrorHandler.java */
/* JADX INFO: loaded from: classes3.dex */
public final class i80 implements fx {
    public final Set<fx> e = Collections.newSetFromMap(new WeakHashMap());

    @Override // defpackage.fx
    public final void q(ki1 ki1Var) {
        Iterator<fx> it = this.e.iterator();
        while (it.hasNext()) {
            it.next().q(ki1Var);
        }
    }
}

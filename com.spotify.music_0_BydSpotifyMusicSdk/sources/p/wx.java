package p;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;

/* JADX INFO: loaded from: classes.dex */
public final class wx implements jb2 {
    public final AtomicReference a;

    public wx(sb sbVar) {
        this.a = new AtomicReference(sbVar);
    }

    @Override // p.jb2
    public final Iterator iterator() {
        jb2 jb2Var = (jb2) this.a.getAndSet(null);
        if (jb2Var != null) {
            return jb2Var.iterator();
        }
        throw new IllegalStateException("This sequence can be consumed only once.");
    }
}

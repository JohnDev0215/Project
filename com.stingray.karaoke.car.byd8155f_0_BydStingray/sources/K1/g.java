package k1;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class g extends AbstractC1597b implements Iterable {
    @Override // k1.AbstractC1597b
    /* JADX INFO: renamed from: H, reason: merged with bridge method [inline-methods] */
    public final g clone() {
        return (g) super.clone();
    }

    @Override // k1.AbstractC1597b, k1.AbstractC1598c
    public final Object clone() {
        return (g) super.clone();
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        f fVar = new f();
        fVar.f17623m = 0;
        fVar.l = this;
        return fVar;
    }
}

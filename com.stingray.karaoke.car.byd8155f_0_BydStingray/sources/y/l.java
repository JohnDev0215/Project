package Y;

import java.util.Iterator;
import n7.AbstractC1986a;

/* JADX INFO: loaded from: classes.dex */
public final class l extends AbstractC1986a {
    public final c l;

    public l(c cVar) {
        this.l = cVar;
    }

    @Override // n7.AbstractC1986a, java.util.Collection, java.util.List
    public final boolean contains(Object obj) {
        return this.l.containsValue(obj);
    }

    @Override // n7.AbstractC1986a
    public final int d() {
        c cVar = this.l;
        cVar.getClass();
        return cVar.f10587m;
    }

    @Override // java.util.Collection, java.lang.Iterable
    public final Iterator iterator() {
        c cVar = this.l;
        n[] nVarArr = new n[8];
        for (int i7 = 0; i7 < 8; i7++) {
            nVarArr[i7] = new o(2);
        }
        return new k(cVar.l, nVarArr);
    }
}

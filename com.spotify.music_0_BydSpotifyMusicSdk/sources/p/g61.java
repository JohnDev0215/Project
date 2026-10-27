package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class g61 extends com.google.common.collect.a {
    public final /* synthetic */ int e;
    public final /* synthetic */ Iterable k;
    public final /* synthetic */ Object l;

    public /* synthetic */ g61(Iterable iterable, Object obj, int i) {
        this.e = i;
        this.k = iterable;
        this.l = obj;
    }

    @Override // java.lang.Iterable
    public final Iterator iterator() {
        int i = this.e;
        Object obj = this.l;
        Iterable iterable = this.k;
        switch (i) {
            case 0:
                Iterator it = iterable.iterator();
                uw1 uw1Var = (uw1) obj;
                it.getClass();
                uw1Var.getClass();
                return new com.google.common.collect.c(it, uw1Var);
            default:
                Iterator it2 = iterable.iterator();
                oz0 oz0Var = (oz0) obj;
                oz0Var.getClass();
                return new h61(it2, oz0Var);
        }
    }
}

package p;

import java.util.AbstractList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public final class t51 extends AbstractList {
    public final List c;
    public final s51 e;

    public t51(r51 r51Var, s51 s51Var) {
        this.c = r51Var;
        this.e = s51Var;
    }

    @Override // java.util.AbstractList, java.util.List
    public final Object get(int i) {
        return this.e.convert(this.c.get(i));
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public final int size() {
        return this.c.size();
    }
}

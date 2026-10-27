package p;

import java.util.RandomAccess;

/* JADX INFO: loaded from: classes.dex */
public final class o1 extends p1 implements RandomAccess {
    public final p1 c;
    public final int e;
    public final int k;

    public o1(p1 p1Var, int i, int i2) {
        v03.j(p1Var, "list");
        this.c = p1Var;
        this.e = i;
        u11.t(i, i2, p1Var.size());
        this.k = i2 - i;
    }

    @Override // kotlin.collections.a
    public final int a() {
        return this.k;
    }

    @Override // java.util.List
    public final Object get(int i) {
        int i2 = this.k;
        if (i < 0 || i >= i2) {
            throw new IndexOutOfBoundsException(hf2.c("index: ", i, ", size: ", i2));
        }
        return this.c.get(this.e + i);
    }
}

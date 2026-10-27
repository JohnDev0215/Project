package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class jb0 implements jb2, kb0 {
    public final jb2 a;
    public final int b;

    public jb0(jb2 jb2Var, int i) {
        v03.j(jb2Var, "sequence");
        this.a = jb2Var;
        this.b = i;
        if (i >= 0) {
            return;
        }
        throw new IllegalArgumentException(("count must be non-negative, but was " + i + '.').toString());
    }

    @Override // p.kb0
    public final jb2 a(int i) {
        int i2 = this.b + i;
        return i2 < 0 ? new jb0(this, i) : new jb0(this.a, i2);
    }

    @Override // p.jb2
    public final Iterator iterator() {
        return new j41(this);
    }
}

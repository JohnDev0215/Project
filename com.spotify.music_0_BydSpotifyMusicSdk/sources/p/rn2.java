package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class rn2 implements jb2 {
    public final jb2 a;
    public final ry0 b;

    public rn2(jb2 jb2Var, ry0 ry0Var) {
        this.a = jb2Var;
        this.b = ry0Var;
    }

    @Override // p.jb2
    public final Iterator iterator() {
        return new qn2(this);
    }
}

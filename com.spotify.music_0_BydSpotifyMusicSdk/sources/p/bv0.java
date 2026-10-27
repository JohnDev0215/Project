package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class bv0 implements jb2 {
    public final jb2 a;
    public final ry0 b;
    public final ry0 c;

    public bv0(jb2 jb2Var, ry0 ry0Var, ry0 ry0Var2) {
        v03.j(jb2Var, "sequence");
        v03.j(ry0Var, "transformer");
        v03.j(ry0Var2, "iterator");
        this.a = jb2Var;
        this.b = ry0Var;
        this.c = ry0Var2;
    }

    @Override // p.jb2
    public final Iterator iterator() {
        return new av0(this);
    }
}

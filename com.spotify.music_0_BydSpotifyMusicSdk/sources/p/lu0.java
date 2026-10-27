package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class lu0 implements jb2 {
    public final jb2 a;
    public final boolean b;
    public final ry0 c;

    public lu0(sb sbVar, ry0 ry0Var) {
        v03.j(ry0Var, "predicate");
        this.a = sbVar;
        this.b = true;
        this.c = ry0Var;
    }

    @Override // p.jb2
    public final Iterator iterator() {
        return new ku0(this);
    }
}

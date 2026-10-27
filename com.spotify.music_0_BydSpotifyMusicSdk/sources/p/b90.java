package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class b90 implements jb2 {
    public final CharSequence a;
    public final int b;
    public final int c;
    public final fz0 d;

    public b90(CharSequence charSequence, int i, int i2, fz0 fz0Var) {
        v03.j(charSequence, "input");
        this.a = charSequence;
        this.b = i;
        this.c = i2;
        this.d = fz0Var;
    }

    @Override // p.jb2
    public final Iterator iterator() {
        return new a90(this);
    }
}

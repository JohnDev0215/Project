package p;

import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class sb implements jb2 {
    public final /* synthetic */ int a;
    public final /* synthetic */ Object b;

    public /* synthetic */ sb(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    @Override // p.jb2
    public final Iterator iterator() {
        int i = this.a;
        Object obj = this.b;
        switch (i) {
            case 0:
                return bk0.K((Object[]) obj);
            case 1:
                return ((Iterable) obj).iterator();
            case 2:
                fz0 fz0Var = (fz0) obj;
                v03.j(fz0Var, "block");
                kb2 kb2Var = new kb2();
                kb2Var.k = vr2.c(kb2Var, kb2Var, fz0Var);
                return kb2Var;
            default:
                return (Iterator) obj;
        }
    }
}

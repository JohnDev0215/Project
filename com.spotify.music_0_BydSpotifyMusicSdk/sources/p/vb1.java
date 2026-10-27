package p;

/* JADX INFO: loaded from: classes.dex */
public final class vb1 extends iu2 {
    public static final po d = new po();
    public final pe2 c = new pe2();

    @Override // p.iu2
    public final void a() {
        pe2 pe2Var = this.c;
        int i = pe2Var.k;
        if (i > 0) {
            u4.z(pe2Var.e[0]);
            throw null;
        }
        Object[] objArr = pe2Var.e;
        for (int i2 = 0; i2 < i; i2++) {
            objArr[i2] = null;
        }
        pe2Var.k = 0;
    }
}

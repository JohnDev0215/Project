package p;

/* JADX INFO: loaded from: classes.dex */
public final class ru implements pl2 {
    public final pl2[] a;

    public ru(pl2... pl2VarArr) {
        this.a = pl2VarArr;
    }

    @Override // p.pl2
    public final void a(vk2 vk2Var) {
        v03.j(vk2Var, "measurement");
        for (pl2 pl2Var : this.a) {
            pl2Var.a(vk2Var);
        }
    }
}

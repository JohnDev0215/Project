package defpackage;

/* JADX INFO: compiled from: DimensionDependency.java */
/* JADX INFO: loaded from: classes.dex */
public class ca0 extends o80 {
    public int m;

    public ca0(gt3 gt3Var) {
        super(gt3Var);
        if (gt3Var instanceof tv0) {
            this.e = 2;
        } else {
            this.e = 3;
        }
    }

    @Override // defpackage.o80
    public final void d(int i) {
        if (this.j) {
            return;
        }
        this.j = true;
        this.g = i;
        for (m80 m80Var : this.k) {
            m80Var.a(m80Var);
        }
    }
}

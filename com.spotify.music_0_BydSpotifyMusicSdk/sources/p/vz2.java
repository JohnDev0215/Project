package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class vz2 implements Runnable {
    public final j23 c;

    public vz2() {
        this.c = null;
    }

    public abstract void a();

    @Override // java.lang.Runnable
    public final void run() {
        try {
            a();
        } catch (Exception e) {
            j23 j23Var = this.c;
            if (j23Var != null) {
                j23Var.a(e);
            }
        }
    }

    public vz2(j23 j23Var) {
        this.c = j23Var;
    }
}

package p;

/* JADX INFO: loaded from: classes.dex */
public final class x02 {
    public final aa0 a;

    public x02(aa0 aa0Var) {
        this.a = aa0Var;
    }

    public final y02 a() {
        ca0 ca0VarQ;
        aa0 aa0Var = this.a;
        coil.disk.a aVar = (coil.disk.a) aa0Var.d;
        synchronized (aVar) {
            aa0Var.a(true);
            ca0VarQ = aVar.q(((ba0) aa0Var.b).a);
        }
        if (ca0VarQ != null) {
            return new y02(ca0VarQ);
        }
        return null;
    }
}

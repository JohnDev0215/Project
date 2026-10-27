package p;

import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class ry2 implements Runnable {
    public final sy2 c;
    public final rx2 e;

    public ry2(sy2 sy2Var, rx2 rx2Var) {
        this.c = sy2Var;
        this.e = rx2Var;
    }

    @Override // java.lang.Runnable
    public final void run() {
        synchronized (this.c.d) {
            try {
                if (((ry2) this.c.b.remove(this.e)) != null) {
                    qy2 qy2Var = (qy2) this.c.c.remove(this.e);
                    if (qy2Var != null) {
                        rx2 rx2Var = this.e;
                        q80 q80Var = (q80) qy2Var;
                        vc1 vc1VarC = vc1.c();
                        Objects.toString(rx2Var);
                        vc1VarC.getClass();
                        q80Var.f171p.execute(new p80(q80Var, 4));
                    }
                } else {
                    vc1 vc1VarC2 = vc1.c();
                    String.format("Timer with %s is already marked as complete.", this.e);
                    vc1VarC2.getClass();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

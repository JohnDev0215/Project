package p;

import java.util.HashMap;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class sy2 {
    public final q70 a;
    public final HashMap b = new HashMap();
    public final HashMap c = new HashMap();
    public final Object d = new Object();

    static {
        vc1.d("WorkTimer");
    }

    public sy2(q70 q70Var) {
        this.a = q70Var;
    }

    public final void a(rx2 rx2Var) {
        synchronized (this.d) {
            try {
                if (((ry2) this.b.remove(rx2Var)) != null) {
                    vc1 vc1VarC = vc1.c();
                    Objects.toString(rx2Var);
                    vc1VarC.getClass();
                    this.c.remove(rx2Var);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}

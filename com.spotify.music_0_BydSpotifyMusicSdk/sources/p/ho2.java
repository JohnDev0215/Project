package p;

import android.content.Context;

/* JADX INFO: loaded from: classes.dex */
public final class ho2 implements go2 {
    public static volatile u50 e;
    public final yt a;
    public final yt b;
    public final a72 c;
    public final jr2 d;

    public ho2(yt ytVar, yt ytVar2, a72 a72Var, jr2 jr2Var, sx2 sx2Var) {
        this.a = ytVar;
        this.b = ytVar2;
        this.c = a72Var;
        this.d = jr2Var;
        sx2Var.getClass();
        sx2Var.a.execute(new nv(12, sx2Var));
    }

    public static ho2 a() {
        u50 u50Var = e;
        if (u50Var != null) {
            return (ho2) u50Var.n.get();
        }
        throw new IllegalStateException("Not initialized!");
    }

    public static void b(Context context) {
        if (e == null) {
            synchronized (ho2.class) {
                try {
                    if (e == null) {
                        y3 y3Var = new y3();
                        context.getClass();
                        y3Var.a = context;
                        e = y3Var.b();
                    }
                } finally {
                }
            }
        }
    }
}

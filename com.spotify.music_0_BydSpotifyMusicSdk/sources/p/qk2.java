package p;

import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public abstract class qk2 {
    public static volatile hk0[] a;
    public static final hk0 b;

    static {
        new ArrayList();
        a = new hk0[0];
        b = new hk0(29);
    }

    public static hk0 a() {
        for (hk0 hk0Var : a) {
            ((ThreadLocal) hk0Var.e).set("RCS");
        }
        return b;
    }
}

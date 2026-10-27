package defpackage;

/* JADX INFO: compiled from: DefaultExecutor.kt */
/* JADX INFO: loaded from: classes.dex */
public final class r70 {
    public static final h80 a;

    /* JADX WARN: Multi-variable type inference failed */
    static {
        String property;
        h80 h80Var;
        int i = gy2.a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null ? false : Boolean.parseBoolean(property)) {
            u70 u70Var = xa0.a;
            cb1 cb1Var = eb1.a;
            cb1Var.j1();
            h80Var = !(cb1Var instanceof h80) ? q70.k : (h80) cb1Var;
        } else {
            h80Var = q70.k;
        }
        a = h80Var;
    }
}

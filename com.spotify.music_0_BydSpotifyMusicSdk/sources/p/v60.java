package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class v60 {
    public static final o80 a;

    static {
        String property;
        o80 o80Var;
        int i = pi2.a;
        try {
            property = System.getProperty("kotlinx.coroutines.main.delay");
        } catch (SecurityException unused) {
            property = null;
        }
        if (property == null || !Boolean.parseBoolean(property)) {
            o80Var = kotlinx.coroutines.c.r;
        } else {
            s70 s70Var = ha0.a;
            w10 w10Var = de1.a;
            kotlinx.coroutines.android.a aVar = ((kotlinx.coroutines.android.a) w10Var).n;
            o80Var = !(w10Var instanceof o80) ? kotlinx.coroutines.c.r : (o80) w10Var;
        }
        a = o80Var;
    }
}

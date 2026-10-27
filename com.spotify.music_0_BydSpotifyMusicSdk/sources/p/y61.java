package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class y61 {
    public static final tz2 a = new tz2("COMPLETING_ALREADY", 3);
    public static final tz2 b = new tz2("COMPLETING_WAITING_CHILDREN", 3);
    public static final tz2 c = new tz2("COMPLETING_RETRY", 3);
    public static final tz2 d = new tz2("TOO_LATE_TO_CANCEL", 3);
    public static final tz2 e = new tz2("SEALED", 3);
    public static final rd0 f = new rd0(false);
    public static final rd0 g = new rd0(true);

    public static final Object a(Object obj) {
        e41 e41Var;
        f41 f41Var = obj instanceof f41 ? (f41) obj : null;
        return (f41Var == null || (e41Var = f41Var.a) == null) ? obj : e41Var;
    }
}

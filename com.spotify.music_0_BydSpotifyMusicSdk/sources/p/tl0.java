package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class tl0 {
    public static final sl0 a = new sl0();
    public static final sl0 b;

    static {
        sl0 sl0Var;
        try {
            sl0Var = (sl0) Class.forName("com.google.protobuf.ExtensionSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            sl0Var = null;
        }
        b = sl0Var;
    }
}

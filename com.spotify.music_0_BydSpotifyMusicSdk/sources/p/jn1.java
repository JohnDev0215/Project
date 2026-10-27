package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class jn1 {
    public static final in1 a;
    public static final in1 b;

    static {
        in1 in1Var;
        try {
            in1Var = (in1) Class.forName("com.google.protobuf.NewInstanceSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            in1Var = null;
        }
        a = in1Var;
        b = new in1();
    }
}

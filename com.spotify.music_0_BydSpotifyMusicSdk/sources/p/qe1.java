package p;

/* JADX INFO: loaded from: classes.dex */
public abstract class qe1 {
    public static final pe1 a;
    public static final pe1 b;

    static {
        pe1 pe1Var;
        try {
            pe1Var = (pe1) Class.forName("com.google.protobuf.MapFieldSchemaFull").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
        } catch (Exception unused) {
            pe1Var = null;
        }
        a = pe1Var;
        b = new pe1();
    }
}

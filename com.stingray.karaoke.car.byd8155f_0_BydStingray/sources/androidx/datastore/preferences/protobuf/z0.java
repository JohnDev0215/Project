package androidx.datastore.preferences.protobuf;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EF12' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public class z0 {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final v0 f11901n;
    public static final w0 o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final x0 f11902p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final /* synthetic */ z0[] f11903q;
    public final A0 l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f11904m;

    /* JADX INFO: Fake field, exist only in values array */
    z0 EF10;

    /* JADX INFO: Fake field, exist only in values array */
    z0 EF11;

    /* JADX INFO: Fake field, exist only in values array */
    z0 EF12;

    static {
        z0 z0Var = new z0("DOUBLE", 0, A0.DOUBLE, 1);
        z0 z0Var2 = new z0("FLOAT", 1, A0.FLOAT, 5);
        A0 a02 = A0.LONG;
        z0 z0Var3 = new z0("INT64", 2, a02, 0);
        z0 z0Var4 = new z0("UINT64", 3, a02, 0);
        A0 a03 = A0.INT;
        z0 z0Var5 = new z0("INT32", 4, a03, 0);
        z0 z0Var6 = new z0("FIXED64", 5, a02, 1);
        z0 z0Var7 = new z0("FIXED32", 6, a03, 5);
        z0 z0Var8 = new z0("BOOL", 7, A0.BOOLEAN, 0);
        v0 v0Var = new v0("STRING", 8, A0.STRING, 2);
        f11901n = v0Var;
        A0 a04 = A0.MESSAGE;
        w0 w0Var = new w0("GROUP", 9, a04, 3);
        o = w0Var;
        x0 x0Var = new x0("MESSAGE", 10, a04, 2);
        f11902p = x0Var;
        f11903q = new z0[]{z0Var, z0Var2, z0Var3, z0Var4, z0Var5, z0Var6, z0Var7, z0Var8, v0Var, w0Var, x0Var, new y0("BYTES", 11, A0.BYTE_STRING, 2), new z0("UINT32", 12, a03, 0), new z0("ENUM", 13, A0.ENUM, 0), new z0("SFIXED32", 14, a03, 5), new z0("SFIXED64", 15, a02, 1), new z0("SINT32", 16, a03, 0), new z0("SINT64", 17, a02, 0)};
    }

    public z0(String str, int i7, A0 a02, int i10) {
        this.l = a02;
        this.f11904m = i10;
    }

    public static z0 valueOf(String str) {
        return (z0) Enum.valueOf(z0.class, str);
    }

    public static z0[] values() {
        return (z0[]) f11903q.clone();
    }
}

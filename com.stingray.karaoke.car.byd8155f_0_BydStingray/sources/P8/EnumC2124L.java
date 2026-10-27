package p8;

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
/* JADX INFO: renamed from: p8.L, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public class EnumC2124L {

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC2124L f20950n;
    public static final EnumC2124L o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final C2121I f20951p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public static final C2122J f20952q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public static final EnumC2124L f20953r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public static final /* synthetic */ EnumC2124L[] f20954s;
    public final EnumC2125M l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final int f20955m;

    /* JADX INFO: Fake field, exist only in values array */
    EnumC2124L EF10;

    /* JADX INFO: Fake field, exist only in values array */
    EnumC2124L EF11;

    /* JADX INFO: Fake field, exist only in values array */
    EnumC2124L EF12;

    static {
        EnumC2124L enumC2124L = new EnumC2124L("DOUBLE", 0, EnumC2125M.f20958p, 1);
        EnumC2124L enumC2124L2 = new EnumC2124L("FLOAT", 1, EnumC2125M.o, 5);
        EnumC2125M enumC2125M = EnumC2125M.f20957n;
        EnumC2124L enumC2124L3 = new EnumC2124L("INT64", 2, enumC2125M, 0);
        EnumC2124L enumC2124L4 = new EnumC2124L("UINT64", 3, enumC2125M, 0);
        EnumC2125M enumC2125M2 = EnumC2125M.f20956m;
        EnumC2124L enumC2124L5 = new EnumC2124L("INT32", 4, enumC2125M2, 0);
        f20950n = enumC2124L5;
        EnumC2124L enumC2124L6 = new EnumC2124L("FIXED64", 5, enumC2125M, 1);
        EnumC2124L enumC2124L7 = new EnumC2124L("FIXED32", 6, enumC2125M2, 5);
        EnumC2124L enumC2124L8 = new EnumC2124L("BOOL", 7, EnumC2125M.f20959q, 0);
        o = enumC2124L8;
        C2120H c2120h = new C2120H("STRING", 8, EnumC2125M.f20960r, 2);
        EnumC2125M enumC2125M3 = EnumC2125M.f20963u;
        C2121I c2121i = new C2121I("GROUP", 9, enumC2125M3, 3);
        f20951p = c2121i;
        C2122J c2122j = new C2122J("MESSAGE", 10, enumC2125M3, 2);
        f20952q = c2122j;
        C2123K c2123k = new C2123K("BYTES", 11, EnumC2125M.f20961s, 2);
        EnumC2124L enumC2124L9 = new EnumC2124L("UINT32", 12, enumC2125M2, 0);
        EnumC2124L enumC2124L10 = new EnumC2124L("ENUM", 13, EnumC2125M.f20962t, 0);
        f20953r = enumC2124L10;
        f20954s = new EnumC2124L[]{enumC2124L, enumC2124L2, enumC2124L3, enumC2124L4, enumC2124L5, enumC2124L6, enumC2124L7, enumC2124L8, c2120h, c2121i, c2122j, c2123k, enumC2124L9, enumC2124L10, new EnumC2124L("SFIXED32", 14, enumC2125M2, 5), new EnumC2124L("SFIXED64", 15, enumC2125M, 1), new EnumC2124L("SINT32", 16, enumC2125M2, 0), new EnumC2124L("SINT64", 17, enumC2125M, 0)};
    }

    public EnumC2124L(String str, int i7, EnumC2125M enumC2125M, int i10) {
        this.l = enumC2125M;
        this.f20955m = i10;
    }

    public static EnumC2124L valueOf(String str) {
        return (EnumC2124L) Enum.valueOf(EnumC2124L.class, str);
    }

    public static EnumC2124L[] values() {
        return (EnumC2124L[]) f20954s.clone();
    }

    public boolean a() {
        return !(this instanceof C2120H);
    }
}

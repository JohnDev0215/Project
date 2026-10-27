package w8;

import N7.k;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.HashSet;
import o8.C2058c;

/* JADX INFO: renamed from: w8.d, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public enum EnumC2609d {
    BOOLEAN(k.BOOLEAN, "boolean", "Z", "java.lang.Boolean"),
    CHAR(k.CHAR, "char", "C", "java.lang.Character"),
    BYTE(k.BYTE, "byte", "B", "java.lang.Byte"),
    SHORT(k.SHORT, "short", "S", "java.lang.Short"),
    INT(k.INT, "int", "I", "java.lang.Integer"),
    FLOAT(k.FLOAT, "float", "F", "java.lang.Float"),
    LONG(k.LONG, "long", "J", "java.lang.Long"),
    DOUBLE(k.DOUBLE, "double", "D", "java.lang.Double");

    public final k l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final String f23359m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public final String f23360n;
    public final C2058c o;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public static final HashSet f23356x = new HashSet();

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public static final HashMap f23357y = new HashMap();

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public static final EnumMap f23358z = new EnumMap(k.class);

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public static final HashMap f23347A = new HashMap();

    static {
        for (EnumC2609d enumC2609d : values()) {
            f23356x.add(enumC2609d.e());
            f23357y.put(enumC2609d.f23359m, enumC2609d);
            f23358z.put(enumC2609d.d(), enumC2609d);
            f23347A.put(enumC2609d.c(), enumC2609d);
        }
    }

    EnumC2609d(k kVar, String str, String str2, String str3) {
        if (kVar == null) {
            a(6);
            throw null;
        }
        this.l = kVar;
        this.f23359m = str;
        this.f23360n = str2;
        this.o = new C2058c(str3);
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x000c  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void a(int r7) {
        /*
            Method dump skipped, instruction units count: 250
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w8.EnumC2609d.a(int):void");
    }

    public static EnumC2609d b(String str) {
        EnumC2609d enumC2609d = (EnumC2609d) f23357y.get(str);
        if (enumC2609d != null) {
            return enumC2609d;
        }
        throw new AssertionError("Non-primitive type name passed: ".concat(str));
    }

    public final String c() {
        String str = this.f23360n;
        if (str != null) {
            return str;
        }
        a(12);
        throw null;
    }

    public final k d() {
        k kVar = this.l;
        if (kVar != null) {
            return kVar;
        }
        a(10);
        throw null;
    }

    public final C2058c e() {
        C2058c c2058c = this.o;
        if (c2058c != null) {
            return c2058c;
        }
        a(13);
        throw null;
    }
}

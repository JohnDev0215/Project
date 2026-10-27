package androidx.datastore.preferences.protobuf;

import x.AbstractC2641j;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'EF0' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: renamed from: androidx.datastore.preferences.protobuf.q, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class EnumC0954q {

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public static final EnumC0954q f11877m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public static final EnumC0954q f11878n;
    public static final EnumC0954q[] o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public static final /* synthetic */ EnumC0954q[] f11879p;
    public final int l;

    /* JADX INFO: Fake field, exist only in values array */
    EnumC0954q EF0;

    static {
        EnumC0962z enumC0962z = EnumC0962z.DOUBLE;
        EnumC0954q enumC0954q = new EnumC0954q("DOUBLE", 0, 0, 1, enumC0962z);
        EnumC0962z enumC0962z2 = EnumC0962z.FLOAT;
        EnumC0954q enumC0954q2 = new EnumC0954q("FLOAT", 1, 1, 1, enumC0962z2);
        EnumC0962z enumC0962z3 = EnumC0962z.LONG;
        EnumC0954q enumC0954q3 = new EnumC0954q("INT64", 2, 2, 1, enumC0962z3);
        EnumC0954q enumC0954q4 = new EnumC0954q("UINT64", 3, 3, 1, enumC0962z3);
        EnumC0962z enumC0962z4 = EnumC0962z.INT;
        EnumC0954q enumC0954q5 = new EnumC0954q("INT32", 4, 4, 1, enumC0962z4);
        EnumC0954q enumC0954q6 = new EnumC0954q("FIXED64", 5, 5, 1, enumC0962z3);
        EnumC0954q enumC0954q7 = new EnumC0954q("FIXED32", 6, 6, 1, enumC0962z4);
        EnumC0962z enumC0962z5 = EnumC0962z.BOOLEAN;
        EnumC0954q enumC0954q8 = new EnumC0954q("BOOL", 7, 7, 1, enumC0962z5);
        EnumC0962z enumC0962z6 = EnumC0962z.STRING;
        EnumC0954q enumC0954q9 = new EnumC0954q("STRING", 8, 8, 1, enumC0962z6);
        EnumC0962z enumC0962z7 = EnumC0962z.MESSAGE;
        EnumC0954q enumC0954q10 = new EnumC0954q("MESSAGE", 9, 9, 1, enumC0962z7);
        EnumC0962z enumC0962z8 = EnumC0962z.BYTE_STRING;
        EnumC0954q enumC0954q11 = new EnumC0954q("BYTES", 10, 10, 1, enumC0962z8);
        EnumC0954q enumC0954q12 = new EnumC0954q("UINT32", 11, 11, 1, enumC0962z4);
        EnumC0962z enumC0962z9 = EnumC0962z.ENUM;
        EnumC0954q enumC0954q13 = new EnumC0954q("ENUM", 12, 12, 1, enumC0962z9);
        EnumC0954q enumC0954q14 = new EnumC0954q("SFIXED32", 13, 13, 1, enumC0962z4);
        EnumC0954q enumC0954q15 = new EnumC0954q("SFIXED64", 14, 14, 1, enumC0962z3);
        EnumC0954q enumC0954q16 = new EnumC0954q("SINT32", 15, 15, 1, enumC0962z4);
        EnumC0954q enumC0954q17 = new EnumC0954q("SINT64", 16, 16, 1, enumC0962z3);
        EnumC0954q enumC0954q18 = new EnumC0954q("GROUP", 17, 17, 1, enumC0962z7);
        EnumC0954q enumC0954q19 = new EnumC0954q("DOUBLE_LIST", 18, 18, 2, enumC0962z);
        EnumC0954q enumC0954q20 = new EnumC0954q("FLOAT_LIST", 19, 19, 2, enumC0962z2);
        EnumC0954q enumC0954q21 = new EnumC0954q("INT64_LIST", 20, 20, 2, enumC0962z3);
        EnumC0954q enumC0954q22 = new EnumC0954q("UINT64_LIST", 21, 21, 2, enumC0962z3);
        EnumC0954q enumC0954q23 = new EnumC0954q("INT32_LIST", 22, 22, 2, enumC0962z4);
        EnumC0954q enumC0954q24 = new EnumC0954q("FIXED64_LIST", 23, 23, 2, enumC0962z3);
        EnumC0954q enumC0954q25 = new EnumC0954q("FIXED32_LIST", 24, 24, 2, enumC0962z4);
        EnumC0954q enumC0954q26 = new EnumC0954q("BOOL_LIST", 25, 25, 2, enumC0962z5);
        EnumC0954q enumC0954q27 = new EnumC0954q("STRING_LIST", 26, 26, 2, enumC0962z6);
        EnumC0954q enumC0954q28 = new EnumC0954q("MESSAGE_LIST", 27, 27, 2, enumC0962z7);
        EnumC0954q enumC0954q29 = new EnumC0954q("BYTES_LIST", 28, 28, 2, enumC0962z8);
        EnumC0954q enumC0954q30 = new EnumC0954q("UINT32_LIST", 29, 29, 2, enumC0962z4);
        EnumC0954q enumC0954q31 = new EnumC0954q("ENUM_LIST", 30, 30, 2, enumC0962z9);
        EnumC0954q enumC0954q32 = new EnumC0954q("SFIXED32_LIST", 31, 31, 2, enumC0962z4);
        EnumC0954q enumC0954q33 = new EnumC0954q("SFIXED64_LIST", 32, 32, 2, enumC0962z3);
        EnumC0954q enumC0954q34 = new EnumC0954q("SINT32_LIST", 33, 33, 2, enumC0962z4);
        EnumC0954q enumC0954q35 = new EnumC0954q("SINT64_LIST", 34, 34, 2, enumC0962z3);
        EnumC0954q enumC0954q36 = new EnumC0954q("DOUBLE_LIST_PACKED", 35, 35, 3, enumC0962z);
        f11877m = enumC0954q36;
        EnumC0954q enumC0954q37 = new EnumC0954q("FLOAT_LIST_PACKED", 36, 36, 3, enumC0962z2);
        EnumC0954q enumC0954q38 = new EnumC0954q("INT64_LIST_PACKED", 37, 37, 3, enumC0962z3);
        EnumC0954q enumC0954q39 = new EnumC0954q("UINT64_LIST_PACKED", 38, 38, 3, enumC0962z3);
        EnumC0954q enumC0954q40 = new EnumC0954q("INT32_LIST_PACKED", 39, 39, 3, enumC0962z4);
        EnumC0954q enumC0954q41 = new EnumC0954q("FIXED64_LIST_PACKED", 40, 40, 3, enumC0962z3);
        EnumC0954q enumC0954q42 = new EnumC0954q("FIXED32_LIST_PACKED", 41, 41, 3, enumC0962z4);
        EnumC0954q enumC0954q43 = new EnumC0954q("BOOL_LIST_PACKED", 42, 42, 3, enumC0962z5);
        EnumC0954q enumC0954q44 = new EnumC0954q("UINT32_LIST_PACKED", 43, 43, 3, enumC0962z4);
        EnumC0954q enumC0954q45 = new EnumC0954q("ENUM_LIST_PACKED", 44, 44, 3, enumC0962z9);
        EnumC0954q enumC0954q46 = new EnumC0954q("SFIXED32_LIST_PACKED", 45, 45, 3, enumC0962z4);
        EnumC0954q enumC0954q47 = new EnumC0954q("SFIXED64_LIST_PACKED", 46, 46, 3, enumC0962z3);
        EnumC0954q enumC0954q48 = new EnumC0954q("SINT32_LIST_PACKED", 47, 47, 3, enumC0962z4);
        EnumC0954q enumC0954q49 = new EnumC0954q("SINT64_LIST_PACKED", 48, 48, 3, enumC0962z3);
        f11878n = enumC0954q49;
        f11879p = new EnumC0954q[]{enumC0954q, enumC0954q2, enumC0954q3, enumC0954q4, enumC0954q5, enumC0954q6, enumC0954q7, enumC0954q8, enumC0954q9, enumC0954q10, enumC0954q11, enumC0954q12, enumC0954q13, enumC0954q14, enumC0954q15, enumC0954q16, enumC0954q17, enumC0954q18, enumC0954q19, enumC0954q20, enumC0954q21, enumC0954q22, enumC0954q23, enumC0954q24, enumC0954q25, enumC0954q26, enumC0954q27, enumC0954q28, enumC0954q29, enumC0954q30, enumC0954q31, enumC0954q32, enumC0954q33, enumC0954q34, enumC0954q35, enumC0954q36, enumC0954q37, enumC0954q38, enumC0954q39, enumC0954q40, enumC0954q41, enumC0954q42, enumC0954q43, enumC0954q44, enumC0954q45, enumC0954q46, enumC0954q47, enumC0954q48, enumC0954q49, new EnumC0954q("GROUP_LIST", 49, 49, 2, enumC0962z7), new EnumC0954q("MAP", 50, 50, 4, EnumC0962z.VOID)};
        EnumC0954q[] enumC0954qArrValues = values();
        o = new EnumC0954q[enumC0954qArrValues.length];
        for (EnumC0954q enumC0954q50 : enumC0954qArrValues) {
            o[enumC0954q50.l] = enumC0954q50;
        }
    }

    public EnumC0954q(String str, int i7, int i10, int i11, EnumC0962z enumC0962z) {
        this.l = i10;
        int iC = AbstractC2641j.c(i11);
        if (iC == 1 || iC == 3) {
            enumC0962z.getClass();
        }
        if (i11 == 1) {
            enumC0962z.ordinal();
        }
    }

    public static EnumC0954q valueOf(String str) {
        return (EnumC0954q) Enum.valueOf(EnumC0954q.class, str);
    }

    public static EnumC0954q[] values() {
        return (EnumC0954q[]) f11879p.clone();
    }

    public final int a() {
        return this.l;
    }
}

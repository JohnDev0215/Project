package com.google.protobuf;

/* JADX WARN: Enum visitor error
jadx.core.utils.exceptions.JadxRuntimeException: Init of enum field 'c' uses external variables
	at jadx.core.dex.visitors.EnumVisitor.createEnumFieldByConstructor(EnumVisitor.java:451)
	at jadx.core.dex.visitors.EnumVisitor.processEnumFieldByRegister(EnumVisitor.java:395)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromFilledArray(EnumVisitor.java:324)
	at jadx.core.dex.visitors.EnumVisitor.extractEnumFieldsFromInsn(EnumVisitor.java:262)
	at jadx.core.dex.visitors.EnumVisitor.convertToEnum(EnumVisitor.java:151)
	at jadx.core.dex.visitors.EnumVisitor.visit(EnumVisitor.java:100)
 */
/* JADX WARN: Failed to restore enum class, 'enum' modifier and super class removed */
/* JADX INFO: loaded from: classes.dex */
public class WireFormat$FieldType {
    public static final WireFormat$FieldType c;
    public static final WireFormat$FieldType e;
    public static final WireFormat$FieldType k;
    public static final WireFormat$FieldType l;
    public static final /* synthetic */ WireFormat$FieldType[] m;
    private final WireFormat$JavaType javaType;
    private final int wireType;

    /* JADX INFO: Fake field, exist only in values array */
    WireFormat$FieldType EF0;

    /* JADX INFO: Fake field, exist only in values array */
    WireFormat$FieldType EF1;

    /* JADX INFO: renamed from: com.google.protobuf.WireFormat$FieldType$1, reason: invalid class name */
    public enum AnonymousClass1 extends WireFormat$FieldType {
    }

    /* JADX INFO: renamed from: com.google.protobuf.WireFormat$FieldType$2, reason: invalid class name */
    public enum AnonymousClass2 extends WireFormat$FieldType {
    }

    /* JADX INFO: renamed from: com.google.protobuf.WireFormat$FieldType$3, reason: invalid class name */
    public enum AnonymousClass3 extends WireFormat$FieldType {
    }

    /* JADX INFO: renamed from: com.google.protobuf.WireFormat$FieldType$4, reason: invalid class name */
    public enum AnonymousClass4 extends WireFormat$FieldType {
    }

    static {
        WireFormat$FieldType wireFormat$FieldType = new WireFormat$FieldType("DOUBLE", 0, WireFormat$JavaType.l, 1);
        WireFormat$FieldType wireFormat$FieldType2 = new WireFormat$FieldType("FLOAT", 1, WireFormat$JavaType.k, 5);
        WireFormat$JavaType wireFormat$JavaType = WireFormat$JavaType.e;
        WireFormat$FieldType wireFormat$FieldType3 = new WireFormat$FieldType("INT64", 2, wireFormat$JavaType, 0);
        c = wireFormat$FieldType3;
        WireFormat$FieldType wireFormat$FieldType4 = new WireFormat$FieldType("UINT64", 3, wireFormat$JavaType, 0);
        WireFormat$JavaType wireFormat$JavaType2 = WireFormat$JavaType.c;
        WireFormat$FieldType wireFormat$FieldType5 = new WireFormat$FieldType("INT32", 4, wireFormat$JavaType2, 0);
        WireFormat$FieldType wireFormat$FieldType6 = new WireFormat$FieldType("FIXED64", 5, wireFormat$JavaType, 1);
        WireFormat$FieldType wireFormat$FieldType7 = new WireFormat$FieldType("FIXED32", 6, wireFormat$JavaType2, 5);
        WireFormat$FieldType wireFormat$FieldType8 = new WireFormat$FieldType("BOOL", 7, WireFormat$JavaType.m, 0);
        AnonymousClass1 anonymousClass1 = new AnonymousClass1("STRING", 8, WireFormat$JavaType.n, 2);
        e = anonymousClass1;
        WireFormat$JavaType wireFormat$JavaType3 = WireFormat$JavaType.q;
        AnonymousClass2 anonymousClass2 = new AnonymousClass2("GROUP", 9, wireFormat$JavaType3, 3);
        k = anonymousClass2;
        AnonymousClass3 anonymousClass3 = new AnonymousClass3("MESSAGE", 10, wireFormat$JavaType3, 2);
        l = anonymousClass3;
        m = new WireFormat$FieldType[]{wireFormat$FieldType, wireFormat$FieldType2, wireFormat$FieldType3, wireFormat$FieldType4, wireFormat$FieldType5, wireFormat$FieldType6, wireFormat$FieldType7, wireFormat$FieldType8, anonymousClass1, anonymousClass2, anonymousClass3, new AnonymousClass4("BYTES", 11, WireFormat$JavaType.o, 2), new WireFormat$FieldType("UINT32", 12, wireFormat$JavaType2, 0), new WireFormat$FieldType("ENUM", 13, WireFormat$JavaType.f43p, 0), new WireFormat$FieldType("SFIXED32", 14, wireFormat$JavaType2, 5), new WireFormat$FieldType("SFIXED64", 15, wireFormat$JavaType, 1), new WireFormat$FieldType("SINT32", 16, wireFormat$JavaType2, 0), new WireFormat$FieldType("SINT64", 17, wireFormat$JavaType, 0)};
    }

    public WireFormat$FieldType(String str, int i, WireFormat$JavaType wireFormat$JavaType, int i2) {
        this.javaType = wireFormat$JavaType;
        this.wireType = i2;
    }

    public static WireFormat$FieldType valueOf(String str) {
        return (WireFormat$FieldType) Enum.valueOf(WireFormat$FieldType.class, str);
    }

    public static WireFormat$FieldType[] values() {
        return (WireFormat$FieldType[]) m.clone();
    }

    public final WireFormat$JavaType a() {
        return this.javaType;
    }

    public final int b() {
        return this.wireType;
    }
}

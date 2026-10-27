package defpackage;

import com.google.protobuf.d0;
import com.google.protobuf.g;
import com.google.protobuf.s;
import com.google.protobuf.z;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: CouponPersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class b10 extends z<b10, a> implements bh1 {
    public static final int CODE_FIELD_NUMBER = 2;
    public static final int CURRENCY_CODE_FIELD_NUMBER = 3;
    private static final b10 DEFAULT_INSTANCE;
    public static final int DISCOUNT_TYPE_FIELD_NUMBER = 5;
    public static final int DISCOUNT_UNITS_FIELD_NUMBER = 4;
    public static final int NAME_FIELD_NUMBER = 1;
    private static volatile wv1<b10> PARSER;
    private int bitField0_;
    private long discountUnits_;
    private String name_ = "";
    private String code_ = "";
    private String currencyCode_ = "";
    private int discountType_ = 1;

    /* JADX INFO: compiled from: CouponPersistedTabData.java */
    public static final class a extends z.a<b10, a> implements bh1 {
        public a() {
            super(b10.DEFAULT_INSTANCE);
        }
    }

    static {
        b10 b10Var = new b10();
        DEFAULT_INSTANCE = b10Var;
        z.M(b10.class, b10Var);
    }

    public static void O(b10 b10Var, String str) {
        b10Var.getClass();
        str.getClass();
        b10Var.bitField0_ |= 2;
        b10Var.code_ = str;
    }

    public static void P(b10 b10Var, String str) {
        b10Var.getClass();
        str.getClass();
        b10Var.bitField0_ |= 4;
        b10Var.currencyCode_ = str;
    }

    public static void Q(b10 b10Var, int i) {
        b10Var.getClass();
        b10Var.discountType_ = C0175b.b(i);
        b10Var.bitField0_ |= 16;
    }

    public static void S(b10 b10Var, long j) {
        b10Var.bitField0_ |= 8;
        b10Var.discountUnits_ = j;
    }

    public static void T(b10 b10Var, String str) {
        b10Var.getClass();
        str.getClass();
        b10Var.bitField0_ |= 1;
        b10Var.name_ = str;
    }

    public static a a0() {
        return (a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    public static b10 b0(ByteBuffer byteBuffer) throws d0 {
        z zVarJ = z.J(DEFAULT_INSTANCE, g.i(byteBuffer, false), s.a());
        z.B(zVarJ);
        z.B(zVarJ);
        return (b10) zVarJ;
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဌ\u0004", new Object[]{"bitField0_", "name_", "code_", "currencyCode_", "discountUnits_", "discountType_", c10.a});
            case NEW_MUTABLE_INSTANCE:
                return new b10();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<b10> bVar = PARSER;
                if (bVar == null) {
                    synchronized (b10.class) {
                        bVar = PARSER;
                        if (bVar == null) {
                            bVar = new z.b<>(DEFAULT_INSTANCE);
                            PARSER = bVar;
                        }
                        break;
                    }
                }
                return bVar;
            default:
                throw new UnsupportedOperationException();
        }
    }

    public final String V() {
        return this.code_;
    }

    public final String W() {
        return this.currencyCode_;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0012  */
    /* JADX WARN: Removed duplicated region for block: B:15:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int X() {
        /*
            r2 = this;
            int r2 = r2.discountType_
            r0 = 1
            if (r2 == r0) goto Ld
            r1 = 2
            if (r2 == r1) goto Le
            r1 = 3
            if (r2 == r1) goto Le
            r2 = 0
            goto Lf
        Ld:
            r1 = r0
        Le:
            r2 = r1
        Lf:
            if (r2 != 0) goto L12
            goto L13
        L12:
            r0 = r2
        L13:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: defpackage.b10.X():int");
    }

    public final long Y() {
        return this.discountUnits_;
    }

    public final String Z() {
        return this.name_;
    }
}

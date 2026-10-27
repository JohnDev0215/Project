package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: PriceTracking.java */
/* JADX INFO: loaded from: classes2.dex */
public final class e42 extends z<e42, a> implements bh1 {
    public static final int AMOUNT_MICROS_FIELD_NUMBER = 2;
    public static final int CURRENCY_CODE_FIELD_NUMBER = 1;
    private static final e42 DEFAULT_INSTANCE;
    private static volatile wv1<e42> PARSER;
    private long amountMicros_;
    private int bitField0_;
    private String currencyCode_ = "";

    /* JADX INFO: compiled from: PriceTracking.java */
    public static final class a extends z.a<e42, a> implements bh1 {
        public a() {
            super(e42.DEFAULT_INSTANCE);
        }
    }

    static {
        e42 e42Var = new e42();
        DEFAULT_INSTANCE = e42Var;
        z.M(e42.class, e42Var);
    }

    public static void O(e42 e42Var, long j) {
        e42Var.bitField0_ |= 2;
        e42Var.amountMicros_ = j;
    }

    public static void P(e42 e42Var, String str) {
        e42Var.getClass();
        str.getClass();
        e42Var.bitField0_ |= 1;
        e42Var.currencyCode_ = str;
    }

    public static e42 U() {
        return DEFAULT_INSTANCE;
    }

    public static a X() {
        return (a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001", new Object[]{"bitField0_", "currencyCode_", "amountMicros_"});
            case NEW_MUTABLE_INSTANCE:
                return new e42();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<e42> bVar = PARSER;
                if (bVar == null) {
                    synchronized (e42.class) {
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

    public final long S() {
        return this.amountMicros_;
    }

    public final String T() {
        return this.currencyCode_;
    }

    public final boolean V() {
        return (this.bitField0_ & 2) != 0;
    }

    public final boolean W() {
        return (this.bitField0_ & 1) != 0;
    }
}

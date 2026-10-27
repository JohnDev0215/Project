package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: ProductPrice.java */
/* JADX INFO: loaded from: classes3.dex */
public final class d62 extends z<d62, Object> implements bh1 {
    public static final int AMOUNT_MICROS_FIELD_NUMBER = 2;
    public static final int CURRENCY_CODE_FIELD_NUMBER = 1;
    private static final d62 DEFAULT_INSTANCE;
    private static volatile wv1<d62> PARSER;
    private long amountMicros_;
    private int bitField0_;
    private String currencyCode_ = "";

    static {
        d62 d62Var = new d62();
        DEFAULT_INSTANCE = d62Var;
        z.M(d62.class, d62Var);
    }

    public static d62 Q() {
        return DEFAULT_INSTANCE;
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
                return new d62();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<d62> bVar = PARSER;
                if (bVar == null) {
                    synchronized (d62.class) {
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

    public final long O() {
        return this.amountMicros_;
    }

    public final String P() {
        return this.currencyCode_;
    }
}

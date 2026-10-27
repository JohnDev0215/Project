package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: PriceTracking.java */
/* JADX INFO: loaded from: classes2.dex */
public final class f42 extends z<f42, Object> implements bh1 {
    private static final f42 DEFAULT_INSTANCE;
    public static final int NEW_PRICE_FIELD_NUMBER = 3;
    public static final int OFFER_ID_FIELD_NUMBER = 1;
    public static final int OLD_PRICE_FIELD_NUMBER = 2;
    private static volatile wv1<f42> PARSER;
    private int bitField0_;
    private e42 newPrice_;
    private long offerId_;
    private e42 oldPrice_;

    static {
        f42 f42Var = new f42();
        DEFAULT_INSTANCE = f42Var;
        z.M(f42.class, f42Var);
    }

    public static f42 O() {
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
                return new a92(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001စ\u0000\u0002ဉ\u0001\u0003ဉ\u0002", new Object[]{"bitField0_", "offerId_", "oldPrice_", "newPrice_"});
            case NEW_MUTABLE_INSTANCE:
                return new f42();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<f42> bVar = PARSER;
                if (bVar == null) {
                    synchronized (f42.class) {
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

    public final e42 P() {
        e42 e42Var = this.newPrice_;
        return e42Var == null ? e42.U() : e42Var;
    }

    public final e42 Q() {
        e42 e42Var = this.oldPrice_;
        return e42Var == null ? e42.U() : e42Var;
    }

    public final boolean S() {
        return (this.bitField0_ & 4) != 0;
    }

    public final boolean T() {
        return (this.bitField0_ & 2) != 0;
    }
}

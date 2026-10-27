package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: PriceTracking.java */
/* JADX INFO: loaded from: classes2.dex */
public final class d42 extends z<d42, Object> implements bh1 {
    public static final int BUYABLE_PRODUCT_FIELD_NUMBER = 1;
    private static final d42 DEFAULT_INSTANCE;
    private static volatile wv1<d42> PARSER = null;
    public static final int PRODUCT_UPDATE_FIELD_NUMBER = 2;
    private int bitField0_;
    private c42 buyableProduct_;
    private f42 productUpdate_;

    static {
        d42 d42Var = new d42();
        DEFAULT_INSTANCE = d42Var;
        z.M(d42.class, d42Var);
    }

    public static d42 T(lk lkVar) {
        return (d42) z.H(DEFAULT_INSTANCE, lkVar);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဉ\u0001", new Object[]{"bitField0_", "buyableProduct_", "productUpdate_"});
            case NEW_MUTABLE_INSTANCE:
                return new d42();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<d42> bVar = PARSER;
                if (bVar == null) {
                    synchronized (d42.class) {
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

    public final c42 O() {
        c42 c42Var = this.buyableProduct_;
        return c42Var == null ? c42.P() : c42Var;
    }

    public final f42 P() {
        f42 f42Var = this.productUpdate_;
        return f42Var == null ? f42.O() : f42Var;
    }

    public final boolean Q() {
        return (this.bitField0_ & 1) != 0;
    }

    public final boolean S() {
        return (this.bitField0_ & 2) != 0;
    }
}

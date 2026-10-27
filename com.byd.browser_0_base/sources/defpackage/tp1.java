package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: Notifications.java */
/* JADX INFO: loaded from: classes2.dex */
public final class tp1 extends z<tp1, Object> implements bh1 {
    public static final int CURRENT_PRICE_FIELD_NUMBER = 4;
    private static final tp1 DEFAULT_INSTANCE;
    public static final int DESTINATION_URL_FIELD_NUMBER = 3;
    public static final int OFFER_ID_FIELD_NUMBER = 2;
    private static volatile wv1<tp1> PARSER = null;
    public static final int PREVIOUS_PRICE_FIELD_NUMBER = 5;
    public static final int PRODUCT_CLUSTER_ID_FIELD_NUMBER = 6;
    public static final int PRODUCT_NAME_FIELD_NUMBER = 1;
    private int bitField0_;
    private e42 currentPrice_;
    private long offerId_;
    private e42 previousPrice_;
    private long productClusterId_;
    private String productName_ = "";
    private String destinationUrl_ = "";

    static {
        tp1 tp1Var = new tp1();
        DEFAULT_INSTANCE = tp1Var;
        z.M(tp1.class, tp1Var);
    }

    public static tp1 b0(lk lkVar) {
        return (tp1) z.H(DEFAULT_INSTANCE, lkVar);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဃ\u0001\u0003ဈ\u0002\u0004ဉ\u0003\u0005ဉ\u0004\u0006ဃ\u0005", new Object[]{"bitField0_", "productName_", "offerId_", "destinationUrl_", "currentPrice_", "previousPrice_", "productClusterId_"});
            case NEW_MUTABLE_INSTANCE:
                return new tp1();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<tp1> bVar = PARSER;
                if (bVar == null) {
                    synchronized (tp1.class) {
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

    public final e42 O() {
        e42 e42Var = this.currentPrice_;
        return e42Var == null ? e42.U() : e42Var;
    }

    public final String P() {
        return this.destinationUrl_;
    }

    public final long Q() {
        return this.offerId_;
    }

    public final e42 S() {
        e42 e42Var = this.previousPrice_;
        return e42Var == null ? e42.U() : e42Var;
    }

    public final long T() {
        return this.productClusterId_;
    }

    public final String U() {
        return this.productName_;
    }

    public final boolean V() {
        return (this.bitField0_ & 8) != 0;
    }

    public final boolean W() {
        return (this.bitField0_ & 4) != 0;
    }

    public final boolean X() {
        return (this.bitField0_ & 2) != 0;
    }

    public final boolean Y() {
        return (this.bitField0_ & 16) != 0;
    }

    public final boolean Z() {
        return (this.bitField0_ & 32) != 0;
    }

    public final boolean a0() {
        return (this.bitField0_ & 1) != 0;
    }
}

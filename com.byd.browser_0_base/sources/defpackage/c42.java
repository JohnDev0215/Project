package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: PriceTracking.java */
/* JADX INFO: loaded from: classes2.dex */
public final class c42 extends z<c42, Object> implements bh1 {
    public static final int COUNTRY_CODE_FIELD_NUMBER = 7;
    public static final int CURRENT_PRICE_FIELD_NUMBER = 3;
    private static final c42 DEFAULT_INSTANCE;
    public static final int IMAGE_URL_FIELD_NUMBER = 2;
    public static final int OFFER_ID_FIELD_NUMBER = 5;
    private static volatile wv1<c42> PARSER = null;
    public static final int PRODUCT_CLUSTER_ID_FIELD_NUMBER = 6;
    public static final int REFERENCE_TYPE_FIELD_NUMBER = 4;
    public static final int TITLE_FIELD_NUMBER = 1;
    private int bitField0_;
    private e42 currentPrice_;
    private long offerId_;
    private long productClusterId_;
    private int referenceType_;
    private String title_ = "";
    private String imageUrl_ = "";
    private String countryCode_ = "";

    static {
        c42 c42Var = new c42();
        DEFAULT_INSTANCE = c42Var;
        z.M(c42.class, c42Var);
    }

    public static c42 P() {
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
                return new a92(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဌ\u0003\u0005စ\u0004\u0006ဃ\u0005\u0007ဈ\u0006", new Object[]{"bitField0_", "title_", "imageUrl_", "currentPrice_", "referenceType_", b42.a, "offerId_", "productClusterId_", "countryCode_"});
            case NEW_MUTABLE_INSTANCE:
                return new c42();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<c42> bVar = PARSER;
                if (bVar == null) {
                    synchronized (c42.class) {
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

    public final long Q() {
        return this.offerId_;
    }

    public final boolean S() {
        return (this.bitField0_ & 4) != 0;
    }
}

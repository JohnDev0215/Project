package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: ShoppingSpecifics.java */
/* JADX INFO: loaded from: classes3.dex */
public final class zl2 extends z<zl2, Object> implements bh1 {
    public static final int BOOKMARK_CREATED_BY_PRICE_TRACKING_FIELD_NUMBER = 10;
    public static final int COUNTRY_CODE_FIELD_NUMBER = 7;
    public static final int CURRENT_PRICE_FIELD_NUMBER = 3;
    private static final zl2 DEFAULT_INSTANCE;
    public static final int IMAGE_URL_FIELD_NUMBER = 2;
    public static final int IS_PRICE_TRACKED_FIELD_NUMBER = 5;
    public static final int LAST_SUBSCRIPTION_CHANGE_TIME_FIELD_NUMBER = 9;
    public static final int OFFER_ID_FIELD_NUMBER = 6;
    private static volatile wv1<zl2> PARSER = null;
    public static final int PREVIOUS_PRICE_FIELD_NUMBER = 8;
    public static final int PRODUCT_CLUSTER_ID_FIELD_NUMBER = 4;
    public static final int TITLE_FIELD_NUMBER = 1;
    private int bitField0_;
    private boolean bookmarkCreatedByPriceTracking_;
    private d62 currentPrice_;
    private boolean isPriceTracked_;
    private long lastSubscriptionChangeTime_;
    private long offerId_;
    private d62 previousPrice_;
    private long productClusterId_;
    private String title_ = "";
    private String imageUrl_ = "";
    private String countryCode_ = "";

    static {
        zl2 zl2Var = new zl2();
        DEFAULT_INSTANCE = zl2Var;
        z.M(zl2.class, zl2Var);
    }

    public static zl2 Q() {
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
                return new a92(DEFAULT_INSTANCE, "\u0001\n\u0000\u0001\u0001\n\n\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဉ\u0002\u0004ဃ\u0003\u0005ဇ\u0004\u0006စ\u0005\u0007ဈ\u0006\bဉ\u0007\tဂ\b\nဇ\t", new Object[]{"bitField0_", "title_", "imageUrl_", "currentPrice_", "productClusterId_", "isPriceTracked_", "offerId_", "countryCode_", "previousPrice_", "lastSubscriptionChangeTime_", "bookmarkCreatedByPriceTracking_"});
            case NEW_MUTABLE_INSTANCE:
                return new zl2();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<zl2> bVar = PARSER;
                if (bVar == null) {
                    synchronized (zl2.class) {
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

    public final String O() {
        return this.countryCode_;
    }

    public final d62 P() {
        d62 d62Var = this.currentPrice_;
        return d62Var == null ? d62.Q() : d62Var;
    }

    @Deprecated
    public final boolean S() {
        return this.isPriceTracked_;
    }

    public final long T() {
        return this.offerId_;
    }

    public final d62 U() {
        d62 d62Var = this.previousPrice_;
        return d62Var == null ? d62.Q() : d62Var;
    }

    public final long V() {
        return this.productClusterId_;
    }
}

package defpackage;

import com.google.protobuf.g;
import com.google.protobuf.s;
import com.google.protobuf.z;
import java.nio.ByteBuffer;

/* JADX INFO: compiled from: ShoppingPersistedTabData.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yl2 extends z<yl2, a> implements bh1 {
    private static final yl2 DEFAULT_INSTANCE;
    public static final int IS_CURRENT_PRICE_DROP_SEEN_FIELD_NUMBER = 8;
    public static final int LAST_PRICE_CHANGE_TIME_MS_FIELD_NUMBER = 5;
    public static final int LAST_UPDATED_MS_FIELD_NUMBER = 4;
    public static final int MAIN_OFFER_ID_FIELD_NUMBER = 6;
    private static volatile wv1<yl2> PARSER = null;
    public static final int PREVIOUS_PRICE_MICROS_FIELD_NUMBER = 2;
    public static final int PRICE_CURRENCY_CODE_FIELD_NUMBER = 3;
    public static final int PRICE_MICROS_FIELD_NUMBER = 1;
    public static final int SERIALIZED_GURL_FIELD_NUMBER = 7;
    private int bitField0_;
    private boolean isCurrentPriceDropSeen_;
    private long lastPriceChangeTimeMs_;
    private long lastUpdatedMs_;
    private long previousPriceMicros_;
    private long priceMicros_;
    private String priceCurrencyCode_ = "";
    private String mainOfferId_ = "";
    private String serializedGurl_ = "";

    /* JADX INFO: compiled from: ShoppingPersistedTabData.java */
    public static final class a extends z.a<yl2, a> implements bh1 {
        public a() {
            super(yl2.DEFAULT_INSTANCE);
        }
    }

    static {
        yl2 yl2Var = new yl2();
        DEFAULT_INSTANCE = yl2Var;
        z.M(yl2.class, yl2Var);
    }

    public static void O(yl2 yl2Var, boolean z) {
        yl2Var.bitField0_ |= 128;
        yl2Var.isCurrentPriceDropSeen_ = z;
    }

    public static void P(yl2 yl2Var, long j) {
        yl2Var.bitField0_ |= 16;
        yl2Var.lastPriceChangeTimeMs_ = j;
    }

    public static void Q(yl2 yl2Var, long j) {
        yl2Var.bitField0_ |= 8;
        yl2Var.lastUpdatedMs_ = j;
    }

    public static void S(yl2 yl2Var, String str) {
        yl2Var.getClass();
        str.getClass();
        yl2Var.bitField0_ |= 32;
        yl2Var.mainOfferId_ = str;
    }

    public static void T(yl2 yl2Var, long j) {
        yl2Var.bitField0_ |= 2;
        yl2Var.previousPriceMicros_ = j;
    }

    public static void U(yl2 yl2Var, String str) {
        yl2Var.getClass();
        str.getClass();
        yl2Var.bitField0_ |= 4;
        yl2Var.priceCurrencyCode_ = str;
    }

    public static void V(yl2 yl2Var, long j) {
        yl2Var.bitField0_ |= 1;
        yl2Var.priceMicros_ = j;
    }

    public static void W(yl2 yl2Var, String str) {
        yl2Var.getClass();
        str.getClass();
        yl2Var.bitField0_ |= 64;
        yl2Var.serializedGurl_ = str;
    }

    public static a g0() {
        return (a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    public static yl2 h0(ByteBuffer byteBuffer) {
        z zVarJ = z.J(DEFAULT_INSTANCE, g.i(byteBuffer, false), s.a());
        z.B(zVarJ);
        z.B(zVarJ);
        return (yl2) zVarJ;
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001\b\b\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003ဈ\u0002\u0004ဂ\u0003\u0005ဂ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဇ\u0007", new Object[]{"bitField0_", "priceMicros_", "previousPriceMicros_", "priceCurrencyCode_", "lastUpdatedMs_", "lastPriceChangeTimeMs_", "mainOfferId_", "serializedGurl_", "isCurrentPriceDropSeen_"});
            case NEW_MUTABLE_INSTANCE:
                return new yl2();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<yl2> bVar = PARSER;
                if (bVar == null) {
                    synchronized (yl2.class) {
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

    public final boolean Y() {
        return this.isCurrentPriceDropSeen_;
    }

    public final long Z() {
        return this.lastPriceChangeTimeMs_;
    }

    public final long a0() {
        return this.lastUpdatedMs_;
    }

    public final String b0() {
        return this.mainOfferId_;
    }

    public final long c0() {
        return this.previousPriceMicros_;
    }

    public final String d0() {
        return this.priceCurrencyCode_;
    }

    public final long e0() {
        return this.priceMicros_;
    }

    public final String f0() {
        return this.serializedGurl_;
    }
}

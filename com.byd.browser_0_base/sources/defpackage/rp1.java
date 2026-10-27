package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: Notifications.java */
/* JADX INFO: loaded from: classes2.dex */
public final class rp1 extends z<rp1, Object> implements bh1 {
    public static final int CHROME_MESSAGE_FIELD_NUMBER = 1;
    private static final rp1 DEFAULT_INSTANCE;
    public static final int NOTIFICATION_DATA_FIELD_NUMBER = 3;
    public static final int NOTIFICATION_DATA_TYPE_FIELD_NUMBER = 2;
    private static volatile wv1<rp1> PARSER;
    private int bitField0_;
    private pp1 chromeMessage_;
    private int notificationDataType_;
    private lk notificationData_ = lk.f;

    static {
        rp1 rp1Var = new rp1();
        DEFAULT_INSTANCE = rp1Var;
        z.M(rp1.class, rp1Var);
    }

    public static rp1 V(byte[] bArr) {
        return (rp1) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဉ\u0000\u0002ဌ\u0001\u0003ည\u0002", new Object[]{"bitField0_", "chromeMessage_", "notificationDataType_", qp1.a, "notificationData_"});
            case NEW_MUTABLE_INSTANCE:
                return new rp1();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<rp1> bVar = PARSER;
                if (bVar == null) {
                    synchronized (rp1.class) {
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

    public final pp1 O() {
        pp1 pp1Var = this.chromeMessage_;
        return pp1Var == null ? pp1.O() : pp1Var;
    }

    public final lk P() {
        return this.notificationData_;
    }

    public final int Q() {
        int i = this.notificationDataType_;
        int i2 = i != 0 ? i != 1 ? 0 : 2 : 1;
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    public final boolean S() {
        return (this.bitField0_ & 1) != 0;
    }

    public final boolean T() {
        return (this.bitField0_ & 4) != 0;
    }

    public final boolean U() {
        return (this.bitField0_ & 2) != 0;
    }
}

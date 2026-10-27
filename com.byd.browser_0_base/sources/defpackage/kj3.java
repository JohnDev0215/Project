package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: UpdateProtos.java */
/* JADX INFO: loaded from: classes2.dex */
public final class kj3 extends z<kj3, a> implements bh1 {
    private static final kj3 DEFAULT_INSTANCE;
    private static volatile wv1<kj3> PARSER = null;
    public static final int RECORDED_SESSION_FIELD_NUMBER = 5;
    public static final int SOURCE_FIELD_NUMBER = 4;
    public static final int TIMESTAMP_MS_FIELD_NUMBER = 1;
    public static final int TYPE_FIELD_NUMBER = 3;
    public static final int VERSION_FIELD_NUMBER = 2;
    private int bitField0_;
    private boolean recordedSession_;
    private long timestampMs_;
    private String version_ = "";
    private int type_ = -1;
    private int source_ = -1;

    /* JADX INFO: compiled from: UpdateProtos.java */
    public static final class a extends z.a<kj3, a> implements bh1 {
        public a() {
            super(kj3.DEFAULT_INSTANCE);
        }
    }

    static {
        kj3 kj3Var = new kj3();
        DEFAULT_INSTANCE = kj3Var;
        z.M(kj3.class, kj3Var);
    }

    public static void O(kj3 kj3Var, boolean z) {
        kj3Var.bitField0_ |= 16;
        kj3Var.recordedSession_ = z;
    }

    public static void P(kj3 kj3Var) {
        kj3Var.getClass();
        kj3Var.source_ = gq2.b(2);
        kj3Var.bitField0_ |= 8;
    }

    public static void Q(kj3 kj3Var, long j) {
        kj3Var.bitField0_ |= 1;
        kj3Var.timestampMs_ = j;
    }

    public static void S(kj3 kj3Var) {
        kj3Var.getClass();
        kj3Var.type_ = C0175b.d(2);
        kj3Var.bitField0_ |= 4;
    }

    public static void T(kj3 kj3Var) {
        kj3Var.getClass();
        kj3Var.bitField0_ |= 2;
        kj3Var.version_ = "4.1.1.0";
    }

    public static a a0() {
        return (a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    public static kj3 b0(byte[] bArr) {
        return (kj3) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဌ\u0002\u0004ဌ\u0003\u0005ဇ\u0004", new Object[]{"bitField0_", "timestampMs_", "version_", "type_", mj3.a, "source_", lj3.a, "recordedSession_"});
            case NEW_MUTABLE_INSTANCE:
                return new kj3();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<kj3> bVar = PARSER;
                if (bVar == null) {
                    synchronized (kj3.class) {
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

    public final boolean V() {
        return this.recordedSession_;
    }

    public final int W() {
        int i;
        int i2 = this.source_;
        if (i2 != -1) {
            i = 2;
            if (i2 != 0) {
                i = i2 != 1 ? i2 != 2 ? 0 : 4 : 3;
            }
        } else {
            i = 1;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }

    public final long X() {
        return this.timestampMs_;
    }

    public final int Y() {
        int i = this.type_;
        int i2 = i != -1 ? i != 0 ? 0 : 2 : 1;
        if (i2 == 0) {
            return 1;
        }
        return i2;
    }

    public final String Z() {
        return this.version_;
    }
}

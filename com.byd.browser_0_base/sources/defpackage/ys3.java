package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: WebsiteEventProtos.java */
/* JADX INFO: loaded from: classes2.dex */
public final class ys3 extends z<ys3, a> implements bh1 {
    private static final ys3 DEFAULT_INSTANCE;
    public static final int NANOS_FIELD_NUMBER = 2;
    private static volatile wv1<ys3> PARSER = null;
    public static final int SECONDS_FIELD_NUMBER = 1;
    private int bitField0_;
    private int nanos_;
    private long seconds_;

    /* JADX INFO: compiled from: WebsiteEventProtos.java */
    public static final class a extends z.a<ys3, a> implements bh1 {
        public a() {
            super(ys3.DEFAULT_INSTANCE);
        }
    }

    static {
        ys3 ys3Var = new ys3();
        DEFAULT_INSTANCE = ys3Var;
        z.M(ys3.class, ys3Var);
    }

    public static void O(ys3 ys3Var, int i) {
        ys3Var.bitField0_ |= 2;
        ys3Var.nanos_ = i;
    }

    public static void P(ys3 ys3Var, long j) {
        ys3Var.bitField0_ |= 1;
        ys3Var.seconds_ = j;
    }

    public static ys3 S() {
        return DEFAULT_INSTANCE;
    }

    public static a V() {
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
                return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဂ\u0000\u0002င\u0001", new Object[]{"bitField0_", "seconds_", "nanos_"});
            case NEW_MUTABLE_INSTANCE:
                return new ys3();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<ys3> bVar = PARSER;
                if (bVar == null) {
                    synchronized (ys3.class) {
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

    public final int T() {
        return this.nanos_;
    }

    public final long U() {
        return this.seconds_;
    }
}

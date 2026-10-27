package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: WebsiteEventProtos.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zs3 extends z<zs3, a> implements bh1 {
    private static final zs3 DEFAULT_INSTANCE;
    public static final int FQDN_FIELD_NUMBER = 1;
    private static volatile wv1<zs3> PARSER = null;
    public static final int TIMESTAMP_FIELD_NUMBER = 2;
    public static final int TYPE_FIELD_NUMBER = 3;
    private int bitField0_;
    private String fqdn_ = "";
    private ys3 timestamp_;
    private int type_;

    /* JADX INFO: compiled from: WebsiteEventProtos.java */
    public static final class a extends z.a<zs3, a> implements bh1 {
        public a() {
            super(zs3.DEFAULT_INSTANCE);
        }
    }

    static {
        zs3 zs3Var = new zs3();
        DEFAULT_INSTANCE = zs3Var;
        z.M(zs3.class, zs3Var);
    }

    public static void O(zs3 zs3Var, String str) {
        zs3Var.getClass();
        str.getClass();
        zs3Var.bitField0_ |= 1;
        zs3Var.fqdn_ = str;
    }

    public static void P(zs3 zs3Var, ys3 ys3Var) {
        zs3Var.getClass();
        zs3Var.timestamp_ = ys3Var;
        zs3Var.bitField0_ |= 2;
    }

    public static void Q(zs3 zs3Var, int i) {
        zs3Var.getClass();
        if (i == 0) {
            throw null;
        }
        zs3Var.type_ = i - 1;
        zs3Var.bitField0_ |= 4;
    }

    public static a W() {
        return (a) ((z.a) DEFAULT_INSTANCE.D(z.f.NEW_BUILDER, null, null));
    }

    public static zs3 X(byte[] bArr) {
        return (zs3) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0001\u0001\u0003\u0003\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဉ\u0001\u0003ဌ\u0002", new Object[]{"bitField0_", "fqdn_", "timestamp_", "type_", at3.a});
            case NEW_MUTABLE_INSTANCE:
                return new zs3();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<zs3> bVar = PARSER;
                if (bVar == null) {
                    synchronized (zs3.class) {
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

    public final String T() {
        return this.fqdn_;
    }

    public final ys3 U() {
        ys3 ys3Var = this.timestamp_;
        return ys3Var == null ? ys3.S() : ys3Var;
    }

    public final int V() {
        int i;
        int i2 = this.type_;
        if (i2 != 0) {
            i = 2;
            if (i2 != 1) {
                i = i2 != 2 ? 0 : 3;
            }
        } else {
            i = 1;
        }
        if (i == 0) {
            return 1;
        }
        return i;
    }
}

package defpackage;

import com.google.protobuf.z;
import defpackage.ou0;

/* JADX INFO: compiled from: PushNotificationProto.java */
/* JADX INFO: loaded from: classes3.dex */
public final class s72 extends z<s72, a> implements bh1 {
    private static final s72 DEFAULT_INSTANCE;
    public static final int HINT_KEY_FIELD_NUMBER = 3;
    public static final int KEY_REPRESENTATION_FIELD_NUMBER = 2;
    public static final int OPTIMIZATION_TYPE_FIELD_NUMBER = 1;
    private static volatile wv1<s72> PARSER = null;
    public static final int PAYLOAD_FIELD_NUMBER = 4;
    private int bitField0_;
    private String hintKey_ = "";
    private int keyRepresentation_;
    private int optimizationType_;
    private nv payload_;

    /* JADX INFO: compiled from: PushNotificationProto.java */
    public static final class a extends z.a<s72, a> implements bh1 {
        public a() {
            super(s72.DEFAULT_INSTANCE);
        }
    }

    static {
        s72 s72Var = new s72();
        DEFAULT_INSTANCE = s72Var;
        z.M(s72.class, s72Var);
    }

    public static void O(s72 s72Var) {
        s72Var.payload_ = null;
        s72Var.bitField0_ &= -9;
    }

    public static a V(s72 s72Var) {
        DEFAULT_INSTANCE.getClass();
        a aVar = new a();
        aVar.z(s72Var);
        return aVar;
    }

    public static s72 W(byte[] bArr) {
        return (s72) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0000\u0000\u0001ဌ\u0000\u0002ဌ\u0001\u0003ဈ\u0002\u0004ဉ\u0003", new Object[]{"bitField0_", "optimizationType_", ou0.a.a, "keyRepresentation_", nu0.a, "hintKey_", "payload_"});
            case NEW_MUTABLE_INSTANCE:
                return new s72();
            case NEW_BUILDER:
                return new a();
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<s72> bVar = PARSER;
                if (bVar == null) {
                    synchronized (s72.class) {
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

    public final ou0 Q() {
        ou0 ou0VarA = ou0.a(this.optimizationType_);
        return ou0VarA == null ? ou0.TYPE_UNSPECIFIED : ou0VarA;
    }

    public final boolean S() {
        return (this.bitField0_ & 4) != 0;
    }

    public final boolean T() {
        return (this.bitField0_ & 2) != 0;
    }

    public final boolean U() {
        return (this.bitField0_ & 1) != 0;
    }
}

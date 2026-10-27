package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: Notifications.java */
/* JADX INFO: loaded from: classes2.dex */
public final class op1 extends z<op1, Object> implements bh1 {
    public static final int ACTION_ID_FIELD_NUMBER = 1;
    private static final op1 DEFAULT_INSTANCE;
    private static volatile wv1<op1> PARSER = null;
    public static final int TEXT_FIELD_NUMBER = 2;
    private int bitField0_;
    private String actionId_ = "";
    private String text_ = "";

    static {
        op1 op1Var = new op1();
        DEFAULT_INSTANCE = op1Var;
        z.M(op1.class, op1Var);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001", new Object[]{"bitField0_", "actionId_", "text_"});
            case NEW_MUTABLE_INSTANCE:
                return new op1();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<op1> bVar = PARSER;
                if (bVar == null) {
                    synchronized (op1.class) {
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
        return this.actionId_;
    }

    public final boolean P() {
        return (this.bitField0_ & 1) != 0;
    }

    public final boolean Q() {
        return (this.bitField0_ & 2) != 0;
    }
}

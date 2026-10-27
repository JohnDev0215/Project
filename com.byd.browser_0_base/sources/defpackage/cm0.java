package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: FeedUiProto.java */
/* JADX INFO: loaded from: classes2.dex */
public final class cm0 extends z<cm0, Object> implements bh1 {
    private static final cm0 DEFAULT_INSTANCE;
    private static volatile wv1<cm0> PARSER = null;
    public static final int TYPE_FIELD_NUMBER = 1;
    private int type_;

    static {
        cm0 cm0Var = new cm0();
        DEFAULT_INSTANCE = cm0Var;
        z.M(cm0.class, cm0Var);
    }

    public static cm0 O() {
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
                return new a92(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\f", new Object[]{"type_"});
            case NEW_MUTABLE_INSTANCE:
                return new cm0();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<cm0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (cm0.class) {
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

    public final int P() {
        int i = this.type_;
        int i2 = 1;
        if (i != 0) {
            if (i != 1) {
                i2 = 3;
                if (i != 2) {
                    i2 = i != 3 ? 0 : 4;
                }
            } else {
                i2 = 2;
            }
        }
        if (i2 == 0) {
            return 5;
        }
        return i2;
    }
}

package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: FeedUiProto.java */
/* JADX INFO: loaded from: classes2.dex */
public final class wl0 extends z<wl0, Object> implements bh1 {
    private static final wl0 DEFAULT_INSTANCE;
    public static final int IS_AT_TOP_FIELD_NUMBER = 1;
    private static volatile wv1<wl0> PARSER;
    private boolean isAtTop_;

    static {
        wl0 wl0Var = new wl0();
        DEFAULT_INSTANCE = wl0Var;
        z.M(wl0.class, wl0Var);
    }

    public static wl0 O() {
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
                return new a92(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\u0007", new Object[]{"isAtTop_"});
            case NEW_MUTABLE_INSTANCE:
                return new wl0();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<wl0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (wl0.class) {
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

    public final boolean P() {
        return this.isAtTop_;
    }
}

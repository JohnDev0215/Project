package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: FeedUiProto.java */
/* JADX INFO: loaded from: classes2.dex */
public final class bm0 extends z<bm0, Object> implements bh1 {
    private static final bm0 DEFAULT_INSTANCE;
    private static volatile wv1<bm0> PARSER = null;
    public static final int XSURFACE_FRAME_FIELD_NUMBER = 1;
    private lk xsurfaceFrame_ = lk.f;

    static {
        bm0 bm0Var = new bm0();
        DEFAULT_INSTANCE = bm0Var;
        z.M(bm0.class, bm0Var);
    }

    public static bm0 O() {
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
                return new a92(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\n", new Object[]{"xsurfaceFrame_"});
            case NEW_MUTABLE_INSTANCE:
                return new bm0();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<bm0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (bm0.class) {
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

    public final lk P() {
        return this.xsurfaceFrame_;
    }
}

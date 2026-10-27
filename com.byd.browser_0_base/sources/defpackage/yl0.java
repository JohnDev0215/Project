package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: FeedUiProto.java */
/* JADX INFO: loaded from: classes2.dex */
public final class yl0 extends z<yl0, Object> implements bh1 {
    private static final yl0 DEFAULT_INSTANCE;
    public static final int ID_FIELD_NUMBER = 1;
    private static volatile wv1<yl0> PARSER = null;
    public static final int XSURFACE_SHARED_STATE_FIELD_NUMBER = 2;
    private String id_ = "";
    private lk xsurfaceSharedState_ = lk.f;

    static {
        yl0 yl0Var = new yl0();
        DEFAULT_INSTANCE = yl0Var;
        z.M(yl0.class, yl0Var);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0000\u0002\u0000\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001Ȉ\u0002\n", new Object[]{"id_", "xsurfaceSharedState_"});
            case NEW_MUTABLE_INSTANCE:
                return new yl0();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<yl0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (yl0.class) {
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

    public final lk O() {
        return this.xsurfaceSharedState_;
    }
}

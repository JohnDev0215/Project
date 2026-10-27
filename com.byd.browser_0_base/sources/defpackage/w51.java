package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: LayerOuterClass.java */
/* JADX INFO: loaded from: classes3.dex */
public final class w51 extends z<w51, Object> implements bh1 {
    private static final w51 DEFAULT_INSTANCE;
    public static final int LAYER_ID_FIELD_NUMBER = 1;
    public static final int LAYER_MEMBER_ID_FIELD_NUMBER = 2;
    private static volatile wv1<w51> PARSER;
    private int bitField0_;
    private int layerId_;
    private int layerMemberId_;

    static {
        w51 w51Var = new w51();
        DEFAULT_INSTANCE = w51Var;
        z.M(w51.class, w51Var);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001", new Object[]{"bitField0_", "layerId_", "layerMemberId_"});
            case NEW_MUTABLE_INSTANCE:
                return new w51();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<w51> bVar = PARSER;
                if (bVar == null) {
                    synchronized (w51.class) {
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
}

package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: FeedUiProto.java */
/* JADX INFO: loaded from: classes2.dex */
public final class zl0 extends z<zl0, Object> implements bh1 {
    private static final zl0 DEFAULT_INSTANCE;
    public static final int LOADING_SPINNER_SLICE_FIELD_NUMBER = 4;
    private static volatile wv1<zl0> PARSER = null;
    public static final int SLICE_ID_FIELD_NUMBER = 2;
    public static final int XSURFACE_SLICE_FIELD_NUMBER = 1;
    public static final int ZERO_STATE_SLICE_FIELD_NUMBER = 3;
    private Object sliceData_;
    private int sliceDataCase_ = 0;
    private String sliceId_ = "";

    static {
        zl0 zl0Var = new zl0();
        DEFAULT_INSTANCE = zl0Var;
        z.M(zl0.class, zl0Var);
    }

    public static zl0 O() {
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
                return new a92(DEFAULT_INSTANCE, "\u0000\u0004\u0001\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001<\u0000\u0002Ȉ\u0003<\u0000\u0004<\u0000", new Object[]{"sliceData_", "sliceDataCase_", bm0.class, "sliceId_", cm0.class, wl0.class});
            case NEW_MUTABLE_INSTANCE:
                return new zl0();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<zl0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (zl0.class) {
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

    public final wl0 P() {
        return this.sliceDataCase_ == 4 ? (wl0) this.sliceData_ : wl0.O();
    }

    public final String Q() {
        return this.sliceId_;
    }

    public final bm0 S() {
        return this.sliceDataCase_ == 1 ? (bm0) this.sliceData_ : bm0.O();
    }

    public final cm0 T() {
        return this.sliceDataCase_ == 3 ? (cm0) this.sliceData_ : cm0.O();
    }

    public final boolean U() {
        return this.sliceDataCase_ == 4;
    }

    public final boolean V() {
        return this.sliceDataCase_ == 1;
    }
}

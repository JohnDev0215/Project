package defpackage;

import com.google.protobuf.c0;
import com.google.protobuf.y0;
import com.google.protobuf.z;

/* JADX INFO: compiled from: FeedUiProto.java */
/* JADX INFO: loaded from: classes2.dex */
public final class am0 extends z<am0, Object> implements bh1 {
    private static final am0 DEFAULT_INSTANCE;
    public static final int FETCH_TIME_MS_FIELD_NUMBER = 3;
    public static final int LOGGING_PARAMETERS_FIELD_NUMBER = 4;
    public static final int NEW_SHARED_STATES_FIELD_NUMBER = 2;
    private static volatile wv1<am0> PARSER = null;
    public static final int UPDATED_SLICES_FIELD_NUMBER = 1;
    private long fetchTimeMs_;
    private xl0 loggingParameters_;
    private c0.e<yl0> newSharedStates_;
    private c0.e<a> updatedSlices_;

    /* JADX INFO: compiled from: FeedUiProto.java */
    public static final class a extends z<a, Object> implements bh1 {
        private static final a DEFAULT_INSTANCE;
        private static volatile wv1<a> PARSER = null;
        public static final int SLICE_FIELD_NUMBER = 1;
        public static final int SLICE_ID_FIELD_NUMBER = 2;
        private int updateCase_ = 0;
        private Object update_;

        static {
            a aVar = new a();
            DEFAULT_INSTANCE = aVar;
            z.M(a.class, aVar);
        }

        @Override // com.google.protobuf.z
        public final Object D(z.f fVar, Object obj, Object obj2) {
            switch (fVar) {
                case GET_MEMOIZED_IS_INITIALIZED:
                    return (byte) 1;
                case SET_MEMOIZED_IS_INITIALIZED:
                    return null;
                case BUILD_MESSAGE_INFO:
                    return new a92(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002Ȼ\u0000", new Object[]{"update_", "updateCase_", zl0.class});
                case NEW_MUTABLE_INSTANCE:
                    return new a();
                case NEW_BUILDER:
                    return new z.a(DEFAULT_INSTANCE);
                case GET_DEFAULT_INSTANCE:
                    return DEFAULT_INSTANCE;
                case GET_PARSER:
                    wv1<a> bVar = PARSER;
                    if (bVar == null) {
                        synchronized (a.class) {
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

        public final zl0 O() {
            return this.updateCase_ == 1 ? (zl0) this.update_ : zl0.O();
        }

        public final String P() {
            return this.updateCase_ == 2 ? (String) this.update_ : "";
        }

        public final boolean Q() {
            return this.updateCase_ == 1;
        }
    }

    static {
        am0 am0Var = new am0();
        DEFAULT_INSTANCE = am0Var;
        z.M(am0.class, am0Var);
    }

    public am0() {
        y0<Object> y0Var = y0.h;
        this.updatedSlices_ = y0Var;
        this.newSharedStates_ = y0Var;
    }

    public static am0 T(byte[] bArr) {
        return (am0) z.I(DEFAULT_INSTANCE, bArr);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0002\u0000\u0001\u001b\u0002\u001b\u0003\u0002\u0004\t", new Object[]{"updatedSlices_", a.class, "newSharedStates_", yl0.class, "fetchTimeMs_", "loggingParameters_"});
            case NEW_MUTABLE_INSTANCE:
                return new am0();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<am0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (am0.class) {
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

    public final long O() {
        return this.fetchTimeMs_;
    }

    public final xl0 P() {
        xl0 xl0Var = this.loggingParameters_;
        return xl0Var == null ? xl0.O() : xl0Var;
    }

    public final c0.e Q() {
        return this.newSharedStates_;
    }

    public final c0.e S() {
        return this.updatedSlices_;
    }
}

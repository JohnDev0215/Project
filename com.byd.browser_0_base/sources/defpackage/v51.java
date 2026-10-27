package defpackage;

import com.google.protobuf.c0;
import com.google.protobuf.y0;
import com.google.protobuf.z;

/* JADX INFO: compiled from: LayerOuterClass.java */
/* JADX INFO: loaded from: classes3.dex */
public final class v51 extends z<v51, Object> implements bh1 {
    private static final v51 DEFAULT_INSTANCE;
    public static final int ENTROPY_MODE_FIELD_NUMBER = 5;
    public static final int ID_FIELD_NUMBER = 1;
    public static final int MEMBERS_FIELD_NUMBER = 3;
    public static final int NUM_SLOTS_FIELD_NUMBER = 2;
    private static volatile wv1<v51> PARSER = null;
    public static final int SALT_FIELD_NUMBER = 4;
    private int bitField0_;
    private int entropyMode_;
    private int id_;
    private c0.e<a> members_ = y0.h;
    private int numSlots_;
    private int salt_;

    /* JADX INFO: compiled from: LayerOuterClass.java */
    public static final class a extends z<a, Object> implements bh1 {
        private static final a DEFAULT_INSTANCE;
        public static final int ID_FIELD_NUMBER = 1;
        private static volatile wv1<a> PARSER = null;
        public static final int SLOTS_FIELD_NUMBER = 2;
        private int bitField0_;
        private int id_;
        private c0.e<C0169a> slots_ = y0.h;

        /* JADX INFO: renamed from: v51$a$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: LayerOuterClass.java */
        public static final class C0169a extends z<C0169a, Object> implements bh1 {
            private static final C0169a DEFAULT_INSTANCE;
            public static final int END_FIELD_NUMBER = 2;
            private static volatile wv1<C0169a> PARSER = null;
            public static final int START_FIELD_NUMBER = 1;
            private int bitField0_;
            private int end_;
            private int start_;

            static {
                C0169a c0169a = new C0169a();
                DEFAULT_INSTANCE = c0169a;
                z.M(C0169a.class, c0169a);
            }

            @Override // com.google.protobuf.z
            public final Object D(z.f fVar, Object obj, Object obj2) {
                switch (fVar) {
                    case GET_MEMOIZED_IS_INITIALIZED:
                        return (byte) 1;
                    case SET_MEMOIZED_IS_INITIALIZED:
                        return null;
                    case BUILD_MESSAGE_INFO:
                        return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0000\u0000\u0001ဋ\u0000\u0002ဋ\u0001", new Object[]{"bitField0_", "start_", "end_"});
                    case NEW_MUTABLE_INSTANCE:
                        return new C0169a();
                    case NEW_BUILDER:
                        return new z.a(DEFAULT_INSTANCE);
                    case GET_DEFAULT_INSTANCE:
                        return DEFAULT_INSTANCE;
                    case GET_PARSER:
                        wv1<C0169a> bVar = PARSER;
                        if (bVar == null) {
                            synchronized (C0169a.class) {
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
                    return new a92(DEFAULT_INSTANCE, "\u0001\u0002\u0000\u0001\u0001\u0002\u0002\u0000\u0001\u0000\u0001ဋ\u0000\u0002\u001b", new Object[]{"bitField0_", "id_", "slots_", C0169a.class});
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
    }

    static {
        v51 v51Var = new v51();
        DEFAULT_INSTANCE = v51Var;
        z.M(v51.class, v51Var);
    }

    @Override // com.google.protobuf.z
    public final Object D(z.f fVar, Object obj, Object obj2) {
        switch (fVar) {
            case GET_MEMOIZED_IS_INITIALIZED:
                return (byte) 1;
            case SET_MEMOIZED_IS_INITIALIZED:
                return null;
            case BUILD_MESSAGE_INFO:
                return new a92(DEFAULT_INSTANCE, "\u0001\u0005\u0000\u0001\u0001\u0005\u0005\u0000\u0001\u0000\u0001ဋ\u0000\u0002ဋ\u0001\u0003\u001b\u0004ဋ\u0002\u0005ဌ\u0003", new Object[]{"bitField0_", "id_", "numSlots_", "members_", a.class, "salt_", "entropyMode_", u51.a});
            case NEW_MUTABLE_INSTANCE:
                return new v51();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<v51> bVar = PARSER;
                if (bVar == null) {
                    synchronized (v51.class) {
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

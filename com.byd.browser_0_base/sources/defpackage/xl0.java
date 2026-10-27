package defpackage;

import com.google.protobuf.z;

/* JADX INFO: compiled from: FeedUiProto.java */
/* JADX INFO: loaded from: classes2.dex */
public final class xl0 extends z<xl0, Object> implements bh1 {
    public static final int CLIENT_INSTANCE_ID_FIELD_NUMBER = 3;
    private static final xl0 DEFAULT_INSTANCE;
    public static final int EMAIL_FIELD_NUMBER = 2;
    public static final int LOGGING_ENABLED_FIELD_NUMBER = 4;
    private static volatile wv1<xl0> PARSER = null;
    public static final int ROOT_EVENT_ID_FIELD_NUMBER = 6;
    public static final int SESSION_ID_FIELD_NUMBER = 1;
    public static final int VIEW_ACTIONS_ENABLED_FIELD_NUMBER = 5;
    private boolean loggingEnabled_;
    private boolean viewActionsEnabled_;
    private String sessionId_ = "";
    private String email_ = "";
    private String clientInstanceId_ = "";
    private lk rootEventId_ = lk.f;

    static {
        xl0 xl0Var = new xl0();
        DEFAULT_INSTANCE = xl0Var;
        z.M(xl0.class, xl0Var);
    }

    public static xl0 O() {
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
                return new a92(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0000\u0001\u0006\u0006\u0000\u0000\u0000\u0001Ȉ\u0002Ȉ\u0003Ȉ\u0004\u0007\u0005\u0007\u0006\n", new Object[]{"sessionId_", "email_", "clientInstanceId_", "loggingEnabled_", "viewActionsEnabled_", "rootEventId_"});
            case NEW_MUTABLE_INSTANCE:
                return new xl0();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<xl0> bVar = PARSER;
                if (bVar == null) {
                    synchronized (xl0.class) {
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
        return this.rootEventId_;
    }
}

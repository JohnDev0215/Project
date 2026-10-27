package defpackage;

import com.google.protobuf.c0;
import com.google.protobuf.y0;
import com.google.protobuf.z;

/* JADX INFO: compiled from: Notifications.java */
/* JADX INFO: loaded from: classes2.dex */
public final class sp1 extends z<sp1, Object> implements bh1 {
    public static final int ACTION_FIELD_NUMBER = 1;
    private static final sp1 DEFAULT_INSTANCE;
    private static volatile wv1<sp1> PARSER;
    private c0.e<op1> action_ = y0.h;

    static {
        sp1 sp1Var = new sp1();
        DEFAULT_INSTANCE = sp1Var;
        z.M(sp1.class, sp1Var);
    }

    public static sp1 P() {
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
                return new a92(DEFAULT_INSTANCE, "\u0001\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0001\u0000\u0001\u001b", new Object[]{"action_", op1.class});
            case NEW_MUTABLE_INSTANCE:
                return new sp1();
            case NEW_BUILDER:
                return new z.a(DEFAULT_INSTANCE);
            case GET_DEFAULT_INSTANCE:
                return DEFAULT_INSTANCE;
            case GET_PARSER:
                wv1<sp1> bVar = PARSER;
                if (bVar == null) {
                    synchronized (sp1.class) {
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

    public final c0.e O() {
        return this.action_;
    }
}

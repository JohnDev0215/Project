package com.spotify.messages;

import com.google.protobuf.ByteString;
import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.a2;
import p.ds1;
import p.e51;
import p.i01;
import p.lj0;
import p.r51;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EventSenderInternalError2NonAuth extends i implements yj1 {
    private static final EventSenderInternalError2NonAuth DEFAULT_INSTANCE;
    public static final int ERROR_TOTAL_COUNTS_FIELD_NUMBER = 2;
    public static final int ERROR_TYPES_FIELD_NUMBER = 1;
    public static final int ERROR_UNREPORTED_COUNTS_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER;
    private v51 errorTypes_ = i.emptyProtobufList();
    private r51 errorTotalCounts_ = i.emptyIntList();
    private r51 errorUnreportedCounts_ = i.emptyIntList();

    static {
        EventSenderInternalError2NonAuth eventSenderInternalError2NonAuth = new EventSenderInternalError2NonAuth();
        DEFAULT_INSTANCE = eventSenderInternalError2NonAuth;
        i.registerDefaultInstance(EventSenderInternalError2NonAuth.class, eventSenderInternalError2NonAuth);
    }

    private EventSenderInternalError2NonAuth() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void e(EventSenderInternalError2NonAuth eventSenderInternalError2NonAuth, int i) {
        r51 r51Var = eventSenderInternalError2NonAuth.errorUnreportedCounts_;
        if (!((a2) r51Var).c) {
            eventSenderInternalError2NonAuth.errorUnreportedCounts_ = i.mutableCopy(r51Var);
        }
        ((e51) eventSenderInternalError2NonAuth.errorUnreportedCounts_).c(i);
    }

    public static void f(EventSenderInternalError2NonAuth eventSenderInternalError2NonAuth, String str) {
        eventSenderInternalError2NonAuth.getClass();
        str.getClass();
        v51 v51Var = eventSenderInternalError2NonAuth.errorTypes_;
        if (!((a2) v51Var).c) {
            eventSenderInternalError2NonAuth.errorTypes_ = i.mutableCopy(v51Var);
        }
        eventSenderInternalError2NonAuth.errorTypes_.add(str);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void g(EventSenderInternalError2NonAuth eventSenderInternalError2NonAuth, int i) {
        r51 r51Var = eventSenderInternalError2NonAuth.errorTotalCounts_;
        if (!((a2) r51Var).c) {
            eventSenderInternalError2NonAuth.errorTotalCounts_ = i.mutableCopy(r51Var);
        }
        ((e51) eventSenderInternalError2NonAuth.errorTotalCounts_).c(i);
    }

    public static lj0 l() {
        return (lj0) DEFAULT_INSTANCE.createBuilder();
    }

    public static EventSenderInternalError2NonAuth m(ByteString byteString) {
        return (EventSenderInternalError2NonAuth) i.parseFrom(DEFAULT_INSTANCE, byteString);
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0003\u0000\u0001\u001a\u0002\u0016\u0003\u0016", new Object[]{"errorTypes_", "errorTotalCounts_", "errorUnreportedCounts_"});
            case 3:
                return new EventSenderInternalError2NonAuth();
            case 4:
                return new lj0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EventSenderInternalError2NonAuth.class) {
                        try {
                            i01Var = PARSER;
                            if (i01Var == null) {
                                i01Var = new i01(DEFAULT_INSTANCE);
                                PARSER = i01Var;
                            }
                        } finally {
                        }
                        break;
                    }
                }
                return i01Var;
            default:
                throw new UnsupportedOperationException();
        }
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final int h() {
        return ((e51) this.errorTotalCounts_).k;
    }

    public final r51 i() {
        return this.errorTotalCounts_;
    }

    public final int j() {
        return this.errorTypes_.size();
    }

    public final v51 k() {
        return this.errorTypes_;
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}

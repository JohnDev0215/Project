package com.spotify.bootstrap.v1.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Bootstrap$TrialsFacadeResponseWrapperV1 extends i implements yj1 {
    private static final Bootstrap$TrialsFacadeResponseWrapperV1 DEFAULT_INSTANCE;
    public static final int ERROR_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int SUCCESS_FIELD_NUMBER = 1;
    private int resultCase_ = 0;
    private Object result_;

    public static final class TrialsFacadeResponseWrapperError extends i implements yj1 {
        private static final TrialsFacadeResponseWrapperError DEFAULT_INSTANCE;
        public static final int ERROR_CODE_FIELD_NUMBER = 1;
        public static final int LOG_ID_FIELD_NUMBER = 3;
        public static final int MESSAGE_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER;
        private int errorCode_;
        private String message_ = "";
        private String logId_ = "";

        static {
            TrialsFacadeResponseWrapperError trialsFacadeResponseWrapperError = new TrialsFacadeResponseWrapperError();
            DEFAULT_INSTANCE = trialsFacadeResponseWrapperError;
            i.registerDefaultInstance(TrialsFacadeResponseWrapperError.class, trialsFacadeResponseWrapperError);
        }

        private TrialsFacadeResponseWrapperError() {
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0004\u0002Ȉ\u0003Ȉ", new Object[]{"errorCode_", "message_", "logId_"});
                case 3:
                    return new TrialsFacadeResponseWrapperError();
                case 4:
                    return new c(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (TrialsFacadeResponseWrapperError.class) {
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

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    public static final class TrialsFacadeResponseWrapperSuccess extends i implements yj1 {
        private static final TrialsFacadeResponseWrapperSuccess DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;

        static {
            TrialsFacadeResponseWrapperSuccess trialsFacadeResponseWrapperSuccess = new TrialsFacadeResponseWrapperSuccess();
            DEFAULT_INSTANCE = trialsFacadeResponseWrapperSuccess;
            i.registerDefaultInstance(TrialsFacadeResponseWrapperSuccess.class, trialsFacadeResponseWrapperSuccess);
        }

        private TrialsFacadeResponseWrapperSuccess() {
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0000", null);
                case 3:
                    return new TrialsFacadeResponseWrapperSuccess();
                case 4:
                    return new d(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (TrialsFacadeResponseWrapperSuccess.class) {
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

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    static {
        Bootstrap$TrialsFacadeResponseWrapperV1 bootstrap$TrialsFacadeResponseWrapperV1 = new Bootstrap$TrialsFacadeResponseWrapperV1();
        DEFAULT_INSTANCE = bootstrap$TrialsFacadeResponseWrapperV1;
        i.registerDefaultInstance(Bootstrap$TrialsFacadeResponseWrapperV1.class, bootstrap$TrialsFacadeResponseWrapperV1);
    }

    private Bootstrap$TrialsFacadeResponseWrapperV1() {
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"result_", "resultCase_", TrialsFacadeResponseWrapperSuccess.class, TrialsFacadeResponseWrapperError.class});
            case 3:
                return new Bootstrap$TrialsFacadeResponseWrapperV1();
            case 4:
                return new b(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Bootstrap$TrialsFacadeResponseWrapperV1.class) {
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

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}

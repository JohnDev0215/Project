package com.spotify.bootstrap.v1.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import com.spotify.ucs.v0.proto.Ucs$UcsResponseWrapper;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class Bootstrap$UcsResponseWrapperV0 extends i implements yj1 {
    private static final Bootstrap$UcsResponseWrapperV0 DEFAULT_INSTANCE;
    public static final int ERROR_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int SUCCESS_FIELD_NUMBER = 1;
    private int resultCase_ = 0;
    private Object result_;

    public enum ResultCase {
        SUCCESS(1),
        ERROR(2),
        RESULT_NOT_SET(0);

        private final int value;

        ResultCase(int i) {
            this.value = i;
        }
    }

    public static final class UcsResponseWrapperError extends i implements yj1 {
        private static final UcsResponseWrapperError DEFAULT_INSTANCE;
        public static final int ERROR_CODE_FIELD_NUMBER = 1;
        public static final int LOG_ID_FIELD_NUMBER = 3;
        public static final int MESSAGE_FIELD_NUMBER = 2;
        private static volatile ds1 PARSER;
        private int errorCode_;
        private String message_ = "";
        private String logId_ = "";

        static {
            UcsResponseWrapperError ucsResponseWrapperError = new UcsResponseWrapperError();
            DEFAULT_INSTANCE = ucsResponseWrapperError;
            i.registerDefaultInstance(UcsResponseWrapperError.class, ucsResponseWrapperError);
        }

        private UcsResponseWrapperError() {
        }

        public static UcsResponseWrapperError e() {
            return DEFAULT_INSTANCE;
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
                    return new UcsResponseWrapperError();
                case 4:
                    return new f(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (UcsResponseWrapperError.class) {
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

        public final String getMessage() {
            return this.message_;
        }

        @Override // com.google.protobuf.i, p.wj1
        public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
            return super.newBuilderForType();
        }
    }

    public static final class UcsResponseWrapperSuccess extends i implements yj1 {
        public static final int CUSTOMIZATION_FIELD_NUMBER = 1;
        private static final UcsResponseWrapperSuccess DEFAULT_INSTANCE;
        private static volatile ds1 PARSER;
        private Ucs$UcsResponseWrapper customization_;

        static {
            UcsResponseWrapperSuccess ucsResponseWrapperSuccess = new UcsResponseWrapperSuccess();
            DEFAULT_INSTANCE = ucsResponseWrapperSuccess;
            i.registerDefaultInstance(UcsResponseWrapperSuccess.class, ucsResponseWrapperSuccess);
        }

        private UcsResponseWrapperSuccess() {
        }

        public static UcsResponseWrapperSuccess f() {
            return DEFAULT_INSTANCE;
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
                    return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0001\u0000\u0000\u0001\u0001\u0001\u0000\u0000\u0000\u0001\t", new Object[]{"customization_"});
                case 3:
                    return new UcsResponseWrapperSuccess();
                case 4:
                    return new g(DEFAULT_INSTANCE);
                case 5:
                    return DEFAULT_INSTANCE;
                case 6:
                    ds1 i01Var = PARSER;
                    if (i01Var == null) {
                        synchronized (UcsResponseWrapperSuccess.class) {
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

        public final Ucs$UcsResponseWrapper e() {
            Ucs$UcsResponseWrapper ucs$UcsResponseWrapper = this.customization_;
            return ucs$UcsResponseWrapper == null ? Ucs$UcsResponseWrapper.e() : ucs$UcsResponseWrapper;
        }

        public final boolean g() {
            return this.customization_ != null;
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
        Bootstrap$UcsResponseWrapperV0 bootstrap$UcsResponseWrapperV0 = new Bootstrap$UcsResponseWrapperV0();
        DEFAULT_INSTANCE = bootstrap$UcsResponseWrapperV0;
        i.registerDefaultInstance(Bootstrap$UcsResponseWrapperV0.class, bootstrap$UcsResponseWrapperV0);
    }

    private Bootstrap$UcsResponseWrapperV0() {
    }

    public static Bootstrap$UcsResponseWrapperV0 e() {
        return DEFAULT_INSTANCE;
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0002\u0001\u0000\u0001\u0002\u0002\u0000\u0000\u0000\u0001<\u0000\u0002<\u0000", new Object[]{"result_", "resultCase_", UcsResponseWrapperSuccess.class, UcsResponseWrapperError.class});
            case 3:
                return new Bootstrap$UcsResponseWrapperV0();
            case 4:
                return new e(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (Bootstrap$UcsResponseWrapperV0.class) {
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

    public final UcsResponseWrapperError f() {
        return this.resultCase_ == 2 ? (UcsResponseWrapperError) this.result_ : UcsResponseWrapperError.e();
    }

    public final ResultCase g() {
        int i = this.resultCase_;
        if (i == 0) {
            return ResultCase.RESULT_NOT_SET;
        }
        if (i == 1) {
            return ResultCase.SUCCESS;
        }
        if (i != 2) {
            return null;
        }
        return ResultCase.ERROR;
    }

    @Override // com.google.protobuf.i, p.yj1
    public final /* bridge */ /* synthetic */ wj1 getDefaultInstanceForType() {
        return super.getDefaultInstanceForType();
    }

    public final UcsResponseWrapperSuccess h() {
        return this.resultCase_ == 1 ? (UcsResponseWrapperSuccess) this.result_ : UcsResponseWrapperSuccess.f();
    }

    @Override // com.google.protobuf.i, p.wj1
    public final /* bridge */ /* synthetic */ vj1 newBuilderForType() {
        return super.newBuilderForType();
    }
}

package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.dg0;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPause$PauseRequest extends i implements yj1 {
    private static final EsPause$PauseRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 2;
    public static final int OPTIONS_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int PAUSE_ORIGIN_FIELD_NUMBER = 3;
    private EsLoggingParams$LoggingParams loggingParams_;
    private EsCommandOptions$CommandOptions options_;
    private EsPauseresumeOrigin$PauseResumeOrigin pauseOrigin_;

    static {
        EsPause$PauseRequest esPause$PauseRequest = new EsPause$PauseRequest();
        DEFAULT_INSTANCE = esPause$PauseRequest;
        i.registerDefaultInstance(EsPause$PauseRequest.class, esPause$PauseRequest);
    }

    private EsPause$PauseRequest() {
    }

    public static void e(EsPause$PauseRequest esPause$PauseRequest, EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        esPause$PauseRequest.getClass();
        esPause$PauseRequest.options_ = esCommandOptions$CommandOptions;
    }

    public static void f(EsPause$PauseRequest esPause$PauseRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esPause$PauseRequest.getClass();
        esPause$PauseRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static void g(EsPause$PauseRequest esPause$PauseRequest, EsPauseresumeOrigin$PauseResumeOrigin esPauseresumeOrigin$PauseResumeOrigin) {
        esPause$PauseRequest.getClass();
        esPause$PauseRequest.pauseOrigin_ = esPauseresumeOrigin$PauseResumeOrigin;
    }

    public static dg0 h() {
        return (dg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t", new Object[]{"options_", "loggingParams_", "pauseOrigin_"});
            case 3:
                return new EsPause$PauseRequest();
            case 4:
                return new dg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPause$PauseRequest.class) {
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

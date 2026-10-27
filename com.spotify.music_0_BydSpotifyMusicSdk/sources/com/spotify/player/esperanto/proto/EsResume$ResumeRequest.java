package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.tg0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsResume$ResumeRequest extends i implements yj1 {
    private static final EsResume$ResumeRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 2;
    public static final int OPTIONS_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int RESUME_ORIGIN_FIELD_NUMBER = 3;
    private EsLoggingParams$LoggingParams loggingParams_;
    private EsCommandOptions$CommandOptions options_;
    private EsPauseresumeOrigin$PauseResumeOrigin resumeOrigin_;

    static {
        EsResume$ResumeRequest esResume$ResumeRequest = new EsResume$ResumeRequest();
        DEFAULT_INSTANCE = esResume$ResumeRequest;
        i.registerDefaultInstance(EsResume$ResumeRequest.class, esResume$ResumeRequest);
    }

    private EsResume$ResumeRequest() {
    }

    public static void e(EsResume$ResumeRequest esResume$ResumeRequest, EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        esResume$ResumeRequest.getClass();
        esResume$ResumeRequest.options_ = esCommandOptions$CommandOptions;
    }

    public static void f(EsResume$ResumeRequest esResume$ResumeRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esResume$ResumeRequest.getClass();
        esResume$ResumeRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static void g(EsResume$ResumeRequest esResume$ResumeRequest, EsPauseresumeOrigin$PauseResumeOrigin esPauseresumeOrigin$PauseResumeOrigin) {
        esResume$ResumeRequest.getClass();
        esResume$ResumeRequest.resumeOrigin_ = esPauseresumeOrigin$PauseResumeOrigin;
    }

    public static tg0 h() {
        return (tg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t", new Object[]{"options_", "loggingParams_", "resumeOrigin_"});
            case 3:
                return new EsResume$ResumeRequest();
            case 4:
                return new tg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsResume$ResumeRequest.class) {
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

package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.fg0;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsPlay$PlayRequest extends i implements yj1 {
    private static final EsPlay$PlayRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 4;
    public static final int OPTIONS_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER = null;
    public static final int PLAY_OPTIONS_FIELD_NUMBER = 2;
    public static final int PREPARE_PLAY_REQUEST_FIELD_NUMBER = 1;
    private EsLoggingParams$LoggingParams loggingParams_;
    private EsCommandOptions$CommandOptions options_;
    private EsPlayOptions$PlayOptions playOptions_;
    private EsPreparePlay$PreparePlayRequest preparePlayRequest_;

    static {
        EsPlay$PlayRequest esPlay$PlayRequest = new EsPlay$PlayRequest();
        DEFAULT_INSTANCE = esPlay$PlayRequest;
        i.registerDefaultInstance(EsPlay$PlayRequest.class, esPlay$PlayRequest);
    }

    private EsPlay$PlayRequest() {
    }

    public static void e(EsPlay$PlayRequest esPlay$PlayRequest, EsPreparePlay$PreparePlayRequest esPreparePlay$PreparePlayRequest) {
        esPlay$PlayRequest.getClass();
        esPreparePlay$PreparePlayRequest.getClass();
        esPlay$PlayRequest.preparePlayRequest_ = esPreparePlay$PreparePlayRequest;
    }

    public static void f(EsPlay$PlayRequest esPlay$PlayRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esPlay$PlayRequest.getClass();
        esPlay$PlayRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static void g(EsPlay$PlayRequest esPlay$PlayRequest, EsPlayOptions$PlayOptions esPlayOptions$PlayOptions) {
        esPlay$PlayRequest.getClass();
        esPlay$PlayRequest.playOptions_ = esPlayOptions$PlayOptions;
    }

    public static fg0 h() {
        return (fg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t\u0004\t", new Object[]{"preparePlayRequest_", "playOptions_", "options_", "loggingParams_"});
            case 3:
                return new EsPlay$PlayRequest();
            case 4:
                return new fg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsPlay$PlayRequest.class) {
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

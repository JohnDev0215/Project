package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.lf0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsAddToQueueRequest$AddToQueueRequest extends i implements yj1 {
    private static final EsAddToQueueRequest$AddToQueueRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int TRACK_FIELD_NUMBER = 1;
    private EsLoggingParams$LoggingParams loggingParams_;
    private EsCommandOptions$CommandOptions options_;
    private EsContextTrack$ContextTrack track_;

    static {
        EsAddToQueueRequest$AddToQueueRequest esAddToQueueRequest$AddToQueueRequest = new EsAddToQueueRequest$AddToQueueRequest();
        DEFAULT_INSTANCE = esAddToQueueRequest$AddToQueueRequest;
        i.registerDefaultInstance(EsAddToQueueRequest$AddToQueueRequest.class, esAddToQueueRequest$AddToQueueRequest);
    }

    private EsAddToQueueRequest$AddToQueueRequest() {
    }

    public static void e(EsAddToQueueRequest$AddToQueueRequest esAddToQueueRequest$AddToQueueRequest, EsContextTrack$ContextTrack esContextTrack$ContextTrack) {
        esAddToQueueRequest$AddToQueueRequest.getClass();
        esAddToQueueRequest$AddToQueueRequest.track_ = esContextTrack$ContextTrack;
    }

    public static void f(EsAddToQueueRequest$AddToQueueRequest esAddToQueueRequest$AddToQueueRequest, EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        esAddToQueueRequest$AddToQueueRequest.getClass();
        esAddToQueueRequest$AddToQueueRequest.options_ = esCommandOptions$CommandOptions;
    }

    public static void g(EsAddToQueueRequest$AddToQueueRequest esAddToQueueRequest$AddToQueueRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esAddToQueueRequest$AddToQueueRequest.getClass();
        esAddToQueueRequest$AddToQueueRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static lf0 h() {
        return (lf0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\t\u0003\t", new Object[]{"track_", "options_", "loggingParams_"});
            case 3:
                return new EsAddToQueueRequest$AddToQueueRequest();
            case 4:
                return new lf0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsAddToQueueRequest$AddToQueueRequest.class) {
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

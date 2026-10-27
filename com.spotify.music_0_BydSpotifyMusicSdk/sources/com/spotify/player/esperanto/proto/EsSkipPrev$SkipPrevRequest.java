package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ch0;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsSkipPrev$SkipPrevRequest extends i implements yj1 {
    public static final int ALLOW_SEEKING_FIELD_NUMBER = 2;
    private static final EsSkipPrev$SkipPrevRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int TRACK_FIELD_NUMBER = 4;
    private boolean allowSeeking_;
    private EsLoggingParams$LoggingParams loggingParams_;
    private EsCommandOptions$CommandOptions options_;
    private EsContextTrack$ContextTrack track_;

    static {
        EsSkipPrev$SkipPrevRequest esSkipPrev$SkipPrevRequest = new EsSkipPrev$SkipPrevRequest();
        DEFAULT_INSTANCE = esSkipPrev$SkipPrevRequest;
        i.registerDefaultInstance(EsSkipPrev$SkipPrevRequest.class, esSkipPrev$SkipPrevRequest);
    }

    private EsSkipPrev$SkipPrevRequest() {
    }

    public static void e(EsSkipPrev$SkipPrevRequest esSkipPrev$SkipPrevRequest, EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        esSkipPrev$SkipPrevRequest.getClass();
        esSkipPrev$SkipPrevRequest.options_ = esCommandOptions$CommandOptions;
    }

    public static void f(EsSkipPrev$SkipPrevRequest esSkipPrev$SkipPrevRequest, boolean z) {
        esSkipPrev$SkipPrevRequest.allowSeeking_ = z;
    }

    public static void g(EsSkipPrev$SkipPrevRequest esSkipPrev$SkipPrevRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esSkipPrev$SkipPrevRequest.getClass();
        esSkipPrev$SkipPrevRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static void h(EsSkipPrev$SkipPrevRequest esSkipPrev$SkipPrevRequest, EsContextTrack$ContextTrack esContextTrack$ContextTrack) {
        esSkipPrev$SkipPrevRequest.getClass();
        esSkipPrev$SkipPrevRequest.track_ = esContextTrack$ContextTrack;
    }

    public static ch0 i() {
        return (ch0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0000\u0001\u0004\u0004\u0000\u0000\u0000\u0001\t\u0002\u0007\u0003\t\u0004\t", new Object[]{"options_", "allowSeeking_", "loggingParams_", "track_"});
            case 3:
                return new EsSkipPrev$SkipPrevRequest();
            case 4:
                return new ch0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsSkipPrev$SkipPrevRequest.class) {
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

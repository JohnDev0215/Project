package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.eh0;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsStop$StopRequest extends i implements yj1 {
    private static final EsStop$StopRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int REASON_FIELD_NUMBER = 2;
    private EsLoggingParams$LoggingParams loggingParams_;
    private EsCommandOptions$CommandOptions options_;
    private int reason_;

    static {
        EsStop$StopRequest esStop$StopRequest = new EsStop$StopRequest();
        DEFAULT_INSTANCE = esStop$StopRequest;
        i.registerDefaultInstance(EsStop$StopRequest.class, esStop$StopRequest);
    }

    private EsStop$StopRequest() {
    }

    public static void e(EsStop$StopRequest esStop$StopRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esStop$StopRequest.getClass();
        esStop$StopRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static eh0 f() {
        return (eh0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\t\u0002\f\u0003\t", new Object[]{"options_", "reason_", "loggingParams_"});
            case 3:
                return new EsStop$StopRequest();
            case 4:
                return new eh0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsStop$StopRequest.class) {
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

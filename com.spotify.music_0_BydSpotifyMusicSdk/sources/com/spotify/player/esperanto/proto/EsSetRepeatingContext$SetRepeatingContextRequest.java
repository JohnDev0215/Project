package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.xg0;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsSetRepeatingContext$SetRepeatingContextRequest extends i implements yj1 {
    private static final EsSetRepeatingContext$SetRepeatingContextRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 3;
    public static final int OPTIONS_FIELD_NUMBER = 2;
    private static volatile ds1 PARSER = null;
    public static final int REPEATING_CONTEXT_FIELD_NUMBER = 1;
    private EsLoggingParams$LoggingParams loggingParams_;
    private EsCommandOptions$CommandOptions options_;
    private boolean repeatingContext_;

    static {
        EsSetRepeatingContext$SetRepeatingContextRequest esSetRepeatingContext$SetRepeatingContextRequest = new EsSetRepeatingContext$SetRepeatingContextRequest();
        DEFAULT_INSTANCE = esSetRepeatingContext$SetRepeatingContextRequest;
        i.registerDefaultInstance(EsSetRepeatingContext$SetRepeatingContextRequest.class, esSetRepeatingContext$SetRepeatingContextRequest);
    }

    private EsSetRepeatingContext$SetRepeatingContextRequest() {
    }

    public static void e(EsSetRepeatingContext$SetRepeatingContextRequest esSetRepeatingContext$SetRepeatingContextRequest, boolean z) {
        esSetRepeatingContext$SetRepeatingContextRequest.repeatingContext_ = z;
    }

    public static void f(EsSetRepeatingContext$SetRepeatingContextRequest esSetRepeatingContext$SetRepeatingContextRequest, EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        esSetRepeatingContext$SetRepeatingContextRequest.getClass();
        esSetRepeatingContext$SetRepeatingContextRequest.options_ = esCommandOptions$CommandOptions;
    }

    public static void g(EsSetRepeatingContext$SetRepeatingContextRequest esSetRepeatingContext$SetRepeatingContextRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esSetRepeatingContext$SetRepeatingContextRequest.getClass();
        esSetRepeatingContext$SetRepeatingContextRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static xg0 h() {
        return (xg0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0003\u0000\u0000\u0001\u0003\u0003\u0000\u0000\u0000\u0001\u0007\u0002\t\u0003\t", new Object[]{"repeatingContext_", "options_", "loggingParams_"});
            case 3:
                return new EsSetRepeatingContext$SetRepeatingContextRequest();
            case 4:
                return new xg0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsSetRepeatingContext$SetRepeatingContextRequest.class) {
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

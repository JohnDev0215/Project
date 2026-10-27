package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.ug0;
import p.vg0;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsSetOptions$SetOptionsRequest extends i implements yj1 {
    private static final EsSetOptions$SetOptionsRequest DEFAULT_INSTANCE;
    public static final int LOGGING_PARAMS_FIELD_NUMBER = 5;
    public static final int MODES_FIELD_NUMBER = 7;
    public static final int OPTIONS_FIELD_NUMBER = 4;
    private static volatile ds1 PARSER = null;
    public static final int PLAYBACK_SPEED_FIELD_NUMBER = 6;
    public static final int REPEATING_CONTEXT_FIELD_NUMBER = 2;
    public static final int REPEATING_TRACK_FIELD_NUMBER = 1;
    public static final int SHUFFLING_CONTEXT_FIELD_NUMBER = 3;
    private int bitField0_;
    private EsLoggingParams$LoggingParams loggingParams_;
    private MapFieldLite<String, String> modes_ = MapFieldLite.c;
    private EsCommandOptions$CommandOptions options_;
    private float playbackSpeed_;
    private EsOptional$OptionalBoolean repeatingContext_;
    private EsOptional$OptionalBoolean repeatingTrack_;
    private EsOptional$OptionalBoolean shufflingContext_;

    static {
        EsSetOptions$SetOptionsRequest esSetOptions$SetOptionsRequest = new EsSetOptions$SetOptionsRequest();
        DEFAULT_INSTANCE = esSetOptions$SetOptionsRequest;
        i.registerDefaultInstance(EsSetOptions$SetOptionsRequest.class, esSetOptions$SetOptionsRequest);
    }

    private EsSetOptions$SetOptionsRequest() {
    }

    public static void e(EsSetOptions$SetOptionsRequest esSetOptions$SetOptionsRequest, EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        esSetOptions$SetOptionsRequest.getClass();
        esOptional$OptionalBoolean.getClass();
        esSetOptions$SetOptionsRequest.repeatingTrack_ = esOptional$OptionalBoolean;
    }

    public static void f(EsSetOptions$SetOptionsRequest esSetOptions$SetOptionsRequest, EsCommandOptions$CommandOptions esCommandOptions$CommandOptions) {
        esSetOptions$SetOptionsRequest.getClass();
        esSetOptions$SetOptionsRequest.options_ = esCommandOptions$CommandOptions;
    }

    public static void g(EsSetOptions$SetOptionsRequest esSetOptions$SetOptionsRequest, EsLoggingParams$LoggingParams esLoggingParams$LoggingParams) {
        esSetOptions$SetOptionsRequest.getClass();
        esSetOptions$SetOptionsRequest.loggingParams_ = esLoggingParams$LoggingParams;
    }

    public static void h(EsSetOptions$SetOptionsRequest esSetOptions$SetOptionsRequest, float f) {
        esSetOptions$SetOptionsRequest.bitField0_ |= 1;
        esSetOptions$SetOptionsRequest.playbackSpeed_ = f;
    }

    public static void i(EsSetOptions$SetOptionsRequest esSetOptions$SetOptionsRequest, EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        esSetOptions$SetOptionsRequest.getClass();
        esOptional$OptionalBoolean.getClass();
        esSetOptions$SetOptionsRequest.repeatingContext_ = esOptional$OptionalBoolean;
    }

    public static void j(EsSetOptions$SetOptionsRequest esSetOptions$SetOptionsRequest, EsOptional$OptionalBoolean esOptional$OptionalBoolean) {
        esSetOptions$SetOptionsRequest.getClass();
        esOptional$OptionalBoolean.getClass();
        esSetOptions$SetOptionsRequest.shufflingContext_ = esOptional$OptionalBoolean;
    }

    public static ug0 k() {
        return (ug0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0007\u0000\u0001\u0001\u0007\u0007\u0001\u0000\u0000\u0001\t\u0002\t\u0003\t\u0004\t\u0005\t\u0006ခ\u0000\u00072", new Object[]{"bitField0_", "repeatingTrack_", "repeatingContext_", "shufflingContext_", "options_", "loggingParams_", "playbackSpeed_", "modes_", vg0.a});
            case 3:
                return new EsSetOptions$SetOptionsRequest();
            case 4:
                return new ug0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsSetOptions$SetOptionsRequest.class) {
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

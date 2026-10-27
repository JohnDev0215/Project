package com.spotify.player.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.a2;
import p.ag0;
import p.ds1;
import p.i01;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EsLoggingParams$LoggingParams extends i implements yj1 {
    public static final int COMMAND_ID_FIELD_NUMBER = 6;
    public static final int COMMAND_INITIATED_TIME_FIELD_NUMBER = 1;
    public static final int COMMAND_RECEIVED_TIME_FIELD_NUMBER = 2;
    private static final EsLoggingParams$LoggingParams DEFAULT_INSTANCE;
    public static final int DEVICE_IDENTIFIER_FIELD_NUMBER = 5;
    public static final int INTERACTION_IDS_FIELD_NUMBER = 4;
    public static final int PAGE_INSTANCE_IDS_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private EsOptional$OptionalInt64 commandInitiatedTime_;
    private EsOptional$OptionalInt64 commandReceivedTime_;
    private v51 pageInstanceIds_ = i.emptyProtobufList();
    private v51 interactionIds_ = i.emptyProtobufList();
    private String deviceIdentifier_ = "";
    private String commandId_ = "";

    static {
        EsLoggingParams$LoggingParams esLoggingParams$LoggingParams = new EsLoggingParams$LoggingParams();
        DEFAULT_INSTANCE = esLoggingParams$LoggingParams;
        i.registerDefaultInstance(EsLoggingParams$LoggingParams.class, esLoggingParams$LoggingParams);
    }

    private EsLoggingParams$LoggingParams() {
    }

    public static void e(EsLoggingParams$LoggingParams esLoggingParams$LoggingParams, EsOptional$OptionalInt64 esOptional$OptionalInt64) {
        esLoggingParams$LoggingParams.getClass();
        esOptional$OptionalInt64.getClass();
        esLoggingParams$LoggingParams.commandInitiatedTime_ = esOptional$OptionalInt64;
    }

    public static void f(EsLoggingParams$LoggingParams esLoggingParams$LoggingParams, String str) {
        esLoggingParams$LoggingParams.getClass();
        str.getClass();
        v51 v51Var = esLoggingParams$LoggingParams.interactionIds_;
        if (!((a2) v51Var).c) {
            esLoggingParams$LoggingParams.interactionIds_ = i.mutableCopy(v51Var);
        }
        esLoggingParams$LoggingParams.interactionIds_.add(str);
    }

    public static void g(EsLoggingParams$LoggingParams esLoggingParams$LoggingParams, String str) {
        esLoggingParams$LoggingParams.getClass();
        str.getClass();
        v51 v51Var = esLoggingParams$LoggingParams.pageInstanceIds_;
        if (!((a2) v51Var).c) {
            esLoggingParams$LoggingParams.pageInstanceIds_ = i.mutableCopy(v51Var);
        }
        esLoggingParams$LoggingParams.pageInstanceIds_.add(str);
    }

    public static ag0 h() {
        return (ag0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0002\u0000\u0001\t\u0002\t\u0003Ț\u0004Ț\u0005Ȉ\u0006ለ\u0000", new Object[]{"bitField0_", "commandInitiatedTime_", "commandReceivedTime_", "pageInstanceIds_", "interactionIds_", "deviceIdentifier_", "commandId_"});
            case 3:
                return new EsLoggingParams$LoggingParams();
            case 4:
                return new ag0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EsLoggingParams$LoggingParams.class) {
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

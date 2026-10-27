package com.spotify.connect.esperanto.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.qd0;
import p.v51;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class CommonMessages$LoggingParams extends i implements yj1 {
    public static final int COMMAND_INITIATED_TIME_FIELD_NUMBER = 1;
    public static final int COMMAND_RECEIVED_TIME_FIELD_NUMBER = 2;
    private static final CommonMessages$LoggingParams DEFAULT_INSTANCE;
    public static final int INTERACTION_IDS_FIELD_NUMBER = 4;
    public static final int PAGE_INSTANCE_IDS_FIELD_NUMBER = 3;
    private static volatile ds1 PARSER;
    private int bitField0_;
    private long commandInitiatedTime_;
    private long commandReceivedTime_;
    private v51 pageInstanceIds_ = i.emptyProtobufList();
    private v51 interactionIds_ = i.emptyProtobufList();

    static {
        CommonMessages$LoggingParams commonMessages$LoggingParams = new CommonMessages$LoggingParams();
        DEFAULT_INSTANCE = commonMessages$LoggingParams;
        i.registerDefaultInstance(CommonMessages$LoggingParams.class, commonMessages$LoggingParams);
    }

    private CommonMessages$LoggingParams() {
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0000\u0004\u0000\u0001\u0001\u0004\u0004\u0000\u0002\u0000\u0001ဂ\u0000\u0002ဂ\u0001\u0003Ț\u0004Ț", new Object[]{"bitField0_", "commandInitiatedTime_", "commandReceivedTime_", "pageInstanceIds_", "interactionIds_"});
            case 3:
                return new CommonMessages$LoggingParams();
            case 4:
                return new qd0(2, 0);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (CommonMessages$LoggingParams.class) {
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

package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.vj1;
import p.wj1;
import p.yi0;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class EventSenderCanaryNonAuth extends i implements yj1 {
    public static final int BATCH_SIZE_FIELD_NUMBER = 3;
    public static final int BATCH_START_TIME_MS_FIELD_NUMBER = 6;
    public static final int BATCH_UUID_FIELD_NUMBER = 1;
    public static final int CONFIGURATION_FIELD_NUMBER = 8;
    private static final EventSenderCanaryNonAuth DEFAULT_INSTANCE;
    public static final int EVENT_ID_FIELD_NUMBER = 2;
    public static final int EVENT_TIME_MS_FIELD_NUMBER = 7;
    public static final int EXPECTED_MUTATION_COUNT_FIELD_NUMBER = 11;
    public static final int EXPERIMENT_VARIANT_FIELD_NUMBER = 9;
    public static final int MUTATION_COUNT_FIELD_NUMBER = 10;
    private static volatile ds1 PARSER = null;
    public static final int SENDING_SYSTEM_FIELD_NUMBER = 4;
    public static final int SENT_AS_PENDING_EVENT_FIELD_NUMBER = 5;
    private long batchSize_;
    private long batchStartTimeMs_;
    private int bitField0_;
    private long eventId_;
    private long eventTimeMs_;
    private long expectedMutationCount_;
    private long mutationCount_;
    private boolean sentAsPendingEvent_;
    private String batchUuid_ = "";
    private String sendingSystem_ = "";
    private String configuration_ = "";
    private String experimentVariant_ = "";

    static {
        EventSenderCanaryNonAuth eventSenderCanaryNonAuth = new EventSenderCanaryNonAuth();
        DEFAULT_INSTANCE = eventSenderCanaryNonAuth;
        i.registerDefaultInstance(EventSenderCanaryNonAuth.class, eventSenderCanaryNonAuth);
    }

    private EventSenderCanaryNonAuth() {
    }

    public static void e(EventSenderCanaryNonAuth eventSenderCanaryNonAuth) {
        eventSenderCanaryNonAuth.getClass();
        eventSenderCanaryNonAuth.bitField0_ |= 256;
        eventSenderCanaryNonAuth.experimentVariant_ = "heartbeat";
    }

    public static yi0 f() {
        return (yi0) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u000b\u0000\u0001\u0001\u000b\u000b\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဂ\u0001\u0003ဂ\u0002\u0004ဈ\u0003\u0005ဇ\u0004\u0006ဂ\u0005\u0007ဂ\u0006\bဈ\u0007\tဈ\b\nဂ\t\u000bဂ\n", new Object[]{"bitField0_", "batchUuid_", "eventId_", "batchSize_", "sendingSystem_", "sentAsPendingEvent_", "batchStartTimeMs_", "eventTimeMs_", "configuration_", "experimentVariant_", "mutationCount_", "expectedMutationCount_"});
            case 3:
                return new EventSenderCanaryNonAuth();
            case 4:
                return new yi0(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (EventSenderCanaryNonAuth.class) {
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

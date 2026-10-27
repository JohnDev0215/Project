package com.spotify.automotive.eventsender.events.proto;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.sj;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class AutomotivePartnerVoiceCommand extends i implements yj1 {
    private static final AutomotivePartnerVoiceCommand DEFAULT_INSTANCE;
    public static final int INTENT_FIELD_NUMBER = 2;
    public static final int LANGUAGE_FIELD_NUMBER = 5;
    private static volatile ds1 PARSER = null;
    public static final int PROVIDER_FIELD_NUMBER = 6;
    public static final int QUERY_FIELD_NUMBER = 3;
    public static final int UBI_INTERACTION_ID_FIELD_NUMBER = 1;
    public static final int UTTERANCE_FIELD_NUMBER = 4;
    private int bitField0_;
    private String ubiInteractionId_ = "";
    private String intent_ = "";
    private String query_ = "";
    private String utterance_ = "";
    private String language_ = "";
    private String provider_ = "";

    static {
        AutomotivePartnerVoiceCommand automotivePartnerVoiceCommand = new AutomotivePartnerVoiceCommand();
        DEFAULT_INSTANCE = automotivePartnerVoiceCommand;
        i.registerDefaultInstance(AutomotivePartnerVoiceCommand.class, automotivePartnerVoiceCommand);
    }

    private AutomotivePartnerVoiceCommand() {
    }

    public static void e(AutomotivePartnerVoiceCommand automotivePartnerVoiceCommand, String str) {
        automotivePartnerVoiceCommand.getClass();
        str.getClass();
        automotivePartnerVoiceCommand.bitField0_ |= 1;
        automotivePartnerVoiceCommand.ubiInteractionId_ = str;
    }

    public static void f(AutomotivePartnerVoiceCommand automotivePartnerVoiceCommand, String str) {
        automotivePartnerVoiceCommand.getClass();
        str.getClass();
        automotivePartnerVoiceCommand.bitField0_ |= 8;
        automotivePartnerVoiceCommand.utterance_ = str;
    }

    public static void g(AutomotivePartnerVoiceCommand automotivePartnerVoiceCommand, String str) {
        automotivePartnerVoiceCommand.getClass();
        str.getClass();
        automotivePartnerVoiceCommand.bitField0_ |= 16;
        automotivePartnerVoiceCommand.language_ = str;
    }

    public static void h(AutomotivePartnerVoiceCommand automotivePartnerVoiceCommand, String str) {
        automotivePartnerVoiceCommand.getClass();
        str.getClass();
        automotivePartnerVoiceCommand.bitField0_ |= 32;
        automotivePartnerVoiceCommand.provider_ = str;
    }

    public static void i(AutomotivePartnerVoiceCommand automotivePartnerVoiceCommand, String str) {
        automotivePartnerVoiceCommand.getClass();
        str.getClass();
        automotivePartnerVoiceCommand.bitField0_ |= 2;
        automotivePartnerVoiceCommand.intent_ = str;
    }

    public static void j(AutomotivePartnerVoiceCommand automotivePartnerVoiceCommand, String str) {
        automotivePartnerVoiceCommand.getClass();
        str.getClass();
        automotivePartnerVoiceCommand.bitField0_ |= 4;
        automotivePartnerVoiceCommand.query_ = str;
    }

    public static sj k() {
        return (sj) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0006\u0000\u0001\u0001\u0006\u0006\u0000\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005", new Object[]{"bitField0_", "ubiInteractionId_", "intent_", "query_", "utterance_", "language_", "provider_"});
            case 3:
                return new AutomotivePartnerVoiceCommand();
            case 4:
                return new sj(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (AutomotivePartnerVoiceCommand.class) {
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

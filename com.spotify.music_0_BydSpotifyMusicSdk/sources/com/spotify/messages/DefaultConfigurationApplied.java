package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.n60;
import p.o60;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultConfigurationApplied extends i implements yj1 {
    public static final int CONFIGURATION_ASSIGNMENT_ID_FIELD_NUMBER = 2;
    private static final DefaultConfigurationApplied DEFAULT_INSTANCE;
    public static final int FETCH_TYPE_FIELD_NUMBER = 6;
    public static final int IDENTIFIERS_FIELD_NUMBER = 8;
    public static final int INSTALLATION_ID_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int PLATFORM_FIELD_NUMBER = 5;
    public static final int RC_CLIENT_ID_FIELD_NUMBER = 3;
    public static final int RC_CLIENT_VERSION_FIELD_NUMBER = 4;
    public static final int REASON_FIELD_NUMBER = 7;
    private int bitField0_;
    private MapFieldLite<String, String> identifiers_ = MapFieldLite.c;
    private String installationId_ = "";
    private String configurationAssignmentId_ = "";
    private String rcClientId_ = "";
    private String rcClientVersion_ = "";
    private String platform_ = "";
    private String fetchType_ = "";
    private String reason_ = "";

    static {
        DefaultConfigurationApplied defaultConfigurationApplied = new DefaultConfigurationApplied();
        DEFAULT_INSTANCE = defaultConfigurationApplied;
        i.registerDefaultInstance(DefaultConfigurationApplied.class, defaultConfigurationApplied);
    }

    private DefaultConfigurationApplied() {
    }

    public static void e(DefaultConfigurationApplied defaultConfigurationApplied) {
        defaultConfigurationApplied.getClass();
        defaultConfigurationApplied.bitField0_ |= 8;
        defaultConfigurationApplied.rcClientVersion_ = "4.4.1.0";
    }

    public static void f(DefaultConfigurationApplied defaultConfigurationApplied) {
        defaultConfigurationApplied.getClass();
        defaultConfigurationApplied.bitField0_ |= 16;
        defaultConfigurationApplied.platform_ = "ANDROID";
    }

    public static MapFieldLite g(DefaultConfigurationApplied defaultConfigurationApplied) {
        if (!defaultConfigurationApplied.identifiers_.e()) {
            defaultConfigurationApplied.identifiers_ = defaultConfigurationApplied.identifiers_.g();
        }
        return defaultConfigurationApplied.identifiers_;
    }

    public static void h(DefaultConfigurationApplied defaultConfigurationApplied, String str) {
        defaultConfigurationApplied.getClass();
        str.getClass();
        defaultConfigurationApplied.bitField0_ |= 2;
        defaultConfigurationApplied.configurationAssignmentId_ = str;
    }

    public static void i(DefaultConfigurationApplied defaultConfigurationApplied) {
        defaultConfigurationApplied.getClass();
        defaultConfigurationApplied.bitField0_ |= 4;
        defaultConfigurationApplied.rcClientId_ = "com.spotify.auto";
    }

    public static n60 j() {
        return (n60) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\b\u0000\u0001\u0001\b\b\u0001\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\b2", new Object[]{"bitField0_", "installationId_", "configurationAssignmentId_", "rcClientId_", "rcClientVersion_", "platform_", "fetchType_", "reason_", "identifiers_", o60.a});
            case 3:
                return new DefaultConfigurationApplied();
            case 4:
                return new n60(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (DefaultConfigurationApplied.class) {
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

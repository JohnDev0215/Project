package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.ds1;
import p.i01;
import p.p60;
import p.q60;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class DefaultConfigurationAppliedNonAuth extends i implements yj1 {
    public static final int CONFIGURATION_ASSIGNMENT_ID_FIELD_NUMBER = 2;
    private static final DefaultConfigurationAppliedNonAuth DEFAULT_INSTANCE;
    public static final int IDENTIFIERS_FIELD_NUMBER = 7;
    public static final int INSTALLATION_ID_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int PLATFORM_FIELD_NUMBER = 5;
    public static final int RC_CLIENT_ID_FIELD_NUMBER = 3;
    public static final int RC_CLIENT_VERSION_FIELD_NUMBER = 4;
    public static final int REASON_FIELD_NUMBER = 6;
    private int bitField0_;
    private MapFieldLite<String, String> identifiers_ = MapFieldLite.c;
    private String installationId_ = "";
    private String configurationAssignmentId_ = "";
    private String rcClientId_ = "";
    private String rcClientVersion_ = "";
    private String platform_ = "";
    private String reason_ = "";

    static {
        DefaultConfigurationAppliedNonAuth defaultConfigurationAppliedNonAuth = new DefaultConfigurationAppliedNonAuth();
        DEFAULT_INSTANCE = defaultConfigurationAppliedNonAuth;
        i.registerDefaultInstance(DefaultConfigurationAppliedNonAuth.class, defaultConfigurationAppliedNonAuth);
    }

    private DefaultConfigurationAppliedNonAuth() {
    }

    public static void e(DefaultConfigurationAppliedNonAuth defaultConfigurationAppliedNonAuth) {
        defaultConfigurationAppliedNonAuth.getClass();
        defaultConfigurationAppliedNonAuth.bitField0_ |= 8;
        defaultConfigurationAppliedNonAuth.rcClientVersion_ = "4.4.1.0";
    }

    public static void f(DefaultConfigurationAppliedNonAuth defaultConfigurationAppliedNonAuth) {
        defaultConfigurationAppliedNonAuth.getClass();
        defaultConfigurationAppliedNonAuth.bitField0_ |= 16;
        defaultConfigurationAppliedNonAuth.platform_ = "ANDROID";
    }

    public static MapFieldLite g(DefaultConfigurationAppliedNonAuth defaultConfigurationAppliedNonAuth) {
        if (!defaultConfigurationAppliedNonAuth.identifiers_.e()) {
            defaultConfigurationAppliedNonAuth.identifiers_ = defaultConfigurationAppliedNonAuth.identifiers_.g();
        }
        return defaultConfigurationAppliedNonAuth.identifiers_;
    }

    public static void h(DefaultConfigurationAppliedNonAuth defaultConfigurationAppliedNonAuth, String str) {
        defaultConfigurationAppliedNonAuth.getClass();
        str.getClass();
        defaultConfigurationAppliedNonAuth.bitField0_ |= 2;
        defaultConfigurationAppliedNonAuth.configurationAssignmentId_ = str;
    }

    public static void i(DefaultConfigurationAppliedNonAuth defaultConfigurationAppliedNonAuth) {
        defaultConfigurationAppliedNonAuth.getClass();
        defaultConfigurationAppliedNonAuth.bitField0_ |= 4;
        defaultConfigurationAppliedNonAuth.rcClientId_ = "com.spotify.auto";
    }

    public static p60 j() {
        return (p60) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0007\u0000\u0001\u0001\u0007\u0007\u0001\u0000\u0000\u0001ဈ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u00072", new Object[]{"bitField0_", "installationId_", "configurationAssignmentId_", "rcClientId_", "rcClientVersion_", "platform_", "reason_", "identifiers_", q60.a});
            case 3:
                return new DefaultConfigurationAppliedNonAuth();
            case 4:
                return new p60(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (DefaultConfigurationAppliedNonAuth.class) {
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

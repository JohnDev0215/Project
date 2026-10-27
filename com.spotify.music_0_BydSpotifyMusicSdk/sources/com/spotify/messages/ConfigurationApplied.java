package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.a;
import com.google.protobuf.i;
import java.util.List;
import java.util.Set;
import p.a2;
import p.ds1;
import p.i01;
import p.r51;
import p.tw;
import p.uw;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigurationApplied extends i implements yj1 {
    public static final int CONFIGURATION_ASSIGNMENT_ID_FIELD_NUMBER = 4;
    private static final ConfigurationApplied DEFAULT_INSTANCE;
    public static final int FETCH_TYPE_FIELD_NUMBER = 8;
    public static final int IDENTIFIERS_FIELD_NUMBER = 9;
    public static final int INSTALLATION_ID_FIELD_NUMBER = 2;
    public static final int LAST_RCS_FETCH_TIME_FIELD_NUMBER = 1;
    private static volatile ds1 PARSER = null;
    public static final int PLATFORM_FIELD_NUMBER = 7;
    public static final int POLICY_GROUP_IDS_FIELD_NUMBER = 3;
    public static final int RC_CLIENT_ID_FIELD_NUMBER = 5;
    public static final int RC_CLIENT_VERSION_FIELD_NUMBER = 6;
    private int bitField0_;
    private long lastRcsFetchTime_;
    private MapFieldLite<String, String> identifiers_ = MapFieldLite.c;
    private String installationId_ = "";
    private r51 policyGroupIds_ = i.emptyIntList();
    private String configurationAssignmentId_ = "";
    private String rcClientId_ = "";
    private String rcClientVersion_ = "";
    private String platform_ = "";
    private String fetchType_ = "";

    static {
        ConfigurationApplied configurationApplied = new ConfigurationApplied();
        DEFAULT_INSTANCE = configurationApplied;
        i.registerDefaultInstance(ConfigurationApplied.class, configurationApplied);
    }

    private ConfigurationApplied() {
    }

    public static void e(ConfigurationApplied configurationApplied, long j) {
        configurationApplied.bitField0_ |= 1;
        configurationApplied.lastRcsFetchTime_ = j;
    }

    public static void f(ConfigurationApplied configurationApplied, String str) {
        configurationApplied.getClass();
        str.getClass();
        configurationApplied.bitField0_ |= 4;
        configurationApplied.configurationAssignmentId_ = str;
    }

    public static void g(ConfigurationApplied configurationApplied) {
        configurationApplied.getClass();
        configurationApplied.bitField0_ |= 8;
        configurationApplied.rcClientId_ = "com.spotify.auto";
    }

    public static void h(ConfigurationApplied configurationApplied) {
        configurationApplied.getClass();
        configurationApplied.bitField0_ |= 16;
        configurationApplied.rcClientVersion_ = "4.4.1.0";
    }

    public static void i(ConfigurationApplied configurationApplied) {
        configurationApplied.getClass();
        configurationApplied.bitField0_ |= 32;
        configurationApplied.platform_ = "ANDROID";
    }

    public static MapFieldLite j(ConfigurationApplied configurationApplied) {
        if (!configurationApplied.identifiers_.e()) {
            configurationApplied.identifiers_ = configurationApplied.identifiers_.g();
        }
        return configurationApplied.identifiers_;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static void k(ConfigurationApplied configurationApplied, Set set) {
        r51 r51Var = configurationApplied.policyGroupIds_;
        if (!((a2) r51Var).c) {
            configurationApplied.policyGroupIds_ = i.mutableCopy(r51Var);
        }
        a.addAll((Iterable) set, (List) configurationApplied.policyGroupIds_);
    }

    public static tw l() {
        return (tw) DEFAULT_INSTANCE.createBuilder();
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
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\t\u0000\u0001\u0001\t\t\u0001\u0001\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003\u0016\u0004ဈ\u0002\u0005ဈ\u0003\u0006ဈ\u0004\u0007ဈ\u0005\bဈ\u0006\t2", new Object[]{"bitField0_", "lastRcsFetchTime_", "installationId_", "policyGroupIds_", "configurationAssignmentId_", "rcClientId_", "rcClientVersion_", "platform_", "fetchType_", "identifiers_", uw.a});
            case 3:
                return new ConfigurationApplied();
            case 4:
                return new tw(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ConfigurationApplied.class) {
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

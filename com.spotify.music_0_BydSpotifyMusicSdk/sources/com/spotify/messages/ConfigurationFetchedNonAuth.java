package com.spotify.messages;

import com.google.protobuf.GeneratedMessageLite$MethodToInvoke;
import com.google.protobuf.MapFieldLite;
import com.google.protobuf.i;
import p.cx;
import p.ds1;
import p.dx;
import p.i01;
import p.vj1;
import p.wj1;
import p.yj1;

/* JADX INFO: loaded from: classes.dex */
public final class ConfigurationFetchedNonAuth extends i implements yj1 {
    public static final int ATTRIBUTES_SET_ID_FIELD_NUMBER = 5;
    public static final int CONFIGURATION_ASSIGNMENT_ID_FIELD_NUMBER = 3;
    private static final ConfigurationFetchedNonAuth DEFAULT_INSTANCE;
    public static final int ERROR_CODE_ACCOUNT_ATTRIBUTES_FIELD_NUMBER = 20;
    public static final int ERROR_CODE_CONFIGURATION_RESOLVE_FIELD_NUMBER = 21;
    public static final int ERROR_MESSAGE_ACCOUNT_ATTRIBUTES_FIELD_NUMBER = 19;
    public static final int ERROR_MESSAGE_CONFIGURATION_RESOLVE_FIELD_NUMBER = 17;
    public static final int ERROR_MESSAGE_FIELD_NUMBER = 15;
    public static final int ERROR_REASON_ACCOUNT_ATTRIBUTES_FIELD_NUMBER = 18;
    public static final int ERROR_REASON_CONFIGURATION_RESOLVE_FIELD_NUMBER = 16;
    public static final int ERROR_REASON_FIELD_NUMBER = 14;
    public static final int FETCH_TYPE_FIELD_NUMBER = 10;
    public static final int IDENTIFIERS_FIELD_NUMBER = 22;
    public static final int INSTALLATION_ID_FIELD_NUMBER = 2;
    public static final int LAST_RCS_FETCH_TIME_FIELD_NUMBER = 1;
    public static final int LATENCY_FIELD_NUMBER = 11;
    private static volatile ds1 PARSER = null;
    public static final int PAYLOAD_SIZE_FIELD_NUMBER = 12;
    public static final int PLATFORM_FIELD_NUMBER = 9;
    public static final int PROPERTY_SET_ID_FIELD_NUMBER = 4;
    public static final int RC_CLIENT_ID_FIELD_NUMBER = 6;
    public static final int RC_CLIENT_VERSION_FIELD_NUMBER = 7;
    public static final int RC_SDK_VERSION_FIELD_NUMBER = 8;
    public static final int STATUS_CODE_FIELD_NUMBER = 13;
    private int bitField0_;
    private int errorCodeAccountAttributes_;
    private int errorCodeConfigurationResolve_;
    private long lastRcsFetchTime_;
    private long latency_;
    private long payloadSize_;
    private int statusCode_;
    private MapFieldLite<String, String> identifiers_ = MapFieldLite.c;
    private String installationId_ = "";
    private String configurationAssignmentId_ = "";
    private String propertySetId_ = "";
    private String attributesSetId_ = "";
    private String rcClientId_ = "";
    private String rcClientVersion_ = "";
    private String rcSdkVersion_ = "";
    private String platform_ = "";
    private String fetchType_ = "";
    private String errorReason_ = "";
    private String errorMessage_ = "";
    private String errorReasonConfigurationResolve_ = "";
    private String errorMessageConfigurationResolve_ = "";
    private String errorReasonAccountAttributes_ = "";
    private String errorMessageAccountAttributes_ = "";

    static {
        ConfigurationFetchedNonAuth configurationFetchedNonAuth = new ConfigurationFetchedNonAuth();
        DEFAULT_INSTANCE = configurationFetchedNonAuth;
        i.registerDefaultInstance(ConfigurationFetchedNonAuth.class, configurationFetchedNonAuth);
    }

    private ConfigurationFetchedNonAuth() {
    }

    public static void e(ConfigurationFetchedNonAuth configurationFetchedNonAuth, long j) {
        configurationFetchedNonAuth.bitField0_ |= 1;
        configurationFetchedNonAuth.lastRcsFetchTime_ = j;
    }

    public static void f(ConfigurationFetchedNonAuth configurationFetchedNonAuth) {
        configurationFetchedNonAuth.getClass();
        configurationFetchedNonAuth.bitField0_ |= 32;
        configurationFetchedNonAuth.rcClientId_ = "com.spotify.auto";
    }

    public static void g(ConfigurationFetchedNonAuth configurationFetchedNonAuth) {
        configurationFetchedNonAuth.getClass();
        configurationFetchedNonAuth.bitField0_ |= 64;
        configurationFetchedNonAuth.rcClientVersion_ = "4.4.1.0";
    }

    public static void h(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        str.getClass();
        configurationFetchedNonAuth.bitField0_ |= 128;
        configurationFetchedNonAuth.rcSdkVersion_ = str;
    }

    public static void i(ConfigurationFetchedNonAuth configurationFetchedNonAuth) {
        configurationFetchedNonAuth.getClass();
        configurationFetchedNonAuth.bitField0_ |= 256;
        configurationFetchedNonAuth.platform_ = "ANDROID";
    }

    public static void j(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        str.getClass();
        configurationFetchedNonAuth.bitField0_ |= 512;
        configurationFetchedNonAuth.fetchType_ = str;
    }

    public static void k(ConfigurationFetchedNonAuth configurationFetchedNonAuth, long j) {
        configurationFetchedNonAuth.bitField0_ |= 1024;
        configurationFetchedNonAuth.latency_ = j;
    }

    public static void l(ConfigurationFetchedNonAuth configurationFetchedNonAuth, long j) {
        configurationFetchedNonAuth.bitField0_ |= 2048;
        configurationFetchedNonAuth.payloadSize_ = j;
    }

    public static void m(ConfigurationFetchedNonAuth configurationFetchedNonAuth, int i) {
        configurationFetchedNonAuth.bitField0_ |= 4096;
        configurationFetchedNonAuth.statusCode_ = i;
    }

    public static void n(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        str.getClass();
        configurationFetchedNonAuth.bitField0_ |= 8192;
        configurationFetchedNonAuth.errorReason_ = str;
    }

    public static void o(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        configurationFetchedNonAuth.bitField0_ |= 16384;
        configurationFetchedNonAuth.errorMessage_ = str;
    }

    public static void p(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        str.getClass();
        configurationFetchedNonAuth.bitField0_ |= 32768;
        configurationFetchedNonAuth.errorReasonConfigurationResolve_ = str;
    }

    public static ds1 parser() {
        return DEFAULT_INSTANCE.getParserForType();
    }

    public static void q(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        configurationFetchedNonAuth.bitField0_ |= 65536;
        configurationFetchedNonAuth.errorMessageConfigurationResolve_ = str;
    }

    public static void r(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        str.getClass();
        configurationFetchedNonAuth.bitField0_ |= 131072;
        configurationFetchedNonAuth.errorReasonAccountAttributes_ = str;
    }

    public static void s(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        configurationFetchedNonAuth.bitField0_ |= 262144;
        configurationFetchedNonAuth.errorMessageAccountAttributes_ = str;
    }

    public static void t(ConfigurationFetchedNonAuth configurationFetchedNonAuth, int i) {
        configurationFetchedNonAuth.bitField0_ |= 524288;
        configurationFetchedNonAuth.errorCodeAccountAttributes_ = i;
    }

    public static void u(ConfigurationFetchedNonAuth configurationFetchedNonAuth, int i) {
        configurationFetchedNonAuth.bitField0_ |= 1048576;
        configurationFetchedNonAuth.errorCodeConfigurationResolve_ = i;
    }

    public static MapFieldLite v(ConfigurationFetchedNonAuth configurationFetchedNonAuth) {
        if (!configurationFetchedNonAuth.identifiers_.e()) {
            configurationFetchedNonAuth.identifiers_ = configurationFetchedNonAuth.identifiers_.g();
        }
        return configurationFetchedNonAuth.identifiers_;
    }

    public static void w(ConfigurationFetchedNonAuth configurationFetchedNonAuth, String str) {
        configurationFetchedNonAuth.getClass();
        configurationFetchedNonAuth.bitField0_ |= 4;
        configurationFetchedNonAuth.configurationAssignmentId_ = str;
    }

    public static void x(ConfigurationFetchedNonAuth configurationFetchedNonAuth) {
        configurationFetchedNonAuth.getClass();
        configurationFetchedNonAuth.bitField0_ |= 8;
        configurationFetchedNonAuth.propertySetId_ = "a296f6a0c8d02612625a2cce0a87cd4903b3a030b8d046935f1922a441cd30df";
    }

    public static cx y() {
        return (cx) DEFAULT_INSTANCE.createBuilder();
    }

    @Override // com.google.protobuf.i
    public final Object dynamicMethod(GeneratedMessageLite$MethodToInvoke generatedMessageLite$MethodToInvoke, Object obj, Object obj2) {
        switch (generatedMessageLite$MethodToInvoke.ordinal()) {
            case 0:
                return (byte) 1;
            case 1:
                return null;
            case 2:
                return i.newMessageInfo(DEFAULT_INSTANCE, "\u0001\u0016\u0000\u0001\u0001\u0016\u0016\u0001\u0000\u0000\u0001ဂ\u0000\u0002ဈ\u0001\u0003ဈ\u0002\u0004ဈ\u0003\u0005ဈ\u0004\u0006ဈ\u0005\u0007ဈ\u0006\bဈ\u0007\tဈ\b\nဈ\t\u000bဂ\n\fဂ\u000b\rင\f\u000eဈ\r\u000fဈ\u000e\u0010ဈ\u000f\u0011ဈ\u0010\u0012ဈ\u0011\u0013ဈ\u0012\u0014င\u0013\u0015င\u0014\u00162", new Object[]{"bitField0_", "lastRcsFetchTime_", "installationId_", "configurationAssignmentId_", "propertySetId_", "attributesSetId_", "rcClientId_", "rcClientVersion_", "rcSdkVersion_", "platform_", "fetchType_", "latency_", "payloadSize_", "statusCode_", "errorReason_", "errorMessage_", "errorReasonConfigurationResolve_", "errorMessageConfigurationResolve_", "errorReasonAccountAttributes_", "errorMessageAccountAttributes_", "errorCodeAccountAttributes_", "errorCodeConfigurationResolve_", "identifiers_", dx.a});
            case 3:
                return new ConfigurationFetchedNonAuth();
            case 4:
                return new cx(DEFAULT_INSTANCE);
            case 5:
                return DEFAULT_INSTANCE;
            case 6:
                ds1 i01Var = PARSER;
                if (i01Var == null) {
                    synchronized (ConfigurationFetchedNonAuth.class) {
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
